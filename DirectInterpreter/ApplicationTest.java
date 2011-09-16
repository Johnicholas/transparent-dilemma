

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ApplicationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ApplicationTest
{
    /**
     * Default constructor for test class ApplicationTest
     */
    public ApplicationTest()
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
    public void ApplyIdentityToFoo()
    {
        Symbol var_foo = new Symbol("foo");
        Lambda i = new Lambda("foo", var_foo);
        java.lang.String bar = new java.lang.String("bar");
        Quote literal_bar = new Quote(bar);
        Application toTest = new Application(i, literal_bar);
        assertEquals("bar", toTest.Start());
    }
}

