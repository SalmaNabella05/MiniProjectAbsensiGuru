package id.ac.polinema.absensiguruprivate_salma;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import id.ac.polinema.absensiguruprivate_salma.api.ApiClient;
import id.ac.polinema.absensiguruprivate_salma.api.ApiInterface;
import id.ac.polinema.absensiguruprivate_salma.helper.Session;
import id.ac.polinema.absensiguruprivate_salma.model.Rekap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RekapActivity extends AppCompatActivity {
    private Session session;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap);

        session = new Session(getApplicationContext());

        final RecyclerView rekapView = findViewById(R.id.rv_rekap);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);
        final List rekap = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Rekap>> call = apiInterface.getRekap();

        call.enqueue(new Callback<List<Rekap>>() {
            @Override
            public void onResponse(Call<List<Rekap>> call, Response<List<Rekap>> response) {
                List<Rekap> rekapItems = response.body();

                for (Rekap item : rekapItems) {
                    rekap.add(new Rekap(item.getUsername(), item.getRekap()));
                }

                itemAdapter.add(rekap);
                rekapView.setAdapter(fastAdapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                rekapView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Rekap>> call, Throwable t) {
                error.setText(t.getMessage());
            }
        });
    }
}
