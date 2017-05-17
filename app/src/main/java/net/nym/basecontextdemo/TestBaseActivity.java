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

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import net.nym.aphbasecontextlibrary.common.BaseActivity;

import java.util.Locale;
import java.util.Random;

/**
 * @author niyueming
 * @date 2017-03-17
 * @time 11:13
 */

public class TestBaseActivity extends BaseActivity {
    boolean isShowBack;
    boolean isShowTitle;
    int[] colors = {Color.BLACK,Color.RED,Color.BLUE,Color.CYAN,Color.DKGRAY,Color.GRAY,Color.GREEN,Color.LTGRAY
                    ,Color.MAGENTA,Color.YELLOW,Color.WHITE};
    int i = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_base);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showIndicator:
                showIndicator();
                break;
            case R.id.changeBack:
                showBack(isShowBack = !isShowBack);
                break;
            case R.id.changeTitle:
                Random random = new Random();
                setTitle(String.format(Locale.getDefault(),"title%d",random.nextInt(100)));
                break;
            case R.id.changeTitleColor:
                setTitleColor(colors[(i++)%colors.length]);
                break;
            case R.id.changeTitleBackground:
                setTitleBackgroundColor(colors[(i++)%colors.length]);
                break;
            case R.id.changeShowTitle:
                showTitle(isShowTitle = !isShowTitle);
                break;
            case R.id.setStatusBarColor:
                setStatusBarColor(colors[(i++)%colors.length]);
                break;
        }
    }
}
