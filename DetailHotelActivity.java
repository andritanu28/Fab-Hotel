package com.example.fabhotels;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabhotels.Database.Booking;
import com.example.fabhotels.Database.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class DetailHotelActivity extends AppCompatActivity {

    TextView nama, alamat, harga, noTelp, latitude, longtitude, cekIn, cekOut, total;
    Button checkIn, checkOut, book;
    SimpleDateFormat dateFormat;
    Date cekIn2;
    Date cekOut2;
    long CekIn3, CekOut3, selisih, totalPrice;
    int yearCekIn, monthCekIn, dateCekIn;
    int yearCekOut, monthCekOut, dateCekOut;
    int Pricee;
    String cekInDate, cekOutDate;
    int idMember, idHotel;

    public void totalHarga() {
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            cekIn2 = dateFormat.parse(cekInDate);
            cekOut2 = dateFormat.parse(cekOutDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CekIn3 = cekIn2.getTime();
        CekOut3 = cekOut2.getTime();
        selisih = (CekOut3 - CekIn3) / (1000*60*60*24);

        totalPrice = (selisih * Pricee);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        Intent intent = getIntent();
        Pricee = Integer.parseInt(intent.getStringExtra("Harga Hotel"));

        Intent getintent = getIntent();
        idMember = getintent.getIntExtra("ID Member", 0);
        idHotel = getintent.getIntExtra("ID Hotel", 0);

        nama = findViewById(R.id.detailHotel_txtV_namaHotel);
        alamat = findViewById(R.id.detailHotel_txtV_alamatHotel);
        harga = findViewById(R.id.detailHotel_txtV_hargaHotel);
        noTelp = findViewById(R.id.detailHotel_txtV_phoneHotel);
        latitude = findViewById(R.id.detailHotel_txtV_latitudeHotel);
        longtitude = findViewById(R.id.detailHotel_txtV_longtitudeHotek);

        checkIn = findViewById(R.id.detail_button_checkIn);
        checkOut = findViewById(R.id.detail_button_checkOut);
        cekIn = findViewById(R.id.detail_txtV_checkIn);
        cekOut = findViewById(R.id.detail_txtV_checkOut);
        total = findViewById(R.id.detail_txtV_Total);

        book = findViewById(R.id.detail_button_book);

        nama.setText(intent.getStringExtra("Nama Hotel"));
        noTelp.setText(intent.getStringExtra("Nomor Telepon Hotel"));
        alamat.setText(intent.getStringExtra("Alamat Hotel"));
        harga.setText(intent.getStringExtra("Harga Hotel"));
        latitude.setText(intent.getStringExtra("Latitude"));
        longtitude.setText(intent.getStringExtra("Longtitude"));

        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dateCekIn = calendar.get(Calendar.DAY_OF_MONTH);
                monthCekIn = calendar.get(Calendar.MONTH);
                yearCekIn = calendar.get(Calendar.YEAR);


                DatePickerDialog datePickerDialog = new DatePickerDialog(DetailHotelActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        cekIn.setText(dayOfMonth + " - " + (month +1) + " - " + year);
                        cekInDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    }
                },yearCekIn,monthCekIn,dateCekIn);

                datePickerDialog.show();
            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dateCekOut = calendar.get(Calendar.DAY_OF_MONTH);
                monthCekOut = calendar.get(Calendar.MONTH);
                yearCekOut = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DetailHotelActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        cekOut.setText(dayOfMonth + " - " + (month +1) + " - " + year);
                        cekOutDate = dayOfMonth + "/" + (month +1) + "/" + year;
                        totalHarga();
                        total.setText(Long.toString(totalPrice));
                    }
                },yearCekOut,monthCekOut,dateCekOut);

                datePickerDialog.show();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CekIn3 - (Calendar.getInstance().getTimeInMillis())) / (1000*60*60*24))<0) {
                    Toast.makeText(DetailHotelActivity.this, "check-in date must not be earlier than today", Toast.LENGTH_SHORT).show();
                }
                else if (selisih < 1) {
                    Toast.makeText(DetailHotelActivity.this, "check-out date must not be earlier than check-in date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Booking booking = new Booking();
                    booking.bookingId = Data.DBbooking.lastElement().bookingId+1;
                    booking.memberId = idMember;
                    booking.hotelId = idHotel;
                    booking.StartDate = cekInDate;
                    booking.EndDate = cekOutDate;
                    booking.TotalPrice = (int)totalPrice;

                    Data.DBbooking.add(booking);
                    Toast.makeText(DetailHotelActivity.this, "Hotel Successfully Booked", Toast.LENGTH_SHORT).show();

                    Intent intent1 = new Intent(DetailHotelActivity.this, HomeActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }
}
