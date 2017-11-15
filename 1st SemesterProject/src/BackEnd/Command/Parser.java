package BackEnd.Command;


import java.util.Scanner;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser {

    private CommandWords commands;
    private Scanner reader;

    //contructor, generates a CommandWords and a scanner
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    //commented throughout
    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");
        //gets string from the player
        inputLine = reader.nextLine();
        //splits the string into one or two words
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }
        //returns a the command derived from the string, through the command class
        return new Command(commands.getCommandWord(word1), word2);
    }

    //prints all Commands
    public void showCommands() {
        commands.showAll();
    }

    public void addFinishers() {
        commands.addFinishers();
    }
}
