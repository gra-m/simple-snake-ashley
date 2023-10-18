package fun.madeby.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import fun.madeby.snake.component.DimensionComponent;
import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.component.TextureComponent;
import fun.madeby.util.Mappers;

public class RenderSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            TextureComponent.class,
            PositionComponent.class,
            DimensionComponent.class
    ).get();
    private final SpriteBatch batch;
    private final Viewport viewport;

    public RenderSystem(SpriteBatch spriteBatch, Viewport viewport) {
        super(FAMILY);
        this.batch = spriteBatch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        //batch.setProjectionMatrix(viewport.getCamera().combined);

        // overriding and calling update within batch process saves it being called for every entity
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent retrievedPosition = Mappers.POSITION_COMPONENT_MAPPER.get(entity);
        DimensionComponent retrievedDimensions = Mappers.DIMENSION_COMPONENT_MAPPER.get(entity);
        TextureComponent retrievedTexture = Mappers.TEXTURE_COMPONENT_MAPPER.get(entity);

        batch.draw(retrievedTexture.textureRegion, retrievedPosition.x, retrievedPosition.y,
                retrievedDimensions.width, retrievedDimensions.height);

    }
}
