package com.heshi.niuniu.ui.password.register;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.util.ToashUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class RegisterActivity extends BaseActivity<RegisterPresent>
        implements RegisterContract.Model {

    @BindView(R.id.text_register_name)
    EditText textRegisterName;
    @BindView(R.id.text_register_num)
    EditText textRegisterNum;
    @BindView(R.id.text_register_send_code)
    Button textRegisterSendCode;
    @BindView(R.id.text_register_commit)
    Button textRegisterCommit;
    @BindView(R.id.text_register_treaty)
    TextView textRegisterTreaty;
    @BindView(R.id.text_password)
    EditText textPassword;

    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(activityModule)
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_pass;

    }

    @OnClick({R.id.text_register_send_code, R.id.text_register_commit})
    public void onViewClicked(View view) {
        String userName = textRegisterName.getText().toString().trim();

        switch (view.getId()) {
            case R.id.text_register_send_code:
                if (TextUtils.isEmpty(userName)) {
                    ToashUtils.show(mContext, "手机号不能为空！");
                } else {
                    mPresenter.getCode(userName, textRegisterSendCode);
                }

                break;
            case R.id.text_register_commit:
                String code = textRegisterNum.getText().toString().trim();
                String pass = textPassword.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    ToashUtils.show(mContext, "手机号不能为空！");

                } else if (TextUtils.isEmpty(code)) {
                    ToashUtils.show(mContext, "验证码不能为空！");

                } else if (TextUtils.isEmpty(pass)) {
                    ToashUtils.show(mContext, "密码不能为空！");
                } else {
                    mPresenter.registerCou(userName, pass, code);
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.stopTimer();

    }
}
