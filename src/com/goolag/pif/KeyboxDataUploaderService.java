package com.goolag.pif;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.security.IKeyboxService;
import android.util.Log;

public class KeyboxDataUploaderService extends Service {
    private static final String TAG = "KeyboxDataUploaderService";

    @Override
    public void onCreate() {
        super.onCreate();
        uploadKeyboxData();
    }

    private void uploadKeyboxData() {
        IKeyboxService keyboxService = IKeyboxService.Stub.asInterface(
                ServiceManager.getService("keybox"));

        if (keyboxService != null) {
            try {
                String[] keyboxData = new String[]{
                    "EC.PRIV:your_ec_private_key",
                    "EC.CERT_1:your_ec_cert_1",
                    "EC.CERT_2:your_ec_cert_2",
                    "EC.CERT_3:your_ec_cert_3",
                    "RSA.PRIV:your_rsa_private_key",
                    "RSA.CERT_1:your_rsa_cert_1",
                    "RSA.CERT_2:your_rsa_cert_2",
                    "RSA.CERT_3:your_rsa_cert_3"
                };
                keyboxService.setKeyboxData(keyboxData);
                Log.i(TAG, "Keybox data uploaded to KeyboxService");
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to set Keybox data", e);
            }
        } else {
            Log.e(TAG, "KeyboxService is not available");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}