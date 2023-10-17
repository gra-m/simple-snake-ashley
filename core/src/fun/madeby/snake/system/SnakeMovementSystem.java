package fun.madeby.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.utils.Logger;

import fun.madeby.snake.component.MovementComponent;
import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.component.SnakeComponent;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.util.Mappers;

/**
 * Delta, the time in between frames is noted and handled automatically so an action may be
 * triggered when a time boundary has been e.g. equalled or exceeded. Basically do this(processEntity)
 * if this amount of time has passed.
 */
public class SnakeMovementSystem extends IntervalIteratingSystem {
    private static final Logger LOG = new Logger(SnakeMovementSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    public SnakeMovementSystem() {
        super(FAMILY, GameConfig.NORMAL_MOVES_EVERY.every);
    }


    @Override
    protected void processEntity(Entity entity) {
        SnakeComponent retrievedSnakeComponent = Mappers.SNAKE_COMPONENT_MAPPER.get(entity);
        moveHead(retrievedSnakeComponent.head);


    }

    private void moveHead(Entity head) {
        MovementComponent retrievedMovementComponent = Mappers.MOVEMENT_COMPONENT_MAPPER.get(head);
        PositionComponent retrievedPositionComponent = Mappers.POSITION_COMPONENT_MAPPER.get(head);

        retrievedPositionComponent.x += retrievedMovementComponent.xSpeed;
        retrievedPositionComponent.y += retrievedMovementComponent.ySpeed;

    }
}
