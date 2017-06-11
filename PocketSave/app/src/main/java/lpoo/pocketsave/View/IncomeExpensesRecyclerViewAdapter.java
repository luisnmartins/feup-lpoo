package lpoo.pocketsave.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;
import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
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
    private Transaction[] mkeys;



    public  class ViewHolder extends RecyclerView.ViewHolder implements AnimateViewHolder {
        private final TextView listType;
        private final TextView listValue;
        private final TextView lisMonths;
        private final ArrayList<Category> categories;

        public ViewHolder(View v) {
            super(v);
            listValue = (TextView) v.findViewById(R.id.valueSettings);
            listType = (TextView) v.findViewById(R.id.typeSettings);
            lisMonths = (TextView) v.findViewById(R.id.monthsSettings);
            categories = DataManager.getInstance().getCategory(null,null,"Income");
            categories.add(DataManager.getInstance().getCategory(null,null,"Fixed Expense").get(0));
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    long catid = mkeys[getAdapterPosition()].getCatID();
                    Category temp = new Category(null, -1, false, 1);
                    temp.setID(catid);
                    int index = categories.indexOf(temp);
                    Category aux = categories.get(index);
                    initiateMonth(getAdapterPosition(),aux);
                }
            });
        }

        public TextView getlistValue() {
            return listValue;
        }
        public TextView getListType() {return listType;}
        public TextView getLisMonths() {return lisMonths;}
        public ArrayList<Category> getCategories(){return categories;}

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

    private  HashMap<Transaction,ArrayList<Integer>> mSortedMap = new HashMap<>();

    HashMap<Transaction,ArrayList<Integer>> getmSortedMap(){return mSortedMap;}







    public IncomeExpensesRecyclerViewAdapter(Context context,ArrayList<Transaction> dataSet,Comparator<Transaction> comp) {
        mContext=context;
        mDataSet = dataSet;
        mComparator = comp;

    }

    @Override
    public IncomeExpensesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_settingslist, viewGroup, false);

        return new IncomeExpensesRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IncomeExpensesRecyclerViewAdapter.ViewHolder viewHolder, final int position) {

        String value = Double.toString(mkeys[position].getValue());
        Transaction transaction = mkeys[position];
        ArrayList<Integer> aux = mSortedMap.get(transaction);
        String months = new String();
        if(!aux.isEmpty())
        {
            for(int i = 0; i < aux.size();i++)
            {
                if(i == aux.size()-1)
                    months += aux.get(i);
                else{
                    months += aux.get(i)+ ", ";

                }
            }
        }
        viewHolder.getlistValue().setText(String.valueOf(value));
        viewHolder.getLisMonths().setText(String.valueOf(months));
        viewHolder.getListType().setText(String.valueOf(mkeys[position].getDescription()));
    }

    @Override
    public int getItemCount() {
        return mSortedMap.size();
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


    public void remove(Transaction tr)
    {
        mSortedMap.remove(tr);
        notifyDataSetChanged();
    }


    public void add(HashMap<Transaction,ArrayList<Integer>> models) {
        if (models != null)
        {
            mSortedMap = models;
            Log.d(TAG,"mSortedMap SIZE" + mSortedMap.size());
            mkeys = mSortedMap.keySet().toArray(new Transaction[models.size()]);
            notifyDataSetChanged();
        }

    }


    public  void initiateMonth(int position,Category cat)
    {
        Intent newintent = new Intent(mContext,Month.class);
        Bundle b = new Bundle();

        if(cat.getTitle() == "Income")
        b.putBoolean("isIncome",true);
        else b.putBoolean("isIncome",false);
        b.putBoolean("isFirst",false);
        b.putDouble("income",mkeys[position].getValue());
        newintent.putExtras(b);
        mContext.startActivity(newintent);
    }


}
