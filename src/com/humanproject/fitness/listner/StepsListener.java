package com.humanproject.fitness.listner;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class StepsListener implements SensorEventListener{
	private static int distanceInFeets;
	private Context context;
	@Override
	public void onSensorChanged(SensorEvent event) {
		SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                SensorManager.SENSOR_DELAY_NORMAL, 1*3600*1000);
		// TODO: understand the sensor and algorithm to convert into steps and then feets (
		// based on height of the person), distanceInFeets. 
		
		
		long lastwalkedat = System.currentTimeMillis();
		// Save lastwalkedat and feets in db by adding this value to dailyfeets and total feets also check 
		// if dailyfeets is greater than 1000 create a notification and send feedback.
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
