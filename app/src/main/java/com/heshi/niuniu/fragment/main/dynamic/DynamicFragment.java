package com.heshi.niuniu.fragment.main.dynamic;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseFragment;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerFragmentComponent;
import com.heshi.niuniu.di.module.FragmentModule;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class DynamicFragment extends BaseFragment<DynamicPresent>
        implements DynamicContract.Model {


    @Override
    protected void setupActivityComponent(AppComponent appComponent, FragmentModule fragmentModule) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .fragmentModule(fragmentModule)
                .build()
                .inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_dynamic;
    }

}
