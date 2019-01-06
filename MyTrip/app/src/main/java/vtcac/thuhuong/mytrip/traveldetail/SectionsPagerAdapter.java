package vtcac.thuhuong.mytrip.traveldetail;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import vtcac.thuhuong.mytrip.traveldetail.dairy.DiaryFragment;
import vtcac.thuhuong.mytrip.traveldetail.expense.ExpenseFragment;
import vtcac.thuhuong.mytrip.traveldetail.plan.PlanFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    // sparse array to keep track of registerd fragments in memory
    private SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public SectionsPagerAdapter(@NonNull FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    private final Context mContext;

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return DiaryFragment.newInstance(position);
            case 2:
                return ExpenseFragment.newInstance(position);
            default:
                return PlanFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return mContext.getString(DiaryFragment.TITLE_ID);
            case 2:
                return mContext.getString(ExpenseFragment.TITLE_ID);
            default:
                return mContext.getString(PlanFragment.TITLE_ID);
        }
    }
    // register the fragment when the item is instantiated

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }
    // unregister when the item is inactive

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    // return the fragment for the position (if instantiated)
    public Fragment getRegisterdFragment(int position) {
        return registeredFragments.get(position);
    }
}
