package fun.madeby.snake.entity;

import fun.madeby.snake.config.GameConfig;

public class SnakeHead extends EntityBase{


    public SnakeHead(){
        super.setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
    }

    public void updateX(float amount) {
        x += amount;
        updateBoundsForCollisionDetection();
    }
    public void updateY(float amount) {
        y += amount;
        updateBoundsForCollisionDetection();
    }





}
