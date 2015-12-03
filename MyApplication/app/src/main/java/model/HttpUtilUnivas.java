package model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by Diego on 28/10/2015.
 */
public class HttpUtilUnivas extends AsyncTask<String, Void, HashMap>{

    private Context context;
    private int id =1;

    public HttpUtilUnivas(Context context) {
        this.context=context;

        Log.d("HttpUtilUnivas", "Construtor");
    }

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected HashMap doInBackground(String... strings) {

        String urlTeste = "http://diogenesrezende.tk:8080/WsAppTa/rest/produtos/" + id;

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(urlTeste));
            HttpResponse response = null;
            response = httpClient.execute(request);
            InputStream content = null;
            content = response.getEntity().getContent();

            Reader reader = new InputStreamReader(content);
            Gson gson = new Gson();
            HashMap result = gson.fromJson(reader, HashMap.class);

            return result;

                Log.d("Disciplina", " - ");
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void onPostExecute(String[] strings) {

    }
}
