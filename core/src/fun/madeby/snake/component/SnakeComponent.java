package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;

public class SnakeComponent implements Component, Pool.Poolable {
    private static final Logger LOG = new Logger(SnakeComponent.class.getName(), Logger.DEBUG);
    public Entity head;
    public final Array<Entity> bodyParts = new Array<Entity>();



    @Override
    public void reset() {
        LOG.debug("Resetting snake component");
        head = null;
        bodyParts.clear();
        LOG.debug("Reset done");

    }
}
