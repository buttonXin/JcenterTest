package com.dopool.mymapclear;

import android.support.annotation.BinderThread;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.dopool.myannotation.BindView;
import com.dopool.myannotation.XgUtils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_center)
    TextView mTextView;
    @BindView(R.id.img)
    ConstraintLayout img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        XgUtils.bindView(this);
        mTextView.setText("fsdfgasdgadsg");

        img.setBackground(ContextCompat.getDrawable(this , R.drawable.tmf1));
    }
}
