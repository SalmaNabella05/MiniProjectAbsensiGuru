package id.ac.polinema.absensiguruprivate_salma;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import id.ac.polinema.absensiguruprivate_salma.api.ApiClient;
import id.ac.polinema.absensiguruprivate_salma.api.ApiInterface;
import id.ac.polinema.absensiguruprivate_salma.model.Detail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRekap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rekap);

        String uname = getIntent().getStringExtra("username");

        final RecyclerView detailView = findViewById(R.id.rv_detailRekap);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);
        final List detail = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Detail>> call = apiInterface.getAbsenByUsernameForRekap(uname);

        call.enqueue(new Callback<List<Detail>>() {
            @Override
            public void onResponse(Call<List<Detail>> call, Response<List<Detail>> response) {
                if (response.isSuccessful()) {
                    List<Detail> absenItems = response.body();

                    for (Detail item : absenItems) {
                        detail.add(new Detail(item.getUsername(), item.getPassword(), item.getJam_login(),
                                item.getJam_logout(), item.getTanggal(), item.getLokasi_latitude(), item.getLokasi_longitude(), item.getNim_siswa(), item.getNama()));
                    }

//                    absen.add(new AbsenGuru(session.getUsername(), session.getPassword(), session.getLoginTime(),
//                            session.getLogoutTime(), session.getDate(), session.getLocLatitude(), session.getLocLongitude(), x, y));

                    itemAdapter.add(detail);
                    detailView.setAdapter(fastAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    detailView.setLayoutManager(layoutManager);
                } else {
                    Toast.makeText(getApplicationContext(), "Server Bermasalah, Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Detail>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
