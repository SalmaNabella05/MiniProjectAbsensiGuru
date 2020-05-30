package id.ac.polinema.absensiguruprivate_salma.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import id.ac.polinema.absensiguruprivate_salma.MapsActivity;
import id.ac.polinema.absensiguruprivate_salma.R;

public class Detail extends AbstractItem<Detail, Detail.ViewHolder> {
    private String username;
    private String password;
    private String jam_login;
    private String jam_logout;
    private String tanggal;
    private double lokasi_latitude;
    private double lokasi_longitude;
    private String nim_siswa;
    private String nama;

    public Detail(String username, String password, String jam_login, String jam_logout, String tanggal, double lokasi_latitude, double lokasi_longitude, String nim_siswa, String nama) {
        this.username = username;
        this.password = password;
        this.jam_login = jam_login;
        this.jam_logout = jam_logout;
        this.tanggal = tanggal;
        this.lokasi_latitude = lokasi_latitude;
        this.lokasi_longitude = lokasi_longitude;
        this.nim_siswa = nim_siswa;
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJam_login() {
        return jam_login;
    }

    public void setJam_login(String jam_login) {
        this.jam_login = jam_login;
    }

    public String getJam_logout() {
        return jam_logout;
    }

    public void setJam_logout(String jam_logout) {
        this.jam_logout = jam_logout;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public double getLokasi_latitude() {
        return lokasi_latitude;
    }

    public void setLokasi_latitude(double lokasi_latitude) {
        this.lokasi_latitude = lokasi_latitude;
    }

    public double getLokasi_longitude() {
        return lokasi_longitude;
    }

    public void setLokasi_longitude(double lokasi_longitude) {
        this.lokasi_longitude = lokasi_longitude;
    }

    public String getNim_siswa() {
        return nim_siswa;
    }

    public void setNim_siswa(String nim_siswa) {
        this.nim_siswa = nim_siswa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_detailRekap;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_detail_rekap;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<Detail> {
        private TextView jam_login, jam_logout, tanggal, latitude, longitude, nama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jam_login = itemView.findViewById(R.id.txt_jam_login_rekap);
            jam_logout = itemView.findViewById(R.id.txt_jam_logout_rekap);
            tanggal = itemView.findViewById(R.id.txt_tanggal_rekap);
            latitude = itemView.findViewById(R.id.txt_lokasi_latitude_rekap);
            longitude = itemView.findViewById(R.id.txt_lokasi_longitude_rekap);
            nama = itemView.findViewById(R.id.txt_siswa_diajar_rekap);
        }

        @Override
        public void bindView(final Detail item, List<Object> payloads) {
            jam_login.setText(item.jam_login);
            jam_logout.setText(item.jam_logout);
            tanggal.setText(item.tanggal);
            latitude.setText(String.valueOf(item.lokasi_latitude));
            longitude.setText(String.valueOf(item.lokasi_longitude));
            nama.setText(item.nama);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("latitude", item.lokasi_latitude);
                    intent.putExtra("longitude", item.lokasi_longitude);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void unbindView(Detail item) {
            jam_login.setText(null);
            jam_logout.setText(null);
            tanggal.setText(null);
            latitude.setText(null);
            longitude.setText(null);
            nama.setText(null);
        }
    }
}
