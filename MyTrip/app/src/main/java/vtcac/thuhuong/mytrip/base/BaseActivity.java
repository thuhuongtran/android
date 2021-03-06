package vtcac.thuhuong.mytrip.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * execute same common code for every activities, except LauncherActivity
 * provide components like alert dialog
 */
public class BaseActivity extends AppCompatActivity {
    private final static String TAG = BaseActivity.class.getSimpleName();

    public static final int REQCD_PLACE_AUTOCOMPLETE = 1000;
    public static final int REQCD_TRAVEL_ADD = 2000;
    public static final int REQCD_TRAVEL_EDIT = 2001;
    public static final String REQKEY_TRAVEL = "REQKEY_TRAVEL";
    public static final String REQACTION_EDIT_TRAVEL = "REQACTION_EDIT_TRAVEL";
    public static final String REQACTION_DEL_TRAVEL = "REQACTION_DEL_TRAVEL";
    public static final String REQKEY_TRAVEL_ID = "REQKEY_TRAVEL_ID";


    /**
     * displays a common alert dialog with Ok and Cancel buttons.
     * @param titleId title string resource id
     * @param messageId message string resource id
     * @param okClickListener the callback when the ok button is clicked
     * @param cancelClickListener the callback when the cancel button is clicked
     */
    protected void showAlertOkCancel(@StringRes int titleId,
                                     @StringRes int messageId,
                                     final DialogInterface.OnClickListener okClickListener,
                                     final DialogInterface.OnClickListener cancelClickListener) {
        new AlertDialog.Builder(this)
                .setTitle(titleId)
                .setMessage(messageId)
                .setPositiveButton(android.R.string.ok, okClickListener)
                .setNegativeButton(android.R.string.cancel, cancelClickListener)
                .show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }
}
