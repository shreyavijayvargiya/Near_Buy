package com.example.nearbuy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.nearbuy.HomeFragment;
import com.example.nearbuy.NearMeFragment;
import com.example.nearbuy.ProfileFragment;
import com.example.nearbuy.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.bottom_nav)
    ChipNavigationBar bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment=null;
                switch (id)
                {
                    case R.id.home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.profile:
                        fragment=new ProfileFragment();
                        break;
                    case R.id.near:
                        fragment=new NearMeFragment();
                        break;

                    //open fragment
                    // getSupportFragmentManager()
                    // .beginTransaction()
                    // .replace(R.id.frame_layout,fragment)
                    //.commit();
                    //break;

                }
                if(fragment!=null)
                {getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

                }

            }
        } );











    }

}


