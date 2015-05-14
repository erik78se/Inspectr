package se.xtremelabs.inspectr;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;
import java.util.List;

import se.xtremelabs.models.Client;
import se.xtremelabs.models.Project;

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
        AddTestClient();
    }

    // Setup
    public void AddTestClient() {
        Client c = new Client();
        c.name = "TestClientX";
        c.email = "chuck@foobar.com";
        c.telephone_number = "1234";
        c.save();
    }

    // Get one
    public void testTestClient() {
        List<Client> lc = Client.find(Client.class, "name = ?", "TestClientX");
        assertEquals("TestClientX", lc.get(0).name);
    }


    // Count
    public void testNumClients() {
        List<Client> lc = Client.find(Client.class, "name = ?", "TestClientX");
        Log.d("ApplicationTest", String.valueOf(lc.size()) );
        assertEquals(1, lc.size());
    }


    // Teardown
    public void RemoveTestClient() {
        List<Client> lc = Client.find(Client.class, "name = ?", "TestClientX");
        lc.get(0).delete();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        RemoveTestClient();
    }
}