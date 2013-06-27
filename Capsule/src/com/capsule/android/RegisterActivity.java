package com.capsule.android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.capsule.android.rest.RestFactory;
import com.capsule.android.rest.models.User;
import com.capsule.common.CommonUtil;
import com.capsule.common.Navigator;


public class RegisterActivity extends BaseActivity {

	private String password = null;
	
	private EditText editTextName = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView textAgreement=(TextView)findViewById(R.id.textAgreement2);
        textAgreement.getPaint().setFakeBoldText(true);
        
        editTextName = (EditText)findViewById(R.id.editTextUsername);
        
        createRandomPassword();
    }
    
    private void createRandomPassword(){
    	password = String.valueOf((int)(Math.random()*10E8));
    	
    }
    
    public void doRegister(View target){
    	String name = editTextName.getText().toString();
    	if(name == null || name.isEmpty()){
    		ShowToast();
    		return;
    	}
    	
    	CommonUtil.showLoadingDialog(RegisterActivity.this, "正在注册，请稍等片刻...");
    	new RegisterTask().execute(name);
    }

	private void ShowToast() {
		Toast.makeText(this, 
				getString(R.string.please_input_your_name), 
				Toast.LENGTH_SHORT).show();
		editTextName.requestFocus();
	}
    	
    private class RegisterTask extends AsyncTask<String, Void, User> {

		@Override
		protected User doInBackground(String... names) {
	    	User restUser = RestFactory.getUserClient().register(names[0], password);
			return restUser;
		}
    	
		@Override
		protected void onPostExecute(User user) {
			// TODO Auto-generated method stub		
			application.userCache.UpdateUser(user);
			CommonUtil.closeLodingDialog();
	    	myNavigator.switchTo(Navigator.BottomTabActivitySEQ);
	    	
		}
    }
	
    
}
