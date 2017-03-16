package net.nym.basecontextdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.zhy.changeskin.SkinManager;

import net.nym.changeskinimpllibrary.impl.SkinManagerImpl;
import net.nym.changeskinlibrary.operation.NSkinManager;

public class MainActivity extends AppCompatActivity {

    NSkinManager<SkinManager> mSkinManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化应该在application里
        mSkinManager = SkinManagerImpl.getInstance(this);
        mSkinManager.getManager().init(this);

        mSkinManager.register(this);
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                mSkinManager.changeSkin("red",null);
                break;
            case R.id.button2:
                mSkinManager.changeSkin("green",null);
                break;
            case R.id.button3:
                mSkinManager.clear();
                break;
            case R.id.button4:
                mSkinManager.switchSkinMode(null);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSkinManager.unregister(this);
    }
}
