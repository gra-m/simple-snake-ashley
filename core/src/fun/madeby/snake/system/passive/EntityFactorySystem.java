package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
import fun.madeby.snake.component.ZOrderComponent;
import fun.madeby.snake.config.GameConfig;

public class EntityFactorySystem extends EntitySystem {
    private static final int BACKGROUND_Z_ORDER = 0;
    private static final  int COIN_Z_ORDER = 1;
    private static final  int BODY_PART_Z_ORDER = 2;
    private static final  int HEAD_Z_ORDER = 3;

    private final AssetManager assetManager;

    private TextureAtlas gameplayAtlas;
    private PooledEngine engine;

    public EntityFactorySystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    @Override
    public void update(float deltaTime) {
       //This is a passive system
    }
    @Override
    public boolean checkProcessing() {
        // Non-processing passive system
        return false;
    }

    private void init() {
        gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ATLAS);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine) engine;
    }

    public void createBackground() {
        // obtain entity, its components and their requirements
        Entity background = engine.createEntity();

        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);

        TextureRegion bg = gameplayAtlas.findRegion(RegionNames.BACKGROUND);

        // set them
        position.x = 0;
        position.y = 0;
        dimension.width = GameConfig.WORLD_WIDTH;
        dimension.height = GameConfig.WORLD_HEIGHT;
        zOrder.z = BACKGROUND_Z_ORDER;
        texture.textureRegion = bg;

        // add them
        background.add(position);
        background.add(dimension);
        background.add(zOrder);
        background.add(texture);

        engine.addEntity(background);

    }

    public Entity createBodyPart(float x, float y) {
        //create components
        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        RectangularBoundsComponent bounds = engine.createComponent(RectangularBoundsComponent.class);
        BodyPartComponent body = engine.createComponent(BodyPartComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);

        // set components
        position.x = x;
        position.y = y;
        dimension.width = GameConfig.COIN_SIZE;
        dimension.height = GameConfig.COIN_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);
        texture.textureRegion = gameplayAtlas.findRegion(RegionNames.BODY);
        zOrder.z = BODY_PART_Z_ORDER;


        // Manufacture Entity and add components
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(body);
        entity.add(texture);
        entity.add(zOrder);

        engine.addEntity(entity);

        return entity;

    }

    public void createCoin() {
        PositionComponent position = engine.createComponent(PositionComponent.class);
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        RectangularBoundsComponent bounds = engine.createComponent(RectangularBoundsComponent.class);
        CoinComponent coin = engine.createComponent(CoinComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);

        dimension.width = GameConfig.COIN_SIZE;
        dimension.height = GameConfig.COIN_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);
        texture.textureRegion = gameplayAtlas.findRegion(RegionNames.COIN);
        zOrder.z = COIN_Z_ORDER;


        // Manufacture Entity and add components
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(coin);
        entity.add(texture);
        entity.add(zOrder);

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
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);


        // Set components
        dimension.width = GameConfig.SNAKE_SIZE;
        dimension.height = GameConfig.SNAKE_SIZE;
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width);
        texture.textureRegion = gameplayAtlas.findRegion(RegionNames.HEAD);
        zOrder.z = HEAD_Z_ORDER;

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
        entity.add(zOrder);

        engine.addEntity(entity);

        return entity;
    }


}
