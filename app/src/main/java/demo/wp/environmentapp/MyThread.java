package demo.wp.environmentapp;

import android.util.Log;

/**
 * Created by Owner on 2015/10/3.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i=0;i<1000000;i++)
        {
            Log.i("mylog", i + "");
        }
    }
}
