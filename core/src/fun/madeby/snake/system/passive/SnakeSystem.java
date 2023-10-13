package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.utils.Logger;

import fun.madeby.snake.component.SnakeComponent;
import fun.madeby.util.Mappers;


/**
 * Passive system == update is not called flagged with checkProcessing <-false
 */
public class SnakeSystem extends EntitySystem implements EntityListener {

    private static final Logger LOG = new Logger(SnakeSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(SnakeComponent.class).get();


    @Override
    public boolean checkProcessing() {
       return false ;
    }

    @Override
    public void addedToEngine(Engine engine) {
        LOG.debug("SnakeSystem addedToEngine. Adding EntityListener");
        engine.addEntityListener(FAMILY, this);

    }

    // Best practice to remove listeners
    @Override
    public void removedFromEngine(Engine engine) {
        LOG.debug("SnakeSystem removed from engine.");
        engine.removeEntityListener(this);
    }

    @Override
    public void update(float deltaTime) {
       // NOT PROCESSING ANYTHING
    }

    @Override
    public void entityAdded(Entity entity) {
        LOG.debug("entityAdded entity = " + entity);

    }

    @Override
    public void entityRemoved(Entity entity) {
        LOG.debug("entityRemoved entity = " + entity.getClass().getSimpleName());

        Engine engine = super.getEngine();
        // Remove Head
        SnakeComponent snakeComponent = Mappers.SNAKE_COMPONENT_MAPPER.get(entity);
        engine.removeEntity(snakeComponent.head);

        for (Entity bodyPart: snakeComponent.bodyParts) {
            engine.removeEntity(bodyPart);
        }

    }
}
