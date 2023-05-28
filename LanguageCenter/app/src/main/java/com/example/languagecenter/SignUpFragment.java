package com.example.languagecenter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText su_username, su_pwd, su_gmail, su_OTP;
    private TextView su_sendOTPAgain, su_sendOTP;
    private RadioButton su_student, su_language;
    private Button SignUp;
    private String urlSendOTP = "http://192.168.1.6/AppProject/FinalTerm/API/verifyOTP.php/";
    private String urlSignUpStudent = "http://192.168.1.6/AppProject/FinalTerm/API/insertStudent.php/";
    private String urlSignUpLangCenter = "http://192.168.1.6/AppProject/FinalTerm/API/insertLangCenter.php/";
    private String OTP;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        findViewById(view);
        su_sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(su_gmail.length()<=0){
                    Toast.makeText(getActivity(), "Vui lòng nhập Gmail!", Toast.LENGTH_SHORT).show();
                }else{
                    sendOTP();
                }
            }
        });
        su_sendOTPAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(su_gmail.length()<=0){
                    Toast.makeText(getActivity(), "Vui lòng nhập Gmail!", Toast.LENGTH_SHORT).show();
                }else{
                    sendOTP();
                }
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(su_username.length()<=0){
                    Toast.makeText(getActivity(), "Vui lòng nhập tên đăng nhập!", Toast.LENGTH_SHORT).show();
                }
                else if(su_pwd.length() < 8){
                    Toast.makeText(getActivity(), "Vui lòng nhập mật khẩu tối thiểu 8 ký tự!", Toast.LENGTH_SHORT).show();
                }
                else if(su_gmail.length() <= 0){
                    Toast.makeText(getActivity(), "Vui lòng nhập gmail!", Toast.LENGTH_SHORT).show();
                }
                else if(su_OTP.length() <= 0){
                    Toast.makeText(getActivity(), "Vui lòng nhập mã OTP", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(su_OTP.getText().toString().equals(OTP)){
                        if(su_student.isChecked()){
                            addAccount(urlSignUpStudent);
                        }
                        if(su_language.isChecked()){
                            addAccount(urlSignUpLangCenter);
                        }
                    }else{
                        Toast.makeText(getActivity(), "Mã OTP không chính xác!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    private void addAccount(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(getActivity(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
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
                params.put("Username", su_username.getText().toString().trim());
                params.put("PWD", su_pwd.getText().toString().trim());
                params.put("Gmail", su_gmail.getText().toString().trim());
                params.put("OTP", su_OTP.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void sendOTP() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlSendOTP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.trim().equals("error")){
                    OTP = response;
                    Toast.makeText(getActivity(), "Gửi OTP thành công!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Gửi OTP thất bại!", Toast.LENGTH_SHORT).show();
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
                params.put("gmail", su_gmail.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void findViewById(View view) {
        su_username = view.findViewById(R.id.su_username);
        su_pwd = view.findViewById(R.id.su_pwd);
        su_gmail = view.findViewById(R.id.su_gmail);
        su_sendOTPAgain = view.findViewById(R.id.su_sendOTPAgain);
        su_sendOTP = view.findViewById(R.id.su_sendOTP);
        su_OTP = view.findViewById(R.id.su_OTP);
        su_student = view.findViewById(R.id.su_student);
        su_language = view.findViewById(R.id.su_language);
        SignUp = view.findViewById(R.id.SignUp);
    }
}