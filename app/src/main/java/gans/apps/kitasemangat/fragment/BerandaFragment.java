package gans.apps.kitasemangat.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import gans.apps.kitasemangat.R;
import gans.apps.kitasemangat.activity.CoronaActivity;

public class BerandaFragment extends Fragment {

    Context context;
    LinearLayout ll_corona;

    public BerandaFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        //fungsi untuk menyembunyikan action bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
        } else {
            requireActivity().getActionBar().hide();
        }

        context = getContext();

        ll_corona = view.findViewById(R.id.ll_corona);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_corona.setOnClickListener(v -> {
            startActivity(new Intent(context, CoronaActivity.class));
        });

    }
}