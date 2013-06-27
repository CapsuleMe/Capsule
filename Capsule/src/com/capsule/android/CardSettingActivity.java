package com.capsule.android;

import java.io.File;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.capsule.android.weibo.WeiboManager;
import com.capsule.common.SharePreferencesEditor;
import com.capsule.common.CommonUtil;

public class CardSettingActivity extends BaseActivity {

    private ImageView faceImage;

    private SharePreferencesEditor preferences;
    
    private String[] picItems=new String[]{"选择本地图片", "拍照"};

    private String[] sexItems=new String[]{"淑女", "绅士"};

    /* 头像名称 */
    private static final String IMAGE_FILE_NAME="faceImage.png";

    /* 请求码 */
    private static final int IMAGE_REQUEST_CODE=0;

    private static final int CAMERA_REQUEST_CODE=1;

    private static final int RESULT_REQUEST_CODE=2;

    private EditText alertTxt;

    private TextView capsuleNameTxtView;

    private TextView userIntrodueTxtView;

    private View alertView;

    private TextView userSexTxtView;

    private TextView userCompanyTxtView;

    private TextView userPositionTxtView;

    private TextView userSchoolTxtView;

    private TextView userPhoneNumberTxtView;

    private TextView userQQTxtView;

    private TextView userEmailTxtView;

