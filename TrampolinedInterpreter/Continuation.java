
/**
 * An interface that know how to resume a computation.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public interface Continuation
{
    /**
     * Resume a computation, given a value that the computation was waiting for.
     * 
     * @param value Some value that this computation was delayed waiting for.
     * @return the result of the computation
     */
    Object resume(Object value);
}
