package fun.madeby.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import fun.madeby.snake.component.DimensionComponent;
import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.component.RectangularBoundsComponent;
import fun.madeby.util.Mappers;

public class BoundsUpdateSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            RectangularBoundsComponent.class
    ).get();

    public BoundsUpdateSystem() {
        super(FAMILY);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        RectangularBoundsComponent retrievedRectangularBoundsComponent = Mappers.RECTANGULAR_BOUNDS_MAPPER.get(entity);
        PositionComponent retrievedPositionComponent = Mappers.POSITION_COMPONENT_MAPPER.get(entity);
        DimensionComponent retrievedDimensionComponent = Mappers.DIMENSION_COMPONENT_MAPPER.get(entity);

        retrievedRectangularBoundsComponent.rectangle.setSize(retrievedDimensionComponent.width,
                retrievedDimensionComponent.height);
        retrievedRectangularBoundsComponent.rectangle.setPosition(retrievedPositionComponent.x,
                retrievedPositionComponent.y);


    }
}
