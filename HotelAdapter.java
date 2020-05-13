package com.example.fabhotels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

public class HotelAdapter extends BaseAdapter {
    Vector<String> namaHotel = new Vector<>();
    Vector<String> alamatHotel = new Vector<>();
    Vector<Integer> hargaHotel = new Vector<>();

    private final Integer[] imageId;
    TextView hotelName, addHotel, hotelPrice;
    ImageView imageView;
    Context context;

    public HotelAdapter(Vector<String> namaHotel, Vector<String> alamatHotel, Vector<Integer> hargaHotel, Integer[] imageId, Context context) {
        this.namaHotel = namaHotel;
        this.alamatHotel = alamatHotel;
        this.hargaHotel = hargaHotel;
        this.context = context;
        this.imageId = imageId;
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
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_hotel, null);

        hotelName = view.findViewById(R.id.listHotel_txtV_namaHotel);
        addHotel = view.findViewById(R.id.listHotel_txtV_alamatHotel);
        hotelPrice = view.findViewById(R.id.listHotel_txtV_pricePN);
        imageView = view.findViewById(R.id.listHotel_imgV_harishotel);

        hotelName.setText(namaHotel.get(position));
        addHotel.setText(alamatHotel.get(position));
        hotelPrice.setText(hargaHotel.get(position).toString());
        imageView.setImageResource(imageId[position]);

        return view;
    }
}