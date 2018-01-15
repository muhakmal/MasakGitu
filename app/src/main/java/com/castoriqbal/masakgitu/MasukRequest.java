package com.castoriqbal.masakgitu;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akmalmuhamad on 16/01/18.
 */

public class MasukRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://masakini.xyz/masakiniapi/Login.php";
    private static Map<String, String> params;

    public MasukRequest(String email, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    public static String getEmail(){
        return params.get("email");
    }
}
