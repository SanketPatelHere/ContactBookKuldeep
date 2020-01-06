package com.example.sanket.contactbooksanket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<DataPojo> lst;
    ArrayList<DataPojo> filterList;
    MyClickListener listener;

    MenuItem search;
    SearchView searchView;
    FloatingActionButton btnAdd;
    CustomAdapter adapter;
    private static final int REQUEST_CODE_ADD_CONTACT = 990;
    private static final int REQUEST_CODE_UPDATE_CONTACT = 880;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = (RecyclerView) findViewById(R.id.rv);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        lst = new ArrayList<>();
        lst.add(new DataPojo("sanket", "ramani", "9723031228"));
        listener = new MyClickListener() {
            @Override
            public void myOnClick(DataPojo dataPojo) {
                Intent i = new Intent(MainActivity.this, ContactShowActivity.class);
                i.putExtra("data", dataPojo);
                startActivityForResult(i, REQUEST_CODE_UPDATE_CONTACT);
            }
        };

        adapter = new CustomAdapter(this, lst, listener);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ContactAddActivity.class);
                startActivityForResult(i, REQUEST_CODE_ADD_CONTACT);
            }
        });
    }

    //not used
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == Activity.RESULT_OK) {

            if (data.getExtras().getParcelable("data") != null) {
                DataPojo dataPojo = data.getExtras().getParcelable("data");
                lst.add(dataPojo);
            }
            adapter.notifyDataSetChanged();

        } else if (requestCode == REQUEST_CODE_UPDATE_CONTACT && resultCode == Activity.RESULT_OK) {
            DataPojo dataPojo = data.getExtras().getParcelable("data");
            ListIterator<DataPojo> iterator = lst.listIterator();
            while (iterator.hasNext()) //for update and filter update
            {
                DataPojo next = iterator.next();
                if (next.getUuid().equals(dataPojo.getUuid())) {
                    iterator.set(dataPojo);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.search_menu, menu);
        search = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    filterList = lst;
                } else {
                    ArrayList<DataPojo> filteredList = new ArrayList<>();
                    for (DataPojo row : lst) {
                        if (row.getFirstname().toLowerCase().contains(newText.toLowerCase()) || row.getSecondname().toLowerCase().contains(newText.toLowerCase()) || row.getPhone().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList.add(row);
                        }
                        filterList = filteredList;
                    }
                }
                adapter.setFilter(filterList);
                return false;
            }
        });
        return true;
    }
}
