package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class DimensionComponent implements Component, Pool.Poolable {
    public float width = 1f;
    public float height = 1f;

    @Override
    public void reset() {
        this.width = 1f;
        this.height = 1f;
    }
}
