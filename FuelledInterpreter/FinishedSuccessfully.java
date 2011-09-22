
/**
 * A simple wrapper for two values, since Java doesn't have multiple-return.
 * 
 * @author Johnicholas Hines
 * @version Thursday, 9/22/2011
 */
public class FinishedSuccessfully implements Result
{
    private Object answer;
    private Integer fuel_remaining;

    /**
     * Constructor
     */
    public FinishedSuccessfully(Object a, Integer f)
    {
        answer = a;
        fuel_remaining = f;
    }

    /**
     * From Result - Visitor design pattern.
     */
    public void unpack(ResultVisitor visitor)
    {
        visitor.FinishedSuccessfully(answer, fuel_remaining);
    }
}
