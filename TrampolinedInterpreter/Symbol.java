
/**
 * An expression that looks up a name in the environment.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Symbol extends Expression
{
    private String name;

    /**
     * Constructor.
     */
    public Symbol(String n)
    {
        name = n;
    }

    /**
     * From Expression.
     */
    public Object eval(Environment env, Continuation k)
    {
        return k.resume(env.lookup(name));
    }
}
