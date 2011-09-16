

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Combinators.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Combinators
{
    private Symbol x;
    private Symbol y;
    private Symbol z;
    private Lambda i;
    private Lambda k1;
    private Lambda k;
    private Application xz;
    private Application yz;
    private Application xz_yz;
    private Lambda s1;
    private Lambda s2;
    private Lambda s;
    private Application sk;
    private Application skk;
    private Quote foo;
    private Quote bar;

    /**
     * Default constructor for test class Combinators
     */
    public Combinators()
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
        x = new Symbol("x");
        y = new Symbol("y");
        z = new Symbol("z");
        i = new Lambda("x", x);
        k1 = new Lambda("y", x);
        k = new Lambda("x", k1);
        xz = new Application(x, z);
        yz = new Application(y, z);
        xz_yz = new Application(xz, yz);
        s1 = new Lambda("z", xz_yz);
        s2 = new Lambda("y", s1);
        s = new Lambda("x", s2);
        sk = new Application(s, k);
        skk = new Application(sk, k);
        foo = new Quote("foo");
        bar = new Quote("bar");
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
    public void IdentityFoo()
    {
        Application toTest = new Application(i, foo);
        assertEquals("foo", toTest.start());
    }

    @Test
    public void IdentityBar()
    {
        Application toTest = new Application(i, bar);
        assertEquals("bar", toTest.start());
    }

    @Test
    public void SkkActsLikeIdentityFoo()
    {
        Application toTest = new Application(skk, foo);
        assertEquals("foo", toTest.start());
    }

    @Test
    public void SkkActsLikeIdentityBar()
    {
        Application toTest = new Application(skk, bar);
        assertEquals("bar", toTest.start());
    }
}




