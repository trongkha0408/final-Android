package com.example.languagecenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    private String url = "http://192.168.1.6/AppProject/FinalTerm/Images/";
    private Context context;
    private List<Post> mPosts;
    private int layout;

    public PostAdapter(Context context, List<Post> mPosts, int layout) {
        this.context = context;
        this.mPosts = mPosts;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return mPosts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
    private ImageView iv_lang_home;
    private TextView lang_home_name, lang_home_des, lang_home_price;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.iv_lang_home = view.findViewById(R.id.iv_lang_home);
            holder.lang_home_name = view.findViewById(R.id.lang_home_name);
            holder.lang_home_des = view.findViewById(R.id.lang_home_des);
            holder.lang_home_price = view.findViewById(R.id.lang_home_price);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Post post = mPosts.get(i);
        Picasso.get().load(url+post.getImage()).fit().into(holder.iv_lang_home);
        holder.lang_home_name.setText(post.getName());
        holder.lang_home_des.setText(post.getDescr());
        holder.lang_home_price.setText(post.getPrice());
        return view;
    }
}
