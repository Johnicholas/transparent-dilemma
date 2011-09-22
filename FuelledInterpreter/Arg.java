
/**
 * A continuation that evals an argument, then plugs it into the body of a function.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Arg implements Continuation
{
    private Expression arg;
    private Environment env;
    private Continuation cont;
    
    /**
     * Constructor
     */
    public Arg(Expression a, Environment e, Continuation k)
    {
        arg = a;
        env = e;
        cont = k;
    }

    /**
     * From Continuation
     */
    public Result resume(Object proc, Integer fuel)
    {
        return arg.pump(env, new Plug((Closure)proc, cont), fuel);
    }
}
