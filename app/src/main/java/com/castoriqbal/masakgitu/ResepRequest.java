package com.castoriqbal.masakgitu;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Castor on 1/9/2018.
 */

public class ResepRequest extends StringRequest {
    private static final String  MASAKGITU_RESEP_REQUEST_URL = "http://masakini.xyz/masakgituapi/resep.php";
    private Map<String, String> parameters = new HashMap<>();

    public ResepRequest(ArrayList<String> listBahan, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, MASAKGITU_RESEP_REQUEST_URL, listener, errorListener);
        parameters.put("jumlahBahan", Integer.toString(listBahan.size()));
        for(int i = 0; i < listBahan.size(); i++){
            parameters.put("bahan"+i, listBahan.get(i));
        }
    }

    @Override
    protected Map<String, String> getParams() {
        return parameters;
    }
}
