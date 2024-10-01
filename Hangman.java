import java.util.*;
import java.io.*;

public class Hangman {
    private List<String> dictionary;
    private String answer;
    private char[][] board;
    private char[] correctLetters;

    public Hangman(List<String> words) {
        if(words.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.dictionary = new ArrayList<>();
        this.dictionary.addAll(words);
        this.board = new char[][]{{' ',' ',' '},
                                  {' ',' ',' '},
                                  {' ',' ',' '},
                                  {' ',' ',' '},
                                  {' ',' ',' '}};
    }

    public void load() {
        Random randIndex = new Random();
        int randNumb = randIndex.nextInt(dictionary.size());
        answer = dictionary.get(randNumb);
        correctLetters = new char[answer.length()];
        for(int i = 0; i < correctLetters.length; i++) {
          correctLetters[i] = '_';
        }
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                result += board[i][j] + " ";
            }

            result += "\n";
        }

        for(int i = 0; i < correctLetters.length; i++) {
            result += correctLetters[i] + " ";
        }
        return result;
    }

    public boolean gameOver(){
        if(board[4][2] == '_') {
            System.out.println("You ran out of Guesses :( \n The word was : " + answer + ", try again later");
            return true;
        } else{
            for(int i = 0; i < correctLetters.length; i++) {
                if(correctLetters[i] == '_') {
                    return false;
                }
            }
        }
        System.out.println("You Found the Word, Congratulations! The word was : " + answer);
        return true;
    }

    public void guess(Scanner input) {
        String guess = input.nextLine().toUpperCase();
        if(answer.equals(guess)) {
            System.out.println("You Guessed the Word, Congratulations! The word was : " + answer);
            for(int i = 0; i < answer.length(); i++ ) {
                correctLetters[i] = answer.charAt(i);
            }
        } else if(guess.length() > 1) {
            System.out.println("Oops, Wrong word, be careful");
            if(board[0][1] == ' ') {
                board[0][1] = 'O';
                board[1][0] = '/';
                board[1][1] = '|';
            } else if (board[1][1] == ' ') {
                board[1][0] = '/';
                board[1][1] = '|';
                board[1][2] = '\\';
            } else if (board[1][0] == ' ') {
                board[1][1] = '|';
                board[1][2] = '\\';
                board[2][1] = '|';
            } else if (board[1][2] == ' ') {
                board[1][2] = '\\';
                board[2][1] = '|';
                board[3][0] = '|';
            } else if (board[2][1] == ' ') {
                board[2][1] = '|';
                board[3][0] = '|';
                board[3][2] = '|';
            } else if(board[3][0] == ' ') {
                board[3][0] = '|';
                board[3][2] = '|';
                board[4][0] = '_';
            } else if (board[3][2] == ' ') {
                board[3][2] = '|';
                board[4][0] = '_';
                board[4][2] = '_';
            } else if (board[4][0] == ' ') {
                board[4][0] = '_';
                board[4][2] = '_';
            } else if (board[4][2] == ' ') {
                board[4][2] = '_';
            }
        }else if(answer.contains(guess.toUpperCase())) {
            List<Integer> correct = locateGuess(answer, guess);
            char letter = guess.charAt(0);
            for(int i = 0; i < correct.size(); i++) {
                correctLetters[correct.get(i)] = letter;
            }
        } else {
                if(board[0][1] == ' ') {
                    board[0][1] = 'O';
                } else if (board[1][1] == ' ') {
                    board[1][1] = '|';
                } else if (board[1][0] == ' ') {
                    board[1][0] = '/';
                } else if (board[1][2] == ' ') {
                    board[1][2] = '\\';
                } else if (board[2][1] == ' ') {
                    board[2][1] = '|';
                } else if(board[3][0] == ' ') {
                    board[3][0] = '|';
                } else if (board[3][2] == ' ') {
                    board[3][2] = '|';
                } else if (board[4][0] == ' ') {
                    board[4][0] = '_';
                } else if (board[4][2] == ' ') {
                    board[4][2] = '_';
                }
            }
    }

    private List<Integer> locateGuess (String word, String guess) {
        List<Integer> correctIndeces = new ArrayList<>();
        for(int i = 0; i < word.length(); i++) {
            if(("" + word.charAt(i)).equals(guess)) {
                correctIndeces.add(i);
            }
        }
        return correctIndeces;
    }
}