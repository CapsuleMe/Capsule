package com.capsule.android.service;

import com.capsule.android.R;
import com.capsule.android.baidu.LocationListener;

import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

/**Foreground service for listening location's modification.
 * Location update's work is done by LocatinoListener
 * The works is done by itself is:
 * 	1. bind and unbind notification listener.
 *  2. send notifications when one alarm alarm.
 * */
public class LocationService extends NotificationService {

	static LocationService instance = null;
	static int minTime = 15*1000;
	static int minMeters = 200;
	
	LocationManager locationManager = null;
	LocationListener gpsListener = null;
	LocationListener networkListener = null;
	
	public static LocationService getLatestInstance(){
		return instance;
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate(){
		Log.d(getString(R.string.debug_tag),"Create LocationService");
		initialVariables();
		bindListener();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(getString(R.string.debug_tag),"Start LocationService");
	    return START_STICKY;
	}
	
	@Override
	public void onDestroy(){
		
		unbindNetLocationListener();
		unbindGPSLocationListener();
		
		toBackground();	
		Log.d(getString(R.string.debug_tag),"Destroy LocationService");
	}

	public void bindNetLocationListener(){
		 locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime,minMeters,networkListener);
	}
	
	public void bindGPSLocationListener(){
		 locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minMeters,gpsListener);
	}
	
	public void unbindNetLocationListener(){
		locationManager.removeUpdates(networkListener);
	}
	
	public void  unbindGPSLocationListener(){
		locationManager.removeUpdates(gpsListener);
	}
	
	private void initialVariables(){
		locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		gpsListener = new LocationListener();
		networkListener = new LocationListener();
		instance = this;
		
	}
	
	private void bindListener(){
		//Read preferences and to decide which locating listener to bind
		bindNetLocationListener();
		bindGPSLocationListener();
	}
	
}
