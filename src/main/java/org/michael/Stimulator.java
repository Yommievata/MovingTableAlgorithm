package org.michael;

import java.util.Scanner;

public class Stimulator {
    private static Table table;
    private static Movement movement;

    public Stimulator(Table table, Movement movement) {
        this.table = table;
        this.movement = movement;
    }
    public static Movement getMovement() {
        return movement;
    }

    public static Table getTable() {
        return table;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        readInitialConfiguration(scanner);

        while (true) {
            System.out.println("Enter a command (0 to quit):");
            int command = scanner.nextInt();

            if (command == 0) {
                System.out.println("Exiting Moving Object Simulator. Goodbye!");
                break;
            }

            processCommand(command);
        }

        int[] finalPosition = movement.getCurrentPosition();
        System.out.println("Final position: [" + finalPosition[0] + ", " + finalPosition[1] + "]");

        scanner.close();
    }

    private static void readInitialConfiguration(Scanner scanner) {
        System.out.println("Enter the initial table configuration (width height x y + direction):");
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        table = new Table(width, height);

        int[] initialPosition = new int[]{scanner.nextInt(), scanner.nextInt()};
        int[] initialDirection = getDirectionVector(scanner.next().toLowerCase());

        movement = new Movement(initialPosition, initialDirection);
    }

    static void processCommand(int command) {
        switch (command) {
            case 1 -> moveForward();
            case 2 -> moveBackward();
            case 3 -> rotateClockwise();
            case 4 -> rotateCounterClockwise();
            default ->
                    System.out.println("Invalid command. Please enter 0 to quit, 1 to move forward, 2 to move backward, 3 to rotate clockwise, or 4 to rotate counterclockwise.");
        }

        int[] currentPosition = movement.getCurrentPosition();
        System.out.println("Current position: [" + currentPosition[0] + ", " + currentPosition[1] + "]");
        System.out.println("Current direction: " + getDirectionString());
    }

    private static void moveForward() {
        int[] newPosition = {movement.getCurrentPosition()[0] + movement.getDirectionVector()[0],
                movement.getCurrentPosition()[1] + movement.getDirectionVector()[1]};
        validatePosition(newPosition);

        movement.updatePosition(newPosition);
    }

    private static void moveBackward() {
        int[] newPosition = {movement.getCurrentPosition()[0] - movement.getDirectionVector()[0],
                movement.getCurrentPosition()[1] - movement.getDirectionVector()[1]};
        validatePosition(newPosition);

        movement.updatePosition(newPosition);
    }

    private static void rotateClockwise() {
        int[] newDirection = {-movement.getDirectionVector()[1], movement.getDirectionVector()[0]};
        movement.updateDirection(newDirection);
    }

    private static void rotateCounterClockwise() {
        int[] newDirection = {movement.getDirectionVector()[1], -movement.getDirectionVector()[0]};
        movement.updateDirection(newDirection);
    }

    private static void validatePosition(int[] position) {
        position[0] = Math.min(Math.max(position[0], 0), table.width() - 1);
        position[1] = Math.min(Math.max(position[1], 0), table.height() - 1);
    }

    private static int[] getDirectionVector(String direction) {
        switch (direction) {
            case "north" -> {
                return new int[]{0, -1};
            }
            case "east" -> {
                return new int[]{1, 0};
            }
            case "south" -> {
                return new int[]{0, 1};
            }
            case "west" -> {
                return new int[]{-1, 0};
            }
            default -> throw new IllegalArgumentException("Invalid initial direction. Please enter north/east/south/west.");
        }
    }

    static String getDirectionString() {
        int[] directionVector = movement.getDirectionVector();
        if (directionVector[0] == 0 && directionVector[1] == -1) {
            return "north";
        } else if (directionVector[0] == 1 && directionVector[1] == 0) {
            return "east";
        } else if (directionVector[0] == 0 && directionVector[1] == 1) {
            return "south";
        } else if (directionVector[0] == -1 && directionVector[1] == 0) {
            return "west";
        } else {
            return "unknown";
        }
    }
}