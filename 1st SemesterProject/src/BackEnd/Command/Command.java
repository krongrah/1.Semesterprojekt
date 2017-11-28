package BackEnd.Command;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Command {   //has two attributes, a commandWord and a matching string

    private CommandWord commandWord;
    private String secondWord;

    //Constructor
    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }
/**
 * Used for processing command words.
 * @return commandWord
 */
    public CommandWord getCommandWord() {
        return commandWord;
    }

    /**
     * getter for secondWord
     * @return secondWord
     */
    public String getSecondWord() {
        return secondWord;
    }
/**
 * returns true if the command is an unknown command.
 * @return (commandWord == CommandWord.UNKNOWN)
 */    
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }
/**
 * returns true if the command has a string attached
 * @return (secondWord != null)
 */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}
