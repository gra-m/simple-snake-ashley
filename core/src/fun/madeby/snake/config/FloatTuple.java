package fun.madeby.snake.config;

/**
 * An object using this class is in constant motion moving  moves world units every seconds
 */
public abstract class FloatTuple {
    public static float moves;
    public static float every;


    public FloatTuple(float moves, float every) {
        this.moves = moves;
        this.every = every;

    }
}
