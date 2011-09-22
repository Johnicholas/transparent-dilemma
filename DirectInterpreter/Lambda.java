
/**
 * An expression that represents an anonymous function.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Lambda extends Expression
{
    private String name;
    private Expression body;

    /**
     * Constructor
     */
    public Lambda(String n, Expression b)
    {
        name = n;
        body = b;
    }

    /**
     * From Expression.
     * 
     * @param env The environment to eval this lambda-expression in.
     * @return a closure, that can be used to continue on into the body, once we have an actual.
     */
    public Object eval(Environment env)
    {
        return new Closure(name, body, env);
    }
}
