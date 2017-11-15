package BackEnd.Command;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord {   //converts Stings to Commands
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), ARREST("arrest"), TALK("talk"), SEARCH("search"), INSPECT("inspect"),DROP("drop"), CONVICT("convict"), LIE("lie"), DRINK("drink"), DRUNKNESS("drunkness");
    private String commandString;

    //contructor
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    //getter
    public String toString() {
        return commandString;
    }
}