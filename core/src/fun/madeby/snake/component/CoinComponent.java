package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class CoinComponent implements Component, Pool.Poolable {
    public boolean isAvailableToEat;


    @Override
    public void reset() {
        isAvailableToEat = false;
    }
}
