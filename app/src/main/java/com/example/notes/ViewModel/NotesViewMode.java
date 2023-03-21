package com.example.notes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notes.Respo.NotesRepo;
import com.example.notes.Room.Notes;

import java.util.List;

public class NotesViewMode extends AndroidViewModel {

    public NotesRepo notesRepo;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;


    public NotesViewMode(@NonNull Application application) {
        super ( application );
         notesRepo=new NotesRepo ( application );
         getAllNotes=notesRepo.getAllNotes;
         highToLow=notesRepo.highToLow;
         lowToHigh=notesRepo.lowToHigh;
    }

   public void insertNote(Notes notes){
        notesRepo.insertNotes ( notes );
    }
   public void deleteNote(int id){
        notesRepo.deleteNotes ( id );
    }
    public void updateNote(Notes notes){
        notesRepo.updateNotes ( notes );
    }
}
