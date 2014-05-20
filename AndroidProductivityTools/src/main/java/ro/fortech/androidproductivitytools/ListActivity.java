package ro.fortech.androidproductivitytools;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

//        @InjectView(R.id.listView) ListView mListView;
        private ListView mListView;

        private ListAdapter mAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);

//            // inject the fragment's base layout
//            ButterKnife.inject(this, rootView);

            mListView = (ListView) rootView.findViewById(R.id.listView);

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();

            ArrayList<String> items = new ArrayList<String>();

            for (int index = 0; index < 20; index++){

                items.add("Item + " + index);

            }

            mAdapter = new ListAdapter(items);

            mListView.setAdapter(mAdapter);

            // register to this Event Bus manager
            ListItemManager.getInstance().register(this);

            // load items asynchronously
            ListItemManager.getInstance().loadItemsAsync();

        }

//        @Override
//        public void onPause() {
//            super.onPause();
//
//            // unregister from this Event Bus manager
//            ListItemManager.getInstance().unregister(this);
//
//        }

        // called from the ListItemManager when the items were loaded from the secondary thread
        @Subscribe
        public void itemsLoaded(ArrayList<String> items) {

            mAdapter.updateDataSet(items);

        }


    }
}
