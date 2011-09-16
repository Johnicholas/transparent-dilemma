
/**
 * An environment is a thing you can look names up in to get values.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public interface Environment
{
    /**
     * The reason this class exists.
     * 
     * @param toFind The name we're looking up.
     * @return Object the value associated with that name.
     */
    Object lookup(String toFind);
}
