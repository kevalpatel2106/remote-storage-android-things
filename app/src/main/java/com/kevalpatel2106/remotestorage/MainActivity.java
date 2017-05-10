package com.kevalpatel2106.remotestorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kevalpatel2106.ftp_server.FTPManager;

public class MainActivity extends AppCompatActivity {

    private FTPManager mFtpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init ftp server
        mFtpManager = new FTPManager(this);
        mFtpManager.startServer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFtpManager.stopServer();
    }
}
