package fun.madeby.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import fun.madeby.snake.common.ZOrderComparator;
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
    private Array<Entity> renderQueue = new Array<>();

    public RenderSystem(SpriteBatch spriteBatch, Viewport viewport) {
        super(FAMILY);
        this.batch = spriteBatch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {

        // populates renderQueue by calling for FAMILY
        super.update(deltaTime);
        // sorts using Comparator
        renderQueue.sort(ZOrderComparator.INSTANCE);
        // draw everything in renderQueue, now in the correct z-order
        draw();
        //clear the renderQueue so it is clear for next frame
        renderQueue.clear();




    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // now just used to add entities from FAMILY
        renderQueue.add(entity);

    }

    private void draw() {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);


        // draw in RenderQueue order:
        batch.begin();
        for (Entity entity : renderQueue) {
            PositionComponent retrievedPosition = Mappers.POSITION_COMPONENT_MAPPER.get(entity);
            DimensionComponent retrievedDimensions = Mappers.DIMENSION_COMPONENT_MAPPER.get(entity);
            TextureComponent retrievedTexture = Mappers.TEXTURE_COMPONENT_MAPPER.get(entity);


            batch.draw(retrievedTexture.textureRegion, retrievedPosition.x, retrievedPosition.y,
                    retrievedDimensions.width, retrievedDimensions.height);

        }

        batch.end();
    }
}
