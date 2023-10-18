package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class TextureComponent implements Component, Pool.Poolable {
    public TextureRegion textureRegion;

    @Override
    public void reset() {
        textureRegion = null;
    }
}
