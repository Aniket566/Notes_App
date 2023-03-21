package com.example.notes.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query ( "select * from NotesDB" )
    LiveData<List<Notes>>  getAllNotes();

    @Query ( "select * from NotesDB order by notes_priority DESC" )
    LiveData<List<Notes>>  highToLow();

    @Query ( "select * from NotesDB order by notes_priority ASC" )
    LiveData<List<Notes>>  lowToHigh();

    @Insert
    void insertNotes(Notes notes);

    @Query ( "delete from NotesDB where id=:id" )
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
