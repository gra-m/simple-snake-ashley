package fun.madeby.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Logger;

import fun.madeby.snake.component.DirectionComponent;
import fun.madeby.snake.component.MovementComponent;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.util.Mappers;

public class DirectionSystem extends IteratingSystem {
    private static final Logger LOG = new Logger(DirectionSystem.class.getName(),Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            DirectionComponent.class,
            MovementComponent.class).get();

    public DirectionSystem() {
        super(FAMILY);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent retrievedDirectionComponent = Mappers.DIRECTION_COMPONENT_MAPPER.get(entity);
        MovementComponent retrievedMovementComponent = Mappers.MOVEMENT_COMPONENT_MAPPER.get(entity);


        // set speed to zero == see if problem
        retrievedMovementComponent.xSpeed = 0;
        retrievedMovementComponent.ySpeed = 0;
        float positiveAxisMovement = GameConfig.NORMAL_MOVES_EVERY.moves;
        float negativeAxisMovement = -GameConfig.NORMAL_MOVES_EVERY.moves;

        if (retrievedDirectionComponent.isLeft())
            retrievedMovementComponent.xSpeed = negativeAxisMovement;
        else if (retrievedDirectionComponent.isRight())
            retrievedMovementComponent.xSpeed = positiveAxisMovement;
        else if (retrievedDirectionComponent.isDown())
            retrievedMovementComponent.ySpeed = negativeAxisMovement;
        else if (retrievedDirectionComponent.isUp())
            retrievedMovementComponent.ySpeed = positiveAxisMovement;


    }
}
