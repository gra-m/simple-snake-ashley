package fun.madeby.snake.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Logger;

import fun.madeby.snake.common.GameManager;
import fun.madeby.snake.config.GameConfig;
import fun.madeby.snake.entity.BodyPart;
import fun.madeby.snake.entity.Coin;
import fun.madeby.snake.entity.Direction;
import fun.madeby.snake.entity.Snake;
import fun.madeby.snake.entity.SnakeHead;

public class GameController {

    private static final Logger LOG = new Logger(GameController.class.getName(), Logger.DEBUG);
    private final CollisionListener collisionListener;

    private Snake snake;
    private Coin coin;
    private float timer;

    public GameController(CollisionListener collisionListener) {
        this.snake = new Snake();
        this.coin = new Coin();
        this.collisionListener = collisionListener;
        restart();
    }

    public void update(float delta) {
        if (GameManager.INSTANCE.isPlaying()) {
            getDirectionAtThisTickFromInput();
            checkIfManualDebugCoinAddition();
            timer += delta;
            if (timer >= GameConfig.NORMAL_MOVES_EVERY.every) {
                timer = 0;
                // GameControl moving head on every timer threshold pass
                snake.move();

                checkOutOfBounds();
                checkCollision();
                GameManager.INSTANCE.update(delta);

            }
            spawnCoin();
        } else {
            checkForRestart();
        }
    }

    private void checkForRestart() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            restart();
        } else if (Gdx.input.isTouched()) {
            restart();
        }
        // android check screen touched.

    }

    private void restart() {
        GameManager.INSTANCE.reset();
        snake.reset();
        coin.setAvailableToEat(false);
        timer = 0;
    }

    private void checkIfManualDebugCoinAddition() {
        if(Gdx.input.isKeyPressed(Input.Keys.N)) {
            snake.insertNewBodyPart();
        }
    }

    /**
     * Collision type1: head/coin if collision with coin (head eats coin) insert new bodypart and set
     * coin to notAvailableToEat.
     */
    private void checkCollision() {
        // head/coin collision
        SnakeHead head = snake.getHead();
        Rectangle headBounds = head.getBoundsThatAreUsedForCollisionDetection();
        Rectangle coinBounds = coin.getBoundsThatAreUsedForCollisionDetection();

        if (Intersector.overlaps(headBounds, coinBounds) && coin.isAvailableToEat()) {
            snake.insertNewBodyPart();
            coin.setAvailableToEat(false);
            collisionListener.hitCoin();
            GameManager.INSTANCE.incrementScore(GameConfig.VOTES_SHOULD_ONLY_BE_INTEGERS);
        }

        // head/body part collision
        for (BodyPart bp : snake.getBodyParts()) {
            if (bp.getJustSpawnedAtHeadNoCollisionPossible()) {
                bp.setJustSpawnedAtHeadNoCollisionPossible(false);
                continue;
            }
            Rectangle bodyPartBounds = bp.getBoundsThatAreUsedForCollisionDetection();
            if (Intersector.overlaps(headBounds, bodyPartBounds)) {
                LOG.debug("Collision with bodypart! ITS Overrrrr!");
                collisionListener.lose();
                GameManager.INSTANCE.setGameOver();
            }
        }
    }

    private void checkOutOfBounds() {
        SnakeHead head = snake.getHead();
        if (head.getX() >= GameConfig.WORLD_WIDTH) {
            head.setX(0);
        } else if (head.getX() < 0)
            head.setX(GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SIZE);

        if (head.getY() >=  GameConfig.Y_CONSTRAINED) {
            head.setY(0);
        } else if (head.getY() < 0)
            head.setY(GameConfig.Y_CONSTRAINED - GameConfig.SNAKE_SIZE);

    }

    private void getDirectionAtThisTickFromInput() {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (leftPressed)
            snake.setDirection(Direction.LEFT);
        else if (rightPressed)
            snake.setDirection(Direction.RIGHT);
        else if (upPressed)
            snake.setDirection(Direction.UP);
        else if (downPressed)
            snake.setDirection(Direction.DOWN);



    }

    public Snake getSnake() {
        return snake;
    }

    public Coin getCoin() {
        return coin;
    }

    public void spawnCoin() {
        if(!coin.isAvailableToEat()) {
            float coinX = MathUtils.random((int) (GameConfig.WORLD_WIDTH - GameConfig.COIN_SIZE));
            float coinY = MathUtils.random((int) (GameConfig.Y_CONSTRAINED - GameConfig.COIN_SIZE));
            coin.setAvailableToEat(true);

            coin.setPosition(coinX, coinY);

        }

    }
}
