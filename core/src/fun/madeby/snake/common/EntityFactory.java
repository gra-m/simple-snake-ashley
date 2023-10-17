package fun.madeby.snake.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import fun.madeby.snake.component.BodyPartComponent;
import fun.madeby.snake.component.CoinComponent;
import fun.madeby.snake.component.DimensionComponent;
import fun.madeby.snake.component.DirectionComponent;
import fun.madeby.snake.component.MovementComponent;
import fun.madeby.snake.component.PlayerComponent;
import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.component.RectangularBoundsComponent;
import fun.madeby.snake.component.SnakeComponent;
import fun.madeby.snake.component.WorldWrapComponent;
import fun.madeby.snake.config.GameConfig;

public class EntityFactory {
    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public Entity createBodyPart(float x, float y) {
        //create components
        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        RectangularBoundsComponent bounds = engine.createComponent(RectangularBoundsComponent.class);
        BodyPartComponent body = engine.createComponent(BodyPartComponent.class);

        // set components
        position.x = x;
        position.y = y;
        dimension.width = GameConfig.COIN_SIZE;
        dimension.height = GameConfig.COIN_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);


        // Manufacture Entity and add components
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(body);

        engine.addEntity(entity);

        return entity;

    }

    public void createCoin() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        RectangularBoundsComponent bounds = engine.createComponent(RectangularBoundsComponent.class);
        CoinComponent coin = engine.createComponent(CoinComponent.class);

        dimension.width = GameConfig.COIN_SIZE;
        dimension.height = GameConfig.COIN_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);


        // Manufacture Entity and add components
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(coin);

        engine.addEntity(entity);

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
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        DirectionComponent direction = engine.createComponent(DirectionComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        WorldWrapComponent wrap = engine.createComponent(WorldWrapComponent.class);

        // Set components
        dimension.width = GameConfig.SNAKE_SIZE;
        dimension.height = GameConfig.SNAKE_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);

        // Manufacture Entity and add components
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(direction);
        entity.add(player);
        entity.add(wrap);

        engine.addEntity(entity);

        return entity;
    }


}