    private TextView userSinaWeiboTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardsetting);
        initUI();
    }

    private void initUI(){
        View topbar=findViewById(R.id.top_bar);
        TextView titlebar=(TextView)topbar.findViewById(R.id.top_bar_title);
        titlebar.setText(this.getString(R.string.edit_namecard));
        preferences=new SharePreferencesEditor(this, SharePreferencesEditor.SettingsName);
        //获取textview
        faceImage=(ImageView)findViewById(R.id.face);
        capsuleNameTxtView=(TextView)findViewById(R.id.capsuleName);
        userIntrodueTxtView=(TextView)findViewById(R.id.userIntrodue);
        userSexTxtView=(TextView)findViewById(R.id.userSex);
        userCompanyTxtView=(TextView)findViewById(R.id.userCompany);
        userPositionTxtView=(TextView)findViewById(R.id.userPosition);
        userSchoolTxtView=(TextView)findViewById(R.id.userSchool);
        userPhoneNumberTxtView=(TextView)findViewById(R.id.userPhoneNumber);
        userQQTxtView=(TextView)findViewById(R.id.userQQ);
        userEmailTxtView=(TextView)findViewById(R.id.userEmail);
        userSinaWeiboTxtView=(TextView)findViewById(R.id.userSinaWeibo);
        // 从preference文件中获取值
        String capsuleNameTxtValue=preferences.get("capsuleName", capsuleNameTxtView.getText().toString());
        String userIntrodueTxtValue=preferences.get("userIntrodue", userIntrodueTxtView.getText().toString());
        String userSexTxtValue=String.valueOf(preferences.get("userSexIndex", 0));
        String userCompanyTxtValue=preferences.get("userCompany", userCompanyTxtView.getText().toString());
        String userPositionTxtValue=preferences.get("userPosition", userPositionTxtView.getText().toString());
        String userSchoolTxtValue=preferences.get("userSchool", userSchoolTxtView.getText().toString());
        String userPhoneNumberTxtValue=preferences.get("userPhoneNumber", userPhoneNumberTxtView.getText().toString());
        String userQQTxtValue=preferences.get("userQQ", userQQTxtView.getText().toString());
        String userEmailTxtValue=preferences.get("userEmail", userEmailTxtView.getText().toString());
        String userSinaWeiboTxtValue=preferences.get("userSinaWeibo", userSinaWeiboTxtView.getText().toString());
        
        String imagePath=preferences.get("head_image_path", "");
        Bitmap factbitMap=null;
        if(!imagePath.equals("")){// 从preference中读取头像路径
            factbitMap=CommonUtil.fileToBitmap(imagePath);
        }else{// 默认的头像
            BitmapDrawable defaultAvatarBitmap=(BitmapDrawable)getResources().getDrawable(R.drawable.default_face);
            factbitMap=CommonUtil.toRoundCorner(defaultAvatarBitmap.getBitmap(), 25);
        }
        // 设置值到textview中
        faceImage.setImageBitmap(factbitMap);
        capsuleNameTxtView.setText(capsuleNameTxtValue);
        userIntrodueTxtView.setText(userIntrodueTxtValue);
        userSexTxtView.setText(sexItems[Integer.valueOf(userSexTxtValue)]);
        userCompanyTxtView.setText(userCompanyTxtValue);
        userPositionTxtView.setText(userPositionTxtValue);
        userSchoolTxtView.setText(userSchoolTxtValue);
        userPhoneNumberTxtView.setText(userPhoneNumberTxtValue);
        userQQTxtView.setText(userQQTxtValue);
        userEmailTxtView.setText(userEmailTxtValue);
        userSinaWeiboTxtView.setText(userSinaWeiboTxtValue);
    }
    
    public void showChooseFaceDialog(View target) {
        new AlertDialog.Builder(this).setTitle(R.string.face_setting).setItems(picItems, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case 0:
                        Intent intentFromGallery=new Intent();
                        intentFromGallery.setType("image/*"); // 设置文件类型
                        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
                        break;
                    case 1:
                        Intent intentFromCapture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 判断存储卡是否可以用，可用进行存储
                        if(CommonUtil.hasSdcard()) {
                            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                        }

                        startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                        break;
                }
            }
        }).setNegativeButton(this.getString(R.string.cancel), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    public void showCapsuleNameDialog(View target){ 
        onCreateDialog();
        alertTxt.setText(capsuleNameTxtView.getText());
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.capsule_name))
              .setView(alertView)
              .setPositiveButton(this.getString(R.string.submit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String txt=alertTxt.getText().toString();
                capsuleNameTxtView.setText(txt);
                preferences.put("capsuleName", txt);
            }
        }).setNegativeButton(this.getString(R.string.cancel), new DialogInterface.OnClickListener() {
 
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    
    public void showUserIntrodueDialog(View target){ 
        onCreateDialog();
        alertTxt.setHint("15个汉字");
        alertTxt.setText(userIntrodueTxtView.getText());
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.user_introdue))
              .setView(alertView)
              .setPositiveButton(this.getString(R.string.submit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String txt=alertTxt.getText().toString();
                userIntrodueTxtView.setText(txt);
                preferences.put("userIntrodue", txt);
            }
        }).setNegativeButton(this.getString(R.string.cancel), new DialogInterface.OnClickListener() {
 
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    public void showUserSexDialog(View target){
        String userSexTxtValue=String.valueOf(preferences.get("userSexIndex", 0));
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.user_sex)).setSingleChoiceItems(
            sexItems, Integer.valueOf(userSexTxtValue), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String sexValue=sexItems[which];
                    userSexTxtView.setText(sexValue);
                    preferences.put("userSexIndex", which);
                    dialog.dismiss();
                }
         }).show();
    }
    
    public void showUserCompanyDialog(View target){
        onCreateDialog();
        alertTxt.setText(userCompanyTxtView.getText());
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.company))
              .setView(alertView)
              .setPositiveButton(this.getString(R.string.submit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String txt=alertTxt.getText().toString();
                userCompanyTxtView.setText(txt);
                preferences.put("userCompany", txt);
            }
        }).setNegativeButton(this.getString(R.string.cancel), new DialogInterface.OnClickListener() {
 
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    
    public void showUserPositionDialog(View target){
        onCreateDialog();
        alertTxt.setText(userPositionTxtView.getText());
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.position))
              .setView(alertView)
              .setPositiveButton(this.getString(R.string.submit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String txt=alertTxt.getText().toString();
                userPositionTxtView.setText(txt);
                preferences.put("userPosition", txt);
            }
        }).setNegativeButton(this.getString(R.string.cancel), new DialogInterface.OnClickListener() {
 
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    
    public void showUserSchoolDialog(View target){
        onCreateDialog();
        alertTxt.setText(userSchoolTxtView.getText());
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.school))
              .setView(alertView)
              .setPositiveButton(this.getString(R.string.submit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String txt=alertTxt.getText().toString();
                userSchoolTxtView.setText(txt);
                preferences.put("userSchool", txt);
            }
        }).setNegativeButton(this.getString(R.string.cancel), new DialogInterface.OnClickListener() {
 
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    
    public void showUserQQDialog(View target){
        onCreateDialog();
        alertTxt.setText(userQQTxtView.getText());
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.qq))
              .setView(alertView)
              .setPositiveButton(this.getString(R.string.submit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String txt=alertTxt.getText().toString();
                userQQTxtView.setText(txt);
                preferences.put("userQQ", txt);
            }
        }).setNegativeButton(this.getString(R.string.cancel), new DialogInterface.OnClickListener() {
 
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    
    public void showUserEmailDialog(View target){
        onCreateDialog();
        alertTxt.setText(userEmailTxtView.getText());
        new AlertDialog.Builder(this).setTitle(this.getString(R.string.email))
              .setView(alertView)
              .setPositiveButton(this.getString(R.string.submit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String txt=alertTxt.getText().toString();
                userEmailTxtView.setText(txt);
                preferences.put("userEmail", txt);
            }
        }).setNegativeButton(this.getString(R.string.cancel), new DialogInterface.OnClickListener() {
 
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    
    public void showWeiboBind(View target){
        BandWeiboHandler handler=new BandWeiboHandler();
        WeiboManager.getWeiboInstance().authorize(this, handler);
        
    }
    
    class BandWeiboHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case WeiboManager.AUTH_SUCCESS:
                    WeiboManager.getUserInfo(CardSettingActivity.this, this);
                case WeiboManager.GET_USER_INFO:
                    TextView weiboTxt=(TextView)findViewById(R.id.userSinaWeibo);
                    weiboTxt.setText(msg.getData().getString("screen_name"));
            }
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 结果码不等于取消时候
        if(resultCode != RESULT_CANCELED) {

            switch(requestCode) {
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    if(CommonUtil.hasSdcard()) {
                        File tempFile=new File(Environment.getExternalStorageDirectory() + File.separator +IMAGE_FILE_NAME);
                        startPhotoZoom(Uri.fromFile(tempFile));
                    } else {
                        Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
                    }

                    break;
                case RESULT_REQUEST_CODE:
                    if(data != null) {
                        getImageToView(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }

    /**
     * 保存裁剪之后的图片数据
     * @param picdata
     */
    private void getImageToView(Intent data) {
        Bundle extras=data.getExtras();
        if(extras != null) {
            try {
                Bitmap photo=extras.getParcelable("data"); 
                Bitmap factbitMap=CommonUtil.toRoundCorner(photo, 25);
                String imagePath=CommonUtil.bitmapToFile(this, IMAGE_FILE_NAME, factbitMap);
                preferences.put("head_image_path", imagePath);
                faceImage.setImageBitmap(factbitMap);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void onCreateDialog(){
        LayoutInflater factory=LayoutInflater.from(this);
        alertView=factory.inflate(R.layout.alert_dialog, null);
        alertTxt=(EditText)alertView.findViewById(R.id.txt);
    }
}