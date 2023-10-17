package fun.madeby.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.component.WorldWrapComponent;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.util.Mappers;

public class WorldWrapSystem extends IteratingSystem {
    private static Family FAMILY = Family.all(
            WorldWrapComponent.class,
            PositionComponent.class
    ).get();

    public WorldWrapSystem() {
        super(FAMILY);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION_COMPONENT_MAPPER.get(entity);

        if (position.x >= GameConfig.WORLD_WIDTH) {
            position.x = 0;
        } else if (position.x < 0)
            position.x = (GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SIZE);

        if (position.y >=  GameConfig.Y_CONSTRAINED) {
            position.y = 0;
        } else if (position.y < 0)
            position.y = (GameConfig.Y_CONSTRAINED - GameConfig.SNAKE_SIZE);
    }
}
