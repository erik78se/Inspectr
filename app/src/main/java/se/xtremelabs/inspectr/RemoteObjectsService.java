package se.xtremelabs.inspectr;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.xtremelabs.models.Client;
import se.xtremelabs.models.Project;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class RemoteObjectsService extends IntentService {

    private final String CLASSTAG = getClass().getSimpleName();


    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FETCHPROJECTS = "se.xtremelabs.inspectr.action.fetchprojects";
    private static final String ACTION_FETCHCLIENTS = "se.xtremelabs.inspectr.action.fetchclients";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "se.xtremelabs.inspectr.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "se.xtremelabs.inspectr.extra.PARAM2";

    /**
     * Starts this service to perform action FetchProjects with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionFetchProjects(Context context, String param1, String param2) {
        Intent intent = new Intent(context, RemoteObjectsService.class);
        intent.setAction(ACTION_FETCHPROJECTS);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action FetchClients with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFetchClients(Context context, String param1, String param2) {
        Intent intent = new Intent(context, RemoteObjectsService.class);
        intent.setAction(ACTION_FETCHCLIENTS);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public RemoteObjectsService() {
        super("RemoteProjectsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCHPROJECTS.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFetchProjects(param1, param2);
            } else if (ACTION_FETCHCLIENTS.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFetchClients(param1, param2);
            }
        }
    }

    /**
     * Handle action FetchProjects in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFetchProjects(String param1, String param2) {
        List<Project> projects = Project.listAll(Project.class);
        Log.i(CLASSTAG, String.format("Num projects: %s", projects.size()) );
    }

    /**
     * Handle action FetchClients in the provided background thread with the provided
     * parameters.
     *
     */
    private void handleActionFetchClients(String param1, String param2) {

        Client c1 = new Client();
        c1.name = "Client1";
        c1.email = "chuck1@foobar.com";
        c1.telephone_number = "1234";

        Client c2 = new Client();
        c2.name = "Client2";
        c2.email = "chuck2@foobar.com";
        c2.telephone_number = "12345";

        Client c3 = new Client();
        c3.name = "Client3";
        c3.email = "chuck3@foobar.com";
        c3.telephone_number = "123456";

        List<Client> clients = new ArrayList<Client>();
        clients.add(c1);clients.add(c2);clients.add(c3);

        Iterator<Client> iterator = clients.iterator();
        while (iterator.hasNext()) {
            Client c = iterator.next();
            String n = c.name;
            if ( Client.find(Client.class, "name = ?", n).size() > 0 ) {
                Log.i(CLASSTAG,
                        String.format("Client %s already in database.", n));
            } else {
                Log.i(CLASSTAG,
                        String.format("Adding new Client %s to database.", n));
                c.save();
            }

        }
    }
}
