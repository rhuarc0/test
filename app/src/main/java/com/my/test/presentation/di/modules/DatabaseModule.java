package com.my.test.presentation.di.modules;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.my.test.data.dao.room.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "weatherDb")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS citydb (" +
                                                  "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                  "name TEXT, " +
                                                  "country TEXT)";

                        db.execSQL(SQL_CREATE_TABLE);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("id", "524901");
                        contentValues.put("name", "Moscow");
                        contentValues.put("country", "RU");
                        db.insert("citydb", OnConflictStrategy.IGNORE, contentValues);

                        contentValues.put("id", "498817");
                        contentValues.put("name", "Saint Petersburg");
                        contentValues.put("country", "RU");
                        db.insert("citydb", OnConflictStrategy.IGNORE, contentValues);

                    }
                })
                .build();
    }
}
