package ro.fortech.androidproductivitytools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by catalin on 5/11/2014.
 */
public class ListAdapter extends BaseAdapter {

    private ArrayList<String> mItems;

    public ListAdapter(ArrayList<String> items) {

        mItems = items == null ? new ArrayList<String>() : items;

    }

    public void updateDataSet(ArrayList<String> items) {

        mItems = items == null ? new ArrayList<String>() : items;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

            holder = new ViewHolder();
//            holder = new ViewHolder(view);
//            holder.mDescription = (TextView) view.findViewById(R.id.description);
//            holder.mTitle = (TextView) view.findViewById(R.id.title);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }

        holder.mDescription.setText(mItems.get(position) + " : " + position);
        holder.mTitle.setText(mItems.get(position));

        return view;
    }

    static class ViewHolder {

        public TextView mTitle;
        public TextView mDescription;

//        @InjectView(R.id.title) TextView mTitle;
//        @InjectView(R.id.description) TextView mDescription;

//        ViewHolder(View view){
//            // load the views for the view holder
//            ButterKnife.inject(this, view);
//        }

    }


    public class ViewHolder {
        public final TextView title;
        public final TextView description;
        public final View root;

        public ViewHolder(View root) {
            title = (TextView) root.findViewById(R.id.title);
            description = (TextView) root.findViewById(R.id.description);
            this.root = root;
        }
    }
}
