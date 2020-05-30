package id.ac.polinema.absensiguruprivate_salma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);
    }

    public void handleLogoutAdmin(View view) {
        Intent intent = new Intent(AdminActivity1.this, LoginAdmin.class);
        startActivity(intent);
    }

    public void handleStudents(View view) {
        Intent intent = new Intent(AdminActivity1.this, DaftarSiswa.class);
        startActivity(intent);
    }

    public void handleTeachersList(View view) {
        Intent intent = new Intent(AdminActivity1.this, AdminActivity.class);
        startActivity(intent);
    }

    public void handleRekap(View view) {
        Intent intent = new Intent(AdminActivity1.this, RekapActivity.class);
        startActivity(intent);
    }
}
