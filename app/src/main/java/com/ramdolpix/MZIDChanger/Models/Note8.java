package com.ramdolpix.MZIDChanger.Models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.ramdolpix.MZIDChanger.MainActivity.sudo;

public class Note8 {
//    public static String checkID() {
//
//        try {
//            // Executes the command.
//            Process process = Runtime.getRuntime().exec("cat /dev/block/mmcblk0p19");
//
//            // Reads stdout.
//            // NOTE: You can write to stdin of the command using
//            //       process.getOutputStream().
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//
//            int read;
//            char[] buffer = new char[4096];
//            StringBuffer output = new StringBuffer();
//            while ((read = reader.read(buffer)) > 0) {
//                output.append(buffer, 0, read);
//            }
//            reader.close();
//
//            // Waits for the command to finish.
//            process.waitFor();
//
//            return output.toString();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static void changeID2Global(){
        sudo("mount -o rw,remount /dev");
        sudo("dd if=/dev/block/bootdevice/by-name/devinfo_mz of=/sdcard/id");
        sudo("sed -i 's/82201001/82251002/' /sdcard/id");
        sudo("dd if=/sdcard/id of=/dev/block/bootdevice/by-name/devinfo_mz");
        sudo("rm -rf /sdcard/id");
    }
    public static void changeID2China(){
        sudo("mount -o rw,remount /dev");
        sudo("dd if=/dev/block/bootdevice/by-name/devinfo_mz of=/sdcard/id");
        sudo("sed -i 's/82251002/82201001/' /sdcard/id");
        sudo("dd if=/sdcard/id of=/dev/block/bootdevice/by-name/devinfo_mz");
        sudo("rm -rf /sdcard/id");
    }
}
