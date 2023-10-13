package fun.madeby.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import fun.madeby.snake.common.Direction;

public class DirectionComponent implements Component, Pool.Poolable {
    public Direction direction = Direction.LEFT;

    public boolean isUp() {return direction.isUp();}
    public boolean isDown() {return direction.isDown();}
    public boolean isLeft() {return direction.isLeft();}
    public boolean isRight() {return direction.isRight();}
    
    @Override
    public void reset() {
        this.direction = Direction.LEFT;

    }
}
