package com.example.liudongxun.myjianshu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.IllegalFormatException;

/**
 * Created by liudongxun on 2017/09/01.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

   private Context context;
   private Drawable itemDivider;
   private int orientation;
   public static int HORIZONTAL_LIST= LinearLayoutManager.HORIZONTAL;
   public static int VERTIACAL_LIST=LinearLayoutManager.VERTICAL;
   public MyItemDecoration(Context context,int orientation) {
       this.orientation=orientation;
       final TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.listDividerAlertDialog});
       this.itemDivider = typedArray.getDrawable(0);
       typedArray.recycle();
   }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(orientation==HORIZONTAL_LIST)
        {
         drawVerticalLine(c,parent,state);
        }else if(orientation==VERTIACAL_LIST)
        {
         drawHorizontalLine(c,parent,state);
        }
    }
    public void drawVerticalLine(Canvas c,RecyclerView parent,RecyclerView.State state)
    {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
             final int top = child.getBottom() + params.bottomMargin;
             final int bottom = top + itemDivider.getIntrinsicHeight();
            itemDivider.setBounds(left, top, right, bottom);
            itemDivider.draw(c);
        }
    }
    public void drawHorizontalLine(Canvas c,RecyclerView parent,RecyclerView.State state)
    {
        int   top = parent.getPaddingLeft();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + itemDivider.getIntrinsicWidth();
            itemDivider.setBounds(left, top, right, bottom);
            itemDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(orientation==HORIZONTAL_LIST)
        {
          outRect.set(0,0,0,itemDivider.getIntrinsicHeight());
        }
        else{
         outRect.set(0,0,itemDivider.getIntrinsicWidth(),0);
        }
    }
}
