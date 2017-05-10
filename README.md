# Remote Storage

#### Your one stop solution for remote file storage.

Connect your old usb hard drive with USB port or the thumb drive in Raspberry pi and access it anywhere in you home wireless. This application will turn you raspberry pi a FTP server. Once you mount your usb drive with the Android system, This will make it available it in your local network.

## Mount USB drive:
- Plug you USB drive at any of the USB port in your Raspberry Pi.
- Open adb shell by typing below command in terminal *(Make sure your raspberry pi is connected via adb)*:
```
adb shell
```
- Mount the USB drive by running below command in adb shell *(Your USB drive srould be formatted in FAT file system.)*: 
```
su
"mkdir /mnt/usb"
"mount -t vfat -o rw /dev/block/sda1 /mnt/usb"
```
- Run "Remote Storage" application.

## Build and run the application:
- Connect your rapberry pi running on [Android Things](https://developer.android.com/things/index.html) via [adb](https://developer.android.com/things/hardware/raspberrypi.html).
- On Android Studio, select the module in the select box by the "Run" button, and then click on the "Run" button. If you prefer to run on the command line, run following command in terminal in the root of the project.
```
./gradlew app:installDebug
adb shell am start com.example.androidthings.simplepio/com.kevalpatel2106.remotestorage.MainActivity
```

## Access the FTP server:
- FTP server is running on the IP on which your rapberry pi is connected. The port address is 53705.
  - Port : 53705
  - URL : ftp://{YOUR IP}:53705
  - Username : admin
  - Password : admin
  
#### How to access on my bowser?
- Open  ftp://{YOUR IP}:53705 url in your bowser and enter username and password.

#### How to access on my computer? 
- [**Mac**](http://osxdaily.com/2011/02/07/ftp-from-mac/)
- [**Windows**](https://www.howtogeek.com/272176/how-to-connect-to-ftp-servers-in-windows-without-extra-software/)

#### How to access on my mobile?
- **Android** : Download ES File manager and create new FTP connection under Network section. Use above credentials and connect.

## Note:
Everytime raspberry pi restarts you need to [mount USB drive](https://github.com/kevalpatel2106/remote-storage-android-things#mount-usb-drive) and [run the application](https://github.com/kevalpatel2106/remote-storage-android-things#build-and-run-the-application).
