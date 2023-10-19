package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;

/**
 * Exists in order to provide an assetManager to the AssetManagerHolderSuperSystem. Allowing the
 * asset manager to be retrieved from the engine, and therefore there being no need for AssetManager
 * constructor parameters anywhere in the AssetManagerHolderSuperSystem.
 */
public class AssetManagerProviderSystem extends EntitySystem {
    private final AssetManager assetManager;

    public AssetManagerProviderSystem(AssetManager assetManager){
        this.assetManager = assetManager;
    }

    @Override
    public void update(float deltaTime) {
        //passive
    }

    @Override
    public boolean checkProcessing() {
        //passive
        return false;
    }

    public AssetManager getAssetManager() {
        if (this.assetManager != null)
            return assetManager;
        else throw new RuntimeException("AssetManagerProviderSystem assetManager is null");
    }
}
