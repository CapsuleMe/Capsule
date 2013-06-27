package com.capsule.android.baidu;

import com.capsule.android.global.Status;
import com.capsule.common.LocationUtils;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

public class LocationListener implements android.location.LocationListener {

	public void onLocationChanged(Location location) {
			
		// TODO Auto-generated method stub
		// Send Intent there;
		Log.i("capsule",location.toString());
		Location oldLocation = Status.getCurrentStatus().getLocation();
		if(LocationUtils.isBetterLocation(location, oldLocation)){
			Status.getCurrentStatus().setLocation(location);
		}
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}	
	
}
