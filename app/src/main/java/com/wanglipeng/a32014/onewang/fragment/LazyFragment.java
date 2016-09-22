package com.wanglipeng.a32014.onewang.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by 32014 on 2016/9/20.
 */
public abstract class LazyFragment extends Fragment {
    protected boolean isVisible;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("=======","==getUserVisibleHint====="+getUserVisibleHint());
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();

        }
    }

    protected void onVisible() {
        Log.e("===========","+++++++++++");
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }


}
