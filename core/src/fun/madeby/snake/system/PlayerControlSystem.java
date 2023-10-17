package fun.madeby.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;

import fun.madeby.snake.common.Direction;
import fun.madeby.snake.component.DirectionComponent;
import fun.madeby.snake.component.PlayerComponent;
import fun.madeby.util.Mappers;

public class PlayerControlSystem extends IteratingSystem {
    private static final Logger LOG = new Logger(PlayerControlSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            DirectionComponent.class
    ).get();

    public PlayerControlSystem() {
        super(FAMILY);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent retrievedDirectionComponent = Mappers.DIRECTION_COMPONENT_MAPPER.get(entity);

        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);


        // check and only set input direction if allowed vs retrieved direction
        if (leftPressed) {
            if (retrievedDirectionComponent.direction.allowed(Direction.LEFT))
                retrievedDirectionComponent.direction = Direction.LEFT;
        } else if (rightPressed) {
            if (retrievedDirectionComponent.direction.allowed(Direction.RIGHT))
                retrievedDirectionComponent.direction = Direction.RIGHT;
        } else if (upPressed) {
            if (retrievedDirectionComponent.direction.allowed(Direction.UP))
                retrievedDirectionComponent.direction = Direction.UP;
        } else if (downPressed) {
            if (retrievedDirectionComponent.direction.allowed(Direction.DOWN))
                retrievedDirectionComponent.direction = Direction.DOWN;
        }
    }
}
