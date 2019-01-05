package vtcac.thuhuong.roomwithviewdemo.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import vtcac.thuhuong.roomwithviewdemo.R;
import vtcac.thuhuong.roomwithviewdemo.entity.Word;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(@NonNull View itemView) {
            super(itemView);
            this.wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords = Collections.emptyList(); //cached copy of words

    public WordListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        if (mWords != null) {
            Word current = mWords.get(i);
            wordViewHolder.wordItemView.setText(current.getWord());
        } else {
            // covers the case of data not being ready yet
            wordViewHolder.wordItemView.setText("No word");
        }
    }

    public void setWords(List<Word> mWords) {
        this.mWords = mWords;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially), it's null, and we can't return null
    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        }
        else return 0;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
