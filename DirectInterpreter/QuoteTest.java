

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class QuoteTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class QuoteTest
{
    /**
     * Default constructor for test class QuoteTest
     */
    public QuoteTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void StraightforwardUsage()
    {
        java.lang.String foo = new java.lang.String("foo");
        Quote quote1 = new Quote(foo);
        EmptyEnvironment emptyEnv1 = new EmptyEnvironment();
        assertEquals("foo", quote1.eval(emptyEnv1));
    }
}

