package se.xtremelabs.inspectr;

import android.content.Intent;
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


public class MainActivity extends ActionBarActivity {
    private final String CLASSTAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDummyDatabase();

        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button_startinspection);
        Button b2 = (Button) findViewById(R.id.button_manageinspections);
        Button b3 = (Button) findViewById(R.id.button_setactiveproject);
        Button b4 = (Button) findViewById(R.id.button_settings);
        Button b5 = (Button) findViewById(R.id.button_debug1);


        View.OnClickListener handler_startinspection = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(CLASSTAG, "Button press for handler_startinspection pressed");
            }
        };

        View.OnClickListener handler_manageinspections  = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(CLASSTAG, "Button press for handler_manageinspections  pressed.");
            }
        };

        View.OnClickListener handler_setactiveproject = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(CLASSTAG, "Button press for handler_setactiveproject pressed.");
               // Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
               // startActivity(i);
            }
        };

        View.OnClickListener handler_settings = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(CLASSTAG, "Button press for handler_settings pressed.");
               // Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
               // startActivity(i);
            }
        };

        View.OnClickListener handler_debug1 = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(CLASSTAG, "Button press for handler_debug1 pressed.");
                List<Project> projects = Project.listAll(Project.class);

                Gson gson = new Gson();
                String project_json = gson.toJson( projects.get(0) );
                Log.d(CLASSTAG, project_json);
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


    public void setupDummyDatabase() {
        Project p = new Project();
        Client c = new Client();
        c.name = "Chuck the Client";
        c.email = "chuck@foobar.com";
        c.telephone_number = "1234";
        p.name = "DummyProject";
        p.client = c;
        p.address = "Dummy Address";
        c.save();
        p.save();
    }

    /**
     *
     * CODE BELOW HERE IS NOT USED
     *
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
