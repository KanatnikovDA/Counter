package kanatnikov.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class CounterActivity extends AppCompatActivity {

    private static final String PREF_VAL = "PREF_VAL";

    private TextView mValueTv;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_counter);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        mValueTv = findViewById(R.id.main_counter);

        updateValue(getValue());

        findViewById(R.id.plus_btn).setOnClickListener(v -> updateValue(getValue()+ 1));

        findViewById(R.id.plus_btn).setOnClickListener(v -> updateValue(getValue()- 1));

        findViewById(R.id.reset_btn).setOnClickListener(v -> {
            int oldValue = getValue();
            updateValue(0);
            Snackbar.make(v, "Counter was reset", Snackbar.LENGTH_SHORT)
                .setAction("Undo", ignored -> updateValue(oldValue))
                .show();
        });
    }

    private void updateValue(int newValue) {
        mPrefs.edit().putInt(PREF_VAL, newValue).apply();
        mValueTv.setText(String.valueOf(newValue));
    }

    private int getValue() {
        return  mPrefs.getInt(PREF_VAL, 0);
    }
}
