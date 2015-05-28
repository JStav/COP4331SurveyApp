package edu.ucf.cop4331.supersweetsurveyapp;


import android.net.http.HttpResponseCache;
import android.os.StrictMode;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Class to hash passwords and connect to the remote database to check if user logs in
public class CredentialsManager {

    public CredentialsManager(){}

    // Generate md5 hash from string 
    public final String md5(final String s) {
        final String MD5 = "MD5";
        
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            
            for (byte digestByte : messageDigest) {
                String h = Integer.toHexString(0xFF & digestByte);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return "";
    }

    public boolean isCredentialValid(final String md5, String username) {

        String db_username = "a9634422_stav";
        String password = "testpass1";
        String link = "http://jstav.site50.net/login.php?username=" + db_username + "&password=" + password;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();

        try {
            request.setURI(new URI(link));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = client.execute(request);

        } catch (java.io.IOException e){
            e.printStackTrace();
        }
        return true;
    }
}
