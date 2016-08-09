package com.android.secret.phabricatordemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.secret.phabricatordemo.ui.fragment.TestFragment;

/**
 * Created by secret on 16/8/8.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    FragmentActivity fragmentActivity;

    public ViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity.getSupportFragmentManager());
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("title", "fixed fragment" + position);
        return Fragment.instantiate(fragmentActivity, TestFragment.class.getName());
    }

    @Override
    public int getCount() {
        return 3;
    }
}
