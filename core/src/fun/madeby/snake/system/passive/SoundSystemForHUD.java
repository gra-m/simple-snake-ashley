package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import fun.madeby.snake.assets.AssetPaths;


public class SoundSystemForHUD extends EntitySystem {
    private AssetManagerProviderSystem assetManagerProviderSystem;
    private AssetManager assetManager;

    private Sound separatedSounds;
    private Sound forEGHudAndGamePlay;
    private Sound orShortAndLong;
    private Sound basicallyForEasierManagement;

    public SoundSystemForHUD() {}


    private void init() {
        separatedSounds = assetManager.get(AssetPaths.LOSE_SOUND);
        forEGHudAndGamePlay = assetManager.get(AssetPaths.COIN_SOUND);
        orShortAndLong = assetManager.get(AssetPaths.LOSE_SOUND);
        basicallyForEasierManagement = assetManager.get(AssetPaths.LOSE_SOUND);
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


    public void separatedSounds() {
        separatedSounds.play();

    }

}
