package com.dannysu.listlistlist;

import android.widget.ListAdapter;

/*
 * List item containing a vertically scrollable ListView
 */
public class VerticalScrollableItem {

    private ListAdapter adapter;

    // Initialize it with the ListAdapter necessary to populate and drive the vertical ListView
    // contained by this item
    public VerticalScrollableItem(ListAdapter adapter) {
        this.adapter = adapter;
    }

    public ListAdapter getAdapter() {
        return adapter;
    }
}