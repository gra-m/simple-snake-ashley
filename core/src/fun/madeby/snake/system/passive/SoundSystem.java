package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import fun.madeby.snake.assets.AssetPaths;


public class SoundSystem extends EntitySystem {
    private AssetManagerProviderSystem assetManagerProviderSystem;
    private AssetManager assetManager;

    private Sound lose;
    private Sound coin;

    public SoundSystem() {}


    private void init() {
        lose = assetManager.get(AssetPaths.LOSE_SOUND);
        coin = assetManager.get(AssetPaths.COIN_SOUND);
    }

    @Override
    public void addedToEngine(Engine engine) {
        assetManagerProviderSystem = engine.getSystem(AssetManagerProviderSystem.class);
        assetManager = assetManagerProviderSystem.getAssetManager();
        init();
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


    public void hitCoin() {
        coin.play();

    }

    public void lose() {
        lose.play();
    }
}
