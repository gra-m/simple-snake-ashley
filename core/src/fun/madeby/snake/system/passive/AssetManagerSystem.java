package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;

public class AssetManagerSystem extends EntitySystem {
    private AssetManager assetManager;

    public AssetManagerSystem(AssetManager spawningAssetManagerProviderSystemsAssetManager) {
        this.assetManager = spawningAssetManagerProviderSystemsAssetManager;
    }
    public AssetManagerSystem(){}



    protected AssetManager getAssetManager() {
        if (this.assetManager != null)
            return this.assetManager;
        else throw new RuntimeException("AssetManagerSystem assetManager is null");
    }


}
