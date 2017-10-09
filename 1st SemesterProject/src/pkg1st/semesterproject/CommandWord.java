package pkg1st.semesterproject;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord 
{   //converts Stings to Commands
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), ACCUSE("accuse"), TALK("talk"), SEARCH("search");
    private String commandString;
    
    //setter
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    //getter
    public String toString()
    {
        return commandString;
    }
}
