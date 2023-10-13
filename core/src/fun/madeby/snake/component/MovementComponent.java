package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MovementComponent implements Component, Pool.Poolable {
    public float xSpeed;
    public float ySpeed;

    @Override
    public void reset() {
        this.xSpeed = 0f;
        this.ySpeed = 0f;

    }
}
