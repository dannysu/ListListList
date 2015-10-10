package com.dannysu.listlistlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.List;

// This is used by the second level horizontal ListView to show a bunch of items each having
// a vertical ListView
public class VerticalScrollableItemAdapter extends
        RecyclerView.Adapter<VerticalScrollableItemAdapter.ViewHolder> {

    private List<VerticalScrollableItem> items;
    private int resourceId;
    private Context context;
    private MultiLevelListView parentListView;

    public VerticalScrollableItemAdapter(int resourceId, List<VerticalScrollableItem> items,
                                     MultiLevelListView parentListView) {
        this.items = items;
        this.resourceId = resourceId;
        this.parentListView = parentListView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(resourceId, viewGroup, false);

        context = viewGroup.getContext();

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final VerticalScrollableItem item = items.get(i);

        ListAdapter adapter = item.getAdapter();

        if (viewHolder.listView != null && adapter != null) {
            viewHolder.listView.setAdapter(adapter);

            // We also set the parent ListView so that whenever the child ListView is scrolling
            // we disable touch events on the parent. This is to prevent the parent from interfering
            // with the interaction being done on the child ListView.
            viewHolder.listView.setParentListView(parentListView);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private MultiLevelListView listView;

        public ViewHolder(View itemView) {
            super(itemView);

            listView = (MultiLevelListView)itemView.findViewById(R.id.listView);
        }
    }
}
