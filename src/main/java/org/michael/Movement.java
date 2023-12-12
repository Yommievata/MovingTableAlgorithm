package org.michael;

public class MoveObject {
    private int[] currentPosition;
    private int[] directionVector;

    public MoveObject(int[] initialPosition, int[] initialDirection) {
        this.currentPosition = initialPosition;
        this.directionVector = initialDirection;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public int[] getDirectionVector() {
        return directionVector;
    }

    public void updatePosition(int[] newPosition) {
        currentPosition = newPosition;
    }

    public void updateDirection(int[] newDirection) {
        directionVector = newDirection;
    }
}