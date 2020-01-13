package com.example.robot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 456;

    private BluetoothAdapter bluetoothAdapter = null;
    private BluetoothClient bluetoothClient = null;
    private List<BluetoothDevice> knownDevices = null;

    private TextView lblConnectedDevice;

    private Button btnForward1;
    private Button btnBackward1;
    private Button btnForward2;
    private Button btnBackward2;

    private ListView deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblConnectedDevice = findViewById( R.id.lblConnectedDevice);

        btnForward1 = findViewById( R.id.btnForward1 );
        btnForward1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    whriteBluetooth('a');
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    whriteBluetooth('b');
                }
                return true;
            }

        });

        btnBackward1 = findViewById( R.id.btnBackward1 );
        btnBackward1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    whriteBluetooth('c');
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    whriteBluetooth('d');
                }
                return true;
            }

        });

        btnForward2 = findViewById( R.id.btnForward2 );
        btnForward2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    whriteBluetooth('e');
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    whriteBluetooth('f');
                }
                return true;
            }

        });

        btnBackward2 = findViewById( R.id.btnBackward2 );
        btnBackward2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    whriteBluetooth('g');
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    whriteBluetooth('h');
                }
                return true;
            }

        });

        deviceList = findViewById( R.id.deviceList);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if ( ! bluetoothAdapter.isEnabled() ) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        knownDevices = new ArrayList<>( bluetoothAdapter.getBondedDevices() );
        ArrayAdapter<BluetoothDevice> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, knownDevices);
        deviceList.setAdapter(adapter);
        deviceList.setOnItemClickListener( deviceListListener );
    }

    private ListView.OnItemClickListener deviceListListener = new ListView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> adapter, View view, int arg2, long rowId) {
            BluetoothDevice device = knownDevices.get( (int) rowId );
            bluetoothClient = new BluetoothClient( device );
            lblConnectedDevice.setText( "Connected to " + device.getName() );
        }
    };

    private void whriteBluetooth(char code){
        if ( bluetoothClient == null ) return;
        bluetoothClient.writeChar( code );
    }

    private class BluetoothClient extends Thread {

        private BluetoothDevice bluetoothDevice;
        private BluetoothSocket bluetoothSocket;
        private InputStream inputStream;
        private OutputStream outputStream;
        private boolean isAlive = true;

        public BluetoothClient( BluetoothDevice device ) {
            try {
                bluetoothDevice = device;
                bluetoothSocket = device.createRfcommSocketToServiceRecord( device.getUuids()[0].getUuid() );
                bluetoothSocket.connect();

                inputStream = bluetoothSocket.getInputStream();
                outputStream = bluetoothSocket.getOutputStream();

                Toast.makeText( MainActivity.this, device.getName() + " connected", Toast.LENGTH_LONG ).show();
            } catch ( IOException exception ) {
                Log.e( "DEBUG", "Cannot establish connection", exception );
            }
        }


        // Inutile dans le code actuel. Mais cela permettrait de recevoir
        // des informations du véhicule dans une future version.
        @Override
        public void run() {
            try {
                while (isAlive) {
                    if ( inputStream.available() > 0 ) {
                        Log.i("DEBUG", String.format("%c", inputStream.read()));
                    } else {
                        Thread.sleep( 100 );
                    }
                }
            } catch( Exception exception ) {
                Log.e( "DEBUG", "Cannot read data", exception );
                close();
            }
        }

        public void writeChar(char code) {
            try {
                outputStream.write( code );
                outputStream.flush();
            } catch (IOException e) {
                Log.e( "DEBUG", "Cannot write message", e );
            }
        }

        // Termine la connexion en cours et tue le thread
        public void close() {
            try {
                bluetoothSocket.close();
                isAlive = false;
            } catch (IOException e) {
                Log.e( "DEBUG", "Cannot close socket", e );
            }
        }
    }
}
