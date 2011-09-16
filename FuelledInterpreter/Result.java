
/**
 * The result of a fuelled computation could be 'done successfully' and a value,
 * or it could be 'exhausted the fuel available', and sufficient information to restart.
 * 
 * This interface models those two options.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public interface Result
{
    /**
     * Unpack the result, calling the appropriate method on a prepared object.
     * 
     * @param v A visitor that is prepared for either scenario.
     */
    void unpack(ResultVisitor v);
}
