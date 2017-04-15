package com.yapp.kindpickyeatingandroid.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.merhold.extensiblepageindicator.ExtensiblePageIndicator;
import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.adapter.SimpleFragmentAdapter;
import com.yapp.kindpickyeatingandroid.fragment.SimpleFragment;

public class TutorialActivity extends AppCompatActivity {

    private SimpleFragmentAdapter mSimpleFragmentAdapter;
    private ViewPager mViewPager;
    private ExtensiblePageIndicator extensiblePageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        extensiblePageIndicator = (ExtensiblePageIndicator) findViewById(R.id.flexibleIndicator);
        mSimpleFragmentAdapter = new SimpleFragmentAdapter(getSupportFragmentManager());

        mSimpleFragmentAdapter.addFragment(SimpleFragment.newInstance(R.color.frag1, R.drawable.tutorial1));
        mSimpleFragmentAdapter.addFragment(SimpleFragment.newInstance(R.color.frag2, R.drawable.tutorial2));
        mSimpleFragmentAdapter.addFragment(SimpleFragment.newInstance(R.color.frag3, R.drawable.tutorial3));
        mSimpleFragmentAdapter.addFragment(SimpleFragment.newInstance(R.color.frag4, R.drawable.tutorial4));

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSimpleFragmentAdapter);
        extensiblePageIndicator.initViewPager(mViewPager);

    }
}
