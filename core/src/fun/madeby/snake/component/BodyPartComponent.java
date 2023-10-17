package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Enables body part entities to show if they have just been created, so head does not immediately
 * collide with newly created body part
  */
public class BodyPartComponent implements Component, Pool.Poolable {
    public boolean justCreated = true;


    @Override
    public void reset() {
        justCreated = true;
    }

}
