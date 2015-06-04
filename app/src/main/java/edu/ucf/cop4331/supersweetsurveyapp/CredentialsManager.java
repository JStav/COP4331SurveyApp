package edu.ucf.cop4331.supersweetsurveyapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Class to hash passwords and check credentials
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
            return "Exception: " + e.getMessage();
        }
    }
    public boolean isCredentialValid(String password, String dbHash) {
        String md5 = md5(password);
        return md5.equals(dbHash);
    }
}
