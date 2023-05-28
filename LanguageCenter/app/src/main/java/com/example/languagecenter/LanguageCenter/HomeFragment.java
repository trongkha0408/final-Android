package com.example.languagecenter.LanguageCenter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import com.example.languagecenter.Post;
import com.example.languagecenter.PostAdapter;
import com.example.languagecenter.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Language_Center account;
    private String url = "http://192.168.1.6/AppProject/FinalTerm/API/getPostLangCenter.php/";
    private ListView lang_home_listview;
    private PostAdapter adapter;
    private ArrayList<Post> mPosts;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_language_center, container, false);
        findViewById(view);
        mPosts = new ArrayList<>();
        adapter = new PostAdapter(getActivity().getApplicationContext(), mPosts, R.layout.item_object);
        lang_home_listview.setAdapter(adapter);
        getData();
        return view;
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray j= new JSONArray(response);
                    mPosts.clear();
                    for (int i = 0; i < j.length(); i++) {
                        try {
                            JSONObject object = j.getJSONObject(i);
                            mPosts.add(new Post(object.getString("PostID"),
                                    object.getString("Name"),
                                    object.getString("Image"),
                                    object.getString("Price"),
                                    object.getString("Descr"),
                                    object.getString("Qty"),
                                    object.getString("Phone"),
                                    object.getString("Address"),
                                    object.getString("Fb"),
                                    object.getString("TimeUpload"),
                                    object.getString("LangID"),
                                    object.getString("DayID"),
                                    object.getString("CertID")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("LangID", String.valueOf(account.getLangID()));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void findViewById(View view) {
        lang_home_listview = view.findViewById(R.id.lang_home_listview);
    }
}