package com.example.notes.Respo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes.Room.NoteDatabase;
import com.example.notes.Room.Notes;
import com.example.notes.Room.NotesDao;

import java.util.List;

public class NotesRepo {
    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;

    public NotesRepo(Application application) {
        NoteDatabase noteDatabase=NoteDatabase.getDatabaseInstance ( application );
        notesDao=noteDatabase.notesDao ();
        getAllNotes=notesDao.getAllNotes ();
        highToLow=notesDao.highToLow ();
        lowToHigh=notesDao.lowToHigh ();
    }

   public void insertNotes(Notes notes){
        notesDao.insertNotes ( notes );
    }
   public void deleteNotes(int id){
        notesDao.deleteNotes ( id );
    }
   public void updateNotes(Notes notes){
        notesDao.updateNotes ( notes );
    }
}
