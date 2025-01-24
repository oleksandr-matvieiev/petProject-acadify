package org.example.acadify.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MappingUtils {
    public static <T, ID> List<ID> mapEntitiesToIds(List<T> entities, Function<T, ID> idExtractor) {
        if (entities == null || entities.isEmpty()) return List.of();

        return entities.stream().map(idExtractor).collect(Collectors.toList());
    }

    public static <T, ID> List<T> mapIdsToEntities(List<ID> ids, Function<List<ID>, List<T>> finder) {
        return finder.apply(ids);
    }
}
