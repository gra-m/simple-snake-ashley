package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;

/**
 * Passive system that Exists only to provide an AssetManager to any class that requires one.
 */
public class AssetManagerProviderSystem extends EntitySystem {
    private AssetManager assetManager;

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
            return assetManager;
    }
}
