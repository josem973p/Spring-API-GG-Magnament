package com.ociGG.goldengate.Config;

import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
@Service
public class MyTrustManager  implements X509TrustManager {

    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }


    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[]      paramArrayOfX509Certificate, String paramString)
            throws CertificateException {
        // do nothing

    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString)
            throws CertificateException {
        // do nothing

    }

    public static void disableSSL() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] { new   MyTrustManager() };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }}
}
