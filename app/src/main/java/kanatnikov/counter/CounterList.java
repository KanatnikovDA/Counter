package kanatnikov.counter;

import android.net.sip.SipAudioCall;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CounterList {

    public interface  Listener {
        void onPlus (Counter counter);
        void onMinus (Counter counter);
        void onOpen (Counter counter);
    }

    private final CounterAdapter mAdapter;
    private final Listener mListener;

    public CounterList(RecyclerView rv, Listener listener){
        mListener = listener;
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        mAdapter = new CounterAdapter();
        rv.setAdapter(mAdapter);
    }

    public void setCounters(List<Counter> list){
        mAdapter.setData(list);

    }

    class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.Vh> {

        private List<Counter> mData;

        void setData(List<Counter> data) {
            mData = data;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Vh onCreateViewHolder(@NonNull ViewGroup parent, int type) {
            return new Vh(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull Vh vh, int position) {
            vh.bind(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class Vh extends RecyclerView.ViewHolder {

            private final TextView mName;
            private final TextView mValue;

            Vh(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.i_counter, parent, false));
                mName = itemView.findViewById(R.id.i_counter_name);
                mValue = itemView.findViewById(R.id.i_counter_value);

                itemView.findViewById(R.id.i_counter_minus).setOnClickListener(view -> mListener.onMinus(mData.get(getAdapterPosition())));
                itemView.findViewById(R.id.i_counter_plus).setOnClickListener(view -> mListener.onPlus(mData.get(getAdapterPosition())));
                itemView.setOnClickListener(view -> mListener.onOpen(mData.get(getAdapterPosition())));

                //TODO OnItemCLick

            }
            void bind (Counter counter){
                mName.setText(counter.name);
                mValue.setText(String.valueOf(counter.value));
            }
        }
    }
}
