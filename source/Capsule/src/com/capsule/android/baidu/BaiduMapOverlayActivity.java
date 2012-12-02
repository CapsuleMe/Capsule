package com.capsule.android.baidu;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;
import com.capsule.android.R;
import com.capsule.android.global.Status;


public abstract class BaiduMapOverlayActivity extends BaiduMapActivity implements Observer{

    protected static String CURRENT_CITY = "";
    protected final static int MAX_SEARCHING_SECOND = 1000*5;
    
    protected MapView mMapView = null;
    protected View mPopView = null; 
    
    TextView popSummary = null;
    BaiduItemizedOverlay myOverlay = null;

    GeoPoint tappedPoint = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        initialMapView();
        //Listen Status' change
        Status.getCurrentStatus().addObserver(this);
    }
    
    @Override
    protected void onResume() {
        onResume(true);
    }
    
    protected void onResume(boolean clearState){
        super.onResume();
        if(clearState){
            initialOverlays();
            hidePopView();
        }
    }
    
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        
        //Remove Status Listen
        Status.getCurrentStatus().deleteObserver(this);
    }
    
    @Override
    public MapView getMapView() {
        // TODO Auto-generated method stub
        return mMapView;
    }
    
    @Override
    public void update(Observable observable, Object data) {
        showCurrentLocation(false);
    }
    
    @Override
    public boolean onTapped(int i, OverlayItem item) {
        Log.d("capsule","onTapped");
        popSummary.setText(item.getTitle());

        tappedPoint = item.getPoint();  
        
        mMapView.getController().setCenter(item.getPoint());    
        mMapView.updateViewLayout(mPopView,
                new MapView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                        item.getPoint(), 0, 0-BaiduItemizedOverlay.MARKER_HEIGHT, 
                        MapView.LayoutParams.BOTTOM_CENTER));
         
        mPopView.setVisibility(View.VISIBLE);
        
        return true;
    }

    @Override
    public void onTapping(GeoPoint pt, MapView v) {
        // TODO Auto-generated method stub
        Log.d("Observer","onTapping");
        mPopView.setVisibility(View.GONE);
    }
    
    
    protected void initialMapView(){
        mMapView =(MapView)findViewById(R.id.mapView);
        mMapView.setBuiltInZoomControls(false);
        mMapView.setDrawOverlayWhenZooming(true);

        initialOverlays();
        attachPopView();
    }

    private void initialOverlays() {
        myOverlay = new BaiduItemizedOverlay(BaiduMapOverlayActivity.this, getResources().getDrawable(R.drawable.gprs_mark_red));
        mMapView.getOverlays().clear();
        mMapView.getOverlays().add(myOverlay);
    }
    
    protected void attachPopView(){
        mPopView=super.getLayoutInflater().inflate(R.layout.popview, null);
        mPopView.setVisibility(View.GONE);
        
        popSummary=(TextView)mPopView.findViewById(R.id.popSummary);
        
        mMapView.addView(mPopView,
                new MapView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                        null, MapView.LayoutParams.BOTTOM_CENTER));
        mPopView.setVisibility(View.GONE);
    }
    
    protected Boolean showCurrentLocation(){
        return showCurrentLocation(true);   
    }
    
    protected Boolean showCurrentLocation(Boolean shouldCenter){
        Location loc = Status.getCurrentStatus().getLocation();
        if(loc ==null){
            return false;
        }
        
        int latitude = (int)(loc.getLatitude()*1E6);
        int longitude = (int)(loc.getLongitude()*1E6); 
        GeoPoint pt = new GeoPoint(latitude, longitude);
        
        String title = this.getString(R.string.current_position);
        String message = pt.getLatitudeE6() + ":" + pt.getLongitudeE6();
        OverlayItem item = new OverlayItem(pt, title+"\n"+message, null);
        
        List<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(item);
        myOverlay.setItems(items);
        
        if(shouldCenter){
            setCenter(pt);  
        }
        mMapView.getController().animateTo(pt);
        this.onTapped(0, item);
        return true;
    }
    
    protected void setCenter(GeoPoint pt){
        mMapView.getController().setCenter(pt);
    }
    
    protected void hidePopView() {
       // mPopView.setVisibility(View.GONE);
    }
    
}
