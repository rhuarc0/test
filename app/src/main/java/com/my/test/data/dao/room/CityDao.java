package com.my.test.data.dao.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.my.test.data.dao.databaseentities.CityDb;

import java.util.List;

@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItem(CityDb item);

    @Query("SELECT * FROM citydb")
    List<CityDb> fetchItems();

    @Query("DELETE FROM citydb WHERE id = :id")
    void delete(long id);
}
