package com.dannysu.listlistlist;

/*
 * List item containing a horizontal scrollable ListView
 */
public class HorizontalScrollableItem {

    private VerticalScrollableItemAdapter adapter;

    public HorizontalScrollableItem(VerticalScrollableItemAdapter adapter) {
        this.adapter = adapter;
    }

    public VerticalScrollableItemAdapter getAdapter() {
        return adapter;
    }
}
