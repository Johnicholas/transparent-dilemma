
/**
 * An abstract class for things that know how to evaluate themselves.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public abstract class Expression
{
    /**
     * Evaluate this expression, in this environment, then pass the result to this continuation,
     * taking no more than this number of steps.
     * 
     * Note: It is important for the termination guarantee that nobody but pump call this
     * directly - everyone else should access eval through pump. Possibly there is a way to do that
     * using private, final, or similar - however, I don't know how to do it offhand, and a comment
     * ought to be sufficient.
     * 
     * @param env The environment this expression should be evaluated in.
     * @param k The continuation that should be applied, once the expression is evalled.
     * @param fuel The amount of steps remaining before being preempted.
     * @return The result of the computation, or a 'fuel exhausted' notice.
     */
    public abstract Result eval(Environment env, Continuation k, Integer fuel);

    /**
     * Pay an increment of fuel to keep going.
     * 
     * @param env The environment this expression should be evaluated in.
     * @param k The continuation that should be applied, once the expression is evalled.
     * @param fuel The amount of steps remaining before being preempted.
     * @return The result of the computation, or a 'fuel exhausted' notice.
     */
    public Result pump(Environment env, Continuation k, Integer fuel) {
           if (fuel > 0) {
               return this.eval(env, k, fuel - 1);
            } else {
                return new ExhaustedFuel(this, env, k);
            }
    }
    
    /**
     * Start a computation in the usual way, running it for a specified number of steps.
     * 
     * @param fuel The maximum number of steps to run.
     * @return The result of the computation if it runs successfully, or a fuel exhausted notice.
     */
    public Result start(Integer fuel) {
        return this.pump(new EmptyEnvironment(), new Done(), fuel);
    }
}
