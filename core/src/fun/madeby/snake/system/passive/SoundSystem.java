package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import fun.madeby.snake.assets.AssetPaths;


public class SoundSystem extends EntitySystem implements Sounds {

    private final AssetManager assetManager;
    private PooledEngine engine;
    private Sound lose;
    private Sound coin;

    public SoundSystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    private void init() {
        lose = assetManager.get(AssetPaths.LOSE_SOUND);
        coin = assetManager.get(AssetPaths.COIN_SOUND);
    }

    @Override
    public void update(float deltaTime) {
       //This is a passive system
    }
    @Override
    public boolean checkProcessing() {
        // Non-processing passive system
        return false;
    }

    @Override
    public void addedToEngine(Engine engine) {
       this.engine = (PooledEngine) engine;
    }

    @Override
    public void hitCoin() {
        coin.play();

    }

    @Override
    public void lose() {
        lose.play();
    }
}
