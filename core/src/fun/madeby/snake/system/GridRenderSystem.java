package fun.madeby.snake.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import fun.madeby.util.ViewportUtils;

/**
 * If a system is inside the engine and it is processing its update method is automatically called
 */
public class GridRenderSystem extends EntitySystem {
    private final Viewport viewport;
    private final ShapeRenderer renderer;


    public GridRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        this.viewport = viewport;
        this.renderer = renderer;
    }

    // Logic previously carried out in renderer through renderDebug()

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        ViewportUtils.drawGrid(viewport, renderer);
    }
}
