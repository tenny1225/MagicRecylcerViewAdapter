package com.xz.magicAdapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by noahkong on 17-8-22.
 */

public abstract class VHProvider<T> {
    public interface ItemClickListener<S> {
        void onItemClick(S o);
    }
    public interface ItemLongClickListener<S> {
        void onItemLongClick(S o);
    }
    protected ItemClickListener<T> onClickListener;
    protected ItemLongClickListener<T> onLongClickListener;

    public VHProvider() {
    }

    public void setOnClickListener(ItemClickListener<T> onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(ItemLongClickListener<T> onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public abstract View getItemView(ViewGroup parent);


    public abstract void onBind(MagicRecyclerAdapter.VH holder, T obj);
}
