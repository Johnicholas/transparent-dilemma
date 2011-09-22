
/**
 * Almost everything needed to eval the body of a function - everything but the value to plug in.
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
     * Once we have an actual, we can enter the body of the function, binding it to the formal.
     * 
     * @param actual The actual value of the formal parameter, that we were waiting for.
     * @return the result of evalling the body, followed by running the continuation on it.
     */
    public Result enter(Object actual, Continuation k, Integer fuel)
    {
        return body.pump(new ExtendEnvironment(formal, actual, env), k, fuel);
    }
}
