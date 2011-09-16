
/**
 * A closure is something like a half-finished function application.
 * It has the formal parameter name, the environment, and the function body,
 * but it doesn't have the actual parameter.
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
        formal = f;
        body = b;
        env = e;
    }

    /**
     * Once we have an actual, we can enter the body and eval it.
     */
    public Object enter(Object actual, Continuation k)
    {
        return body.eval(new ExtendEnvironment(formal, actual, env), k);
    }
}
