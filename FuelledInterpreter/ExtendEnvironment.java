
/**
 * A nonempty environment, extending another environment, binding a name to a value.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class ExtendEnvironment implements Environment
{
    private String name;
    private Object value;
    private Environment env;
    
    /**
     * Constructor
     */
    public ExtendEnvironment(String n, Object v, Environment e)
    {
        name = n;
        value = v;
        env = e;
    }

    /**
     * From Environment.
     */
    public Object lookup(String toFind)
    {
        if (toFind.equals(name)) {
            return value;
        } else {
            return env.lookup(toFind);
        }
    }
}
