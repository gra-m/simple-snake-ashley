package fun.madeby.snake.screen.game;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fun.madeby.snake.assets.AssetDescriptors;
import fun.madeby.snake.assets.RegionNames;
import fun.madeby.snake.common.GameManager;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.snake.entity.BodyPart;
import fun.madeby.snake.entity.Coin;
import fun.madeby.snake.entity.Snake;
import fun.madeby.snake.entity.SnakeHead;
import fun.madeby.util.GdxUtils;
import fun.madeby.util.ViewportUtils;
import fun.madeby.util.debug.DebugCameraController;

// super dispose in game disposes game screen and this dispose

/**
 * A class for everything related to rendering in the game so all textures etc
 */
@Deprecated
public class GameRenderer implements Disposable {
    private static final Logger LOG = new Logger(GameRenderer.class.getName(), Logger.DEBUG);
    private boolean chooseDebuggingHere = false;
    private final GameController controller;
    private final AssetManager assetManager;

    // Camera and Viewport for Debug (renderer.Line) and Game (batch.Texture) rendering
    private OrthographicCamera gameCamera;
    private Viewport viewport;

    // debug wireframe and ShaperRenderer that renders them (follows entity bounds for collision)
    private ShapeRenderer renderer;
    private DebugCameraController debugCameraController;

    // game Textures and the batch that renders them
    private TextureRegion backgroundRegion;
    private TextureRegion headRegion;
    private TextureRegion bodyRegion;
    private TextureRegion coinRegion;
    private final SpriteBatch batch;

    // Rendering for HUD
    private Viewport hudViewport;
    private BitmapFont font;
    private GlyphLayout layout = new GlyphLayout();



    public GameRenderer(GameController gameController, SpriteBatch spriteBatch, AssetManager assetManager) {
        this.controller = gameController;
        this.batch = spriteBatch;
        this.assetManager = assetManager;
        init();
    }

    private void init() {
        gameCamera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, gameCamera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        renderer = new ShapeRenderer();

        font = assetManager.get(AssetDescriptors.UI_FONT);
        TextureAtlas gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY_ATLAS);
        backgroundRegion = gameplayAtlas.findRegion(RegionNames.BACKGROUND);
        headRegion = gameplayAtlas.findRegion(RegionNames.HEAD);
        bodyRegion = gameplayAtlas.findRegion(RegionNames.BODY);
        coinRegion = gameplayAtlas.findRegion(RegionNames.COIN);

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(gameCamera);

        GdxUtils.clearScreen();

        renderGameplay();
        renderHUD();
        if (chooseDebuggingHere) {
            renderDebug();
        }
    }

    private void renderGameplay() {
        viewport.apply();

        batch.setProjectionMatrix(gameCamera.combined);

        batch.begin();
        drawGameplay();
        batch.end();
    }

    private void drawGameplay() {
        batch.draw(backgroundRegion, 0,0, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);

        Coin coin = controller.getCoin();
        if (coin.isAvailableToEat()) {
            batch.draw(coinRegion, coin.getX(), coin.getY(),
                    GameConfig.COIN_SIZE, GameConfig.COIN_SIZE);
        }

        Snake snake = controller.getSnake();

        for (BodyPart part: snake.getBodyParts()) {
            batch.draw(bodyRegion, part.getX(), part.getY(),
                    GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
        }

        SnakeHead head  = snake.getHead();
        batch.draw(headRegion, head.getX(), head.getY(),
                GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);



    }

    private void renderHUD() {
        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);

        batch.begin();
        drawHUD();

        batch.end();
    }

    private void drawHUD() {
        String scoreString = "SCORE: " + GameManager.INSTANCE.getDisplayScore();
        String highScoreString = "HIGH-SCORE: " + GameManager.INSTANCE.getDisplayHighScore();

        float allHudTextY = hudViewport.getWorldHeight() - GameConfig.HUD_PADDING;
        float highScoreX = GameConfig.HUD_PADDING;

        layout.setText(font, highScoreString);
        font.draw(batch, layout, highScoreX, allHudTextY);

        layout.setText(font, scoreString);
        float scoreX = hudViewport.getWorldWidth() - (layout.width + GameConfig.HUD_PADDING);

        font.draw(batch, layout, scoreX, allHudTextY);




    }

    private void renderDebug() {

        viewport.apply();
        ViewportUtils.drawGrid(viewport, renderer);


        Color oldColor = renderer.getColor().cpy();

        renderer.setProjectionMatrix(gameCamera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        drawDebug();

        renderer.end();

        renderer.setColor(oldColor);



    }

    private void drawDebug() {
        Snake snake = controller.getSnake();
        SnakeHead head = snake.getHead();
        Coin coin = controller.getCoin();

        // Render Body 1st
        renderer.setColor(Color.PURPLE);
        for (BodyPart bodyPart : snake.getBodyParts()){
            Rectangle bodyPartBounds = bodyPart.getBoundsThatAreUsedForCollisionDetection();
            renderer.rect(bodyPartBounds.x, bodyPartBounds.y, bodyPartBounds.getWidth(), bodyPartBounds.getHeight());
        }


        renderer.setColor(Color.RED);
        renderer.rect(head.getX(), head.getY(), head.getWidth(), head.getHeight());
        renderer.rect(coin.getX(), coin.getY(), coin.getWidth(), coin.getHeight());
        // draw bounds position if you see red as well as green or blue something wrong.
        Rectangle headBounds = head.getBoundsThatAreUsedForCollisionDetection();
        Rectangle coinBounds = coin.getBoundsThatAreUsedForCollisionDetection();
        // Render head 2nd
        renderer.setColor(Color.GREEN);
        renderer.rect(headBounds.x, headBounds.y, headBounds.getWidth(), headBounds.getHeight());
        // Render coin 3rd
        if (coin.isAvailableToEat()) {
            renderer.setColor(Color.BLUE);
            renderer.rect(coinBounds.x, coinBounds.y, coinBounds.getWidth(), coinBounds.getHeight());
        }


    }

    // called at start of game and for any subsequent resize event.
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);

        LOG.debug("Getting game Pixels per unit from debugPixelsPerUnit(viewport): ");
        ViewportUtils.debugPixelsPerUnit(viewport);
        ViewportUtils.debugPixelsPerUnit(hudViewport);
    }


    @Override
    public void dispose() {
    renderer.dispose();

    }
}
