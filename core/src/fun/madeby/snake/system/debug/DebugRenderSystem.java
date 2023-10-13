package fun.madeby.snake.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import fun.madeby.snake.component.RectangularBoundsComponent;
import fun.madeby.util.Mappers;

/**
 * Renders everything that has bounds
 */
public class DebugRenderSystem extends IteratingSystem {
    // It doesn't care about ANY other components so just get necessary
    private static final Family FAMILY = Family.all(RectangularBoundsComponent.class).get();
    private final Viewport viewport;
    private final ShapeRenderer renderer;

    public DebugRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;

    }

    @Override
    public void update(float deltaTime) {
        // Always
        viewport.apply();
        // before super iterates through FAMILY entities processEntity()
        Color oldColor = renderer.getColor().cpy();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setColor(Color.RED);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        super.update(deltaTime);
        renderer.end();

        // after super iterates through FAMILY entities processEntity()
        renderer.setColor(oldColor);
    }

    /**
     * @param entity The current Entity being processed from the family consisting of anything with rectangular bounds
     * @param deltaTime The delta time between the last and current frame
     */
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RectangularBoundsComponent bounds = Mappers.RECTANGULAR_BOUNDS_MAPPER.get(entity);
        renderer.rect(bounds.rectangle.x, bounds.rectangle.y, bounds.rectangle.width, bounds.rectangle.height);
    }
}
