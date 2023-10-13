package fun.madeby.snake.entity;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public boolean isUp() {return this == UP;}
    public boolean isDown() {return this == DOWN;}
    public boolean isLeft() {return this == LEFT;}
    public boolean isRight() {return this == RIGHT;}

    public boolean allowed(Direction newDirection) {
        if (this.isUp()) {
            return newDirection != Direction.DOWN;
        }if (this.isDown()) {
            return newDirection != Direction.UP;
        }if (this.isLeft()) {
            return newDirection != Direction.RIGHT;
        }if (this.isRight()) {
            return  newDirection != Direction.LEFT;
        }

        // this cannot happen
        throw new IllegalArgumentException("Direction entered does not exist" + newDirection.toString());

    }
}
