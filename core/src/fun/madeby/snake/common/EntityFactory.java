package fun.madeby.snake.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import fun.madeby.snake.component.DimensionComponent;
import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.component.RectangularBoundsComponent;
import fun.madeby.snake.component.SnakeComponent;
import fun.madeby.snake.config.GameConfig;

public class EntityFactory {
    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public Entity createSnake(){
        SnakeComponent snake = engine.createComponent(SnakeComponent.class);
        snake.head = createSnakeHead();

        // create instance of entity
        Entity snakeEntity = engine.createEntity();
        // add component/s
        snakeEntity.add(snake);
        engine.addEntity(snakeEntity);

        return snakeEntity;

    }

    public Entity createSnakeHead() {

        // Create components
        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        RectangularBoundsComponent bounds = engine.createComponent(RectangularBoundsComponent.class);

        // Set components
        dimension.width = GameConfig.SNAKE_SIZE;
        dimension.height = GameConfig.SNAKE_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);

        // Manufacture Entity and add component characteristics
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);

        engine.addEntity(entity);

        return entity;
    }
}
