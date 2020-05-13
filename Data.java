package com.example.fabhotels.Database;


import java.util.Vector;

public class Data {

    public static Vector<Booking> DBbooking = new Vector<>();
    public static Vector<Hotel> DBhotel = new Vector<>();
    public static Vector<Member> DBmember = new Vector<>();

    public static void initBooking() {
        Booking booking = new Booking();

        booking.bookingId = 1;
        booking.memberId = 1;
        booking.hotelId = 3;
        booking.StartDate = "23-11-2019";
        booking.EndDate = "24-11-2019";
        booking.TotalPrice = 3509000;

        DBbooking.add(booking);
        booking = new Booking();

        booking.bookingId = 2;
        booking.memberId = 1;
        booking.hotelId = 2;
        booking.StartDate = "24-12-2019";
        booking.EndDate = "28-12-2019";
        booking.TotalPrice = 12105260;

        DBbooking.add(booking);
        booking = new Booking();

        booking.bookingId = 3;
        booking.memberId = 2;
        booking.hotelId = 1;
        booking.StartDate = "24-12-2019";
        booking.EndDate = "25-12-2019";
        booking.TotalPrice = 2896341;

        DBbooking.add(booking);
    }

    public static void initHotel() {
        Hotel hotel = new Hotel();

        hotel.hotelID = 1;
        hotel.hotelName = "NEO+";
        hotel.hotelAddress = "Kebayoran";
        hotel.hotelPhone = "02122777888";
        hotel.hotelPrice = 364000;
        hotel.Latitude = -6.2377162;
        hotel.Longtitude = 106.77609;

        DBhotel.add(hotel);
        hotel = new Hotel();

        hotel.hotelID = 2;
        hotel.hotelName = "Horison";
        hotel.hotelAddress = "Ciledug";
        hotel.hotelPhone = "02130487700";
        hotel.hotelPrice = 500000;
        hotel.Latitude = -6.2364611;
        hotel.Longtitude = 106.744531;

        DBhotel.add(hotel);
        hotel = new Hotel();

        hotel.hotelID = 3;
        hotel.hotelName = "Grand Setiabudi";
        hotel.hotelAddress = "Jakarta";
        hotel.hotelPhone = "0222044002";
        hotel.hotelPrice = 650000;
        hotel.Latitude = -6.8748216;
        hotel.Longtitude = 107.5949251;

        DBhotel.add(hotel);
    }

    public static void initMember() {
        Member member = new Member();

        member.MemberId = 1;
        member.Email = "admin@gmail.com";
        member.fName = "New Admin";
        member.Password = "Pika123";
        member.Phone = "087880373678";

        DBmember.add(member);
        member = new Member();

        member.MemberId = 2;
        member.Email = "lala@gmail.com";
        member.fName = "Lala Luna";
        member.Password = "Luna345";
        member.Phone = "081362112321";

        DBmember.add(member);
    }


}
