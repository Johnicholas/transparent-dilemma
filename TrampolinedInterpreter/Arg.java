
/**
 * A continuation that evals the argument of a function, then plugs it into the body.
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
     * From Continuation.
     */
    public Object resume(Object proc)
    {
        // proc had better be a closure
        return arg.eval(env, new Plug((Closure)proc, cont));
    }
}
