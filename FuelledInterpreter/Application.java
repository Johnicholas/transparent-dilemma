
/**
 * An expression that applies a function to an argument.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Application extends Expression
{
    private Expression fun;
    private Expression arg;

    /**
     * Constructor
     */
    public Application(Expression f, Expression a)
    {
        fun = f;
        arg = a;
    }

    /**
     * From Expression.
     */
    public Result eval(Environment env, Continuation k, Integer fuel)
    {
        return fun.pump(env, new Arg(arg, env, k), fuel);
    }
}
