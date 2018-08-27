package my.edu.utem.trainingrecyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.trainingrecyclerview);
        CustomAdapter adapter = new CustomAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));   //guna yg ini atau yg bawah ni
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tempat;
        TextView description;
        ImageView imej;
        public CustomViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.tempatmenarik, parent, false));
            tempat = itemView.findViewById(R.id.tempat);
            description = itemView.findViewById(R.id.description);
            imej = itemView.findViewById(R.id.imej);
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
        public final String[] namaTempat, desc;
        public Drawable[] imej;

        public CustomAdapter(Context context) {
            Resources resources = context.getResources();
            namaTempat =  resources.getStringArray(R.array.nama_tempat);
            desc = resources.getStringArray(R.array.description);

            //Cara ambil image dpd drawable folder
            TypedArray a = resources.obtainTypedArray(R.array.imej);
            imej = new Drawable[a.length()];
            for (int i=0; i<imej.length; i++) {
                imej[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
            customViewHolder.tempat.setText(namaTempat[i]);
            customViewHolder.description.setText(desc[i]);
            customViewHolder.imej.setImageDrawable(imej[i]);

        }

        @Override
        public int getItemCount() {
            return namaTempat.length; //ikut bilangan array yg dah kita masukkan
        }
    }
}
