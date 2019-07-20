package com.ramdolpix.MZIDChanger.Models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.ramdolpix.MZIDChanger.MainActivity.sudo;

public class M5Note {
    public static String checkID() {

        try {
            // Executes the command.
            Process process = Runtime.getRuntime().exec("cat /dev/block/mmcblk0p28");

            // Reads stdout.
            // NOTE: You can write to stdin of the command using
            //       process.getOutputStream().
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();

            // Waits for the command to finish.
            process.waitFor();

            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void changeID2Global(){
        sudo("mount -o rw,remount /dev");
        sudo("echo -e '5' | dd of=/dev/block/mmcblk0p28 bs=1 seek=515 count=1");
    }
    public static void changeID2China(){
        sudo("mount -o rw,remount /dev");
        sudo("echo -e '0' | dd of=/dev/block/mmcblk0p28 bs=1 seek=515 count=1");

    }
}
