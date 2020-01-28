package kanatnikov.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        List<Counter> data = new ArrayList<>();
        data.add(new Counter("Counter1", 123));
        data.add(new Counter("Counter2", 456));
        data.add(new Counter("Counter3", 789));

        CounterList list = new CounterList(findViewById(R.id.list));
        list.setCounters(data);
    }
}
