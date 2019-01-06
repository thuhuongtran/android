package vtcac.thuhuong.mytrip.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import vtcac.thuhuong.mytrip.entity.Travel;

@Dao
public interface TravelDao {
    @Query("SELECT * from travel ORDER BY id DESC")
    LiveData<List<Travel>> getAllTravels();

    @Query("SELECT * FROM travel ORDER BY title")
    LiveData<List<Travel>> getAllTravelsByTitleAsc();

    @Query("SELECT * FROM travel ORDER BY title DESC")
    LiveData<List<Travel>> getAllTravelsByTitleDesc();

    @Query("SELECT * FROM travel ORDER BY startDt")
    LiveData<List<Travel>> getAllTravelsByStartAsc();

    @Query("SELECT * FROM travel ORDER BY startDt DESC")
    LiveData<List<Travel>> getAllTravelsByStartDesc();

    @Insert
    void insert(Travel travel);

    @Update
    void update(Travel travel);

    @Delete
    void delete(Travel... travels);
}
