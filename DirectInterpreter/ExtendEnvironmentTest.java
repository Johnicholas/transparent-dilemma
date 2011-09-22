

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ExtendEnvironmentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ExtendEnvironmentTest
{
    /**
     * Default constructor for test class ExtendEnvironmentTest
     */
    public ExtendEnvironmentTest()
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
    public void SuccessfulLookup()
    {
        EmptyEnvironment emptyEnv1 = new EmptyEnvironment();
        java.lang.String string1 = new java.lang.String("bar");
        ExtendEnvironment extendEn1 = new ExtendEnvironment("foo", string1, emptyEnv1);
        assertEquals("bar", extendEn1.lookup("foo"));
    }

    @Test
    public void Delegate()
    {
        EmptyEnvironment emptyEnv1 = new EmptyEnvironment();
        java.lang.String baz = new java.lang.String("baz");
        ExtendEnvironment extendEn1 = new ExtendEnvironment("bar", baz, emptyEnv1);
        ExtendEnvironment extendEn2 = new ExtendEnvironment("foo", null, extendEn1);
        assertEquals("baz", extendEn2.lookup("bar"));
    }
}


