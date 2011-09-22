
/**
 * A simple, open, visible, structure-like implementation of ResultVisitor, for unit tests to use.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class ResultRecorder implements ResultVisitor
{
    public Boolean doneSuccessfully;
    public Object result;
    public Integer fuel_remaining;
    public Expression exp;
    public Environment env;
    public Continuation cont;
    
    /**
     * Constructor
     */
    public ResultRecorder()
    {
        // everything starts out null in java, right?
    }

    /**
     * From ResultVisitor.
     */
    public void FinishedSuccessfully(Object o, Integer f) {
        doneSuccessfully = true;
        result = o;
        fuel_remaining = f;
    }

    /**
     * From ResultVisitor.
     */
    public void ExhaustedFuel(Expression x, Environment e, Continuation k) {
        doneSuccessfully = false;
        exp = x;
        env = e;
        cont = k;
    }

}
