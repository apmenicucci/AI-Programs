public class ColorfulHelloWorld {
    public static void main(String[] args) {
        // ANSI escape codes for text color
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        String purple = "\u001B[35m";

        System.out.println(red + "H" + green + "e" + yellow + "l" + blue + "l" + purple + "o" + reset + ", " + red + "W" + green + "o" + yellow + "r" + blue + "l" + purple + "d" + reset + "!");
    }
}
