package gans.apps.kitasemangat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gans.apps.kitasemangat.R;
import gans.apps.kitasemangat.model.CoronaProvinsi;

public class CoronaProvinsiAdapter extends RecyclerView.Adapter<CoronaProvinsiAdapter.ViewHolder> {

    private Context context;
    private List<CoronaProvinsi> coronaProvinsis;

    public CoronaProvinsiAdapter(Context context, List<CoronaProvinsi> coronaProvinsis) {
        this.context = context;
        this.coronaProvinsis = coronaProvinsis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_corona, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CoronaProvinsi coronaProvinsi = coronaProvinsis.get(position);
        String nama_provinsi, kasus_positif, kasus_sembuh, kasus_meninggal;
        nama_provinsi = coronaProvinsi.getProvinsi();
        kasus_positif = coronaProvinsi.getKasus_positif();
        kasus_sembuh = coronaProvinsi.getKasus_sembuh();
        kasus_meninggal = coronaProvinsi.getKasus_meninggal();

        holder.tv_provinsi.setText(nama_provinsi);
        holder.tv_kasus_positif.setText(kasus_positif);
        holder.tv_kasus_sembuh.setText(kasus_sembuh);
        holder.tv_kasus_meninggal.setText(kasus_meninggal);

    }

    @Override
    public int getItemCount() {
        return coronaProvinsis.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_provinsi, tv_kasus_positif, tv_kasus_sembuh, tv_kasus_meninggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_provinsi = itemView.findViewById(R.id.tv_provinsi);
            tv_kasus_positif = itemView.findViewById(R.id.tv_kasus_positif);
            tv_kasus_sembuh = itemView.findViewById(R.id.tv_kasus_sembuh);
            tv_kasus_meninggal = itemView.findViewById(R.id.tv_kasus_meninggal);

        }
    }

    public void setFilter(ArrayList<CoronaProvinsi> filterList) {
        coronaProvinsis = filterList;
        notifyDataSetChanged();
    }

}
