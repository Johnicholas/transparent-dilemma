
/**
 * Things that know how to evaluate themselves.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public abstract class Expression
{
    /**
     * Evaluates an expression in a particular environment.
     * 
     * @param env An environment providing actual values for free formals in this expression.
     * @return The result of running this expression.
     */
    abstract Object eval(Environment env);

    /**
     * A helper method, for conveniently starting an evaluation.
     *
     * @return The result of running this expression in the empty environment.
     */
    public Object Start()
    {
        return this.eval(new EmptyEnvironment());
    }
}
