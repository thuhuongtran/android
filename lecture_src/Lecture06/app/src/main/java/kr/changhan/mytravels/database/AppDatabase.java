package kr.changhan.mytravels.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import kr.changhan.mytravels.dao.TravelDao;
import kr.changhan.mytravels.entity.Travel;

@Database(entities = {Travel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "travel_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TravelDao travelDao();
}
