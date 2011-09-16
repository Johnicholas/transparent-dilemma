
/**
 * A runtime value of a lambda expression; all we need is an actual for the formal, and we can go.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Closure
{
    private String formal;
    private Expression body;
    private Environment env;

    /**
     * Constructor
     */
    public Closure(String f, Expression b, Environment e)
    {
        formal= f;
        body= b;
        env= e;
    }

    /**
     * Once we have an actual, we can bind it to the formal and enter the body.
     * 
     * @param actual The actual value of the formal parameter.
     * @return The result of the body, once the actual is bound to the formal.
     */
    public Object enter(Object actual)
    {
        return body.eval(new ExtendEnvironment(formal, actual, env));
    }
}
