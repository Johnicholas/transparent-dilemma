
/**
 * An expression representing the application of a function to an argument.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Application extends Expression
{
    private Expression fun;
    private Expression arg;

    /**
     * Constructor.
     */
    public Application(Expression f, Expression a)
    {
        fun = f;
        arg = a;
    }

    /**
     * To eval an expression, first eval the function, then eval the arg, then plug the arg-val into the fun-val.
     */
    public Object eval(Environment env, Continuation k)
    {
        return fun.eval(env, new Arg(arg, env, k));
    }
}
