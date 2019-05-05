package com.example.ghoniy.projectakhirpapbl.RumahSakit;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.ghoniy.projectakhirpapbl.MainActivity;
import com.example.ghoniy.projectakhirpapbl.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;


public class HospitalMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private BottomSheetBehavior mBottomSheetBehavior;
    double curLat, curLong;
    private TextView txt_nama, txt_alamat, txt_web, txt_operasional, txt_tlp;
    private ImageView img_hospital;
    private List<HospitalModel> data_list;
    private static String [] latList, longList;
    private static List<LatLng> latLngList;
    private static List<Marker> markerArray;
    String urlSearch ="http://amamipro.000webhostapp.com/json/getallrs.php" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        retrieveJSON();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txt_nama = findViewById(R.id.txt_nama);
        txt_alamat = findViewById(R.id.txt_alamat);
        txt_tlp = findViewById(R.id.txt_tlp);
        txt_web = findViewById(R.id.txt_web);
        txt_operasional = findViewById(R.id.txt_operasional);
        img_hospital = findViewById(R.id.img_hospital);
        View bottomSheetInfo = findViewById(R.id.bottom_sheet_info);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheetInfo);
        data_list = new ArrayList<>();
        latLngList = new ArrayList<>();
        markerArray = new ArrayList<>();

        txt_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) HospitalMapsActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("url",txt_web.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(HospitalMapsActivity.this,"Alamat Web Berhasil Disalin",Toast.LENGTH_SHORT).show();

            }
        });

        txt_tlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) HospitalMapsActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("tlp",txt_tlp.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(HospitalMapsActivity.this,"Nomor Telepon Berhasil Disalin",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void retrieveJSON(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TRY1", "TRY 1 ");
                        try {
                            Log.d("TRY2", "TRY 2 ");
                            JSONArray hospitals = new JSONArray(response);
                            for (int i = 0; i<hospitals.length(); i++){
                                JSONObject hospitalJSONObject = hospitals.getJSONObject(i);
                                Log.d("TRY 3",  hospitalJSONObject.toString());
                                String jNama = hospitalJSONObject.get("nama").toString();
                                String jAddress = hospitalJSONObject.get("address").toString();
                                String jWeb = hospitalJSONObject.get("web").toString();
                                String jTlpn = hospitalJSONObject.get("tlpn").toString();
                                String jOpen = hospitalJSONObject.get("open").toString();
                                String jGambar = hospitalJSONObject.get("gambar").toString();
                                String jLatLong = hospitalJSONObject.get("latlong").toString();
                                HospitalModel hospitalData = new HospitalModel(jNama, jAddress, jWeb, jTlpn, jOpen, jGambar, jLatLong);
                                data_list.add(hospitalData);
                                Log.d("TES JSON", jLatLong);
                                Log.d("MODEL NAMA", data_list.get(i).getNama());
                            }
                            Log.d("size", "onResponse: "+data_list.size());
                            latList = new String[data_list.size()];
                            longList = new String[data_list.size()];
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HospitalMapsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("onmap", "onMapReady: 1");
        googleMap.setOnMarkerClickListener(this);

        GPSTracker gps = new GPSTracker(HospitalMapsActivity.this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        curLat=latitude;
        curLong=longitude;
        Toast.makeText(HospitalMapsActivity.this, String.valueOf(curLat)+String.valueOf(curLong), Toast.LENGTH_SHORT).show();

        mMap = googleMap;

        Log.d("datasize", "onMapReady: "+this.data_list.size());

        for (int i = 0; i < this.data_list.size(); i++) {

            Log.d("INO", "onMapReady: "+i);
            Log.d("NAMANJAY", this.data_list.get(i).getNama());
            Log.d("LATLONG", this.data_list.get(i).getLatlong());
            Log.d("data", "onMapReady: "+this.data_list.size());

            String[] parts = this.data_list.get(i).getLatlong().split(", ");
            Log.d("PART1", "onMapReady: "+parts[0]);
            Log.d("PART2", "onMapReady: "+parts[1]);
            latList[i]=parts[0];
            Log.d("LATLIST", "onMapReady: "+latList[i].toString());
            longList [i] = parts[1];
            Log.d("LONGLIST", "onMapReady: "+longList[i].toString());
            latLngList.add(new LatLng(Double.parseDouble(latList[i]),Double.parseDouble(longList[i])));
            markerArray.add(mMap.addMarker(new MarkerOptions().position(latLngList.get(i)).title(this.data_list.get(i).getNama()).icon(bitmapDescriptorFromVector(this, R.drawable.ic_hospital))));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(curLat,curLong),15.0f));
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int vectorDrawableResourceId) {
        Drawable background = ContextCompat.getDrawable(context, R.drawable.ic_hospital);
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId);
        vectorDrawable.setBounds(40, 20, vectorDrawable.getIntrinsicWidth() + 40, vectorDrawable.getIntrinsicHeight() + 20);
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int i = markerArray.indexOf(marker);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        txt_nama.setText(this.data_list.get(i).getNama());
        txt_alamat.setText(this.data_list.get(i).getAddress());
        txt_web.setText(this.data_list.get(i).getWeb());
        txt_operasional.setText(this.data_list.get(i).getOpen());
        txt_tlp.setText(this.data_list.get(i).getTlpn());
        Glide.with(img_hospital.getContext())
                .load(this.data_list.get(i).getGambar())
                .into(img_hospital);
        return false;
    }
}