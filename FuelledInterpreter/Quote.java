
/**
 * An expression that always returns the same thing.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Quote extends Expression
{
    private Object quoted;   

    /**
     * Constructor
     */
    public Quote(Object q)
    {
        quoted = q;
    }

    /**
     * From Expression.
     */
    public Result eval(Environment env, Continuation k, Integer fuel)
    {
        return k.resume(quoted, fuel);
    }
}
