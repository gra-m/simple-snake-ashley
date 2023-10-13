package fun.madeby;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;

import fun.madeby.snake.screen.game.GameScreen;
import fun.madeby.snake.screen.loading.LoadingScreen;

// As opposed to ApplicationAdapter Game can have multiple screens
public class SimpleSnakeGame extends Game {
    private AssetManager assetManager;
    private SpriteBatch batch;
    @Override
    public void create() {
        // Set log level for entire application, removal removes all logging at level:
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assetManager = new AssetManager();
        assetManager.getLogger().setLevel(Logger.DEBUG);

        batch = new SpriteBatch();

        setScreen(new LoadingScreen(this));
    }

    @Override
    public void dispose() {
       super.dispose();
       assetManager.dispose();
       batch.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
