import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class battleshipGame {
    ArrayList<ArrayList<String>> ocean = new ArrayList<>();

    public battleshipGame() {

        for (int r = 0; r < 6; r++) {
            ArrayList<String> row = new ArrayList<>();
            for (int c = 0; c < 6; c++) {
                row.add(" ");
            }
            ocean.add(row);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        battleshipGame bg = new battleshipGame();
        bg.playerAddShips();
        bg.computerAddShips();
	    bg.displayOcean();
	    while (true) {
	        bg.playerFire();
	        if (bg.shipsRemaining("$") == 0) {
	            System.out.println("You won - hooray!");
	            break;
            }
	        bg.computerFire();
            if (bg.shipsRemaining("#") == 0) {
                System.out.println("Computer won!");
                break;
            }
        }
    }

    public void playerAddShips() {
        System.out.println("Welcome to battle ship game! The ocean is represented by a 6x6 matrix. You will deploy 3 ships to any coordinate and computer will do the same. " +
                "Then you will fire shots by entering coordinates, with the objective of sinking all of computer ships, while computer will try doing the same to you. " +
                "Your ships are represented with # on the map, your shots are marked with x and computer's shots are marked with o. You won't see the computer ships on the map, " +
                "you have to guess where they are. Begin the game by deploy your ships on the ocean.");
        int x; int y;
        for (int i = 0; i < 3; i++) {
            int[] coordinates = generateInput();
            x = coordinates[0];
            y = coordinates[1];
            if (!checkIfCharPresentAtCurrentLocation(x, y, "#")) {
                ocean.get(y).set(x, "#");
                System.out.println("A ship has been successfully deployed.");
            } else {
                System.out.println("You already have a ship on this coordinate, try again.");
                i--;
            }
        }
    }

    public void computerAddShips() {
        Random rand = new Random();
        int x; int y;
        for (int i = 0; i < 3; i++) {
            x = rand.nextInt(6);
            y = rand.nextInt(6);
            while (!checkIfCharPresentAtCurrentLocation(x, y, " ")) {
                x = rand.nextInt(6);
                y = rand.nextInt(6);
            }
            ocean.get(x).set(y, "$");
        }
        System.out.println("The computer has deployed it's ships.");
    }

    public boolean checkIfCharPresentAtCurrentLocation(int x, int y, String type) {
        if (ocean.get(y).get(x) == type) {
            return true;
        }
        else { return false; }
    }

    public void playerFire() {
        System.out.println("Its your turn to fire.");
        int[] coordinates = generateInput();
        int x = coordinates[0];
        int y = coordinates[1];

        if (checkIfCharPresentAtCurrentLocation(x, y, "x")) {
            System.out.println("You have already fired at those coordinates, thus wasted your time.");
        }
        else if (checkIfCharPresentAtCurrentLocation(x, y, "$")) {
            ocean.get(y).set(x, "x");
            System.out.println("Boom! You sunk one of computer's ships, congratulations! Computer has " + shipsRemaining("$") + " ships left.");
        }
        else if (checkIfCharPresentAtCurrentLocation(x, y, "#")) {
            ocean.get(y).set(x, "x");
            System.out.println("You have sunk your own ship!! You have " + shipsRemaining("#") + " ships left.");
        }
        else {
            System.out.println("You missed.");
            ocean.get(y).set(x, "x");
        }
    }

    public void computerFire() throws InterruptedException {
        System.out.print("Computer is firing...   ");
        TimeUnit.SECONDS.sleep(1);
        Random rand = new Random();
        int x; int y;
        x = rand.nextInt(6);
        y = rand.nextInt(6);
        while (checkIfCharPresentAtCurrentLocation(x, y, "o") || (checkIfCharPresentAtCurrentLocation(x, y, "x"))) {
            x = rand.nextInt(6);
            y = rand.nextInt(6);
        }
        if (checkIfCharPresentAtCurrentLocation(x, y, "$")) {
            ocean.get(y).set(x, "o");
            System.out.println("Computer just sunk his own ship! Computer has " + shipsRemaining("$") + " ships left.");
        }
        else if (checkIfCharPresentAtCurrentLocation(x, y, "#")) {
            ocean.get(y).set(x, "o");
            System.out.println("Computer has sunk one of your ships! You have " + shipsRemaining("#") + " ships left.");
        }
        else {
            System.out.println("Computer missed.");
            ocean.get(y).set(x, "o");
        }
        displayOcean();
    }

    public int shipsRemaining(String type) {
        int counter = 0;
        for (int r = 0; r < ocean.size(); r++) {
            for (int c = 0; c < ocean.get(r).size(); c++) {
                if (ocean.get(c).get(r) == type) {
                    counter++;
                }
            }
        }
        return counter;
    }
    public static int[] generateInput() {
        Scanner input = new Scanner(System.in);
        int x = 100; int y = 100;
        while (true) {
            System.out.print("Enter X coordinate: ");
            try {
                x = input.nextInt();
            } catch (InputMismatchException e) {
                input.next();
            }
            if (x < 0 || x > 5) {
                System.out.println("You must enter a digit 0 to 5, try again.");
            } else { break; }
        }

        while (true) {
            System.out.print("Enter Y coordinate: ");
            try {
                y = input.nextInt();
            } catch (InputMismatchException e) {
                input.next();
            }
            if (y < 0 || y > 5) {
                System.out.println("You must enter a digit 0 to 5, try again.");
            } else { break; }
        }
        int[] coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }

    public void displayOcean() {
        System.out.println();
        System.out.print("   ");
        for (int r = 0; r < ocean.size(); r++) {
            System.out.print(r);
        }
        System.out.println();
        for (int r = 0; r < ocean.size(); r++) {
            System.out.print(r + " |");
            for (int c = 0; c < ocean.get(r).size(); c++) {
                if (checkIfCharPresentAtCurrentLocation(c, r, "$")) {
                    System.out.print(" ");
                } else {
                    System.out.print(ocean.get(r).get(c));
                }
            }
            System.out.println("| " + r);
        }
        System.out.print("   ");
        for (int r = 0; r < ocean.size(); r++) {
            System.out.print(r);
        }
        System.out.println();
    }
}
