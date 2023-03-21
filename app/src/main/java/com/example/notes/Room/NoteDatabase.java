package com.example.notes.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database ( entities={Notes.class},version=1 )
public abstract class NoteDatabase extends RoomDatabase {

   public abstract NotesDao notesDao();
   public static NoteDatabase INSTANCE;

   public static NoteDatabase getDatabaseInstance(Context context){
       if (INSTANCE==null){
           INSTANCE=Room.databaseBuilder (
                   context.getApplicationContext (),
                   NoteDatabase.class,
                   "NotesDB")
                   .allowMainThreadQueries ()
                   .fallbackToDestructiveMigration ()
                   .build ();
       }
       return INSTANCE;
   }


}
