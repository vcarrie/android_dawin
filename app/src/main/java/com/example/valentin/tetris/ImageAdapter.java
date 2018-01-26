package com.example.valentin.tetris;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by valentin on 12/01/18.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Integer> images;

    public ImageAdapter(Context c, ArrayList<Integer> images) {
        mContext = c;
        this.images = images;

    }

    public int getCount() {
        return images.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {

            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(30, 30));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0,1,0,1);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(images.get(position));
        return imageView;
    }

}
