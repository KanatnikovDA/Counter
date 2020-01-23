package kanatnikov.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private TextView mValueTv;
    private  int mValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mValueTv = findViewById(R.id.main_counter);

        findViewById(R.id.plus_btn).setOnClickListener(v -> {
            mValue++;
            updateValue();
        });

        findViewById(R.id.minus_btn).setOnClickListener(v -> {
            mValue--;
            updateValue();
        });

        findViewById(R.id.reset_btn).setOnClickListener(v -> {
            int oldValue = mValue;
            mValue = 0;
            updateValue();
            Snackbar.make(v, "Counter was reset", Snackbar.LENGTH_SHORT)
                .setAction("Undo", ignored ->{
                    mValue = oldValue;
                    updateValue();
                })
                    .show();
        });
    }

    private void updateValue() {
        mValueTv.setText(String.valueOf(mValue));
    }
}
