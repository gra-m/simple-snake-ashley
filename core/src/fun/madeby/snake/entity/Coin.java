package fun.madeby.snake.entity;

import fun.madeby.snake.config.GameConfig;

public class Coin extends EntityBase{
    private boolean availableToEat;

    public Coin() {
        super.setSize(GameConfig.COIN_SIZE, GameConfig.COIN_SIZE);
    }

    public boolean isAvailableToEat() {
        return availableToEat;
    }

    public void setAvailableToEat(boolean isAvailableToEat) {
        this.availableToEat = isAvailableToEat;
    }
}
