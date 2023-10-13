package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import java.awt.Rectangle;

public class RectangularBoundsComponent implements Component, Pool.Poolable {
   public final Rectangle rectangle = new Rectangle(0, 0, 1, 1);

    @Override
    public void reset() {
       rectangle.setBounds(0,0,1, 1);
    }
}
