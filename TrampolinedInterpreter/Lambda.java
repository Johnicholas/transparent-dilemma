
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
     * Constructor.
     */
    public Lambda(String n, Expression b)
    {
        name = n;
        body = b;
    }

    /**
     * From Expression.
     */
    public Object eval(Environment env, Continuation k)
    {
        return k.resume(new Closure(name, body, env));
    }
}