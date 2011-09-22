
/**
 * An expression that evaluates itself by looking up a name in the environment.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Symbol extends Expression
{
    private String name;

    /**
     * Constructor
     */
    public Symbol(String n)
    {
        name = n;
    }

    /**
     * From Expression.
     * 
     * @param env The environment we will use to look up this object's name in.
     * @return The value associated with this object's name.
     */
    public Object eval(Environment env)
    {
        return env.lookup(name);
    }
}
