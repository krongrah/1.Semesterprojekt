package pkg1st.semesterproject;

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

    //getter for commandWord
    public CommandWord getCommandWord() {
        return commandWord;
    }

    //getter for secondWord
    public String getSecondWord() {
        return secondWord;
    }

    //returns true the command is the unknown command.
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }

    //returns true if the command has a string attached. 
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}
