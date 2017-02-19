package com.vlad17021995m.android.listways.model;

import java.io.Serializable;

/**
 * Created by qwerty on 17.02.2017.
 */
public class Route extends Entity implements Serializable {
    private City from_city;
    private String from_date;
    private String from_time;
    private String from_info;
    private City to_city;
    private String to_date;
    private String to_time;
    private String to_info;
    private String info;
    private int price;
    private long bus_id;
    private int reservation_count;

    public Route(long id) {
        super(id);
    }

    public City getFrom_city() {
        return from_city;
    }

    public void setFrom_city(City from_city) {
        this.from_city = from_city;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getFrom_info() {
        return from_info;
    }

    public void setFrom_info(String from_info) {
        this.from_info = from_info;
    }

    public City getTo_city() {
        return to_city;
    }

    public void setTo_city(City to_city) {
        this.to_city = to_city;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getTo_info() {
        return to_info;
    }

    public void setTo_info(String to_info) {
        this.to_info = to_info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getBus_id() {
        return bus_id;
    }

    public void setBus_id(long bus_id) {
        this.bus_id = bus_id;
    }

    public int getReservation_count() {
        return reservation_count;
    }

    public void setReservation_count(int reservation_count) {
        this.reservation_count = reservation_count;
    }

    @Override
    public String toString() {
        return "Информация о городе отправки: \n" + from_city.toString() + "\n дата отправки - " + from_date + "\n время отправки - " + from_time
                + "\n информация об отправке - " + from_info + "\n информация о конечном городе: \n" + to_city.toString() + "\n дата прибытия - "
                + to_date + "\n время прибытия - " + to_time + "\n информация о прибытии - " + to_info + "\n информация о маршруте - " + info
                + "\n цена - " + price + "\n номер автобуса - " + bus_id + "\n зарезервировано мест - " + reservation_count;
    }
}
