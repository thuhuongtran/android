package vtcac.thuhuong.mytrip.traveldetail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import vtcac.thuhuong.mytrip.entity.Travel;

public abstract class TravelDetailBaseFragment extends Fragment {
    private final static String TAG = TravelDetailBaseFragment.class.getSimpleName();
    protected static final String ARG_SECTION_NUMBER = "SECTION_NUMBER";

    protected abstract void onTravelChanged(Travel travel);

    public abstract void requestAddItem();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
