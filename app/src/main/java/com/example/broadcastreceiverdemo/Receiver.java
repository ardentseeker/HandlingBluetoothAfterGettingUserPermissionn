package com.example.broadcastreceiverdemo;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

    private static final String TAG = "Receiver";

    public Receiver(BluetoothHandler bluetoothHandler) {
        this.bluetoothHandler = bluetoothHandler;
    }

    BluetoothHandler bluetoothHandler;

    @Override
    public void onReceive(Context context, Intent intent) {
        checkingNetConnection(context);
    }

    public void checkingNetConnection(Context context){
        ConnectivityManager connec = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connec.getActiveNetworkInfo()!= null && connec.getActiveNetworkInfo().isConnected()){
            Toast.makeText(context, "net is on ", Toast.LENGTH_SHORT).show();
        }else{

            BluetoothManager bluetoothManager = context.getSystemService(BluetoothManager.class);
            BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
            if (bluetoothAdapter == null) {
                Toast.makeText(context, "This device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
            }else {
                Log.d(TAG, "checkingNetConnection: "+ bluetoothAdapter.isEnabled());
                if (!bluetoothAdapter.isEnabled()) {
                    bluetoothHandler.turnOnBluetooth();

                }
            }

        }
    }
}
