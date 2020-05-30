package id.ac.polinema.absensiguruprivate_salma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import id.ac.polinema.absensiguruprivate_salma.model.SiswaAdmin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarSiswa extends AppCompatActivity {
    private Session session;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_siswa);

        session = new Session(getApplicationContext());

        final RecyclerView siswaView = findViewById(R.id.rv_siswa);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);
        final List siswa = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SiswaAdmin>> call1 = apiInterface.getAllSiswa();

        call1.enqueue(new Callback<List<SiswaAdmin>>() {
            @Override
            public void onResponse(Call<List<SiswaAdmin>> call, Response<List<SiswaAdmin>> response) {
                List<SiswaAdmin> siswaItems = response.body();

                for (SiswaAdmin item : siswaItems) {
                    siswa.add(new SiswaAdmin(item.getNim(), item.getNama(), item.getAlamat(), item.getJenis_kelamin(),
                            item.getTanggal_lahir(), item.getKelas()));
                }

                itemAdapter.add(siswa);
                siswaView.setAdapter(fastAdapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                siswaView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<SiswaAdmin>> call, Throwable t) {
                error.setText(t.getMessage());
            }
        });
    }

    public void handleAddSiswa(View view) {
        Intent intent = new Intent(DaftarSiswa.this, TambahSiswa.class);
        startActivity(intent);
    }
}
