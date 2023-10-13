package fun.madeby.snake.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import fun.madeby.SimpleSnakeGame;

public class GameManager {
    public static final GameManager INSTANCE = new GameManager();

    private GameState state = GameState.READY;

    private static final String HIGH_SCORE_KEY = "highscore";
    public boolean isReady(){return state.isReady();}
    public boolean isPlaying(){return state.isPlaying();}
    public boolean isGameOver(){return state.isGameOver();}
    private int score;
    private int displayScore;
    private int highScore;
    private int displayHighScore;
    private Preferences prefs;

    private GameManager(){
        prefs = Gdx.app.getPreferences( SimpleSnakeGame.class.getSimpleName() );
        highScore = prefs.getInteger(HIGH_SCORE_KEY, 0);
        displayHighScore = highScore;
    }

    public void setPlaying() {
        state = GameState.PLAYING;}
    public void setGameOver() {
        state = GameState.GAME_OVER;}


    public int getDisplayScore() {
        return displayScore;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }

    public void incrementScore(int amount) {
        score += amount;
        if(score >= highScore) {
            highScore = score;
        }
    }

    public void updateHighScore() {
        if (score > highScore) {
            prefs.putInteger( HIGH_SCORE_KEY, score );
            prefs.flush(); // save prefs
        }
    }

    public void update(float delta) {
        smoothDisplayScores(delta);
    }

    public void reset() {
        updateHighScore();
        setPlaying();
        score = 0;
        displayScore = 0;
    }

    public void smoothDisplayScores(float delta) {
        // stops jumps of say 20 directly hitting displays, drip feeds score increases.
        if(displayScore < score) {
            displayScore = Math.min(score, (displayScore + (int) (100 * delta)) );
        }

        if(displayHighScore < score) {
            displayHighScore = Math.min(score, (displayHighScore + (int) (100 * delta)) );
        }


    }


}
