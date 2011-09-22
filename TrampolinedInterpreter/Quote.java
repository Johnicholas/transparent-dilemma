
/**
 * An expression that always returns the same value.
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
    public Object eval(Environment env, Continuation k)
    {
        return k.resume(quoted);
    }
}
