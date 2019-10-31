package com.example.williamfuller.anything;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by William Fuller on 14/01/2018.
 */
public class WiFiP2pActivity extends AppCompatActivity {

    WifiP2pManager.Channel mChannel;
    WifiP2pManager mManager;
    BroadcastReceiver mReceiver;
    IntentFilter mIntentFilter;
    private List peers = new ArrayList();
    ListView peerArray;
    Toast noPeers;
    WifiP2pManager.PeerListListener peerListListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifiactivty);



        //Change in Wi-Fi P2P status
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);


        peerArray=(ListView)findViewById(R.id.peerList);
        ArrayAdapter<String> itemsAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,peers);
        peerArray.setAdapter(itemsAdapter);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);


        //Peer discovery
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast WDon=Toast.makeText(getApplicationContext(),"Wifi Direct: on",Toast.LENGTH_SHORT);
                WDon.show();
            }
            @Override
            public void onFailure(int reasonCode) {
            }
        });

        //Peer List display
        peerListListener=new WifiP2pManager.PeerListListener() {
            @Override
            public void onPeersAvailable(WifiP2pDeviceList peerList) {
                List<WifiP2pDevice> refreshedPeers= (List<WifiP2pDevice>) peerList.getDeviceList();
                if(!refreshedPeers.equals(peers)){
                    peers.clear();
                    peers.addAll(refreshedPeers);
                }
                if (peers.size()==0){

                }
            }
        };



    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}
