package gen.y.hacking.easytv;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class streamActivity extends ActionBarActivity {
    private String urlStream;
    private VideoView myVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        myVideoView = (VideoView)this.findViewById(R.id.videoView);
        MediaController mc = new MediaController(this);
        myVideoView.setMediaController(mc);
        urlStream = "http://str81.creacast.com/weo/smil:weo.smil/playlist.m3u8";
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myVideoView.setVideoURI(Uri.parse(urlStream));
                myVideoView.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stream, menu);
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
