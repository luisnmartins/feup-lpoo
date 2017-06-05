package lpoo.pocketsave.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;
import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.Logic.DataManager;
import lpoo.pocketsave.Logic.Transaction;
import lpoo.pocketsave.R;

public class MyOverviewListRecyclerViewAdapter extends RecyclerView.Adapter<MyOverviewListRecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "CustomAdapter";

    private Context mContext;
    private ArrayList<Transaction> mDataSet;
    private  Comparator<Transaction> mComparator;
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



    public  class ViewHolder extends RecyclerView.ViewHolder implements AnimateViewHolder {
        private final TextView listValue;
        private final TextView listDate;
        private final TextView listCat;
        private final ImageView CatIcon;
        private final ImageView CatColor;
        private final ArrayList<Category> categories;
        private final ImageButton editTransButton;

        public ViewHolder(View v) {
            super(v);
            listValue = (TextView) v.findViewById(R.id.listValue);
            listDate = (TextView) v.findViewById(R.id.listDate);
            listCat = (TextView) v.findViewById(R.id.listCat);
            CatIcon = (ImageView) v.findViewById(R.id.transIcon);
            CatColor = (ImageView) v.findViewById(R.id.CatColor);
            categories = DataManager.getInstance().getCategory(null,null,"Variable Expense");
            editTransButton = (ImageButton) v.findViewById(R.id.editButtonOver);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                        loadTransaction(mSortedList.get(getAdapterPosition()),false);






                }
            });
        }

        public TextView getlistValue() {
            return listValue;
        }
        public TextView getListDate() {return listDate;}
        public TextView getListCat() {return listCat;}
        public ImageView getCatIcon() {return CatIcon;}
        public ImageView getCatColor(){return CatColor;}
        public ArrayList<Category> getCategoriesVariable() {return categories;}

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






    public MyOverviewListRecyclerViewAdapter(Context context,ArrayList<Transaction> dataSet,Comparator<Transaction> comp) {
        mContext=context;
        mDataSet = dataSet;
        mComparator = comp;
    }

    public void setmComparator(Comparator<Transaction> comp)
    {
        mComparator = comp;
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

        viewHolder.editTransButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadTransaction(mSortedList.get(position),true);

            }
        });

        String value = Double.toString(mSortedList.get(position).getValue());
        long catid = mSortedList.get(position).getCatID();
        int index = viewHolder.getCategoriesVariable().indexOf(new Category(catid,null,0,false,1));
        Category aux = viewHolder.getCategoriesVariable().get(index);
        viewHolder.getlistValue().setText(String.valueOf(value));
        viewHolder.getListCat().setText(String.valueOf(aux.getTitle()));
        viewHolder.getListDate().setText(String.valueOf(mSortedList.get(position).getDate()));
        if(mSortedList.get(position).getImage() != null)
        {

            viewHolder.getCatIcon().setImageResource(android.R.color.transparent);
            viewHolder.getCatIcon().setImageDrawable(Drawable.createFromPath(mSortedList.get(position).getImage()));


        }
        viewHolder.getCatColor().setBackgroundColor(aux.getColor());
    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    public SortedList<Transaction> getmSortedList()
    {
        return mSortedList;
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

    public  void loadTransaction(Transaction t,Boolean istoEdit)
    {

        Intent transactionIntent = new Intent(mContext,TransactionActivity.class);
        Bundle b = new Bundle();
        b.putBoolean("isToAdd",false);
        if(istoEdit)
            b.putBoolean("isToEdit",true);
        b.putDouble("value",t.getValue());
        b.putInt("isCash",t.isCashMethod());
        b.putLong("myID",t.getID());
        b.putString("description",t.getDescription());
        b.putString("date",t.getDate());
        b.putString("image",t.getImage());
        b.putLong("cat",t.getCatID());
        transactionIntent.putExtras(b);
        mContext.startActivity(transactionIntent);

    }

}
