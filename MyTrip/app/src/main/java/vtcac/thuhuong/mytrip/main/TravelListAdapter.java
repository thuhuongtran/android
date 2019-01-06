package vtcac.thuhuong.mytrip.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import vtcac.thuhuong.mytrip.EditTravelActivity;
import vtcac.thuhuong.mytrip.MainActivity;
import vtcac.thuhuong.mytrip.R;
import vtcac.thuhuong.mytrip.base.BaseActivity;
import vtcac.thuhuong.mytrip.entity.Travel;
import vtcac.thuhuong.mytrip.utils.MyDate;

public class TravelListAdapter extends RecyclerView.Adapter<TravelListAdapter.TravelViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<Travel> mList;
    private ListItemClickListener mListItemClickListener;

    public TravelListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    class TravelViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTxt;
        private final TextView placeTxt;
        private final TextView dateTxt;
        private final ImageButton editBtn;

        private TravelViewHolder(@NonNull View itemView) {
            super(itemView);
            //
            if (mListItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListItemClickListener.onListItemClick(v,
                                getAdapterPosition(),
                                getItem(getAdapterPosition()));
                    }
                });
            }
            this.titleTxt = itemView.findViewById(R.id.title_txt);
            this.placeTxt = itemView.findViewById(R.id.place_txt);
            this.dateTxt = itemView.findViewById(R.id.date_txt);
            this.editBtn = itemView.findViewById(R.id.editBtn);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // call EditTravelActivity with a selected Travel entity
                    Travel travel = getItem(getAdapterPosition());
                    Intent intent = new Intent(mContext, EditTravelActivity.class);
                    intent.putExtra(BaseActivity.REQKEY_TRAVEL, travel);
                    intent.setAction(BaseActivity.REQACTION_EDIT_TRAVEL);
                    ((MainActivity) mContext).startActivityForResult(intent,
                            BaseActivity.REQCD_TRAVEL_EDIT);
                }
            });
        }
    }

    /**
     * Interface definition for the callback to call when the list item is clicked
     */
    public interface ListItemClickListener {
        /**
         * Called when the list item has been clicked
         *
         * @param view
         * @param position
         * @param travel
         */
        void onListItemClick(View view, int position, Travel travel);

    }

    public void setListItemClickListener(ListItemClickListener listItemClickListener) {
        this.mListItemClickListener = listItemClickListener;
    }

    public void setList(List<Travel> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.main_travel_item, parent, false);
        return new TravelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        if (getItemCount() == 0) return;
        Travel item = mList.get(position);
        holder.titleTxt.setText(item.getTitle());
        holder.placeTxt.setText(item.getPlaceName() + " - " + item.getPlaceAddr());
        holder.dateTxt.setText(MyDate.getString(item.getStartDt()) + " - " + MyDate.getString(item.getEndDt()));
    }

    @Override
    public int getItemCount() {
        if (mList == null) return 0;
        return mList.size();
    }

    public Travel getItem(int position) {
        if (getItemCount() == 0) return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (getItemCount() == 0) return RecyclerView.NO_ID;
        return mList.get(position).getId();
    }
}
