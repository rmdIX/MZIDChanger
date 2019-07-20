package com.ramdolpix.MZIDChanger.Models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.ramdolpix.MZIDChanger.MainActivity.sudo;

public class Note9 {
// public static String checkID() {
//
//        try {
//            // Executes the command.
//            Process process = Runtime.getRuntime().exec("cat /dev/block/mmcblk0p74");
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
        sudo("dd if=/dev/block/bootdevice/by-name/mz_devinfo of=/sdcard/id");
        sudo("sed -i 's/92301001/92351001/' /sdcard/id");
        sudo("dd if=/sdcard/id of=/dev/block/bootdevice/by-name/mz_devinfo");
        sudo("rm -rf /sdcard/id");
    }
    public static void changeID2China(){
        sudo("mount -o rw,remount /dev");
        sudo("dd if=/dev/block/bootdevice/by-name/mz_devinfo of=/sdcard/id");
        sudo("sed -i 's/92351001/92301001/' /sdcard/id");
        sudo("dd if=/sdcard/id of=/dev/block/bootdevice/by-name/mz_devinfo");
        sudo("rm -rf /sdcard/id");
    }
}

