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

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private int times = 0;
    private com.alipay.euler.andfix.patch.PatchManager mPatchManager;
    private static final String APATCH_PATH = "/out.apatch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mViewPagerAdapter = new ViewPagerAdapter(this);

        mViewPager.setAdapter(mViewPagerAdapter);

        mPatchManager = new PatchManager(this);

        Log.e(TAG, A.a("good"));
        Log.e(TAG, "" + new A().b("s1", "s2"));
        Log.e(TAG, "" + new A().getI());

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

            // add patch at runtime
            // load patch
//            mPatchManager.loadPatch();
            String patchFileString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + APATCH_PATH;
            Log.d("TAG", "apatch:" + patchFileString + " added.");
//            Toast.makeText(MainActivity.this, patchFileString, Toast.LENGTH_LONG).show();
            try {
                // .apatch file path
//                Toast.makeText(MainActivity.this, patchFileString, Toast.LENGTH_SHORT).show();
                mPatchManager.addPatch(patchFileString);

                //add successfully and delete .apatch file in sdcard
                File file = new File(patchFileString);
                if (file.exists()) {
                    file.delete();
                }

            } catch (IOException e) {
                Log.e("TAG", "", e);
                Toast.makeText(MainActivity.this, "e: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            Toast.makeText(MainActivity.this, Fix.a("good"), Toast.LENGTH_SHORT).show();
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
