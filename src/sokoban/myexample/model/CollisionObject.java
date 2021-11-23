package sokoban.myexample.model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction) {
            case LEFT:
                if (((x - Model.FIELD_CELL_SIZE) == gameObject.getX()) && (y == gameObject.getY()))
                    return true;
                break;
            case RIGHT:
                if (((x + Model.FIELD_CELL_SIZE) == gameObject.getX()) && (y == gameObject.getY()))
                    return true;
                break;
            case UP:
                if ((x == gameObject.getX()) && ((y - Model.FIELD_CELL_SIZE) == gameObject.getY()))
                    return true;
                break;
            case DOWN:
                if ((x == gameObject.getX()) && ((y + Model.FIELD_CELL_SIZE) == gameObject.getY()))
                    return true;
                break;
        }
        return false;
    }
}
