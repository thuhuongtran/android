package vtcac.thuhuong.mytrip.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Task need to run before the creation of first activity
 * */
public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    private final static String TRAVEL_SORT_NAME = "TRAVEL_SORT_NAME";
    private final static String TRAVEL_SORT_KEY = "TRAVEL_SORT_KEY";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    /**
     * saves the sorting option selected in SharedPreferences
     * @param travelSort the sorting option.
     */
    public void setTravelSort(TravelSort travelSort) {
        SharedPreferences sp = getSharedPreferences(TRAVEL_SORT_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TRAVEL_SORT_KEY, travelSort.name());
        editor.commit();
    }

    public TravelSort getTravelSort() {
        SharedPreferences sp = getSharedPreferences(TRAVEL_SORT_NAME, Context.MODE_PRIVATE);
        String name = sp.getString(TRAVEL_SORT_KEY, TravelSort.DEFAULT.name());
        TravelSort travelSort = TravelSort.DEFAULT;
        try {
            travelSort = TravelSort.valueOf(name);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return travelSort;
    }
}
