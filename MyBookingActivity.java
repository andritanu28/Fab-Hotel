package com.example.fabhotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fabhotels.Database.Data;

import java.util.Vector;

public class MyBookingActivity extends AppCompatActivity {
    ListView listView;
    Vector<String> namaHotel = new Vector<>();
    Vector<String> alamatHotel = new Vector<>();
    Vector<String> cekInDate = new Vector<>();
    Vector<String> cekOutDate = new Vector<>();
    Vector<Integer> totalPrice = new Vector<>();
    Vector<Integer> bookingId = new Vector<>();
    int idMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        Intent intent = getIntent();
        idMember = intent.getIntExtra("ID Member", 0);

        for (int i = 0; i < Data.DBbooking.size(); i++) {
            if (idMember == Data.DBbooking.get(i).memberId) {
                namaHotel.add(Data.DBhotel.get(Data.DBbooking.get(i).hotelId).hotelName);
                alamatHotel.add(Data.DBhotel.get(Data.DBbooking.get(i).hotelId).hotelAddress);
                cekInDate.add(Data.DBbooking.get(i).StartDate);
                cekOutDate.add(Data.DBbooking.get(i).EndDate);
                totalPrice.add(Data.DBbooking.get(i).TotalPrice);
                bookingId.add(Data.DBbooking.get(i).bookingId);
            }
        }
        listView = findViewById(R.id.myBooking_listV_listMyBooking);
        BookingAdapter bookingAdapter = new BookingAdapter(namaHotel, alamatHotel, cekInDate, cekOutDate, totalPrice, bookingId,  this);
        listView.setAdapter(bookingAdapter);
    }
}
