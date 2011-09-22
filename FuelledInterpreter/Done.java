
/**
 * Done is a continuation that just returns anything that it is handed.
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
    public Result resume(Object value, Integer fuel_remaining)
    {
        return new FinishedSuccessfully(value, fuel_remaining);
    }
}
