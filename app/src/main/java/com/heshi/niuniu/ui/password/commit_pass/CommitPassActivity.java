package com.heshi.niuniu.ui.password.commit_pass;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class CommitPassActivity extends BaseActivity<CommitPassPresent>implements
        CommitPassContract.Model {


    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {
        DaggerActivityComponent.builder()
                .activityModule(activityModule)
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commit_pass;
    }
}
