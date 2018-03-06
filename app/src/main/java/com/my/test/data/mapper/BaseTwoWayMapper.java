package com.my.test.data.mapper;


import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTwoWayMapper<Source, Destination>
        extends BaseMapper<Source, Destination> {

    public abstract Source reverseMap(Destination destination);

    public List<Source> reverseMap(List<Destination> destinationList) {
        return Stream.of(destinationList).map(this::reverseMap).toList();
    }

}
