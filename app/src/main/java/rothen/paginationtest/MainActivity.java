package rothen.paginationtest;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new MyAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private final Context mContext;
        private ArrayList<String> data = new ArrayList<>();

        public MyAdapter(Context context) {
            this.mContext = context;
            addDataToData();
        }

        private void addDataToData() {
            for (int i = 0; i < 10; i++) {
                data.add( (i + 1) +"");
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.xd, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            Log.d(TAG, "onBindViewHolder() called with: holder = [" + holder + "], position = [" + position + "]");
            holder.mTextView.setText(data.get(position));
            if (position == getItemCount() - 1) {
                addDataToData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notifyItemInserted(position);
                    }
                }, 2500);

            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            public final TextView mTextView;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTextView = ((TextView) itemView.findViewById(R.id.textView));
            }
        }
    }
}


