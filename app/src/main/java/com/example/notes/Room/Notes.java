package com.example.notes.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="NotesDB")
public class Notes {
    @PrimaryKey(autoGenerate=true)
    public int id;

    @ColumnInfo(name="notes_title")
    public String notesTitle;

    @ColumnInfo(name="notes_sub_title")
    public String notesSubTitle;

    @ColumnInfo(name="notes_date")
    public String notesDate;

    @ColumnInfo(name="notes_priority")
    public String notesPriority;
    @ColumnInfo(name="notes")
    public String notes;
}
