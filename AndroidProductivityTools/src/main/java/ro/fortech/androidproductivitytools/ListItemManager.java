package ro.fortech.androidproductivitytools;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

import java.util.ArrayList;

/**
 * Created by catalin on 5/15/2014.
 */
public class ListItemManager extends Bus {

    private static ListItemManager mInstance = new ListItemManager();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public static ListItemManager getInstance() {
        return mInstance;
    }

    private ListItemManager() {
    }

    // fixes the issue when having to post events on UI thread...
    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ListItemManager.super.post(event);
                }
            });
        }
    }

    public void loadItemsAsync(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList<String> items = new ArrayList<String>();

                for (int index = 0; index < 80; index++){

                    items.add("Item + " + index);

                }

                // send the items
                post(items);

            }
        }).start();

    }

}
