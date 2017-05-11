# Remote Storage
<a href="https://www.paypal.me/kevalpatel2106"> <img src="https://img.shields.io/badge/paypal-donate-yellow.svg" /></a> [![Build Status](https://travis-ci.org/kevalpatel2106/remote-storage-android-things.svg?branch=master)](https://travis-ci.org/kevalpatel2106/remote-storage-android-things)

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
mkdir /mnt/usb
mount -t vfat -o rw /dev/block/sda1 /mnt/usb
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
  - **Port** : 53705
  - **URL** : ftp://*{YOUR IP}*:53705
  - **Username** : admin
  - **Password** : admin
  
#### How to access on my bowser?
- Open *ftp://{YOUR IP}:53705* url in your bowser and enter username and password.

#### How to access on my computer? 
- [**Mac**](http://osxdaily.com/2011/02/07/ftp-from-mac/)
- [**Windows**](https://www.howtogeek.com/272176/how-to-connect-to-ftp-servers-in-windows-without-extra-software/)

#### How to access on my mobile?
- **Android** : Download ES File manager and create new FTP connection under Network section. Use above credentials and connect.

## Note:
Everytime raspberry pi restarts you need to [mount USB drive](https://github.com/kevalpatel2106/remote-storage-android-things#mount-usb-drive) and [run the application](https://github.com/kevalpatel2106/remote-storage-android-things#build-and-run-the-application).

## Questions?🤔
Hit me on twitter [![Twitter](https://img.shields.io/badge/Twitter-@kevalpatel2106-blue.svg?style=flat)](https://twitter.com/kevalpatel2106)

## License
Copyright 2017 Keval Patel

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
