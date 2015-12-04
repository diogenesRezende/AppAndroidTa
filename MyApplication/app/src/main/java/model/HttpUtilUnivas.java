package model;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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


public class HttpUtilUnivas extends AsyncTask<String, Void, ServerDetail> {

    private Context context;
    private ProgressDialog progressDialog;
    private int id =1;


    public HttpUtilUnivas(Context context) {
        this.context=context;
        Log.d("HttpUtilUnivas", "Construtor");
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(this.context,"Aguarde um Instante!","Buscando o estoque!");
    }

    @Override
    protected ServerDetail doInBackground(String... strings) {

        String urlTeste = "http://diogenesrezende.tk:8080/WsAppTa/rest/produtos/" + id;

       ServerDetail result = null;
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
            result = gson.fromJson(reader, ServerDetail.class);
//            retorno =
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ServerDetail hashMap) {
        progressDialog.hide();
    }
}
