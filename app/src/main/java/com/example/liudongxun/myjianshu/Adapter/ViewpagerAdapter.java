package com.example.liudongxun.myjianshu.Adapter;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.liudongxun.myjianshu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;
import java.util.List;

/**
 * Created by liudongxun on 2017/09/03.
 */

public class ViewpagerAdapter extends PagerAdapter {

   private List<String> list;
    public ViewpagerAdapter(List<String> list)
    {
       this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//
         LinearLayout linearLayout=(LinearLayout) LayoutInflater.from(container.getContext())
                                                 .inflate(R.layout.viewpager_item,null);
        SimpleDraweeView simpleDraweeView=(SimpleDraweeView)linearLayout.findViewById(R.id.viewpager_item_draweeview);
        Uri uri=Uri.parse(list.get(position));
        simpleDraweeView.setImageURI(uri);
        container.addView(linearLayout);
        return linearLayout ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout linearLayout=(LinearLayout)object;
        container.removeView(linearLayout);
    }
}
