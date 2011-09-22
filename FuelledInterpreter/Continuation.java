
/**
 * An interface for computations that could resume, if only they had a value.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public interface Continuation
{
    /**
     * Resume the computation with the provided value.
     * 
     * @param value The value this computation was waiting for.
     * @param fuel The amount of fuel that is left.
     * @return The result of the computation, applied to the value.
     */
    Result resume(Object value, Integer fuel);
}
