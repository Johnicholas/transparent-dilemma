

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
    public void SKKFoo()
    {
        java.lang.String foo = new java.lang.String("foo");
        Quote q_foo = new Quote(foo);
        Application toTest = new Application(skk, q_foo);
    
        Result exhauste1 = toTest.start(1);
        ResultRecorder recorder = new ResultRecorder();
        exhauste1.unpack(recorder);
        assert !recorder.doneSuccessfully;

        Result exhauste2 = toTest.start(2);
        exhauste2.unpack(recorder);
        assert !recorder.doneSuccessfully;

        Result exhauste3 = toTest.start(3);
        exhauste3.unpack(recorder);
        assert !recorder.doneSuccessfully;

        Result exhauste4 = toTest.start(4);
        exhauste4.unpack(recorder);
        assert !recorder.doneSuccessfully;

        Result exhauste5 = toTest.start(5);
        exhauste5.unpack(recorder);
        assert !recorder.doneSuccessfully;

        Result exhauste6 = toTest.start(6);
        exhauste6.unpack(recorder);
        assert !recorder.doneSuccessfully;

        Result exhauste7 = toTest.start(7);
        exhauste7.unpack(recorder);
        assert !recorder.doneSuccessfully;

        Result done = toTest.start(100);
        done.unpack(recorder);
        assert recorder.doneSuccessfully;
        assert "foo".equals(recorder.result);
    }

    @Test
    public void InfiniteLoopsArePreempted()
    {
        Application xx = new Application(x, x);
        Lambda selfapply = new Lambda("x", xx);
        Application omega = new Application(selfapply, selfapply);
        Result exhausted = omega.start(100);
        ResultRecorder recorder = new ResultRecorder();
        exhausted.unpack(recorder);
        assert !recorder.doneSuccessfully;
    }
}


