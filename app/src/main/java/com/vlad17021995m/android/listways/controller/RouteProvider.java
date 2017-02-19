package com.vlad17021995m.android.listways.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vlad17021995m.android.listways.model.City;
import com.vlad17021995m.android.listways.model.Route;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by qwerty on 18.02.2017.
 */
public class RouteProvider {
    private static RouteProvider rp;
    List<Route> routeList = null;

    private RouteProvider(){}

    public static RouteProvider getInstance(){
        if (rp == null){
            rp = new RouteProvider();
        }
        return rp;
    }

    public List<Route> getRoutes(String date1, String date2, AppCompatActivity activity){
        if (routeList == null){
            routeList = new ArrayList<>();
        }
        routeList.clear();
        String url = "http://smartbus.gmoby.org/web/index.php/api/trips?from_date=" + date1 + "&to_date=" + date2;
        String res = null;
        try {
            res = new ParseTask().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(res);
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i = 0;i < data.length();i++){
                JSONObject route_info = data.getJSONObject(i);
                //Log.d("ROUTE", String.valueOf(route_info.getLong("id")));
                Route r = new Route(route_info.getLong("id"));
                JSONObject from_city = route_info.getJSONObject("from_city");
                r.setFrom_city(new City(from_city.getLong("id"), from_city.getInt("highlight"), from_city.getString("name")));
                r.setFrom_date(route_info.getString("from_date"));
                r.setFrom_time(route_info.getString("from_time"));
                r.setFrom_info(route_info.getString("from_info"));
                JSONObject to_city = route_info.getJSONObject("to_city");
                r.setTo_city(new City(to_city.getLong("id"), to_city.getInt("highlight"), to_city.getString("name")));
                r.setTo_date(route_info.getString("to_date"));
                r.setTo_time(route_info.getString("to_time"));
                r.setTo_info(route_info.getString("to_info"));
                r.setInfo(route_info.getString("info"));
                r.setPrice(route_info.getInt("price"));
                r.setBus_id(route_info.getLong("bus_id"));
                r.setReservation_count(route_info.getInt("reservation_count"));
                routeList.add(r);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveList(activity);
        return routeList;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    private class ParseTask extends AsyncTask<String, Void, String>{

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(is));
                String buf;
                while ((buf = reader.readLine()) != null){
                    buffer.append(buf);
                }
                resultJson = buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }
    }

    public void saveList(AppCompatActivity activity){
        File file = new File(activity.getFilesDir(), "list.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(routeList);
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Route> getRoutesFromFile(AppCompatActivity activity){
        if (routeList == null){
            routeList = new ArrayList<>();
        }
        File file = new File(activity.getFilesDir(), "list.txt");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                routeList = (ArrayList<Route>)ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return routeList;
    }
}
