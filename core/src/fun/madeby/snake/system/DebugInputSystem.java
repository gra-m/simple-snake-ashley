package fun.madeby.snake.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import fun.madeby.snake.system.debug.DebugRenderSystem;
import fun.madeby.snake.system.debug.GridRenderSystem;

/**
 * Note, this was added after I had already made the addition of these systems dependent on the
 * debugMode boolean in my GameScreen, so the addition of this system is also linked to this boolean
 * When added this system allows the removal of grid and debug outlines.
 */
public class DebugInputSystem extends EntitySystem {
    private boolean debugGrid = true;
    private boolean debugRender = true;

    private EntitySystem gridRenderSystem;
    private EntitySystem debugRenderSystem;

    public DebugInputSystem(){}

    /**
     * Called whenever a system is added to Engine, empty in super
     * @param engine The {@link Engine} this system was added to.
     */
    @Override
    public void addedToEngine(Engine engine) {
        gridRenderSystem = engine.getSystem(GridRenderSystem.class);
        debugRenderSystem = engine.getSystem(DebugRenderSystem.class);
        toggleSystems();
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            debugGrid = !debugGrid;
            toggleSystems();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
            debugRender = !debugRender;
            toggleSystems();
        }
    }

    private void toggleSystems() {
        gridRenderSystem.setProcessing(debugGrid);
        debugRenderSystem.setProcessing(debugRender);
    }
}
