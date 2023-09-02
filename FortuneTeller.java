import java.util.Scanner;

public class FortuneTeller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Fortune Teller!");
        System.out.println("Please enter your first name: ");
        String name = scanner.nextLine();

        // Count the number of vowels in the name
        int vowelCount = countVowels(name);

        // Determine the fortune based on vowel count
        String fortune = determineFortune(vowelCount);

        // DIsplay the fortune
        System.out.println("Your fortune is that " + fortune);

        scanner.close();
    }

    public static int countVowels(String name) {
        int count = 0;
        for (char c : name.toLowerCase().toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String determineFortune(int vowelCount) {
        switch (vowelCount) {
            case 0:
                return "you will face many challenges.";
            case 1:
                return "you will find happiness in unexpected places.";
            case 2:
                return "a journey awaits you in the near future.";
            case 3:
                return "good tidings will come your way.";
            case 4:
                return "you are destined for greatness!";
            default:
                return "your destiny is uncertain.";
        }
    }
}