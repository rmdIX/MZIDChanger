package com.ramdolpix.MZIDChanger.Models;

import static com.ramdolpix.MZIDChanger.MainActivity.sudo;

public class M3Note {
    public static void changeID2Global(){
        sudo("mount -o rw,remount /dev");
        sudo("echo 51001 | dd of=/dev/block/mmcblk0 bs=1 seek=797443075 count=5");
    }
    public static void changeID2China(){
        sudo("mount -o rw,remount /dev");
        sudo("echo 01001 | dd of=/dev/block/mmcblk0 bs=1 seek=797443075 count=5");

    }
}
