package se.xtremelabs.inspectr;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;
import se.xtremelabs.models.Client;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    // Just a dummy test
    public void testDummy() {
        assertEquals(1,1);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}