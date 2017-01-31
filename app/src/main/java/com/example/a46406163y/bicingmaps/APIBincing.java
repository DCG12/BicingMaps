package com.example.a46406163y.bicingmaps;

import android.net.Uri;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class APIBincing {

    private static final String BASE_URL = "http://barcelonaapi.marcpous.com/bicing/stations";


    static ArrayList<Bicing> getInfoStations() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        return doCall(url);

    }

    @Nullable
    private static ArrayList<Bicing> doCall(String url){
        try {
            String JsonResponse = HttpUtils.get(url);
            ArrayList<Bicing> bicing = new ArrayList<>();

            JSONObject data = new JSONObject(JsonResponse);
            JSONArray jsonBoss = data.getJSONArray("bici");

            for (int i = 0; i < jsonBoss.length(); i++) {
                Bicing bici = new Bicing();
                JSONObject object = jsonBoss.getJSONObject(i);
                bici.setId(object.getString("id"));
                bici.setName(object.getString("name"));
                bicing.add(bici);
            }

            return bicing;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
