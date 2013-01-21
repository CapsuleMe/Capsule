package com.capsule.android;

import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.capsule.android.widget.OnViewChangeListener;
import com.capsule.android.widget.ScrollLayout;

public class AgreementActivity extends BaseActivity implements OnViewChangeListener{

    private LinearLayout focueLayout;

    private ScrollLayout scrollLayout;

    private int[] images=new int[]{R.drawable.agreement_one, R.drawable.agreement_two, R.drawable.agreement_three};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        scrollLayout=(ScrollLayout)findViewById(R.id.scrollLayout);
        scrollLayout.setOnViewChangeListener(this);
        focueLayout=(LinearLayout)this.findViewById(R.id.focus_layout);
        ImageView iv=null;
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for(int i=0;i<images.length;i++) {
            scrollLayout.addView(addImageView(images[i], i));
            iv=addImageView(R.drawable.page_indicator_bg, i);
            iv.setLayoutParams(lp);
            iv.setPadding(10, 10, 10, 10);
            iv.setClickable(true);
            iv.setEnabled(true);
            focueLayout.addView(iv);
        }
        setCurrentItme();

    }

    private ImageView addImageView(int id, int tag) {
        ImageView iv=new ImageView(this);
        iv.setImageResource(id);
        iv.setTag(tag);
        return iv;
    }

    private void setCurrentItme() {
        int item=(Integer)scrollLayout.getCurScreen();
        for(int i=0;i<images.length;i++) {
            ImageView view=(ImageView)focueLayout.getChildAt(i);
            if(i == item) {
                view.setEnabled(false);
            } else {
                view.setEnabled(true);
            }
        }
    }

    public void onViewChange(int view) {
        setCurrentItme();
    }
}
