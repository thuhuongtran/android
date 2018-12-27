package com.vtcac.thuhuong.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {
    ArrayList<Note> notes = new ArrayList<>();
    Context context;

    public NoteAdapter(ArrayList<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowiew = inflater.inflate(R.layout.item_note, parent, false);
        ImageView ivNote = rowiew.findViewById(R.id.ivListNote);
        TextView tvNote = rowiew.findViewById(R.id.tvNote);
        Note note = notes.get(position);
        tvNote.setText(note.getText());
        switch (note.getType()){
            case "email":
                ivNote.setImageResource(R.mipmap.envelope);
                break;
            case "calendar":
                ivNote.setImageResource(R.mipmap.calendar);
                break;
            case "day":
                ivNote.setImageResource(R.mipmap.writing);
                break;
        }

        return rowiew;
    }
}
