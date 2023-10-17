package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import fun.madeby.snake.common.Direction;

public class DirectionComponent implements Component, Pool.Poolable {

    private static final Direction START_AND_RESET_DIRECTION = Direction.RIGHT;
    public Direction direction = START_AND_RESET_DIRECTION;


    public boolean isUp() {return direction.isUp();}
    public boolean isDown() {return direction.isDown();}
    public boolean isLeft() {return direction.isLeft();}
    public boolean isRight() {return direction.isRight();}
    
    @Override
    public void reset() {
        this.direction = START_AND_RESET_DIRECTION;

    }
}
