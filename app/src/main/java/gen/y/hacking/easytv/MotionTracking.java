package gen.y.hacking.easytv;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MotionTracking extends Service implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSigMotion;
    public MotionTracking() {


    }

    @Override
    public void onCreate() {
        Log.i("service","service started!!");
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSigMotion = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
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

        Log.i("SERVICE", "WALKING!@#");
    }
    //
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
