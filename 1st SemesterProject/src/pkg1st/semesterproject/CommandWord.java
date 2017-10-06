package pkg1st.semesterproject;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord 
{   //converts commands to string
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?");
    
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
