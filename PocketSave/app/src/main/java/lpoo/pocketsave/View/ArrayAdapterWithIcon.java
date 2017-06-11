package lpoo.pocketsave.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lpoo.pocketsave.Logic.Category;
import lpoo.pocketsave.R;

/**
 * Created by Carlos Freitas on 28/05/2017.
 */

public class ArrayAdapterWithIcon extends ArrayAdapter<Category> {

    private List<Integer> images;

    public ArrayAdapterWithIcon(Context context, List<Category> items) {
        super(context, android.R.layout.select_dialog_item, items);
        //this.images = images;
    }

    public ArrayAdapterWithIcon(Context context, Category[] items, Integer[] images) {
        super(context, android.R.layout.select_dialog_item, items);
        this.images = Arrays.asList(images);
    }

    public ArrayAdapterWithIcon(Context context, int items, int images) {
        super(context, android.R.layout.select_dialog_item, (Category[]) context.getResources().getTextArray(items));

        final TypedArray imgs = context.getResources().obtainTypedArray(images);
        this.images = new ArrayList<Integer>() {{ for (int i = 0; i < imgs.length(); i++) {add(imgs.getResourceId(i, -1));} }};
        imgs.recycle();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.shopping_cart, 0, 0, 0);
        } else {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.shopping_cart, 0, 0, 0);

        }
        textView.setCompoundDrawablePadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getContext().getResources().getDisplayMetrics()));
        return view;
    }

}