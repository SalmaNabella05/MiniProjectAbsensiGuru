package id.ac.polinema.absensiguruprivate_salma.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import id.ac.polinema.absensiguruprivate_salma.R;

public class SiswaAdmin extends AbstractItem<SiswaAdmin, SiswaAdmin.ViewHolder> {
//    private String nim, nama, alamat, jenis_kelamin, tanggal_lahir, kelas;
//
//    public SiswaAdmin(String nim, String nama, String alamat, String jenis_kelamin, String tanggal_lahir, String kelas) {
//        this.nama = nama;
//        this.alamat = alamat;
//        this.jenis_kelamin = jenis_kelamin;
//        this.tanggal_lahir = tanggal_lahir;
//        this.kelas = kelas;
//        this.nim = nim;
//    }
//
//    public SiswaAdmin(String nama, String alamat, String jenis_kelamin, String tanggal_lahir, String kelas) {
//        this.nama = nama;
//        this.alamat = alamat;
//        this.jenis_kelamin = jenis_kelamin;
//        this.tanggal_lahir = tanggal_lahir;
//        this.kelas = kelas;
//    }

    private String nim;
    private String nama;
    private String alamat;
    private String jenis_kelamin;
    private String tanggal_lahir;
    private String kelas;

    public SiswaAdmin(String nim, String nama, String alamat, String jenis_kelamin, String tanggal_lahir, String kelas) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.tanggal_lahir = tanggal_lahir;
        this.kelas = kelas;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

//    public void setNama(String nama) {
//        this.nama = nama;
//    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getKelas() {
        return kelas;
    }


    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_siswa;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_siswa;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<SiswaAdmin> {
        TextView nim, nama, alamat, jenis_kelamin, tanggal_lahir, kelas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nim = itemView.findViewById(R.id.nim_siswa);
            nama = itemView.findViewById(R.id.nama_siswa);
            alamat = itemView.findViewById(R.id.alamat_siswa);
            jenis_kelamin = itemView.findViewById(R.id.jenis_kelamin_siswa);
            tanggal_lahir = itemView.findViewById(R.id.tglLahir_siswa);
            kelas = itemView.findViewById(R.id.kelas_siswa);
        }

        @Override
        public void bindView(final SiswaAdmin item, List<Object> payloads) {
            nim.setText("NIM : " + item.nim);
            nama.setText("Nama : " + item.nama);
            alamat.setText("Alamat : " + item.alamat);
            jenis_kelamin.setText("Jenis Kelamin : " + item.jenis_kelamin);
            tanggal_lahir.setText("Tanggal Lahir : " + item.tanggal_lahir);
            kelas.setText("Kelas : " + item.kelas);
        }

        @Override
        public void unbindView(SiswaAdmin item) {
            nim.setText(null);
            nama.setText(null);
            alamat.setText(null);
            jenis_kelamin.setText(null);
            tanggal_lahir.setText(null);
            kelas.setText(null);
        }
    }
}
