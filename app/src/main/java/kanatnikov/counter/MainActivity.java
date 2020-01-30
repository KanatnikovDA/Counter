package kanatnikov.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CounterList mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        mList = new CounterList(findViewById(R.id.list), new CounterList.Listener() {
            @Override
            public void onPlus(Counter counter) {
                Repo.getInstance().inc(counter);
                //Toast.makeText(MainActivity.this, "Plus on " + counter.name, Toast.LENGTH_SHORT).show();
                updateList();
            }

            @Override
            public void onMinus(Counter counter) {
                Repo.getInstance().dec(counter);
                //Toast.makeText(MainActivity.this, "Minus on " + counter.name, Toast.LENGTH_SHORT).show();
                updateList();
            }

            @Override
            public void onOpen(Counter counter) {
                startActivity(new Intent(MainActivity.this, CounterActivity.class));
            }
        });
        updateList();
    }

    private void updateList() {
        mList.setCounters(Repo.getInstance().getCounters());
    }
   /* private  List<Counter> createTestData() {
        int count = 100;
        Random random = new Random();
        List<Counter> data = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            data.add(new Counter("Counter " + (i + 1), random.nextInt(1000)));
        }
        return data;
    }*/
}
