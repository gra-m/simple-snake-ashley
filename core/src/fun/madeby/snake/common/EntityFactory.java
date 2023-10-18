package fun.madeby.snake.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import fun.madeby.snake.assets.AssetDescriptors;
import fun.madeby.snake.assets.RegionNames;
import fun.madeby.snake.component.BodyPartComponent;
import fun.madeby.snake.component.CoinComponent;
import fun.madeby.snake.component.DimensionComponent;
import fun.madeby.snake.component.DirectionComponent;
import fun.madeby.snake.component.MovementComponent;
import fun.madeby.snake.component.PlayerComponent;
import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.component.RectangularBoundsComponent;
import fun.madeby.snake.component.SnakeComponent;
import fun.madeby.snake.component.TextureComponent;
import fun.madeby.snake.component.WorldWrapComponent;
import fun.madeby.snake.config.GameConfig;

public class EntityFactory {
    private final PooledEngine engine;
    private final AssetManager assetManager;
    private TextureAtlas gameplayAtlas;

    public EntityFactory(PooledEngine engine, AssetManager assetManager) {
        this.engine = engine;
        this.assetManager = assetManager;
        init();
    }

    private void init() {
        gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ATLAS);
    }

    public Entity createBodyPart(float x, float y) {
        //create components
        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        RectangularBoundsComponent bounds = engine.createComponent(RectangularBoundsComponent.class);
        BodyPartComponent body = engine.createComponent(BodyPartComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);

        // set components
        position.x = x;
        position.y = y;
        dimension.width = GameConfig.COIN_SIZE;
        dimension.height = GameConfig.COIN_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);
        texture.textureRegion = gameplayAtlas.findRegion(RegionNames.BODY);


        // Manufacture Entity and add components
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(body);
        entity.add(texture);

        engine.addEntity(entity);

        return entity;

    }

    public void createCoin() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        RectangularBoundsComponent bounds = engine.createComponent(RectangularBoundsComponent.class);
        CoinComponent coin = engine.createComponent(CoinComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);

        dimension.width = GameConfig.COIN_SIZE;
        dimension.height = GameConfig.COIN_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);
        texture.textureRegion = gameplayAtlas.findRegion(RegionNames.COIN);



        // Manufacture Entity and add components
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(coin);
        entity.add(texture);

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
        TextureComponent texture = engine.createComponent(TextureComponent.class);

        // Set components
        dimension.width = GameConfig.SNAKE_SIZE;
        dimension.height = GameConfig.SNAKE_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);
        texture.textureRegion = gameplayAtlas.findRegion(RegionNames.HEAD);

        // Manufacture Entity and add components
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(movement);
        entity.add(direction);
        entity.add(player);
        entity.add(wrap);
        entity.add(texture);

        engine.addEntity(entity);

        return entity;
    }


}
