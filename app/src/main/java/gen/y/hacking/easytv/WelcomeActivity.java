package gen.y.hacking.easytv;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class WelcomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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

    public void logIn(View view) {
        String txtComigoText = ((com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.txtCode)).getText().toString();

        if (!txtComigoText.equals("")) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("comigo_key", txtComigoText);
            startActivity(i);
        }
        else
            Toast.makeText(this, "Please fill your COMIGO's code below", Toast.LENGTH_SHORT).show();
    }
}
