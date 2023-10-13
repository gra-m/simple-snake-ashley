package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PositionComponent implements Component, Pool.Poolable {
    public float x;
    public float y;

    @Override
    public void reset() {
        this.x = 0f;
        this.y = 0f;
    }
}
