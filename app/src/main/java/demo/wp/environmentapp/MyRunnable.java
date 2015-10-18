package demo.wp.environmentapp;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by Owner on 2015/10/3.
 */
public class MyRunnable implements Runnable {
    Handler handler;
    MyRunnable(Handler handler)
    {
        this.handler=handler;
    }
    @Override
    public void run() {
        for (int i=0;i<1000000;i++)
        {
            Log.i("mylog", i + "");
            Message msg=handler.obtainMessage();
            msg.what=1;
            msg.arg1=i;
            handler.sendMessage(msg);
        }
    }
}
