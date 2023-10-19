package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;

public class AssetManagerSystem extends EntitySystem {
    private AssetManagerProviderSystem assetManagerProviderSystem;
    private AssetManager assetManager;

    public AssetManagerSystem() {
        addedToEngine(getEngine());
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.assetManagerProviderSystem = engine.getSystem(AssetManagerProviderSystem.class);
        init();
    }

    private void init() {
        if (this.assetManagerProviderSystem != null)
                this.assetManager = assetManagerProviderSystem.getAssetManager();
        else throw new RuntimeException("assetManagerProviderSystemIsNull");

    }

    protected AssetManager getAssetManager() {
        if (this.assetManager != null)
            return this.assetManager;
        else throw new RuntimeException("AssetManagerSystem assetManager is null");
    }


}
