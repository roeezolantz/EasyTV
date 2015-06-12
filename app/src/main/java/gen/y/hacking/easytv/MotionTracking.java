package gen.y.hacking.easytv;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MotionTracking extends Service implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSigMotion;
    public Boolean flag = false;
    public Boolean vidFlag = false;
    public  NotificationManager mNotifyMgr;
    public MotionTracking() {


    }

    @Override
    public void onCreate() {
        Log.i("service", "service started!!");
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSigMotion = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (mSigMotion != null) {
            mSensorManager.registerListener(this, mSigMotion, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.i("service", "sensorChanged");

        if(flag){
            if(!vidFlag) {

                Intent dismissIntent = new Intent(this, receiver.class);
                dismissIntent.putExtra("notificationId", 1);
                PendingIntent btDismissIntent = PendingIntent.getBroadcast(this, 0, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent resultIntent = new Intent(this, streamActivity.class);
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                this,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("EasyTv")
                                .setPriority(Notification.PRIORITY_HIGH)
                                .setContentText("Keep watching on your phone")
                                .setAutoCancel(true)
                                .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText("Keep watching on your phone"))
                                .addAction(R.drawable.abc_ic_clear_mtrl_alpha,
                                        getString(R.string.dismiss), btDismissIntent)
                                .addAction(R.drawable.mr_ic_play_dark,
                                        getString(R.string.watch), resultPendingIntent);

                mBuilder.setContentIntent(resultPendingIntent);

                // Sets an ID for the notification
                int mNotificationId = 2;
// Gets an instance of the NotificationManager service
                mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
                vidFlag = true;
            }
    }else{
            flag = true;
        }

    }
    //
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
