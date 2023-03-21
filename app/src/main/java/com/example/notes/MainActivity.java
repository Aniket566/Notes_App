package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.notes.Adapter.NotesAdapter;
import com.example.notes.Layouts.InsertNotesActivity;
import com.example.notes.Room.Notes;
import com.example.notes.ViewModel.NotesViewMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton newNotesBtn;
    RecyclerView recyclerView;
    NotesViewMode viewMode;
    NotesAdapter adapter;
    TextView noFilter, HighToLow, LowToHigh;
    List<Notes> filterNotesAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        newNotesBtn=findViewById ( R.id.newNotesBtn );
        recyclerView=findViewById ( R.id.recyclerView );

        noFilter=findViewById ( R.id.txtNoFilter );
        HighToLow=findViewById ( R.id.txtHighFilter );
        LowToHigh=findViewById ( R.id.txtLowFilter );

        noFilter.setBackgroundResource ( R.drawable.shape_f_selected );
        noFilter.setOnClickListener ( v -> {
            loadData(0);
            HighToLow.setBackgroundResource ( R.drawable.shape_f );
            LowToHigh.setBackgroundResource ( R.drawable.shape_f );
            noFilter.setBackgroundResource ( R.drawable.shape_f_selected );

        } );
        HighToLow.setOnClickListener ( v -> {
            loadData(1);
            HighToLow.setBackgroundResource ( R.drawable.shape_f_selected );
            LowToHigh.setBackgroundResource ( R.drawable.shape_f );
            noFilter.setBackgroundResource ( R.drawable.shape_f );

        } );
        LowToHigh.setOnClickListener ( v -> {
            loadData(2);
            HighToLow.setBackgroundResource ( R.drawable.shape_f );
            LowToHigh.setBackgroundResource ( R.drawable.shape_f_selected );
            noFilter.setBackgroundResource ( R.drawable.shape_f );

        } );

        viewMode=ViewModelProviders.of ( this ).get ( NotesViewMode.class );


        newNotesBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent ( MainActivity.this, InsertNotesActivity.class );
                startActivity ( intent );
            }
        } );
        viewMode.getAllNotes.observe ( this, new Observer<List<Notes>> () {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter (notes);
                filterNotesAllList=notes;
            }
        } );

    }

    private void loadData(int i) {
        if (i==0)
        {
            viewMode.getAllNotes.observe ( this, new Observer<List<Notes>> () {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter (notes);
                    filterNotesAllList=notes;
                }
            } );
        }
        else if (i==1)
        {
            viewMode.highToLow.observe ( this, new Observer<List<Notes>> () {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter (notes);
                    filterNotesAllList=notes;
                }
            } );
        }
        else if (i==2)
        {
            viewMode.lowToHigh.observe ( this, new Observer<List<Notes>> () {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter (notes);
                    filterNotesAllList=notes;
                }
            } );
        }

    }

    public void setAdapter(List<Notes> notes) {
        recyclerView.setLayoutManager ( new StaggeredGridLayoutManager ( 2, StaggeredGridLayoutManager.VERTICAL) );
        adapter=new NotesAdapter ( MainActivity.this, notes );
        recyclerView.setAdapter ( adapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.search_notes,menu );
        MenuItem item=menu.findItem ( R.id.app_bar_search );
        android.widget.SearchView searchView=(android.widget.SearchView) item.getActionView ();
        searchView.setQueryHint ( "Search Notes Here..." );
        searchView.setOnQueryTextListener ( new SearchView.OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                NotesFilter(s);
                return false;
            }
        } );

        return true;
    }

    private void NotesFilter(String newText) {
        ArrayList<Notes> arrayList=new ArrayList<> ();
        for (Notes notes:this.filterNotesAllList)
        {
            if (notes.notesTitle.contains ( newText ) || notes.notesSubTitle.contains ( newText ) )
            {
                arrayList.add ( notes );
            }
        }
        this.adapter.searchNotes ( arrayList );
    }
}