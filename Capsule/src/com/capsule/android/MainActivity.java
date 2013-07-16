package com.capsule.android;

import com.capsule.android.rest.RestFactory;
import com.capsule.common.CommonUtil;
import com.capsule.common.Navigator;
import com.capsule.model.User;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class MainActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        View layout=findViewById(R.id.welcomeLayout);
        layout.getBackground().setAlpha(180);
        
        User user = application.cache.getUser().getUser();
		if(user == null){
			goRegister(null);
			return;
		}
		
		CommonUtil.showLoadingDialog(MainActivity.this, "正在登录，请稍等片刻...");
		new LoginTask().execute(user); 
    }

	public void goRegister(View v){
        myNavigator.switchTo(Navigator.RegistActivitySEQ);
    }
    
    public void goLogin(View v){
        myNavigator.switchTo(Navigator.LoginActivitySEQ);
    }
    
    public void goMain(){
    	myNavigator.switchTo(Navigator.BottomTabActivitySEQ);
    }
    
    private class LoginTask extends AsyncTask<User, Void, Integer> {

		@Override
		protected Integer doInBackground(User... users) {
			User user = users[0];
	    	return RestFactory.getUserClient().login(user.getId(), user.getPassword());
		}
    	
		@Override
		protected void onPostExecute(Integer val) {
			// TODO Auto-generated method stub		
			CommonUtil.closeLodingDialog();
			Log.i("value",String.valueOf(val));
			if(val == 0){
				goMain();
			}
		}
    }
}
