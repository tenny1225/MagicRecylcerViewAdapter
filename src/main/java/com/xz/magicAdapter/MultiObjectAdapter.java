package com.xz.magicAdapter;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by noahkong on 17-8-22.
 */

public class MultiObjectAdapter extends MagicRecyclerAdapter<Object> {
    private SparseArray<VHProvider> multiVH = new SparseArray<>();

    public MultiObjectAdapter(List<Object> itemList) {
        super(itemList);
        setListItemClickListener(new ListItemClickListener<Object>() {
            @Override
            public void onItemClick(Object o, int p) {
                VHProvider.ItemClickListener l = multiVH.get(getItemViewType(p)).onClickListener;
                if (l != null) {
                    l.onItemClick(o);
                }
            }

            @Override
            public void onItemLongClick(Object o, int p) {
                VHProvider.ItemLongClickListener l = multiVH.get(getItemViewType(p)).onLongClickListener;
                if (l != null) {
                    l.onItemLongClick(o);
                }
            }
        });
    }

    public void register(Class cla, VHProvider provider) {
        multiVH.put(cla.hashCode(), provider);
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getClass().hashCode();
    }

    @Override
    protected View getItemView(ViewGroup parent, int viewType) {
        return multiVH.get(viewType).getItemView(parent);
    }

    @Override
    protected void onBind(VH holder, Object obj) {
        multiVH.get(obj.getClass().hashCode()).onBind(holder, obj);
    }
}
