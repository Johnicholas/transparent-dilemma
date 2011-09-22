
/**
 * Environments are associative maps from names (formal parameters) to values (actual parameters).
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public interface Environment
{
    /**
     * This method is the reason Environment exists.
     * 
     * @param toFind the name of the formal parameter that we're looking up.
     * @return the value bound to that formal parameter. 
     */
    Object lookup(String tofind);
}
