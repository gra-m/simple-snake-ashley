package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Marker component, does not need to be Poolable == single component added to head for direction input
 */
public class PlayerComponent implements Component, Pool.Poolable {

    @Override
    public void reset() {

    }
}
