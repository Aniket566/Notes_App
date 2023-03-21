package com.example.notes.Layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.Room.Notes;
import com.example.notes.ViewModel.NotesViewMode;
import com.example.notes.databinding.ActivityInsertNotesBinding;
import com.example.notes.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {
    ActivityUpdateNotesBinding binding;
    String priority="1";
    String sTitle, sSubTitle, sNotes, sPriority;
    NotesViewMode viewMode;
    int sId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        binding=ActivityUpdateNotesBinding.inflate ( getLayoutInflater () );
        setContentView ( binding.getRoot () );

        viewMode=ViewModelProviders.of ( this ).get ( NotesViewMode.class );

        sId=getIntent ().getIntExtra ( "id", 0 );
        sTitle=getIntent ().getStringExtra ( "title" );
        sSubTitle=getIntent ().getStringExtra ( "subtitle" );
        sNotes=getIntent ().getStringExtra ( "notes" );
        sPriority=getIntent ().getStringExtra ( "priority" );

        binding.upTitle.setText ( sTitle );
        binding.upSubTitle.setText ( sSubTitle );
        binding.upNotes.setText ( sNotes );
        if (priority.equals ( "1" )) {
            binding.firstPriority.setImageResource ( R.drawable.ic_baseline_done_24 );
        } else if (priority.equals ( "2" )) {
            binding.secondPriority.setImageResource ( R.drawable.ic_baseline_done_24 );
        } else if (priority.equals ( "2" )) {
            binding.thirdPriority.setImageResource ( R.drawable.ic_baseline_done_24 );
        }


        binding.firstPriority.setOnClickListener ( v -> {
            binding.firstPriority.setImageResource ( R.drawable.ic_baseline_done_24 );
            binding.secondPriority.setImageResource ( 0 );
            binding.thirdPriority.setImageResource ( 0 );

            priority="1";

        } );
        binding.thirdPriority.setOnClickListener ( v -> {
            binding.firstPriority.setImageResource ( 0 );
            binding.secondPriority.setImageResource ( 0 );
            binding.thirdPriority.setImageResource ( R.drawable.ic_baseline_done_24 );
            priority="3";
        } );
        binding.secondPriority.setOnClickListener ( v -> {
            binding.firstPriority.setImageResource ( 0 );
            binding.secondPriority.setImageResource ( R.drawable.ic_baseline_done_24 );
            binding.thirdPriority.setImageResource ( 0 );
            priority="2";
        } );

        binding.updateNotesBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String title=binding.upTitle.getText ().toString ();
                String subTitle=binding.upSubTitle.getText ().toString ();
                String notes=binding.upSubTitle.getText ().toString ();
                updateData ( title, subTitle, notes );


            }
        } );

    }

    private void updateData(String title, String subTitle, String notes) {
        Date date=new Date ();
        CharSequence sequence=DateFormat.format ( "MMMM d. yyyy", date.getTime () );
        Notes upNote=new Notes ();
        upNote.id=sId;
        upNote.notesTitle=title;
        upNote.notesSubTitle=subTitle;
        upNote.notes=notes;
        upNote.notesDate=sequence.toString ();
        upNote.notesPriority=priority;
        viewMode.updateNote ( upNote );


        Toast.makeText ( this, "Notes Updted", Toast.LENGTH_LONG ).show ();
        finish ();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.delete_menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId ()==R.id.delete)
        {
            BottomSheetDialog dialog=new BottomSheetDialog ( UpdateNotesActivity.this);
            View view=LayoutInflater.from ( UpdateNotesActivity.this ).inflate ( R.layout.delete_bottom_sheet,(LinearLayout)findViewById ( R.id.bottomSheet ) );
            dialog.setContentView ( view );

            TextView no,yes;
            yes=view.findViewById ( R.id.txtYes );
            no=view.findViewById ( R.id.txtNo );
            yes.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    viewMode.deleteNote ( sId );
                    finish ();
                }
            } );
            no.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    dialog.dismiss ();
                }
            } );
            dialog.show ();


        }
        return true;
    }
}