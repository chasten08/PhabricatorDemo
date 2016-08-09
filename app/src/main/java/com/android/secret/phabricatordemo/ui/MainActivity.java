package com.android.secret.phabricatordemo.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alipay.euler.andfix.patch.PatchManager;
import com.android.secret.phabricatordemo.A;
import com.android.secret.phabricatordemo.Fix;
import com.android.secret.phabricatordemo.R;
import com.android.secret.phabricatordemo.ViewPagerAdapter;
import com.android.secret.phabricatordemo.util.PatchUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private int times = 0;
    private PatchUtil patchUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mViewPagerAdapter = new ViewPagerAdapter(this);

        mViewPager.setAdapter(mViewPagerAdapter);

        patchUtil = PatchUtil.getInstance(getApplication());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Intent intent = new Intent(this, MainService.class);
//            if (times % 2 == 0) {
//                startService(intent);
//            } else {
//                stopService(intent);
//            }
//            times++;
            patchUtil.loadPatch();
            patchUtil.addPatch();
            Toast.makeText(this, Fix.a("good"), Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
