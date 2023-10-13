package fun.madeby.snake.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fun.madeby.SimpleSnakeGame;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.snake.system.GridRenderSystem;
import fun.madeby.util.GdxUtils;

public class GameScreen extends ScreenAdapter {
    private final SimpleSnakeGame game;
    private final AssetManager assetManager;
    private boolean debugMode = true;

    // fields required by DebugUtil moved up to GameScreen from GameRenderer:
    private Viewport viewport;
    private ShapeRenderer renderer;

    // One engine per game is the target:
    private PooledEngine engine;

    public GameScreen(SimpleSnakeGame simpleSnakeGame) {
        this.game = simpleSnakeGame;
        this.assetManager = game.getAssetManager();
    }

    // Automatically called and used to init fields
    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        addAllRequireSystemsToEngine();

    }

    private void addAllRequireSystemsToEngine() {
        if (debugMode) {
            engine.addSystem(new GridRenderSystem(viewport, renderer));
        }
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        // With OOP controller.update(delta) and renderer.render(delta) were called here though:
        engine.update(delta);
        //todo I did this elsewhere in original = reminder if my method is removed:
        /*if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }*/
    }

    @Override
    public void resize(int width, int height) {
        // todo look, an uncentered camera!
        viewport.update(width, height, false);

    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
    }
}
