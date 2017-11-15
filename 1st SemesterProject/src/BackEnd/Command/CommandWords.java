package BackEnd.Command;

import java.util.HashMap;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class CommandWords {

    private HashMap<String, CommandWord> validCommands;

    //constructor, places Commandwords in the validCommands map
    public CommandWords() {
        validCommands = new HashMap<String, CommandWord>();
        for (CommandWord command : CommandWord.values()) {

            switch (command) {
                case UNKNOWN:
                    break;
                case LIE:
                    break;
                case CONVICT:
                    break;
                default:
                    validCommands.put(command.toString(), command);
            }
        }
    }

    //getter
    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        if (command != null) {
            return command;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    //returns true if aString is a command
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    //prints all commands
    public void showAll() {
        for (String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }

    public void addFinishers() {
        for (CommandWord command : CommandWord.values()) {

            switch (command) {
                case LIE:
                    validCommands.put(command.toString(), command);
                    break;
                case CONVICT:
                    validCommands.put(command.toString(), command);
                    break;
                default:
                    break;
            }
        }
    }
}