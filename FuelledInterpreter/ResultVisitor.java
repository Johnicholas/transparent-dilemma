
/**
 * The result of a fuelled computation could be 'done successfully' and a value,
 * or it could be 'exhausted the fuel available', and sufficient information to restart.
 * 
 * This interface can handle either of those two options.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public interface ResultVisitor
{
    /**
     * The computation finished successfully within the alloted time.
     * 
     * @param o The result of the computation.
     * @param fuel_remaining The number of steps remaining out of the original allotment.
     */
    void FinishedSuccessfully(Object o, Integer fuel_remaining);

    /**
     * The computation ran out of fuel.
     * 
     * Note that these three arguments are exactly what you would need, with an integer, to call "pump"
     * to resume the computation.
     * 
     * @param exp The subexpression that would have been evalled next.
     * @param env The environment holding the values of the free variables in exp.
     * @param k The continuation that had been built up.
     */
    void ExhaustedFuel(Expression exp, Environment env, Continuation k);
}
