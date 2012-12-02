package com.capsule.android.baidu;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;
import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;
import com.capsule.android.R;


public abstract class BaiduMapActivity extends MapActivity{

    protected BMapManager mBMapMan = null;  

    protected MapView mMapView = null;
    
    public abstract MapView getMapView();
    
    public abstract boolean onTapped(int i, OverlayItem item);

    public abstract void onTapping(GeoPoint pt, MapView v);
    
    protected abstract int getConentViewId();
    
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(this.getConentViewId());
        
        if (mBMapMan == null) {
            mBMapMan = new BMapManager(getApplication());
            mBMapMan.init("DBC116220F2040B76081BF632757104D2960CF1A", null);
        }
        mBMapMan.start();
        super.initMapActivity(mBMapMan);
        initialMapView();
    }
    
    protected void initialMapView(){
        mMapView =(MapView)findViewById(R.id.mapView);
        mMapView.setBuiltInZoomControls(false);
        mMapView.setDrawOverlayWhenZooming(true);
        mMapView.getController().setZoom(18);
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    @Override
    protected void onDestroy() {
        if (mBMapMan != null) {
            mBMapMan.destroy();
            mBMapMan = null;
        }
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        if (mBMapMan != null) {
            mBMapMan.stop();
        }
        super.onPause();
    }
    @Override
    protected void onResume() {
        if (mBMapMan != null) {
            mBMapMan.start();
        }
        super.onResume();
    }
}
