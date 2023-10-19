package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import fun.madeby.snake.assets.AssetPaths;


public class SoundSystem extends AssetManagerSystem implements Sounds {

    private Sound lose;
    private Sound coin;

    public SoundSystem() {
        init();
    }


    private void init() {
        lose = getAssetManager().get(AssetPaths.LOSE_SOUND);
        coin = getAssetManager().get(AssetPaths.COIN_SOUND);
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
    public void hitCoin() {
        coin.play();

    }

    @Override
    public void lose() {
        lose.play();
    }
}
