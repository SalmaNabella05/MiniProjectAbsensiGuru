package id.ac.polinema.absensiguruprivate_salma.model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import id.ac.polinema.absensiguruprivate_salma.GuruActivity;
import id.ac.polinema.absensiguruprivate_salma.R;

public class Siswa extends AbstractItem<Siswa, Siswa.ViewHolder> {
    private String nim, nama, alamat, jenis_kelamin, tanggal_lahir, kelas;

    public Siswa(String nim, String nama, String alamat, String jenis_kelamin, String tanggal_lahir, String kelas) {
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.tanggal_lahir = tanggal_lahir;
        this.kelas = kelas;
        this.nim = nim;
    }

//    public Siswa(String nama, String alamat, String jenis_kelamin, String tanggal_lahir, String kelas) {
//        this.nama = nama;
//        this.alamat = alamat;
//        this.jenis_kelamin = jenis_kelamin;
//        this.tanggal_lahir = tanggal_lahir;
//        this.kelas = kelas;
//    }

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
    public Siswa.ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_siswaforTeacher;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_siswa;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<Siswa> {
        TextView nim, nama, alamat, jenis_kelamin, tanggal_lahir, kelas;

        public ViewHolder(View itemView) {
            super(itemView);
            nim = itemView.findViewById(R.id.nim_siswa);
            nama = itemView.findViewById(R.id.nama_siswa);
            alamat = itemView.findViewById(R.id.alamat_siswa);
            jenis_kelamin = itemView.findViewById(R.id.jenis_kelamin_siswa);
            tanggal_lahir = itemView.findViewById(R.id.tglLahir_siswa);
            kelas = itemView.findViewById(R.id.kelas_siswa);
        }

        @Override
        public void bindView(final Siswa item, List<Object> payloads) {
            nim.setText("NIM : " + item.nim);
            nama.setText("Nama : " + item.nama);
            alamat.setText("Alamat : " + item.alamat);
            jenis_kelamin.setText("Jenis Kelamin : " + item.jenis_kelamin);
            tanggal_lahir.setText("Tanggal Lahir : " + item.tanggal_lahir);
            kelas.setText("Kelas : " + item.kelas);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setCancelable(false);
                    builder.setMessage("Apakah Anda yakin memilih " + item.getNama() + " sebagai siswa private?");
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(itemView.getContext(), GuruActivity.class);
                            intent.putExtra("nim", item.nim);
//                            intent.putExtra("nama", item.nama);
                            itemView.getContext().startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }

        @Override
        public void unbindView(Siswa item) {
            nim.setText(null);
            nama.setText(null);
            alamat.setText(null);
            jenis_kelamin.setText(null);
            tanggal_lahir.setText(null);
            kelas.setText(null);
        }
    }
}
