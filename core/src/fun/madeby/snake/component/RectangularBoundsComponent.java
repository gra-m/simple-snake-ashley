package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;


public class RectangularBoundsComponent implements Component, Pool.Poolable {
   public final Rectangle rectangle = new Rectangle(0, 0, 1, 1);

    @Override
    public void reset() {
       rectangle.setPosition(0f, 0f);
       rectangle.setSize(1f);
    }
}
