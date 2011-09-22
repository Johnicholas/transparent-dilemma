
/**
 * Expressions are things that know how to evaluate themselves.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public abstract class Expression
{
    /**
     * Evaluate the expression, in the given environment.
     * 
     * @param env An environment mapping names of free variables to actual values.
     * @param next A computation that indicates something further to be done.
     * @return the result of evaluating this exp, in that env, then passing that to the continuation.
     */
    public abstract Object eval(Environment env, Continuation k);

    /**
     * A helper method to start the computation off in the standard manner.
     * @return the result of evaluating this expression
     */
    public Object start()
    {
        return this.eval(new EmptyEnvironment(), new Done());
    }

}
