package com.example.fabhotels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fabhotels.Database.Data;

import java.util.Vector;

public class HomeActivity extends AppCompatActivity {
    Vector<String> namaHotel = new Vector<>();
    Vector<String> alamatHotel = new Vector<>();
    Vector<Integer> hargaHotel = new Vector<>();

    ListView listView;
    int idMember;

    Integer[] imageId = {
            R.drawable.hotelneo,
            R.drawable.hotelhorison,
            R.drawable.hotelgrandsetia
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_myBooking :
                Intent intent = new Intent(HomeActivity.this, MyBookingActivity.class);
                intent.putExtra("ID Member", idMember);
                startActivity(intent);
                return true;

            case R.id.menu_item_logOut :
                Intent intent1 = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        for (int i = 0; i < Data.DBhotel.size(); i++){
            namaHotel.add(Data.DBhotel.get(i).hotelName);
            alamatHotel.add(Data.DBhotel.get(i).hotelAddress);
            hargaHotel.add(Data.DBhotel.get(i).hotelPrice);
        }

        HotelAdapter hotelAdapter = new HotelAdapter(namaHotel, alamatHotel, hargaHotel, imageId, getApplicationContext());
        listView = findViewById(R.id.home_listV_listHotel);
        listView.setAdapter(hotelAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, DetailHotelActivity.class);

                intent.putExtra("Nama Hotel", namaHotel.get(position));
                intent.putExtra("Alamat Hotel", alamatHotel.get(position));

                String harga = hargaHotel.get(position).toString();
                intent.putExtra("Harga Hotel", harga);
                intent.putExtra("Nomor Telepon Hotel", Data.DBhotel.get(position).hotelPhone);

                String latitude = Double.toString(Data.DBhotel.get(position).Latitude);
                intent.putExtra("Latitude", latitude);

                String longtitude = Double.toString(Data.DBhotel.get(position).Longtitude);
                intent.putExtra("Longtitude", longtitude);
                intent.putExtra("ID Member", idMember);
                intent.putExtra("ID Hotel", position);

                startActivity(intent);
            }
        });
    }
}
