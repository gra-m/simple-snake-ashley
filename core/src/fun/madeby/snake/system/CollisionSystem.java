package fun.madeby.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;

import fun.madeby.snake.common.EntityFactory;
import fun.madeby.snake.component.CoinComponent;
import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.component.RectangularBoundsComponent;
import fun.madeby.snake.component.SnakeComponent;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.util.Mappers;

/**
 * A super-class of IntervalIteratingSystem, but instead of automatically iterating over a FAMILY it
 * calls updateInterval when the interval has elapsed
 */
public class CollisionSystem extends IntervalSystem {
    private static final Family COIN_FAMILY = Family.all(
            CoinComponent.class
    ).get();
    private static final Family SNAKE_FAMILY = Family.all(
            SnakeComponent.class
    ).get();
    private final EntityFactory factory;

    public CollisionSystem(EntityFactory entityFactory) {
        super(GameConfig.NORMAL_MOVES_EVERY.every);
        this.factory = entityFactory;
    }

    @Override
    protected void updateInterval() {
        Engine engine = getEngine();
        ImmutableArray<Entity> AllSnakes =  engine.getEntitiesFor(SNAKE_FAMILY);
        ImmutableArray<Entity> AllCoins =  engine.getEntitiesFor(COIN_FAMILY);

        for (Entity snakeEntity : AllSnakes) {
            for (Entity coinEntity : AllCoins) {
                SnakeComponent snakeComponent = Mappers.SNAKE_COMPONENT_MAPPER.get(snakeEntity);
                CoinComponent coinComponent = Mappers.COIN_COMPONENT_MAPPER.get(coinEntity);
                if (coinComponent.isAvailableToEat && overlaps(snakeComponent.head, coinEntity)) {
                    // coin now unavailable
                    coinComponent.isAvailableToEat = false;
                    // new body part added
                    PositionComponent position = Mappers.POSITION_COMPONENT_MAPPER.get(snakeComponent.head);
                    Entity newBodyPart = factory.createBodyPart(position.x, position.y);
                    snakeComponent.bodyParts.insert(0, newBodyPart);

                }
            }
        }
    }

    private boolean overlaps(Entity first, Entity second) {
        RectangularBoundsComponent firstBounds = Mappers.RECTANGULAR_BOUNDS_MAPPER.get(first);
        RectangularBoundsComponent secondBounds = Mappers.RECTANGULAR_BOUNDS_MAPPER.get(second);

        return Intersector.overlaps(firstBounds.rectangle, secondBounds.rectangle);
    }
}
