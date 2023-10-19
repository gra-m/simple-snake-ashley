package fun.madeby.snake.screen.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fun.madeby.SimpleSnakeGame;
import fun.madeby.snake.assets.AssetDescriptors;
import fun.madeby.snake.system.passive.EntityFactorySystem;
import fun.madeby.snake.common.GameManager;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.snake.screen.menu.MenuScreen;
import fun.madeby.snake.system.BoundsUpdateSystem;
import fun.madeby.snake.system.CoinSystem;
import fun.madeby.snake.system.CollisionSystem;
import fun.madeby.snake.system.DebugInputSystem;
import fun.madeby.snake.system.DirectionSystem;
import fun.madeby.snake.system.HudRenderSystem;
import fun.madeby.snake.system.RenderSystem;
import fun.madeby.snake.system.SnakeMovementSystem;
import fun.madeby.snake.system.PlayerControlSystem;
import fun.madeby.snake.system.WorldWrapSystem;
import fun.madeby.snake.system.debug.DebugCameraSystem;
import fun.madeby.snake.system.debug.DebugRenderSystem;
import fun.madeby.snake.system.debug.GridRenderSystem;
import fun.madeby.snake.system.passive.SnakeSystem;
import fun.madeby.util.GdxUtils;

public class GameScreen extends ScreenAdapter {
    // If you want to turn this off
    private boolean debugMode = true;
    private Entity snake;
    private static final Logger LOG = new Logger(GameScreen.class.getName(), Logger.DEBUG);
    private final SimpleSnakeGame game;
    private final AssetManager assetManager;
    private OrthographicCamera camera;

    // fields required by DebugUtil moved up to GameScreen from GameRenderer:
    private Viewport viewport;
    private ShapeRenderer renderer;
    private Sound coinSound;
    private Sound loseSound;

    // Hud Only
    private Viewport hudViewport;
    private BitmapFont hudFont;
    // One engine per game is the target:
    private PooledEngine engine;
    private EntityFactorySystem entityFactorySystem;
    private SpriteBatch batch;
    private CollisionListener listener;

    public GameScreen(SimpleSnakeGame simpleSnakeGame) {
        this.game = simpleSnakeGame;
        this.assetManager = game.getAssetManager();
        this.batch = game.getBatch();

        listener = new CollisionListener() {
            @Override
            public void hitCoin() {
                coinSound.play();
            }

            @Override
            public void lose() {
                loseSound.play();
            }
        };

    }

    // Automatically called and used to init fields
    @Override
    public void show() {

        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        hudFont = assetManager.get(AssetDescriptors.UI_FONT);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);

        addAllRequireSystemsToEngine();

        // retrieving added system here in same way as it can be retrieved from engine anywhere it is needed
        entityFactorySystem = engine.getSystem(EntityFactorySystem.class);

        snake = entityFactorySystem.createSnake();
        entityFactorySystem.createCoin();
        entityFactorySystem.createBackground();
    }

    private void addAllRequireSystemsToEngine() {
        if (debugMode) {
            engine.addSystem(new GridRenderSystem(viewport, renderer));
            engine.addSystem(new DebugCameraSystem(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y, camera));
            engine.addSystem(new DebugRenderSystem(viewport, renderer));
            engine.addSystem(new DebugInputSystem());
        }
        engine.addSystem(new SnakeSystem());
        engine.addSystem(new DirectionSystem());
        engine.addSystem(new SnakeMovementSystem());
        engine.addSystem(new BoundsUpdateSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CoinSystem());
        engine.addSystem(new EntityFactorySystem(engine, assetManager));
        engine.addSystem(new CollisionSystem(listener));
        engine.addSystem(new RenderSystem(batch, viewport));
        engine.addSystem(new HudRenderSystem(batch, hudViewport, hudFont));
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        if (debugMode) {
            removeSnakeEntityForDebugging();
        }

        // With OOP controller.update(delta) and renderer.render(delta) were called here engine does it all:
        engine.update(delta);
        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
            GameManager.INSTANCE.reset();
        }
    }

    private void removeSnakeEntityForDebugging() {
        if (debugMode && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            LOG.debug("entity count before snake removed " + engine.getEntities().size());
            engine.removeEntity(snake);
            LOG.debug("entity count after snake removed " +  engine.getEntities().size() + " press again for confirmation head and all body parts removed.");
            // todo add snake back in? More complex useful to remove objects from system?
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
    }
}
