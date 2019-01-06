package vtcac.thuhuong.mytrip.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import vtcac.thuhuong.mytrip.base.TravelSort;
import vtcac.thuhuong.mytrip.dao.TravelDao;
import vtcac.thuhuong.mytrip.database.AppDatabase;
import vtcac.thuhuong.mytrip.entity.Travel;

public class TravelRepository {
    private static volatile TravelRepository INSTANCE;
    private final TravelDao mTravelDao;
    private LiveData<List<Travel>> mAllTravels;

    public TravelRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.mTravelDao = db.travelDao();

        hello();
    }

    void hello(){

    }

    public static TravelRepository getInstance(final Application application) {
        if (INSTANCE == null) {
            synchronized (TravelRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TravelRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    /*public LiveData<List<Travel>> getAllTravels() {
        if (mAllTravels == null) {
            mAllTravels = mTravelDao.getAllTravels();
        }
        return mAllTravels;
    }*/
    public LiveData<List<Travel>> getAllTravels(TravelSort travelSort) {
        switch (travelSort) {
            case DEFAULT:
                return mTravelDao.getAllTravels();
            case TITLE_ASC:
                return mTravelDao.getAllTravelsByTitleAsc();
            case TITLE_DESC:
                return mTravelDao.getAllTravelsByTitleDesc();
            case START_ASC:
                return mTravelDao.getAllTravelsByStartAsc();
            case START_DESC:
                return mTravelDao.getAllTravelsByStartDesc();
        }
        return mTravelDao.getAllTravels();
    }
    /**
     * insert
     * @param travel
     */
    public void insert(Travel travel) {
        new insertAsyncTask(mTravelDao).execute(travel);
    }

    private static class insertAsyncTask extends AsyncTask<Travel, Void, Void> {
        private TravelDao mAsyncTaskDao;

        insertAsyncTask(TravelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Travel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * update
     * @param travel
     */
    public void update(Travel travel) {
        new updateAsyncTask(mTravelDao).execute(travel);
    }

    private static class updateAsyncTask extends AsyncTask<Travel, Void, Void> {
        private TravelDao mAsyncTaskDao;

        updateAsyncTask(TravelDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Travel... travels) {
            mAsyncTaskDao.update(travels[0]);
            return null;
        }
    }

    /**
     * delete
     */
    public void delete(Travel...travels) {
        new deleteAsyncTask(mTravelDao).execute(travels);
    }
    private static class deleteAsyncTask extends AsyncTask<Travel, Void, Void> {
        private TravelDao mAsyncTaskDao;

         deleteAsyncTask(TravelDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Travel... travels) {
            mAsyncTaskDao.delete(travels);
            return null;
        }
    }

}
