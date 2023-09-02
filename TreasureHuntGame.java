// This game is based on if/else statements

import java.util.Random;
import java.util.Scanner;

public class TreasureHuntGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Treasure Hunt Game!");
        System.out.println("You are in front of a dark and mysterious cave...");

        // Generate a random number (1 or 2) to determine the cave entrance
        int caveEntrance = random.nextInt(2) + 1;
        System.out.println("Choose the cave entrance (1 or 2):");
        int userChoice = scanner.nextInt();

        if (userChoice == caveEntrance) {
            System.out.println("You enter the cave...");

            // Generate a random number (1 or 2) to determine the next choice
            int nextChoice = random.nextInt(2) + 1;

            if (nextChoice == 1) {
                System.out.println("You encounter a dragon!");
                System.out.println("Choose to 'fight' or 'run':");
                scanner.nextLine(); // Consume the newline
                String action = scanner.nextLine().toLowerCase();

                if (action.equals("fight")) {
                    System.out.println("You bravely fight the dragon and defeat it!");
                    System.out.println("Congratulations! You find the hidden treasure!");
                } else {
                    System.out.println("You run away in fear. The dragon guards the treasure.");
                    System.out.println("Game Over!");
                }
            } else {
                System.out.println("You find a treasure chest!");
                System.out.println("Do you want to 'open' it or 'leave' it?");
                scanner.nextLine(); // Consume the newline
                String action = scanner.nextLine().toLowerCase();

                if (action.equals("open")) {
                    System.out.println("You open the chest and discover a pile of gold!");
                    System.out.println("Congratulations! You find the hidden treasure!");
                } else {
                    System.out.println("You leave the chest behind.");
                    System.out.println("Game Over!");
                }
            }
        } else {
            System.out.println("You chose the wrong cave entrance.");
            System.out.println("Game Over!");
        }

        scanner.close();
    }
}
