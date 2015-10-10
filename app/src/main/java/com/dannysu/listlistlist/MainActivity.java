package com.dannysu.listlistlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Top container ListView
    private MultiLevelListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (MultiLevelListView)findViewById(R.id.listView);
        listView.setAdapter(this.getHorizontalScrollableItemAdapter());
    }

    // Returns an Adapter for populating the top container ListView.
    // Each item from the adapter are a HorizontalScrollableItem, since the items themselves will
    // be presenting a child horizontal ListView.
    private HorizontalScrollableItemAdapter getHorizontalScrollableItemAdapter() {
        List<HorizontalScrollableItem> items = new ArrayList<>();

        // Add 10 example items to the top container ListView, which scrolls vertically.
        for (int i = 0; i < 10; i++) {
            items.add(getHorizontalScrollableItem(i));
        }

        final HorizontalScrollableItemAdapter adapter = new HorizontalScrollableItemAdapter(this,
                R.layout.layout_containing_horizontal_listview,
                items.toArray(new HorizontalScrollableItem[items.size()]));

        return adapter;
    }

    // Creates a HorizontalScrollableItem. Each of these items would also contain an adapter for
    // populating the horizontal ListView with vertical ListViews.
    private HorizontalScrollableItem getHorizontalScrollableItem(int num) {
        List<VerticalScrollableItem> items = getVerticalScrollableItems(num);

        VerticalScrollableItemAdapter adapter =
                new VerticalScrollableItemAdapter(R.layout.layout_containing_vertical_listview, items,
                        listView);

        // Each HorizontalScrollableItem also contains an adapter
        return new HorizontalScrollableItem(adapter);
    }

    private List<VerticalScrollableItem> getVerticalScrollableItems(int num) {
        List<VerticalScrollableItem> items = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            String strings[] = new String[num];
            for (int j = 0; j < num; j++) {
                strings[j] = "Item " + j;
            }
            ListAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
            items.add(new VerticalScrollableItem(adapter));
        }

        return items;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
