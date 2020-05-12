import java.util.Scanner;

public class FractionCalc {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Enter first fraction, followed by operation, and then next fraction, like 1,2,+,1,3. Enter q to quit.");
            String n1 = input.next();
            if(checkIfQuit(n1)) { break; }
            while(!checkIfInt(n1)) {
                System.out.println("That wasn't a number, try again: ");
                n1 = input.next();
            }
            String d1 = input.next();
            if(checkIfQuit(d1)) { break; }
            while(!checkIfInt(d1)) {
                System.out.println("That wasn't a number, try again: ");
                d1 = input.next();
            }
            String op = input.next();
            if(checkIfQuit(op)) { break; }
            while(!checkOperator(op)) {
                System.out.println("That wasn't a valid operator, enter +, -, *, or /.");
                op = input.next();
            }
            String n2 = input.next();
            if(checkIfQuit(n2)) { break; }
            while(!checkIfInt(n2)) {
                System.out.println("That wasn't a number, try again: ");
                n2 = input.next();
            }
            String d2 = input.next();
            if(checkIfQuit(d2)) { break; }
            while(!checkIfInt(d2)) {
                System.out.println("That wasn't a number, try again: ");
                d2 = input.next();
            }
            Fraction one = new Fraction(Integer.parseInt(n1), Integer.parseInt(d1));
            Fraction two = new Fraction(Integer.parseInt(n2), Integer.parseInt(d2));
            System.out.println("The numbers you have entered are: " + one.toString() + " " + two.toString());
            switch (op) {
                case "+":
                    System.out.println("Addition: " + one.add(two).toString());
                    break;
                case "-":
                    System.out.println("Subtraction: " + one.sub(two).toString());
                    break;
                case "*":
                    System.out.println("Multiplication: " + one.mul(two).toString());
                    break;
                case "/":
                    System.out.println("Division: " + one.div(two).toString());
                    break;
            }
            System.gc();
        }
        System.out.print("Bye!");
    }
    private static boolean checkIfQuit(String input) {
        if(input.equals("Q") || input.equals("q")) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean checkIfInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    private static boolean checkOperator(String input) {
        if(input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/")) {
            return true;
        } else {
            return false;
        }
    }
}