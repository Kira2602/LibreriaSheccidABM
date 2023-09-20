package com.example.tienda;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    fragment_one fragmentOne = new fragment_one();
    fragment_two fragmentTwo = new fragment_two();
    fragment_three fragmentThree = new fragment_three();
    fragmentContacto fragmentContacto = new fragmentContacto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentOne).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                    int id = item.getItemId();

                    if(item.getItemId() == R.id.people){
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentOne).commit();
                        return true;
                    } else if (item.getItemId() == R.id.products) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentTwo).commit();
                        return true;
                    }
                    else if (item.getItemId() == R.id.ventas) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentThree).commit();
                        return true;
                    }else if (item.getItemId() == R.id.contacto) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentContacto).commit();
                        return true;
                    }


                return false;
            }
        });

    }
}