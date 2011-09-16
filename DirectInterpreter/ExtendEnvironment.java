
/**
 * A nonempty environment, extending a previous environment binding one name to one value.
 * 
 * @author Johnicholas Hines 
 * @version Thursday, 9/22/2011
 */
public class ExtendEnvironment implements Environment
{
    private String name;
    private Object value;
    private Environment nextEnvironment;

    /**
     * Constructor
     */
    public ExtendEnvironment(String n, Object v, Environment e)
    {
        name= n;
        value= v;
        nextEnvironment= e;
    }

    /**
     * Look up something by checking if this object should handle it, and delegating if not.
     * 
     * @param toFind the name of the formal parameter that we're trying to look up.
     * @return the associated value 
     */
    public Object lookup(String toFind)
    {
        if (toFind.equals(name)) {
            return value;
        } else {
            return nextEnvironment.lookup(toFind);
        }
    }
}
