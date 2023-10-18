package fun.madeby.snake.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import fun.madeby.snake.common.GameManager;
import fun.madeby.snake.config.GameConfig;

public class HudRenderSystem extends EntitySystem {
    private final SpriteBatch batch;
    private final Viewport hudViewport;
    private BitmapFont font;
    private GlyphLayout layout = new GlyphLayout();

    public HudRenderSystem(SpriteBatch batch, Viewport hudViewport, BitmapFont hudFont) {
        this.batch = batch;
        this.hudViewport = hudViewport;
        this.font = hudFont;
    }

    @Override
    public void update(float deltaTime) {
        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);

        GameManager.INSTANCE.updateDisplayScore(deltaTime);

        batch.begin();
        drawHUD();

        batch.end();
    }


    private void drawHUD() {
        String scoreString = "SCORE: " + GameManager.INSTANCE.getDisplayScore();
        String highScoreString = "HIGH-SCORE: " + GameManager.INSTANCE.getDisplayHighScore();

        float allHudTextY = hudViewport.getWorldHeight() - GameConfig.HUD_PADDING;
        float highScoreX = GameConfig.HUD_PADDING;

        layout.setText(font, highScoreString);
        font.draw(batch, layout, highScoreX, allHudTextY);

        layout.setText(font, scoreString);
        float scoreX = hudViewport.getWorldWidth() - (layout.width + GameConfig.HUD_PADDING);

        font.draw(batch, layout, scoreX, allHudTextY);
    }

}
