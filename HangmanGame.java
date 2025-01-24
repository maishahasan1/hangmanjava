import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;

public class HangmanGame {

    public static void main(String[] args) {
        // List of possible words
        String[] wordList = {
            "programming", "java", "computer", "algorithm", "developer",
            "software", "debugging", "function", "variable", "compile"
        };

        // Generate a random word
        Random random = new Random();
        String word = wordList[random.nextInt(wordList.length)];

        char[] guessedWord = new char[word.length()];
        HashSet<Character> guessedLetters = new HashSet<>();
        int attemptsLeft = 6; // Number of incorrect attempts allowed

        // Initialize the guessed word with underscores
        for (int i = 0; i < word.length(); i++) {
            guessedWord[i] = '_';
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        System.out.println("Guess the word:");

        while (attemptsLeft > 0) {
            System.out.println("\nWord: " + String.valueOf(guessedWord));
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase();

            // Validate input
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input! Please enter a single letter.");
                continue;
            }

            char guessedLetter = input.charAt(0);

            // Check if the letter was already guessed
            if (guessedLetters.contains(guessedLetter)) {
                System.out.println("You already guessed that letter!");
                continue;
            }

            guessedLetters.add(guessedLetter);

            // Check if the guessed letter is in the word
            if (word.contains(String.valueOf(guessedLetter))) {
                System.out.println("Good guess!");

                // Update the guessed word
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guessedLetter) {
                        guessedWord[i] = guessedLetter;
                    }
                }
            } else {
                System.out.println("Wrong guess!");
                attemptsLeft--;
            }

            // Check if the player has guessed the entire word
            if (String.valueOf(guessedWord).equals(word)) {
                System.out.println("\nCongratulations! You've guessed the word: " + word);
                break;
            }
        }

        // If the player runs out of attempts
        if (attemptsLeft == 0) {
            System.out.println("\nGame over! The word was: " + word);
        }

        scanner.close();
    }
}
