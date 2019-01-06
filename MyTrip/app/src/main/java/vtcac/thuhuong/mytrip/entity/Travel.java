package vtcac.thuhuong.mytrip.entity;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import vtcac.thuhuong.mytrip.utils.MyDate;

@Entity(tableName = "travel")
public class Travel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String title;
    private long startDt;
    private long endDt;
    private String placeId;
    private String placeName;
    private String placeAddr;
    private double placeLat;
    private double placeLng;

    public Travel(@NonNull String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public long getStartDt() {
        return startDt;
    }

    public void setStartDt(long startDt) {
        this.startDt = startDt;
    }

    public long getEndDt() {
        return endDt;
    }

    public void setEndDt(long endDt) {
        this.endDt = endDt;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getPlaceLat() {
        return placeLat;
    }

    public void setPlaceLat(double placeLat) {
        this.placeLat = placeLat;
    }

    public double getPlaceLng() {
        return placeLng;
    }

    public void setPlaceLng(double placeLng) {
        this.placeLng = placeLng;
    }

    public String getPlaceAddr() {
        return placeAddr;
    }

    public void setPlaceAddr(String placeAddr) {
        this.placeAddr = placeAddr;
    }

    @NonNull
    @Override
    public String toString() {
        return "Travel {" +
                "id="+id+
                ", title="+title+
                ", startDt="+startDt+
                ",endDt="+endDt+
                ",placeId="+placeId+
                ",placeName="+placeName+
                ",placeAdrr="+placeAddr+
                ", placeLat="+placeLat+
                ",placeLng="+placeLng+
                "}";
    }

    /**
     * gets the string expression of the start date
     * @return the string expression of the start date
     */
    public String getStartDtText() {
        return MyDate.getString(startDt);
    }

    /**
     * gets the string expression of the end date
     * @return the string expression of the end date
     */
    public String getEndDtText() {
        return MyDate.getString(endDt);
    }


}
