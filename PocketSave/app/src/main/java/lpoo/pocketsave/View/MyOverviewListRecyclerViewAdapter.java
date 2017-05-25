package lpoo.pocketsave.View;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.View.dummy.DummyContent.DummyItem;
import lpoo.pocketsave.R;

import java.util.ArrayList;
import java.util.List;

public class MyOverviewListRecyclerViewAdapter extends RecyclerView.Adapter<MyOverviewListRecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "CustomAdapter";

    private Context mContext;
    private ArrayList<Transaction> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder implements AnimateViewHolder {
        private final TextView listValue;
        private final TextView listDate;
        private final TextView listCat;
        private final ImageView CatIcon;


        public ViewHolder(View v) {
            super(v);
            listValue = (TextView) v.findViewById(R.id.listValue);
            listDate = (TextView) v.findViewById(R.id.listDate);
            listCat = (TextView) v.findViewById(R.id.listCat);
            CatIcon = (ImageView) v.findViewById(R.id.transIcon);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
        }

        public TextView getlistValue() {
            return listValue;
        }
        public TextView getListDate() {return listDate;}
        public TextView getListCat() {return listCat;}
        public ImageView getCatIcon() {return CatIcon;}

        @Override
        public void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
            ViewCompat.setAlpha(itemView, 0);
        }

        @Override
        public void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {

        }

        @Override
        public void animateAddImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(0)
                    .alpha(1)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }

        @Override
        public void animateRemoveImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(-itemView.getHeight() * 0.3f)
                    .alpha(0)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }
    }


    public MyOverviewListRecyclerViewAdapter(Context context,ArrayList<Transaction> dataSet) {
        mContext=context;
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_overviewlist, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
       // Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        //Picasso.with(mContext).load(R.mipmap.ic_launcher).into(viewHolder.getCatIcon());
        viewHolder.getlistValue().setText(String.valueOf(mDataSet.get(position).getValue()));
        viewHolder.getListCat().setText(String.valueOf(mDataSet.get(position).getCatID()));
        viewHolder.getListDate().setText(String.valueOf(mDataSet.get(position).getDate()));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public  void remove(int position)
    {
        mDataSet.remove(position);
        this.notifyItemRemoved(position);

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public  void add(Transaction text,int position)
    {
        mDataSet.add(position,text);
        this.notifyItemInserted(position);
    }
}
