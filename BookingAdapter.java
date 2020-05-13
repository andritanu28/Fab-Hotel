package com.example.fabhotels;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.fabhotels.Database.Data;

import java.util.Vector;

public class BookingAdapter extends BaseAdapter {

    Vector<String> namaHotel;
    Vector<String> alamatHotel;
    Vector<String> cekInDate;
    Vector<String> cekOutDate;
    Vector<Integer> totalPrice;
    Vector<Integer> idBooking;

    TextView hotelName, hotelAlamat, CekInDate, CekOutDate, TotalPrice;
    Button cancel;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    Context context;

    public BookingAdapter(Vector <String> namaHotel, Vector<String> alamatHotel, Vector<String> cekInDate, Vector<String> cekOutDate, Vector<Integer> totalPrice, Vector<Integer> idBooking, Context context) {
        this.namaHotel = namaHotel;
        this.alamatHotel = alamatHotel;
        this.cekInDate = cekInDate;
        this.cekOutDate = cekOutDate;
        this.totalPrice = totalPrice;
        this.idBooking = idBooking;
        this.context = context;
    }

    @Override
    public int getCount() {
        return namaHotel.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listmybooking, null);

        hotelName = view.findViewById(R.id.listMyBooking_txtV_namaHotel);
        hotelAlamat = view.findViewById(R.id.listMyBooking_txtV_address);
        CekInDate = view.findViewById(R.id.listMyBooking_txtV_cekIn);
        CekOutDate = view.findViewById(R.id.listMyBooking_txtV_cekOut);
        TotalPrice = view.findViewById(R.id.listMyBooking_txtV_totalHarga);
        cancel = view.findViewById(R.id.listMyBooking_button_cancelBook);

        hotelName.setText(namaHotel.get(position));
        hotelAlamat.setText(alamatHotel.get(position));
        CekInDate.setText(cekInDate.get(position));
        CekOutDate.setText(cekOutDate.get(position));
        TotalPrice.setText(totalPrice.get(position).toString());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < Data.DBbooking.size(); i++) {
                            if (idBooking.get(position) == Data.DBbooking.get(i).bookingId) {
                                Data.DBbooking.remove(i);
                            }
                        }
                        namaHotel.remove(position);
                        alamatHotel.remove(position);
                        cekInDate.remove(position);
                        cekOutDate.remove(position);
                        totalPrice.remove(position);

                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setCancelable(false);

                alertDialog = builder.create();
                alertDialog.setTitle("Cancel Booking");
                alertDialog.setMessage("Are You Sure ? ");
                alertDialog.show();

            }
        });

        return view;
    }
}
