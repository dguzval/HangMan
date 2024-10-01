import java.util.*;
import java.io.*;

public class HangmanGame {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        Scanner easyFile = new Scanner(new File("easyLib.txt"));
        Scanner normalFile = new Scanner(new File("normalLib.txt"));
        Scanner hardFile = new Scanner (new File("hardLib.txt"));
        List<String> easyDictionary = fileToList(easyFile);
        List<String> normalDictionary = fileToList(normalFile);
        List<String> hardDictionary = fileToList(hardFile);

        Hangman game = null;

        System.out.println("Hello! Welcome to the game of Hangman!");
        
        System.out.print("What difficulty would you like: (H)ard, (N)ormal, (E)asy or (Q)uit the Game: ");
        String input = console.nextLine().toUpperCase();
        while(!input.equals("Q") && game == null) {
            if(input.equalsIgnoreCase("H")) {
                game = new Hangman(hardDictionary);
            } else if (input.equalsIgnoreCase("N")) {
                game = new Hangman(normalDictionary);
            } else if(input.equalsIgnoreCase("E")) {
                game = new Hangman(easyDictionary);
            } else {
                System.out.println("Wrong Input. Try Again!");
                System.out.print("What difficulty would you like: (H)ard, (N)ormal, (E)asy or (Q)uit the Game: ");
                input = console.nextLine().toUpperCase();
            }
        }

        game.load();

        while(!game.gameOver()) {
            System.out.println(game);
            System.out.print("Guess a letter or if you think you got it, Guess the Whole Word: ");
            game.guess(console);
        }

        System.out.println("Thanks for playing!");
    }

    public static List<String> fileToList (Scanner file) {
        List<String> list = new ArrayList<>();
        while(file.hasNextLine()) {
            String line = file.nextLine();
            list.add(line);
        }
        return list;
    }
}