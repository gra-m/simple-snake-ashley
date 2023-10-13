package fun.madeby.snake.screen.game;

import com.badlogic.gdx.ScreenAdapter;

import fun.madeby.SimpleSnakeGame;
import fun.madeby.util.GdxUtils;

public class GameScreen extends ScreenAdapter {
    private final SimpleSnakeGame game;

    public GameScreen(SimpleSnakeGame simpleSnakeGame) {
        this.game = simpleSnakeGame;
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
    }

    @Override
    public void dispose() {
    }
}
