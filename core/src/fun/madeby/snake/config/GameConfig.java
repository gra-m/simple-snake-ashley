package fun.madeby.snake.config;

public class GameConfig {

    private static final float HUD_DEPTH = 2f;

    // pixel width and height the world units actually used to reference the world:
    public static final float WIDTH = 800f;
    public static final float HEIGHT = 480f;
    public static final float WORLD_WIDTH = 25f; // = 32ppu
    public static final float WORLD_HEIGHT = 15f; // 32ppu

    public static final float HUD_WIDTH = 800f;
    public static final float HUD_HEIGHT = 480f;
    public static final float HUD_PADDING = 20f;

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2;
    public static final float WORLD_CENTER_Y  = WORLD_HEIGHT / 2;

    //Snake
    public static final float SNAKE_SIZE = 1f;
    public static final FloatTuple NORMAL_MOVES_EVERY = new NormalMovesEvery();
    //Coin
    public static final float COIN_SIZE = 1f;
    public static final int VOTES_SHOULD_ONLY_BE_INTEGERS = 20;


    public static final float Y_CONSTRAINED = WORLD_HEIGHT - HUD_DEPTH;
    private GameConfig(){}
}
