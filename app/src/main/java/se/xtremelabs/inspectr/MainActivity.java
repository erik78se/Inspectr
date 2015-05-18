package se.xtremelabs.inspectr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;

import se.xtremelabs.models.Client;
import se.xtremelabs.models.Project;



//#TODO: Account to OC: http://www.finalconcept.com.au/article/view/android-account-manager-step-by-step
//#TODO: http://www.finalconcept.com.au/article/view/android-account-manager-step-by-step-2


public class MainActivity extends ActionBarActivity {
    private final String CLASSTAG = getClass().getSimpleName();
    private final String SHARED_PREFS_NAME = "se.xtremelabs.inspectr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button_startinspection);
        Button b2 = (Button) findViewById(R.id.button_manageinspections);
        Button b3 = (Button) findViewById(R.id.button_setactiveproject);
        Button b4 = (Button) findViewById(R.id.button_settings);
        Button b5 = (Button) findViewById(R.id.button_debug1);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        //App preference keeps the id of the active project
        String currP = prefs.getString("ACTIVE_PROJECT", null);

        if ( currP != null ) {
            Log.i(CLASSTAG, String.format("Current project id: %s ", currP));
        } else {
            Log.i(CLASSTAG, "No active project in preferences.");
        }


        View.OnClickListener handler_startinspection = new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(CLASSTAG, "Button press for handler_startinspection pressed");
            }
        };

        View.OnClickListener handler_manageinspections  = new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(CLASSTAG, "Button press for handler_manageinspections  pressed.");
            }
        };

        View.OnClickListener handler_setactiveproject = new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(CLASSTAG, "Button press for handler_setactiveproject pressed.");
                Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_SHORT).show();
               // Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
               // startActivity(i);
            }
        };

        View.OnClickListener handler_settings = new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(CLASSTAG, "Button press for handler_settings pressed.");
               // Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
               // startActivity(i);
            }
        };

        View.OnClickListener handler_debug1 = new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(CLASSTAG, "Button press for handler_debug1 pressed.");
                RemoteObjectsService.startActionFetchClients(getApplicationContext(), "", "");
                // List<Project> projects = Project.listAll(Project.class);

                // Gson gson = new Gson();
                // String project_json = gson.toJson( projects.get(0) );
                // Log.d(CLASSTAG, project_json);
                // Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                // startActivity(i);
            }
        };

        b1.setOnClickListener( handler_startinspection );

        b2.setOnClickListener( handler_manageinspections );

        b3.setOnClickListener( handler_setactiveproject );

        b4.setOnClickListener( handler_settings );

        b5.setOnClickListener( handler_debug1 );

    }

}
