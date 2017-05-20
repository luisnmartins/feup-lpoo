package lpoo.pocketsave.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import lpoo.pocketsave.View.dummy.DummyContent.DummyItem;
import lpoo.pocketsave.R;

import java.util.List;

public class MyOverviewListRecyclerViewAdapter extends RecyclerView.Adapter<MyOverviewListRecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "CustomAdapter";

    private String[] mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView listValue;
        private final TextView listDate;
        private final TextView listCat;
        private final ImageView CatIcon;


        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            listValue = (TextView) v.findViewById(R.id.listValue);
            listDate = (TextView) v.findViewById(R.id.listDate);
            listCat = (TextView) v.findViewById(R.id.listCat);
            CatIcon = (ImageView) v.findViewById(R.id.transIcon);
        }

        public TextView getlistValue() {
            return listValue;
        }
        public TextView getListDate() {return listDate;}
        public TextView getListCat() {return listCat;}
        public ImageView getCatIcon() {return CatIcon;}
    }


    public MyOverviewListRecyclerViewAdapter(String[] dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_overviewlist, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
       // Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getlistValue().setText(mDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

}
