package com.example.helloworld;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.helloworld.databinding.ActivityDemoBinding;
import com.example.helloworld.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityDemoBinding b;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityDemoBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("Recycler View");

        setupRecyclerView();
    }

    private void setupRecyclerView() {

//        Data: List of Strings
        List<Item> items = Item.listFromCoursesStrings(new ArrayList<>(Arrays.asList("Apple", "Banana", "Watermelon", "Grapes", "Kiwi", "Chiku", "Peach", "Plum", "Pineapple", "Apricot",
                "Blueberry", "Banana", "Watermelon", "Grapes", "Kiwi", "Strawberry", "Peach", "Plum", "Pineapple", "Apricot", "Apple", "Banana", "Watermelon", "Grapes", "Kiwi", "Chiku", "Peach", "Plum", "Pineapple", "Apricot",
                "Apple", "Banana", "Watermelon", "Grapes", "Kiwi", "Chiku", "Peach", "Plum", "Pineapple", "Apricot")));


        adapter = new ItemAdapter(this, items);

        b.list.setLayoutManager(new LinearLayoutManager(this));

        b.list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.searchIcon).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
