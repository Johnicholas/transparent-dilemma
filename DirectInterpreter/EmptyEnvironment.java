
/**
 * If you try to find anything in this class, there is an assertion error.
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
     * From Environment
     * 
     * @param toFind the name of the formal parameter that we're trying to find.
     * @return 
     */
    public Object lookup(String toFind)
    {
        assert false : "Tried to look up '" + toFind + "' in the empty environment.";
        return null;
    }
}
