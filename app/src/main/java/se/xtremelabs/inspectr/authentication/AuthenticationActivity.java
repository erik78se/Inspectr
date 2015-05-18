package se.xtremelabs.inspectr.authentication;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import se.xtremelabs.inspectr.R;

public class AuthenticationActivity extends AccountAuthenticatorActivity {

    public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_authentication, menu);
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



    public void onCancelClick(View v) {
        this.finish();
    }

    public void onSaveClick(View v) {
        TextView tvUsername;
        TextView tvPassword;
        TextView tvUrl;
        String username;
        String password;
        String apiKey;
        boolean hasErrors = false;

        tvUsername = (TextView) this.findViewById(R.id.uc_txt_username);
        tvPassword = (TextView) this.findViewById(R.id.uc_txt_password);
        tvUrl = (TextView) this.findViewById(R.id.uc_txt_oc_url);

        tvUsername.setBackgroundColor(Color.WHITE);
        tvPassword.setBackgroundColor(Color.WHITE);
        tvUrl.setBackgroundColor(Color.WHITE);

        username = tvUsername.getText().toString();
        password = tvPassword.getText().toString();
        apiKey = tvUrl.getText().toString();

        if (username.length() < 3) {
            hasErrors = true;
            tvUsername.setBackgroundColor(Color.MAGENTA);
        }
        if (password.length() < 3) {
            hasErrors = true;
            tvPassword.setBackgroundColor(Color.MAGENTA);
        }
        if (apiKey.length() < 3) {
            hasErrors = true;
            tvUrl.setBackgroundColor(Color.MAGENTA);
        }

        if (hasErrors) {
            return;
        }

        // Now that we have done some simple "client side" validation it
        // is time to check with the server

        // ... perform some network activity here

        // finished

        String accountType = this.getIntent().getStringExtra(PARAM_AUTHTOKEN_TYPE);
        if (accountType == null) {
            accountType = "se.xtremelabs.inspectr";
        }

        AccountManager accMgr = AccountManager.get(this);

        if (hasErrors) {

            // handel errors

        } else {

            // This is the magic that addes the account to the Android Account Manager
            final Account account = new Account(username, accountType);
            accMgr.addAccountExplicitly(account, password, null);

            // Now we tell our caller, could be the Andreoid Account Manager or even our own application
            // that the process was successful

            final Intent intent = new Intent();
            intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, username);
            intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType);
            intent.putExtra(AccountManager.KEY_AUTHTOKEN, accountType);
            this.setAccountAuthenticatorResult(intent.getExtras());
            this.setResult(RESULT_OK, intent);
            this.finish();

        }
    }





}
