package guessergame;

import java.util.InputMismatchException;
import java.util.Scanner;

class Guesser {
    private int guessNum;

    public int guessNumber(int minRange, int maxRange) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Guesser, kindly guess a number between " + minRange + " and " + maxRange + ":");

        while (true) {
            try {
                guessNum = scan.nextInt();
                if (guessNum < minRange || guessNum > maxRange) {
                    System.out.println("Invalid input. Please enter a number within the specified range:");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number:");
                scan.nextLine(); // Clear the input buffer
            }
        }

        return guessNum;
    }
}

class Player {
    private int pguessNum;

    public int guessNumber(int minRange, int maxRange) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Player, kindly guess a number between " + minRange + " and " + maxRange + ":");

        while (true) {
            try {
                pguessNum = scan.nextInt();
                if (pguessNum < minRange || pguessNum > maxRange) {
                    System.out.println("Invalid input. Please enter a number within the specified range:");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number:");
                scan.nextLine(); // Clear the input buffer
            }
        }

        return pguessNum;
    }
}

class Umpire {
    private int numFromGuesser;
    private int numFromPlayer1;
    private int numFromPlayer2;
    private int numFromPlayer3;
    private int roundsPlayed;
    private int minRange;
    private int maxRange;
    private int winningPlayer;
    private int winningNumber;

    public Umpire(int minRange, int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public void collectNumFromGuesser() {
        Guesser g = new Guesser();
        numFromGuesser = g.guessNumber(minRange, maxRange);
    }

    public void collectNumFromPlayer() {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();

        numFromPlayer1 = p1.guessNumber(minRange, maxRange);
        numFromPlayer2 = p2.guessNumber(minRange, maxRange);
        numFromPlayer3 = p3.guessNumber(minRange, maxRange);
    }

    public void compare() {
        if (numFromGuesser == numFromPlayer1) {
            if (numFromGuesser == numFromPlayer2 && numFromGuesser == numFromPlayer3) {
                System.out.println("Game tied! All three players guessed correctly.");
            } else if (numFromGuesser == numFromPlayer2) {
                System.out.println("Player 1 and Player 2 won the game.");
            } else if (numFromGuesser == numFromPlayer3) {
                System.out.println("Player 1 and Player 3 won the game.");
            } else {
                System.out.println("Player 1 won the game.");
            }
            winningPlayer = 1;
            winningNumber = numFromGuesser;
        } else if (numFromGuesser == numFromPlayer2) {
            if (numFromGuesser == numFromPlayer3) {
                System.out.println("Player 2 and Player 3 won the game.");
            } else {
                System.out.println("Player 2 won the game.");
            }
            winningPlayer = 2;
            winningNumber = numFromGuesser;
        } else if (numFromGuesser == numFromPlayer3) {
            System.out.println("Player 3 won the game.");
            winningPlayer = 3;
            winningNumber = numFromGuesser;
        } else {
            System.out.println("Game lost! Try again.");
            winningPlayer = -1; // No winning player
            winningNumber = -1; // Invalid number
        }
    }

    public void resetGuesses() {
        numFromGuesser = 0;
        numFromPlayer1 = 0;
        numFromPlayer2 = 0;
        numFromPlayer3 = 0;
    }

    public void incrementRoundsPlayed() {
        roundsPlayed++;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public int getWinningPlayer() {
        return winningPlayer;
    }

    public int getWinningNumber() {
        return winningNumber;
    }
}

public class LaunchGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the minimum range of numbers:");
        int minRange = scan.nextInt();
        System.out.println("Enter the maximum range of numbers:");
        int maxRange = scan.nextInt();

        Umpire u = new Umpire(minRange, maxRange);
        u.incrementRoundsPlayed();

        do {
            u.collectNumFromGuesser();
            u.collectNumFromPlayer();
            u.compare();
            u.resetGuesses();

            System.out.println("Number of rounds played: " + u.getRoundsPlayed());
            System.out.println("Do you want to play another round? (Y/N)");
            String playAgain = scan.next();

            if (!playAgain.equalsIgnoreCase("Y")) {
                System.out.println("Thank you for playing! Goodbye.");
                break;
            }

            u.incrementRoundsPlayed();
        } while (true);

        if (u.getWinningPlayer() != -1) {
            System.out.println("Winning Player: " + u.getWinningPlayer());
            System.out.println("Winning Number: " + u.getWinningNumber());
        }
    }
}
