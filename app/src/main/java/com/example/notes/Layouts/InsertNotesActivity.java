package com.example.notes.Layouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.Room.Notes;
import com.example.notes.ViewModel.NotesViewMode;
import com.example.notes.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {
    ActivityInsertNotesBinding binding;
    String title,subTitle,notes;
    NotesViewMode viewMode;
    String priority="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        binding=ActivityInsertNotesBinding.inflate ( getLayoutInflater () );
        setContentView ( binding.getRoot () );
        viewMode=ViewModelProviders.of ( this ).get ( NotesViewMode.class );

        binding.greenPriority.setOnClickListener ( v -> {
            binding.greenPriority.setImageResource ( R.drawable.ic_baseline_done_24 );
            binding.yellowPriority.setImageResource ( 0 );
            binding.redPriority.setImageResource ( 0 );

            priority="1";

        } );
        binding.redPriority.setOnClickListener ( v -> {
            binding.greenPriority.setImageResource ( 0 );
            binding.yellowPriority.setImageResource ( 0 );
            binding.redPriority.setImageResource ( R.drawable.ic_baseline_done_24 );
            priority="3";
        } );
        binding.yellowPriority.setOnClickListener ( v -> {
            binding.greenPriority.setImageResource ( 0 );
            binding.yellowPriority.setImageResource (  R.drawable.ic_baseline_done_24 );
            binding.redPriority.setImageResource ( 0);
            priority="2";

        } );

        binding.doneNotesBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                title=binding.notesTitle.getText ().toString ();
                subTitle=binding.notesSubTitle.getText ().toString ();
                notes=binding.notes.getText ().toString ();
                creteNotes(title,subTitle,notes);

            }
        } );

    }

    private void creteNotes(String title, String subTitle, String notes) {
        Date date=new Date ();
        CharSequence sequence=DateFormat.format ( "MMMM d. yyyy",date.getTime () );

        Notes notes1=new Notes ();
        notes1.notesTitle=title;
        notes1.notesSubTitle=subTitle;
        notes1.notes=notes;
        notes1.notesDate=sequence.toString ();
        notes1.notesPriority=priority;
        viewMode.insertNote ( notes1 );
        Toast.makeText ( this,"Notes Creted",Toast.LENGTH_LONG ).show ();
        finish ();
    }
}