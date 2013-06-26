package com.capsule.android;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.capsule.android.rest.RestFactory;
import com.capsule.android.rest.models.User;
import com.capsule.common.Navigator;


public class RegisterActivity extends BaseActivity {

	private String password = null;
	
	EditText editTextName = null;
	
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
    	String name = editTextName.toString();
    	
    	if(name == null || name.isEmpty()){
    		Toast.makeText(this, "请先告诉我你的大名，谢谢!", Toast.LENGTH_SHORT).show();
    		editTextName.requestFocus();
    		return;
    	}
    	
    	RestFactory.BaseUrl = "http://10.200.52.62:3000";
    	User restUser = RestFactory.getUserClient().register(name, password);
    	application.userCache.UpdateUser(restUser);
    	myNavigator.switchTo(Navigator.FriendListActivitySEQ);
    }
    
   /* private void sendValidCode(){
    	int code =(int)( Math.random()*1000);
    	validCode = String.valueOf(code);
    	
    	SmsManager sms = SmsManager.getDefault();
    	
    	String msg = getMessage();
    	List<String> divideMsg = sms.divideMessage(msg);
    	for(String text:divideMsg){
    		sms.sendTextMessage(getPhoneNumber(), null, text, getSendIntent(), getDeliveryIntent());
    	}
    
    }*/
    
    
   /* private PendingIntent getDeliveryIntent() {
		// TODO Auto-generated method stub
		return null;
	}

	private PendingIntent getSendIntent() {

		String SENT_SMS_ACTION = "SENT_SMS_ACTION";  
		Intent sentIntent = new Intent(SENT_SMS_ACTION);  
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, sentIntent,0);  
		// register the Broadcast Receivers  
		this.registerReceiver(new BroadcastReceiver() {  
		    
			@Override  
		    public void onReceive(Context _context, Intent _intent) {  
		        switch (getResultCode()) {  
		        case Activity.RESULT_OK:  
		            Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
		            break;  
		        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:  
		        	 break;  
		        case SmsManager.RESULT_ERROR_RADIO_OFF:  
		        	 break;  
		        case SmsManager.RESULT_ERROR_NULL_PDU:  
		        	 break;  
		        }  
		    } 
		}, new IntentFilter(SENT_SMS_ACTION));  
		
		
		return sentPI; 
	}

	private String getPhoneNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}*/

	
    
}
