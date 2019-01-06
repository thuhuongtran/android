package vtcac.thuhuong.mytrip.traveldetail.plan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import vtcac.thuhuong.mytrip.R;
import vtcac.thuhuong.mytrip.entity.Travel;
import vtcac.thuhuong.mytrip.traveldetail.TravelDetailBaseFragment;

public class PlanFragment extends TravelDetailBaseFragment {
    private static final String TAG = PlanFragment.class.getSimpleName();
    public static final int TITLE_ID = R.string.title_frag_daily_plans;

    public PlanFragment() {
    }

    public static PlanFragment newInstance(int sectionNumber) {
        Log.d(TAG, "newInstance: sectionNumber="+sectionNumber);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        PlanFragment fragment = new PlanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plan, container, false);
        return rootView;
    }

    @Override
    protected void onTravelChanged(Travel travel) {

    }

    @Override
    public void requestAddItem() {

    }
}
