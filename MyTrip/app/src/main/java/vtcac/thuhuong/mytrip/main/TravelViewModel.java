package vtcac.thuhuong.mytrip.main;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import vtcac.thuhuong.mytrip.base.MyApplication;
import vtcac.thuhuong.mytrip.base.TravelSort;
import vtcac.thuhuong.mytrip.entity.Travel;
import vtcac.thuhuong.mytrip.repository.TravelRepository;

public class TravelViewModel extends AndroidViewModel {
    private final TravelRepository mRepository;
    // keep current user's selection
    private final MutableLiveData<TravelSort> mTravelSort = new MutableLiveData<>();
    // get travel list by using the selected sorting option
    private final LiveData<List<Travel>> mAllTravels = Transformations.switchMap(mTravelSort,
            new Function<TravelSort, LiveData<List<Travel>>>() {
                @Override
                public LiveData<List<Travel>> apply(TravelSort input) {
                    return mRepository.getAllTravels(input);
                }
            });

    // if user change the sorting option then save the selected option in SharedPreference
    public void setTravelSort(TravelSort option) {
        mTravelSort.setValue(option);
        ((MyApplication) getApplication()).setTravelSort(option);
    }

    public TravelViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = TravelRepository.getInstance(application);
        // TODO more
    }

    public LiveData<List<Travel>> getAllTravels() {
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
