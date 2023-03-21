package com.example.notes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Layouts.UpdateNotesActivity;
import com.example.notes.MainActivity;
import com.example.notes.R;
import com.example.notes.Room.Notes;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    Context context;
    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesItem;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity=mainActivity;
        this.notes=notes;
        allNotesItem=new ArrayList<> (notes);
    }

    public void searchNotes(List<Notes> filterName){
        this.notes=filterName;
        notifyDataSetChanged ();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder ( LayoutInflater.from ( mainActivity ).inflate ( R.layout.item_notes, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notes notes1=notes.get ( position );
        holder.title.setText ( notes1.notesTitle );
        holder.subtitle.setText ( notes1.notesSubTitle );
        holder.date.setText ( notes1.notesDate );

        switch (notes1.notesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource ( R.drawable.green_shape );
                break;
            case "2":
                holder.notesPriority.setBackgroundResource ( R.drawable.yellow_shape );
                break;
            case "3":
                holder.notesPriority.setBackgroundResource ( R.drawable.red_shape );
                break;
        }
        holder.itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (mainActivity, UpdateNotesActivity.class );
                intent.putExtra ( "id",notes1.id );
                intent.putExtra ( "title",notes1.notesTitle );
                intent.putExtra ( "subtitle",notes1.notesSubTitle );
                intent.putExtra ( "notes",notes1.notes );
                intent.putExtra ( "priority",notes1.notesPriority );
                mainActivity.startActivity ( intent );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return notes.size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle, date;
        View notesPriority;

        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );
            title=itemView.findViewById ( R.id.notesTitle );
            subtitle=itemView.findViewById ( R.id.notesSubTitle );
            date=itemView.findViewById ( R.id.notesDate );
            notesPriority=itemView.findViewById ( R.id.notesPriority );
        }
    }
}
