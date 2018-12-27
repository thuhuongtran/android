package com.vtcac.thuhuong.lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Note> notes = new ArrayList<>();
    ListView lsvNotes;
    Button btnAddNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsvNotes = findViewById(R.id.lsvListNote);
        notes.add(new Note("day", "Go to shopping"));
        notes.add(new Note("email", "Send an email to boss"));
        notes.add(new Note("calendar", "Submit assignment"));
        NoteAdapter adapter = new NoteAdapter(notes, getBaseContext());
        lsvNotes.setAdapter(adapter);

        btnAddNote = findViewById(R.id.btnAddNote);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddNewNoteActivity.class);
                startActivity(intent);
            }
        });

    }
}
