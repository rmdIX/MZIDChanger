package com.ramdolpix.MZIDChanger;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ramdolpix.MZIDChanger.Models.M2Mini;
import com.ramdolpix.MZIDChanger.Models.M2Note;
import com.ramdolpix.MZIDChanger.Models.M3Note;
import com.ramdolpix.MZIDChanger.Models.M5;
import com.ramdolpix.MZIDChanger.Models.M5Note;
import com.ramdolpix.MZIDChanger.Models.M5s;
import com.ramdolpix.MZIDChanger.Models.M6;
import com.ramdolpix.MZIDChanger.Models.M6Note;
import com.ramdolpix.MZIDChanger.Models.M6s;
import com.ramdolpix.MZIDChanger.Models.Note8;
import com.ramdolpix.MZIDChanger.Models.MX6;
import com.ramdolpix.MZIDChanger.Models.MeizuS6;
import com.ramdolpix.MZIDChanger.Models.Note9;
import com.ramdolpix.MZIDChanger.Models.PRO6;
import com.ramdolpix.MZIDChanger.Models.PRO6s;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView infoViewer;
    private Button change2Global;
    private Button change2China;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoViewer = findViewById(R.id.infoViewer);
        sudo();
        change2Global = findViewById(R.id.changeID2GlobalButton);
        change2China = findViewById(R.id.changeID2ChinaButton);
        if (!isRooted()){
            change2Global.setEnabled(false);
            change2China.setEnabled(false);
            infoViewer.setText(R.string.su_file_not_found);
            infoViewer.setTextColor(getResources().getColor(R.color.red));
        }
    }

    public static boolean findBinary(String binaryName) {
        boolean found = false;
        if (!found) {
            String[] places = {"/sbin/", "/system/bin/", "/system/xbin/",
                    "/data/local/xbin/", "/data/local/bin/",
                    "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/",
                    "/su/bin/"
            };
            for (String where : places) {
                if (new File(where + binaryName).exists()) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
    private static boolean isRooted() {
        return findBinary("su");
    }
    public static void sudo(String...strings) {
        try {
            Process su = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

            for (String s: strings) {
                outputStream.writeBytes(s + "\n");
                outputStream.flush();
            }

            outputStream.writeBytes("exit\n");
            outputStream.flush();
            try {
                su.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickDeviceInfo(View view) {

        String brand, model, flymeVer, serialNumber, androidVer,rootStatus;


        if (isRooted()){
            rootStatus = "Rooted";
        }else rootStatus = "Not Rooted";

        brand = Build.BRAND;
        model = android.os.Build.MODEL;
        flymeVer = Build.DISPLAY;
        serialNumber = Build.SERIAL;
        androidVer = Build.VERSION.RELEASE;

        AlertDialog.Builder devInfoAlertDialog = new AlertDialog.Builder(this);
        devInfoAlertDialog.setMessage(
                getString(R.string.brand) + brand + "\n" +
                        getString(R.string.model) + model + "\n" +
                        getString(R.string.flymeVersion) + flymeVer + "\n" +
                        getString(R.string.serial) + serialNumber + "\n" +
                        getString(R.string.androidVer) + androidVer + "\n" +
                        getString(R.string.root_status) + rootStatus
        );
        devInfoAlertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        devInfoAlertDialog.show();
    }

    public void onClickChangeID2Global(View view) {
        switch (android.os.Build.MODEL){
            case "M6 Note":
                M6Note.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "M5 Note":
                M5Note.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "m3 mote":
                M3Note.changeID2Global();
                infoViewer.setText("Operation complete");
                break;
            case "M2 Note":
                M2Note.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "M2 mini":
                M2Mini.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "PRO 6":
                PRO6.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "PRO 6s":
                PRO6s.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "M5s":
                M5s.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "M5":
                M5.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "M6":
                M6.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "M6s":
                M6s.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "Meizu S6":
                MeizuS6.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
            case "MX6":
                MX6.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;
                //это есть офф.состояние note8
            case "meizu note8":
                Note8.changeID2Global();
                infoViewer.setText("Operation complete");
                break;
                //это если его прошили сервисной прошивкой,т.е меняется ИД
            case "M1822":
                    Note8.changeID2Global();
                    infoViewer.setText("Operation complete");
                    break;

            case "meizu note9":
                Note9.changeID2Global();
                infoViewer.setText("Operation complete.");
                break;

                default:
                    infoViewer.setText("Error.Can\'t check model.");
                    break;
        }
    }
    public void onClickChangeID2China(View view) {
        switch (android.os.Build.MODEL){
            case "M6 Note":
                M6Note.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "M5 Note":
                M5Note.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "m3 mote":
                M3Note.changeID2China();
                infoViewer.setText("Operation complete");
                break;
            case "M2 Note":
                M2Note.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "M2 mini":
                M2Mini.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "PRO 6":
                PRO6.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "PRO 6s":
                PRO6s.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "M5s":
                M5s.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "M5":
                M5.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "M6":
                M6.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "M6s":
                M6s.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "Meizu S6":
                MeizuS6.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            case "MX6":
                MX6.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            //это есть офф.состояние note8
            case "meizu note8":
                Note8.changeID2China();
                infoViewer.setText("Operation complete");
                break;
            //это если его прошили сервисной прошивкой,т.е меняется ИД
            case "M1822":
                Note8.changeID2China();
                infoViewer.setText("Operation complete");
                break;
            case "meizu note9":
                Note9.changeID2China();
                infoViewer.setText("Operation complete.");
                break;
            default:
                infoViewer.setText("Error.Can\'t check model.");
                break;

        }
    }
}
