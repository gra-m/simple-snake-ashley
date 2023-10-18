package fun.madeby.snake.common;

import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

import fun.madeby.util.Mappers;

public class ZOrderComparator implements Comparator<Entity> {

    public static final ZOrderComparator INSTANCE = new ZOrderComparator();

    private ZOrderComparator() {}

    @Override
    public int compare(Entity entityOne, Entity entityTwo) {
        return Integer.compare(
                Mappers.Z_ORDER_COMPONENT_MAPPER.get(entityOne).z,
                Mappers.Z_ORDER_COMPONENT_MAPPER.get(entityTwo).z
        );
    }
}
