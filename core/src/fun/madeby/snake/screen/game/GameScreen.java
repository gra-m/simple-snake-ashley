package fun.madeby.snake.screen.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fun.madeby.SimpleSnakeGame;
import fun.madeby.snake.common.EntityFactory;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.snake.system.BoundsUpdateSystem;
import fun.madeby.snake.system.CoinSystem;
import fun.madeby.snake.system.DirectionSystem;
import fun.madeby.snake.system.SnakeMovementSystem;
import fun.madeby.snake.system.PlayerControlSystem;
import fun.madeby.snake.system.WorldWrapSystem;
import fun.madeby.snake.system.debug.DebugCameraSystem;
import fun.madeby.snake.system.debug.DebugRenderSystem;
import fun.madeby.snake.system.debug.GridRenderSystem;
import fun.madeby.snake.system.passive.SnakeSystem;
import fun.madeby.util.GdxUtils;

public class GameScreen extends ScreenAdapter {
    private boolean debugMode = true;
    private Entity snakeForDebugging;
    private static final Logger LOG = new Logger(GameScreen.class.getName(), Logger.DEBUG);
    private final SimpleSnakeGame game;
    private final AssetManager assetManager;
    private OrthographicCamera camera;

    // fields required by DebugUtil moved up to GameScreen from GameRenderer:
    private Viewport viewport;
    private ShapeRenderer renderer;

    // One engine per game is the target:
    private PooledEngine engine;
    private EntityFactory factory;

    public GameScreen(SimpleSnakeGame simpleSnakeGame) {
        this.game = simpleSnakeGame;
        this.assetManager = game.getAssetManager();
    }

    // Automatically called and used to init fields
    @Override
    public void show() {

        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        factory = new EntityFactory(engine);
        addAllRequireSystemsToEngine();
LOG.debug("entity count before adding snake " + engine.getEntities().size());
        snakeForDebugging = factory.createSnake();
        factory.createCoin();
LOG.debug("entity count after adding snake " + engine.getEntities().size());
    }

    private void addAllRequireSystemsToEngine() {
        if (debugMode) {
            engine.addSystem(new GridRenderSystem(viewport, renderer));
            engine.addSystem(new DebugCameraSystem(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y, camera));
        }
        engine.addSystem(new DebugRenderSystem(viewport, renderer));
        engine.addSystem(new SnakeSystem());
        engine.addSystem(new DirectionSystem());
        engine.addSystem(new SnakeMovementSystem());
        engine.addSystem(new BoundsUpdateSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CoinSystem());
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            LOG.debug("entity count before remove debugSnake " + engine.getEntities().size());
            engine.removeEntity(snakeForDebugging);
            LOG.debug("entity count after remove debugSnake " + engine.getEntities().size());
        }


        // With OOP controller.update(delta) and renderer.render(delta) were called here though:
        engine.update(delta);
        //todo I did this elsewhere in original = reminder if my method is removed:
        /*if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }*/
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
    }
}
