package lpoo.pocketsave.View;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

/**
 * Created by Carlos Freitas on 31/05/2017.
 */

public class IncomeExpensesRecyclerViewAdapter extends RecyclerView.Adapter<IncomeExpensesRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "CustomAdapter";

    private Context mContext;
    private ArrayList<Transaction> mDataSet;
    private Comparator<Transaction> mComparator;



    public static class ViewHolder extends RecyclerView.ViewHolder implements AnimateViewHolder {
        private final TextView listType;
        private final TextView listValue;
        private final TextView lisMonths;

        public ViewHolder(View v) {
            super(v);
            listValue = (TextView) v.findViewById(R.id.valueSettings);
            listType = (TextView) v.findViewById(R.id.typeSettings);
            lisMonths = (TextView) v.findViewById(R.id.monthsSettings);
        }

        public TextView getlistValue() {
            return listValue;
        }
        public TextView getListType() {return listType;}
        public TextView getLisMonths() {return lisMonths;}

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

    private final SortedList<Transaction> mSortedList = new SortedList<>(Transaction.class, new SortedList.Callback<Transaction>() {
        @Override
        public int compare(Transaction a, Transaction b) {
            return mComparator.compare(a, b);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(Transaction oldItem, Transaction newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(Transaction item1, Transaction item2) {
            return item1 == item2;
        }
    });




    public IncomeExpensesRecyclerViewAdapter(Context context,ArrayList<Transaction> dataSet,Comparator<Transaction> comp) {
        mContext=context;
        mDataSet = dataSet;
        mComparator = comp;
    }

    public void setmComparator(Comparator<Transaction> comp)
    {
        mComparator = comp;
    }
    @Override
    public IncomeExpensesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_overviewlist, viewGroup, false);

        return new IncomeExpensesRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IncomeExpensesRecyclerViewAdapter.ViewHolder viewHolder, final int position) {

        String value = Double.toString(mSortedList.get(position).getValue());
        viewHolder.getlistValue().setText(String.valueOf(value));
        viewHolder.getLisMonths().setText(String.valueOf(mSortedList.get(position).getDate()));
        viewHolder.getListType().setText(String.valueOf(mSortedList.get(position).getCatID()));
    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
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

    public void add(Transaction tr)
    {
        mSortedList.add(tr);
    }

    public void remove(Transaction tr)
    {
        mSortedList.remove(tr);
    }

    public void add(List<Transaction> models) {
        if (models != null)
            mSortedList.addAll(models);
    }

    public void remove(List<Transaction> models) {
        mSortedList.beginBatchedUpdates();
        for (Transaction model : models) {
            mSortedList.remove(model);
        }
        mSortedList.endBatchedUpdates();
    }

    public void replaceAll(List<Transaction> models) {
        mSortedList.beginBatchedUpdates();
        for (int i = mSortedList.size() - 1; i >= 0; i--) {
            final Transaction model = mSortedList.get(i);
            if (!models.contains(model)) {
                mSortedList.remove(model);
            }
        }
        mSortedList.addAll(models);
        mSortedList.endBatchedUpdates();
    }
}
