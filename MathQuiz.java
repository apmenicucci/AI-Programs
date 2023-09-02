import java.util.Random;
import java.util.Scanner;

public class MathQuiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Math Quiz!");

        int correctAnswers = 0;
        int totalQuestions = 5; // number of questions

        for (int i = 1; i <= totalQuestions; i++) {
            int num1 = random.nextInt(10); // generate random numbers between 0 and 9 
            int num2 = random.nextInt(10);

            int correctAnswer = num1 + num2;

            System.out.println("\nQuestion " + i + ":");
            System.out.print(num1 + " + " + num2 + " = ? ");

            int userAnswer = scanner.nextInt();

            if (userAnswer == correctAnswer) {
                System.out.println("Correct! Good job.");
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The correct answer is " + correctAnswer);
            }
        }

        System.out.println("\nQuiz completed!");
        System.out.println("You got " + correctAnswers + " out of " + totalQuestions + " questions correct.");
        double percentage = (double) correctAnswers / totalQuestions * 100;
        System.out.println("Your score: " + percentage + "%");

        scanner.close();
    }
}
