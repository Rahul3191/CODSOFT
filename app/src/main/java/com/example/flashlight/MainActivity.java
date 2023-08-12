package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.content.Context;

import android.widget.Toast;
import android.os.Bundle;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

public class MainActivity extends AppCompatActivity {

    Button OnFlash, OffFlash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OffFlash = findViewById(R.id.flashOff);
        OnFlash = findViewById(R.id.flashOn);


        OnFlash.setOnClickListener(view -> flashOnMethod());

        OffFlash.setOnClickListener(view -> {
            try {
                flashOffMethod();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressLint("NewApi")
    public void flashOnMethod(){
        CameraManager cameraManager;
        cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);

        String cameraId = null;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        try {
            cameraManager.setTorchMode(cameraId,true);
            Toast.makeText(this, "Flashlight On", Toast.LENGTH_SHORT).show();

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    @SuppressLint("NewApi")
    public void flashOffMethod() throws CameraAccessException {

        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        String cameraId = null;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        cameraManager.setTorchMode(cameraId,false);
        Toast.makeText(this, "Flashlight OFF", Toast.LENGTH_SHORT).show();

    }
}
