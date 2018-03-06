package com.my.test.data.mapper;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper<Source, Destination> {

    public abstract Destination map(Source source);

    public List<Destination> map(List<Source> sourceList) {
        return Stream.of(sourceList).map(this::map).toList();
    }
}