package id.ac.polinema.absensiguruprivate_salma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import id.ac.polinema.absensiguruprivate_salma.api.ApiClient;
import id.ac.polinema.absensiguruprivate_salma.api.ApiInterface;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahSiswa extends AppCompatActivity {
    private EditText nama_siswa, alamat_siswa, tglLahir_siswa, kelas_siswa;
    private RadioGroup radioGroup_siswa;
    private RadioButton selected_siswa;
    private Button btnTambahSiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_siswa);

        nama_siswa = findViewById(R.id.edt_nama_siswa);
        alamat_siswa = findViewById(R.id.edt_alamat_siswa);
        tglLahir_siswa = findViewById(R.id.edt_tglLahir_siswa);
        kelas_siswa = findViewById(R.id.edt_kelas_siswa);
        radioGroup_siswa = findViewById(R.id.group_jk_siswa);
        btnTambahSiswa = findViewById(R.id.btn_tambah_data_siswa);

        btnTambahSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahSiswa();
            }
        });
    }

    public void tambahSiswa() {

        String nama = nama_siswa.getText().toString();
        String alamat = alamat_siswa.getText().toString();
        selected_siswa = findViewById(radioGroup_siswa.getCheckedRadioButtonId());
        String jenis_kelamin = "";
        if (selected_siswa != null) {
            jenis_kelamin = selected_siswa.getText().toString();
        }
        String tglLahir = tglLahir_siswa.getText().toString();
        String kelas = kelas_siswa.getText().toString();

//        Siswa siswa = new Siswa(nama, alamat, jenis_kelamin, tglLahir, kelas);
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("nama", createPartFromString(nama));
        map.put("alamat", createPartFromString(alamat));
        map.put("jenis_kelamin", createPartFromString(jenis_kelamin));
        map.put("tanggal_lahir", createPartFromString(tglLahir));
        map.put("kelas", createPartFromString(kelas));


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.tambahSiswa(map);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Menambahkan Data Berhasil", Toast.LENGTH_SHORT).show();
                    Intent mintent = new Intent(getApplicationContext(), DaftarSiswa.class);
                    startActivity(mintent);
                } else {
                    Toast.makeText(getApplicationContext(), "Server Bermasalah, Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private RequestBody createPartFromString(String description) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, description);
    }
}