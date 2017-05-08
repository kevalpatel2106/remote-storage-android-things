package com.kevalpatel2106.ftp_server;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by Keval Patel on 06/05/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class FTPManager {
    private static final int PORT_NUMBER = 53705;// The PORT_NUMBER number
    private static final String TAG = FTPManager.class.getSimpleName();

    private FtpServer mFtpServer;
    private String ftpConfigDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ftpConfig/";

    /**
     * Initialize the FTP server
     *
     * @param context instance of the caller.
     * @see 'http://stackoverflow.com/a/42474815/4690731'
     */
    public FTPManager(Context context) {
//        try {
//            mountDrive();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//            return;
//        }

        File f = new File(ftpConfigDir);
        if (!f.exists()) f.mkdir();

        //Copy the resources.
        copyResourceFile(context, R.raw.users, ftpConfigDir + "users.properties");
        copyResourceFile(context, R.raw.ftpserver, ftpConfigDir + "ftpserver.jks");
    }


    public String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> enumInetAddress = enumNetworkInterfaces.nextElement().getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();
                    if (inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Copy all the resources from assets to local file storage.
     *
     * @param context    instance of the caller.
     * @param rid        Assets id.
     * @param targetFile target file to copy.
     */
    private void copyResourceFile(Context context, int rid, String targetFile) {
        InputStream fin = context.getResources().openRawResource(rid);
        FileOutputStream fos = null;
        int length;
        try {
            fos = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            while ((length = fin.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Start the FTP server.
     */
    public void startServer() {
        //Set the user factory
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        String filename = ftpConfigDir + "users.properties";
        File files = new File(filename);
        userManagerFactory.setFile(files);

        //Set the server factory
        FtpServerFactory serverFactory = new FtpServerFactory();
        serverFactory.setUserManager(userManagerFactory.createUserManager());

        //Set the port number
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(PORT_NUMBER);
        try {
            serverFactory.addListener("default", factory.createListener());
            FtpServer server = serverFactory.createServer();
            mFtpServer = server;

            //Start the server
            server.start();
        } catch (FtpException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onCreate: FTP server started. IP address: " + getLocalIpAddress() + ":" + PORT_NUMBER);
            }
        }).start();
    }

    /**
     * Stop the FTP server.
     */
    public void stopServer() {
        if (null != mFtpServer) {
            mFtpServer.stop();
            mFtpServer = null;
        }
    }

    private void mountDrive() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("su -c \"mkdir /mnt/usb\"");
        p.waitFor();
        p = Runtime.getRuntime().exec("su -c \"mount -t vfat -o rw /dev/block/sda1 /mnt/usb\"");
        p.waitFor();
    }
}