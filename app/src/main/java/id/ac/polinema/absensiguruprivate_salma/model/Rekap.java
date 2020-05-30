package id.ac.polinema.absensiguruprivate_salma.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import id.ac.polinema.absensiguruprivate_salma.DetailRekap;
import id.ac.polinema.absensiguruprivate_salma.R;

public class Rekap extends AbstractItem<Rekap, Rekap.ViewHolder> {
    private String username, rekap;

    public Rekap(String username, String rekap) {
        this.username = username;
        this.rekap = rekap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRekap() {
        return rekap;
    }

    public void setRekap(String rekap) {
        this.rekap = rekap;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_rekap;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_rekap;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<Rekap> {
        TextView username, rekap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.rekapUsername);
            rekap = itemView.findViewById(R.id.jumRekap);
        }

        @Override
        public void bindView(final Rekap item, List<Object> payloads) {
            username.setText(item.username);
            rekap.setText(item.rekap);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, DetailRekap.class);
                    intent.putExtra("username", item.username);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void unbindView(Rekap item) {
            username.setText(null);
            rekap.setText(null);
        }
    }
}
