package com.heshi.niuniu.util.recyclerview.manager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by FrameJack on 2017/7/12.
 * RecyclerView 滚动禁止和开启
 */

public class ScrollGridLayoutManager extends GridLayoutManager {

    private boolean isScrollEnabled = true;
    public ScrollGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public ScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }


    public ScrollGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }


    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }

}
