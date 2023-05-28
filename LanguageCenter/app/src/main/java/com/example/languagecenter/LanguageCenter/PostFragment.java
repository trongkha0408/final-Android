package com.example.languagecenter.LanguageCenter;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.languagecenter.Language_Center;
import com.example.languagecenter.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String url = "http://192.168.1.6/AppProject/FinalTerm/API/insertPost.php/";
    private String urlGetCert = "http://192.168.1.6/AppProject/FinalTerm/API/getCertificate.php/";
    private String urlGetDay = "http://192.168.1.6/AppProject/FinalTerm/API/getDayStudy.php/";
    private Spinner cert, day;
    private ImageView lang_post_img;
    private EditText name, des, price, qty, phone, address, fb;
    private Button post;
    private Bitmap bitmap;
    private String encodeStringImg;
    private Language_Center account;
    private String select_day;
    private String select_cert;

    private ArrayList<String> certList = new ArrayList<>();
    private ArrayAdapter<String> certAdapter;
    private ArrayList<String> dayList = new ArrayList<>();
    private ArrayAdapter<String> dayAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        account = LanguageCenterActivity.account_language_center;
        View view = inflater.inflate(R.layout.fragment_post_language_center, container, false);
        findViewById(view);
        getCert();
        certAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, certList);
        certAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cert.setAdapter(certAdapter);

        cert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select_cert = String.valueOf(adapterView.getItemAtPosition(i).toString().split("_")[0]);
                Toast.makeText(getActivity(), select_cert.trim(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getDay();
        dayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, dayList);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(dayAdapter);

        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select_day = String.valueOf(adapterView.getItemAtPosition(i).toString().split("_")[0]);
                Toast.makeText(getActivity(), select_day.trim(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lang_post_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(getActivity()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Chon Anh"), 1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.length()<=0){
                    Toast.makeText(getActivity(), "vui lỏng nhập tên trung tâm!", Toast.LENGTH_SHORT).show();
                }else if(des.length()<=0){
                    Toast.makeText(getActivity(), "vui lỏng nhập phần giới thiệu!", Toast.LENGTH_SHORT).show();
                }
                else if(encodeStringImg.length()<=0){
                    Toast.makeText(getActivity(), "vui lỏng chon anh!", Toast.LENGTH_SHORT).show();
                }else if(qty.length()<=0){
                    Toast.makeText(getActivity(), "vui lỏng nhập số lượng học viên!", Toast.LENGTH_SHORT).show();
                }else if(phone.length()<10){
                    Toast.makeText(getActivity(), "vui lỏng nhập chính xác số điện thoại!", Toast.LENGTH_SHORT).show();
                }else if(address.length()<=0){
                    Toast.makeText(getActivity(), "vui lỏng nhập địa chỉ trung tâm!", Toast.LENGTH_SHORT).show();
                }else if(fb.length()<=0){
                    Toast.makeText(getActivity(), "vui lỏng nhập Facebook trung tâm!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), String.valueOf(account.getLangID()), Toast.LENGTH_SHORT).show();
                    postToDB();
                }
            }
        });
        return view;
    }

    private void postToDB() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(getActivity(), "Đăng tin thành công!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Đã xảy ra lỗi, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Name", name.getText().toString().trim());
                params.put("Price", price.getText().toString().trim());
                params.put("upload", encodeStringImg);
                params.put("Descr", des.getText().toString().trim());
                params.put("Qty", qty.getText().toString().trim());
                params.put("Phone", phone.getText().toString().trim());
                params.put("Address", address.getText().toString().trim());
                params.put("Fb", fb.getText().toString().trim());
                params.put("LangID", String.valueOf(account.getLangID()));
                params.put("DayID", select_day.trim());
                params.put("CertID", select_cert.trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode== Activity.RESULT_OK){
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getActivity().getApplicationContext().getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                lang_post_img.setImageBitmap(bitmap);

                encodeBitmapImg(bitmap);
            }catch (Exception e){
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodeBitmapImg(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        encodeStringImg = android.util.Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void getDay() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetDay, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dayList.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        dayList.add(object.getString("DayID")+"_"+object.getString("Day")+"_"+object.getString("Time"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                dayAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getCert() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetCert, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                certList.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        certList.add(object.getString("CertID")+"_"+object.getString("Name"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                certAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void findViewById(View view) {
        cert = view.findViewById(R.id.lang_post_spinner_cert);
        day = view.findViewById(R.id.lang_post_spinner_day);
        post = view.findViewById(R.id.lang_post_post);
        name = view.findViewById(R.id.lang_post_name);
        des = view.findViewById(R.id.lang_post_descr);
        price = view.findViewById(R.id.lang_post_price);
        qty = view.findViewById(R.id.lang_post_qty);
        phone = view.findViewById(R.id.lang_post_phone);
        address = view.findViewById(R.id.lang_post_address);
        fb = view.findViewById(R.id.lang_post_fb);
        lang_post_img = view.findViewById(R.id.lang_post_img);
    }
}