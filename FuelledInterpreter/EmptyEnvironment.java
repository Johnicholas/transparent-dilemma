
/**
 * An empty environment - if you try to find something in it, there will be an error.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class EmptyEnvironment implements Environment
{
    /**
     * Constructor.
     */
    public EmptyEnvironment()
    {
    }

    /**
     * From Environment.
     */
    public Object lookup(String toFind)
    {
        assert false : "Tried to look up '" + toFind + "' in the empty environment.";
        return null;
    }
}
