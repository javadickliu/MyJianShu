package com.example.liudongxun.myjianshu.Adapter;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liudongxun.myjianshu.R;
import com.example.liudongxun.myjianshu.model.HomeDatasBean;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmall.ultraviewpager.UltraViewPager;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.List;

/**
 * Created by liudongxun on 2017/08/28.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener{

//这里的<>括号里面表示要传入一个继承了ViewHolder类的对象即可
       public static final int TYPE_HEADER=0;
       public static final int TYPE_NORMAL=1;
       private HomeDatasBean data;
       private MyItemOnClickListener myItemOnClickListener=null;
       private View mHeaderView;
       public RecyclerViewAdapter(HomeDatasBean data){
           this.data=data;
       }
       public void setmHeaderView(View headerview)//提供一个接口给adapter设置是否使用header
       {
         this.mHeaderView=headerview;
       }
    @Override
    public int getItemViewType(int position) {//这是个官方的方法返回的viewType跟下面onCreateViewHolder参数是对应的
         if(position==0) return TYPE_HEADER;
         else{
             return TYPE_NORMAL;
         }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//显示一个item就执行一下
        if(viewType==TYPE_HEADER&&mHeaderView!=null)//判断是否使能了header,并返回header对应的viewholder
        {
            return new ViewHolder(mHeaderView);
        }
     //   Log.d("test", "这里已经加载了正常的视图了");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);//创建和recycleview的item相关的布局和viewholder
        view.setOnClickListener(this);//为每个item设置监听
     //   Log.d("gggggggggg", "11111111111111111");
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//item的控件的事件逻辑在这里,
 //       Log.d("gggggggggg", "22222222222222");
        if(getItemViewType(position)==TYPE_HEADER) {return;}
        position=position-1;//因为下标志0用来放header，所以数据下标需要减1
        Uri uri= Uri.parse(data.getResult().getData().get(position).getThumbnail_pic_s().toString());//注意这里啊，list类型的数据是怎么用的
        holder.draweeView.setImageURI(uri);
        holder.textView_author.setText(data.getResult().getData().get(position).getAuthor_name());
        holder.textView_title.setText(data.getResult().getData().get(position).getTitle());
        holder.textView_data.setText(data.getResult().getData().get(position).getDate());
        holder.itemView.setTag(position);//加这个recyclerview的点击才生效
    }

    @Override
    public int getItemCount() {
     //   Log.d("gggggggggg", "33333333333333333");
        return data.getResult().getData().size()+1;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public SimpleDraweeView draweeView;
        public TextView textView_author;
        public TextView textView_title;
        public TextView textView_data;

        public ViewHolder(View view)//是上面加載的view
        {
            super(view);
        //    Log.d("gggggggggg", "4444444444444");
            if(mHeaderView==view) {return;}
            draweeView=view.findViewById(R.id.recyclerview_itme_pic_thumbnail1);
            textView_author=view.findViewById(R.id.recyclerview_itme_text_authorname);
            textView_title=view.findViewById(R.id.recyclerview_itme_text_texttitle);
            textView_data=view.findViewById(R.id.recyclerview_itme_text_data);
        }

    }
    public interface MyItemOnClickListener{//定义一个接口让外部调用
        void myItemClick(View v,int position);
    }
    public void setOnItemClickListener(MyItemOnClickListener listener)
    {
        this.myItemOnClickListener=listener;//传入外部实现了MyItemOnClickListener接口的对象的实例
    }

    @Override
    public void onClick(View view) {
         if(myItemOnClickListener!=null)//如果调用了setOnItemClickListener方法
         {
            // myItemOnClickListener.myItemClick(view,view.getTag());
             myItemOnClickListener.myItemClick(view, (int)view.getTag());
         }
    }
}
