package fun.madeby.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

import fun.madeby.snake.component.CoinComponent;
import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.util.Mappers;

public class CoinSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(
            CoinComponent.class,
            PositionComponent.class
    ).get();

    public CoinSystem() {
        super(FAMILY);
    }


    @Override
    protected void processEntity(Entity entity, float DeltaTime) {
        CoinComponent retrievedCoinComponent = Mappers.COIN_COMPONENT_MAPPER.get(entity);
        PositionComponent retrievedPositionComponent = Mappers.POSITION_COMPONENT_MAPPER.get(entity);

       //Quick exit
        if(retrievedCoinComponent.isAvailableToEat)
            return;


            float coinX = MathUtils.random((int) (GameConfig.WORLD_WIDTH - GameConfig.COIN_SIZE));
            float coinY = MathUtils.random((int) (GameConfig.Y_CONSTRAINED - GameConfig.COIN_SIZE));
            retrievedCoinComponent.isAvailableToEat = true;

            retrievedPositionComponent.x = coinX;
            retrievedPositionComponent.y = coinY;

        }

    }

