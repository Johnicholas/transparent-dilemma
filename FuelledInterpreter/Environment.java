
/**
 * Environments are where you can look up names to get values.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public interface Environment
{
    /**
     * The reason the Environment class exists.
     * 
     * @param toFind the name we're looking up
     * @return the associated value
     */
    Object lookup(String toFind);
}
