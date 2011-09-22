

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SymbolTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SymbolTest
{
    /**
     * Default constructor for test class SymbolTest
     */
    public SymbolTest()
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
        EmptyEnvironment emptyEnv1 = new EmptyEnvironment();
        java.lang.String bar = new java.lang.String("bar");
        ExtendEnvironment extendEn1 = new ExtendEnvironment("foo", bar, emptyEnv1);
        Symbol toTest = new Symbol("foo");
        assertEquals("bar", toTest.eval(extendEn1));
    }
}

