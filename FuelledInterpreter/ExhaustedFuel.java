
/**
 * A simple wrapper for three values, since Java doesn't have multiple-return.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class ExhaustedFuel implements Result
{
    private Expression exp;
    private Environment env;
    private Continuation cont;
    
    /**
     * Constructor
     */
    public ExhaustedFuel(Expression x, Environment e, Continuation k)
    {
       exp = x;
       env = e;
       cont = k;
    }

    /**
     * From Result
     */
    public void unpack(ResultVisitor visitor)
    {
        visitor.ExhaustedFuel(exp, env, cont);
    }
}
