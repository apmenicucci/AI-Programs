import java.util.Scanner;

public class RectangleAreaCalculator {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for input
        System.out.println("Welcome to the Rectangle Area Calculator!");
        System.out.print("Enter the length of the rectangle: ");
        double length = scanner.nextDouble();
        System.out.print("Enter the width of the rectangle: ");
        double width = scanner.nextDouble();
        System.out.print("Enter the unit of measurement: ");
        String units = scanner.next();

        // Calculate the area of the rectangle
        double area = length * width;

        // Display the result
        System.out.println("The area of the rectangle is " + area + units);

        // Close the scanner
        scanner.close();
    }
}
