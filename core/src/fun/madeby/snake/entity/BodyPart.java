package fun.madeby.snake.entity;

import fun.madeby.snake.config.GameConfig;

public class BodyPart extends EntityBase{
    private boolean justSpawnedAtHeadNoCollisionPossible = true;

    public BodyPart() {
        super.setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
    }

    public boolean getJustSpawnedAtHeadNoCollisionPossible() {
        return justSpawnedAtHeadNoCollisionPossible;
    }

    public void setJustSpawnedAtHeadNoCollisionPossible(boolean collisionPossible) {
        this.justSpawnedAtHeadNoCollisionPossible = collisionPossible;
    }
}
