package kr.changhan.mytravels.main;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import kr.changhan.mytravels.base.MyApplication;
import kr.changhan.mytravels.base.TravelSort;
import kr.changhan.mytravels.entity.Travel;
import kr.changhan.mytravels.repository.TravelRepository;

public class TravelViewModel extends AndroidViewModel {
    private final TravelRepository mRepository;
    private LiveData<List<Travel>> mAllTravels;

    public TravelViewModel(@NonNull Application application) {
        super(application);
        mRepository = TravelRepository.getInstance(application);
        mAllTravels = mRepository.getAllTravels();
    }

    public LiveData<List<Travel>> getAllTravels() {
        return mAllTravels;
    }

    public LiveData<List<Travel>> getAllTravels(TravelSort travelSort) {
        ((MyApplication) getApplication()).setTravelSort(travelSort);
        mAllTravels = mRepository.getAllTravels(travelSort);
        return mAllTravels;
    }

    public void insert(Travel travel) {
        mRepository.insert(travel);
    }

    public void update(Travel travel) {
        mRepository.update(travel);
    }

    public void delete(Travel... travels) {
        mRepository.delete(travels);
    }
}
