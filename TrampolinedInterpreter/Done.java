
/**
 * Done is a continuation that returns whatever it is handed.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Done implements Continuation
{
    /**
     * Constructor
     */
    public Done()
    {
    }

    /**
     * From Continuation.
     */
    public Object resume(Object value)
    {
        return value;
    }
}
