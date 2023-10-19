package fun.madeby.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import fun.madeby.snake.assets.AssetPaths;


public class StartUpSystem extends EntitySystem{


    EntityFactorySystem factory;

    public StartUpSystem() {
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.factory = engine.getSystem(EntityFactorySystem.class);
        init();
    }

    private void init() {
        factory.createCoin();
        factory.createBackground();
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




}
