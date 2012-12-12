package com.capsule.android;

import java.util.Observable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.capsule.android.baidu.BaiduMapOverlayActivity;
import com.capsule.android.service.LocationService;
import com.capsule.android.widget.MapBarTextView;

public class CapSuleMapActivity extends BaiduMapOverlayActivity{
    
    private TextView t1, t2, t3;// 页卡头标
    private TextView currView;// 当前页
    private MapBarTextView cursor;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layout=findViewById(R.id.mapTabLayout);
        layout.getBackground().setAlpha(220);
        initMapBarView();
        startLocationService();
    }

    @Override
    protected int getConentViewId() {
        return R.layout.baidu_map;
    }
    
    public void zoomIn(View target){
        mMapView.getController().zoomIn();
    }
    
    public void zoomOut(View target){
        mMapView.getController().zoomOut();
    }
    

    /**
     * 初始化地图标签页
     */
    private void initMapBarView() {
        t1 = (TextView) findViewById(R.id.allBtn);
        t2 = (TextView) findViewById(R.id.familyBtn);
        t3 = (TextView) findViewById(R.id.friendBtn);
        t1.setOnClickListener(new TabChangeListener());
        t2.setOnClickListener(new TabChangeListener());
        t3.setOnClickListener(new TabChangeListener());
        cursor=(MapBarTextView)findViewById(R.id.cursor);
        currView=t1;
    }

    /**
     * 标签切换监听
     */
    public class TabChangeListener implements View.OnClickListener {
        
        public void onClick(View v) {
            Animation animation = null;
            int from=currView.getLeft();
            int to=v.getLeft();
            animation = new TranslateAnimation(from, to, 0, 0);
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);
            currView=(TextView)v;
        }
    }
    
    
    public void showCurrentLocation(View target){
        showCurrentLocation();
    }
    
    /**Start Location Service*/
    private void startLocationService(){
            Intent intent = new Intent(this, LocationService.class);
            this.startService(intent);  
    }

    @Override
    public void update(Observable observable, Object data) {
        
    }
}
