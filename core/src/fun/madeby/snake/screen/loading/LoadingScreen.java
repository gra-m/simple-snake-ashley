package fun.madeby.snake.screen.loading;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fun.madeby.SimpleSnakeGame;
import fun.madeby.snake.assets.AssetDescriptors;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.snake.screen.menu.MenuScreen;
import fun.madeby.util.GdxUtils;

public class LoadingScreen extends ScreenAdapter {
    private static final Logger LOG = new Logger(LoadingScreen.class.getName(), Logger.DEBUG);

    private static final float PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH / 2f;
    private static final float PROGRESS_BAR_HEIGHT = GameConfig.HUD_HEIGHT /6f;

    private final SimpleSnakeGame game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private float progress;
    private float ensureProgressBarSeenWaitTime = 0.75f;
    private float waitedSoFar;
    private boolean waitCompletedAndAllActionsOnThisScreenCompleted;


    public LoadingScreen(SimpleSnakeGame simpleSnakeGame) {
        this.game = simpleSnakeGame;
        this.assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        assetManager.load(AssetDescriptors.UI_FONT);
        assetManager.load(AssetDescriptors.UI_SKIN);
        assetManager.load(AssetDescriptors.GAMEPLAY_ATLAS);
        assetManager.load(AssetDescriptors.COIN_SOUND);
        assetManager.load(AssetDescriptors.LOSE_SOUND);
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        update(delta);

        draw();
    }

    private void draw() {


        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        // x/y position centralised on HUD, width height
        renderer.rect(
                (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) /2f,
                (GameConfig.HUD_HEIGHT - PROGRESS_BAR_HEIGHT) /2f,
                progress * PROGRESS_BAR_WIDTH,
                PROGRESS_BAR_HEIGHT
        );

        renderer.end();

        if (waitCompletedAndAllActionsOnThisScreenCompleted) {
            game.setScreen(new MenuScreen(game));
        }
    }

    /**
     * Get progress from asset manager and trigger load completion
     * @param delta
     */
    private void update(float delta) {
        waitedSoFar += delta;
        progress = assetManager.getProgress();

        if (assetManager.update()  && waitedSoFar >= ensureProgressBarSeenWaitTime ) {
            waitCompletedAndAllActionsOnThisScreenCompleted = true;
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
