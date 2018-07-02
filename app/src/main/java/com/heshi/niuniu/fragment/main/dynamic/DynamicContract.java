package com.heshi.niuniu.fragment.main.dynamic;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class DynamicContract {

    interface Model extends MModel {

    }

    interface Presenter extends IPresenter<Model> {

    }

}
