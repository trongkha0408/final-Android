package com.example.languagecenter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * Use the {@link ForgetpwdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgetpwdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText pwd_OTP, pwd_gmail, pwd_newPWD, pwd_confPWD;
    private TextView pwd_sendOTP, pwd_sendOTPAgain;
    private RadioButton pwd_student, pwd_language;
    private Button PWD;
    private String OTP;
    private String urlSendOTP = "http://192.168.1.6/AppProject/FinalTerm/API/verifyOTP.php/";
    private String urlPWDStudent = "http://192.168.1.6/AppProject/FinalTerm/API/updateStudent.php/";
    private String urlPWDLangCenter = "http://192.168.1.6/AppProject/FinalTerm/API/updateLangCenter.php/";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForgetpwdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForgetpwdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForgetpwdFragment newInstance(String param1, String param2) {
        ForgetpwdFragment fragment = new ForgetpwdFragment();
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
        View view = inflater.inflate(R.layout.fragment_forgetpwd, container, false);
        findViewById(view);
        pwd_sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pwd_gmail.length()<=0){
                    Toast.makeText(getActivity(), "Vui lòng nhập Gmail!", Toast.LENGTH_SHORT).show();
                }else{
                    sendOTP();
                }
            }
        });
        pwd_sendOTPAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pwd_gmail.length()<=0){
                    Toast.makeText(getActivity(), "Vui lòng nhập Gmail!", Toast.LENGTH_SHORT).show();
                }else{
                    sendOTP();
                }
            }
        });
        PWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pwd_gmail.length()<=0){
                    Toast.makeText(getActivity(), "Vui lòng nhập gmail!", Toast.LENGTH_SHORT).show();
                }
                else if(pwd_OTP.length() <= 0){
                    Toast.makeText(getActivity(), "Vui lòng nhập mã OTP!", Toast.LENGTH_SHORT).show();
                }else if(pwd_newPWD.length() < 8){
                    Toast.makeText(getActivity(), "Vui lòng nhập mật khẩu mới nhiều hơn 8 ký tự!", Toast.LENGTH_SHORT).show();
                }else if(pwd_confPWD.length() < 0){
                    Toast.makeText(getActivity(), "Vui lòng nhập xác nhận mật khẩu mới!", Toast.LENGTH_SHORT).show();
                }else if(pwd_newPWD.toString().trim().equals(pwd_confPWD.toString().trim())){
                    Toast.makeText(getActivity(), "Mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pwd_OTP.getText().toString().equals(OTP)){
                        if(pwd_student.isChecked()){
                            updateAccount(urlPWDStudent);
                        }
                        if(pwd_language.isChecked()){
                            updateAccount(urlPWDLangCenter);
                        }
                    }else{
                        Toast.makeText(getActivity(), "Mã OTP không chính xác!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    private void updateAccount(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại!", Toast.LENGTH_SHORT).show();
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
                params.put("Gmail", pwd_gmail.getText().toString().trim());
                params.put("PWD", pwd_newPWD.getText().toString().trim());
                params.put("OTP", pwd_OTP.getText().toString().trim());
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
                params.put("gmail", pwd_gmail.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void findViewById(View view) {
        pwd_OTP = view.findViewById(R.id.pwd_OTP);
        pwd_gmail = view.findViewById(R.id.pwd_gmail);
        pwd_sendOTP = view.findViewById(R.id.pwd_sendOTP);
        pwd_sendOTPAgain = view.findViewById(R.id.pwd_sendOTPAgain);
        pwd_newPWD = view.findViewById(R.id.pwd_newPWD);
        pwd_confPWD = view.findViewById(R.id.pwd_confPWD);
        pwd_student = view.findViewById(R.id.pwd_student);
        pwd_language = view.findViewById(R.id.pwd_language);
        PWD = view.findViewById(R.id.PWD);
    }
}