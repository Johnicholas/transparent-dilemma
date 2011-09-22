
/**
 * A continuation that plugs an evalled arg into an evalled closure.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class Plug implements Continuation
{
    private Closure proc;
    private Continuation cont;
    
    /**
     * Constructor
     */
    public Plug(Closure p, Continuation k)
    {
        proc = p;
        cont = k;
    }

    /**
     * From Continuation.
     */
    public Object resume(Object actual)
    {
        return proc.enter(actual, cont);
    }
}
