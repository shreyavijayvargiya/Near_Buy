package com.example.nearbuy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import butterknife.BindView;

public class HomeFragment extends Fragment {
    @BindView(R.id.bottom_nav)
    ChipNavigationBar bottomNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton btn1;
        btn1 = (ImageButton) view.findViewById(R.id.pani_puri);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), for_switch.class);
                startActivity(intent);
            }
        }); return  view;
    }
}