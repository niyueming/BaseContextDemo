/*
 * Copyright (c) 2017  Ni YueMing<niyueming@163.com>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */

package net.nym.basecontextdemo;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import net.nym.basecontextlibrary.common.NBaseActivity;

/**
 * @author niyueming
 * @date 2017-03-17
 * @time 09:50
 */

public abstract class BaseActivity extends NBaseActivity {

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private ViewGroup mContainer;
    private View mIndicator;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.base,null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            view.setFitsSystemWindows(true);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        super.setContentView(view);

        initDefaultView(layoutResID);
        initDefaultToolBar();

    }

    private void initDefaultToolBar() {
        if (mToolbar != null){
            setSupportActionBar(mToolbar);
//            mToolbar.setNavigationIcon(); //返回图标
            showBack(true);
        }
    }

    private void initDefaultView(int layoutResID) {
        mAppBarLayout = findView(R.id.appBarLayout);
        mToolbar = findView(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setTransitionGroup(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mToolbar.setForegroundGravity(Gravity.CENTER);
        }
        mContainer = findView(R.id.contentLayer);
        mIndicator = findView(R.id.indicator);
        mIndicator.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        mContainer.removeAllViews();
        View childView = getLayoutInflater().inflate(layoutResID,mContainer,false);
        mContainer.addView(childView);

    }

    protected void showBack(boolean isShow){
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
        }
    }

    protected void showTitle(boolean isShow){
        if (getSupportActionBar() == null){
            return;
        }
        if (isShow){
            getSupportActionBar().show();
        }else {
            getSupportActionBar().hide();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getText(titleId));
    }

    @Override
    public void setTitleColor(int textColor) {
        if (mToolbar != null){
            mToolbar.setTitleTextColor(textColor);
        }
    }

    protected void setTitleBackground(Drawable drawable){
        if (mToolbar != null){
            mToolbar.setBackground(drawable);
        }
    }

    protected void setTitleBackground(@DrawableRes int resId){
        if (mToolbar != null){
            mToolbar.setBackgroundResource(resId);
        }
    }

    protected void setTitleBackgroundColor(int color){
        if (mToolbar != null){
            mToolbar.setBackgroundColor(color);
        }
    }

    @Override
    public void toast(@NonNull String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(@StringRes int stringId) {
        Toast.makeText(this,stringId,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIndicator() {
        if (mIndicator != null){
            mIndicator.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dismissIndicator() {
        if (mIndicator != null){
            mIndicator.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (mIndicator != null){
            if (mIndicator.getVisibility() == View.VISIBLE){
                dismissIndicator();
                return;
            }
        }
        super.onBackPressed();
    }
}
