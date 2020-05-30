package id.ac.polinema.absensiguruprivate_salma;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import id.ac.polinema.absensiguruprivate_salma.api.ApiClient;
import id.ac.polinema.absensiguruprivate_salma.api.ApiInterface;
import id.ac.polinema.absensiguruprivate_salma.helper.Session;
import id.ac.polinema.absensiguruprivate_salma.model.Siswa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSiswaTeacher extends AppCompatActivity {
    private Session session;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_siswa_for_teacher);

        session = new Session(getApplicationContext());

        final RecyclerView siswaView = findViewById(R.id.rv_siswaforTeacher);
        final ItemAdapter itemAdapter = new ItemAdapter<>();
        final FastAdapter fastAdapter = FastAdapter.with(itemAdapter);
        final List siswa = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Siswa>> call1 = apiInterface.getSiswa();

        call1.enqueue(new Callback<List<Siswa>>() {
            @Override
            public void onResponse(Call<List<Siswa>> call, Response<List<Siswa>> response) {
                List<Siswa> siswaItem = response.body();
                if (response.isSuccessful()) {
                    for (Siswa item : siswaItem) {
                        siswa.add(new Siswa(item.getNim(), item.getNama(), item.getAlamat(), item.getJenis_kelamin(),
                                item.getTanggal_lahir(), item.getKelas()));
                    }

                    itemAdapter.add(siswa);
                    siswaView.setAdapter(fastAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    siswaView.setLayoutManager(layoutManager);
                } else {
                    Toast.makeText(getApplicationContext(), "Data gagal ditampilkan!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Siswa>> call, Throwable t) {
                error.setText(t.getMessage());
            }
        });

        if (ContextCompat.checkSelfPermission(ListSiswaTeacher.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ListSiswaTeacher.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(ListSiswaTeacher.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(ListSiswaTeacher.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertMessageNoGps();
        }
    }

    private void AlertMessageNoGps() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(ListSiswaTeacher.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Akses Lokasi Diizinkan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Akses Lokasi Ditolak", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}