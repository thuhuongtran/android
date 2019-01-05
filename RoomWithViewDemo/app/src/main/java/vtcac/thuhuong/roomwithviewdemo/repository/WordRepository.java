package vtcac.thuhuong.roomwithviewdemo.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import vtcac.thuhuong.roomwithviewdemo.dao.WordDao;
import vtcac.thuhuong.roomwithviewdemo.dao.WordRoomDatabase;
import vtcac.thuhuong.roomwithviewdemo.entity.Word;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        this.mWordDao = db.wordDao();
        this.mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsynTaskDao;
        insertAsyncTask(WordDao dao) {
             mAsynTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsynTaskDao.insert(params[0]);
            return null;
        }
    }

}

