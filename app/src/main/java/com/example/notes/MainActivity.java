package com.example.notes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    Adapter adapter;
    FloatingActionButton fab;
    List<Modal> notesList;
    DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, AddNotesActivity.class);
                startActivity(intent);

            }
        });
        notesList = new ArrayList<>();
        databaseClass= new DatabaseClass(this);
        fetchAllnotesFromDatabase();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(this,MainActivity.this,notesList);
        recyclerView.setAdapter(adapter);
    }

    void fetchAllnotesFromDatabase() {
        Cursor cursor= databaseClass.readAllData();
        if(cursor.getCount()==0){
//            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();

        }else {
            while(cursor.moveToNext()){
                notesList.add(new Modal(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        MenuItem searchItems=menu.findItem(R.id.searchbar);
        SearchView searchView= (SearchView) searchItems.getActionView();
        searchView.setQueryHint("search Notes Here");
        SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };
        searchView.setOnQueryTextListener(listener);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all_notes){
            deleteAllNotes();
        }
        return super.onOptionsItemSelected(item);
    }
    private  void deleteAllNotes(){
        DatabaseClass db= new DatabaseClass(MainActivity.this);
        db.deleteAllNotes();
        recreate();
    }
}

