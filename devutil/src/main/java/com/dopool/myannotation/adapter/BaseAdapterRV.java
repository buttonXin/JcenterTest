package com.dopool.myannotation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator mOnItemClickListener 2017/2/9 0009.
 */
public abstract class BaseAdapterRV<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<T> mDatas = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;//点击事件

    /**
     * 添加数据
     */
    public void addData(List<T> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    //清除数据
    public void cleanData() {
        mDatas.clear();
    }

    //删除单条数据
    public void deleteData(int pos) {
        mDatas.remove(pos);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createVHolder(parent, viewType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final T t = mDatas.get(position);
        onBindVH(holder, position, t);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {

                    mOnItemClickListener.onItemClick(position, holder.itemView, t);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 添加数据
     */
    public void addItem(T t) {

        mDatas.add(0, t);
        notifyItemInserted(0);
    }

    /**
     * 删除数据,如果用notifyDataSetChanged();没有动画效果
     */
    public T removeItem(T t) {
        int position = mDatas.indexOf(t);
        mDatas.remove(position);
        notifyItemRemoved(position);
        return  t;
    }


    public abstract RecyclerView.ViewHolder createVHolder(ViewGroup parent, int viewType);

    protected abstract void onBindVH(RecyclerView.ViewHolder holder, int pos, T t);

    static abstract class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public <T>void setItemOnClickListener(OnItemClickListener<T> listener) {
        mOnItemClickListener = listener;
    }


    public interface OnItemClickListener<T> {
        void onItemClick(int position, View view, T t);
    }



}
