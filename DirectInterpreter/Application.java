
/**
 * An expression that is a function application.
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
     * To eval a function application, eval both the function an arg,
     * then enter the function's value (which should be a closure),
     * carrying the arg's value.
     * 
     * @param the environment to evaluate in
     * @return the result of applying fun to arg, in the given environment.
     */
    public Object eval(Environment env)
    {
        Closure fun_val = (Closure)(fun.eval(env));
        Object arg_val = arg.eval(env);
        return fun_val.enter(arg_val);
    }
}
