package fun.madeby.snake.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import fun.madeby.SimpleSnakeGame;
import fun.madeby.snake.assets.AssetDescriptors;
import fun.madeby.snake.common.GameManager;
import fun.madeby.snake.screen.menu.MenuScreen;

public class GameScreen extends ScreenAdapter {
    private final SimpleSnakeGame game;
    private final AssetManager assetManager;
    private final CollisionListener collisionListener;

    private GameRenderer renderer;
    private GameController controller;
    private Sound coinSound;
    private Sound loseSound;

    public GameScreen(SimpleSnakeGame simpleSnakeGame) {
        this.game = simpleSnakeGame;
        this.assetManager = game.getAssetManager();

        collisionListener = new CollisionListener() {
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


    @Override
    public void show() {
        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);
        // only instantiated when screen needs to be shown
        controller = new GameController(collisionListener);
        renderer = new GameRenderer(controller, this.game.getBatch(), this.game.getAssetManager());
    }

    // the nexus, where render called automatically for this screen splits game logic/model (controller)
    // with rendering/view (renderer)
    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);
        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }




    @Override
    public void resize(int width, int height) {
        // propagate resize to renderer:
        renderer.resize(width, height);
    }


    @Override
    public void hide() {
        // screens are not auto disposed so we do this on hide
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
