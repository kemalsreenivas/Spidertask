package com.example.admin.flashlight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivityFlash1 extends AppCompatActivity {


    private Camera camera;
    Camera.Parameters parameters;
    boolean isOn = false;
    boolean isflash = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_flash1);
        final Button btn = (Button) findViewById(R.id.button);
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            camera = Camera.open();
            parameters = camera.getParameters();
            isflash = true;
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isflash) {
                    if (!isOn) {
                        btn.setBackgroundResource(R.drawable.onbutton);
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();
                        isOn = true;
                    } else {
                        btn.setBackgroundResource(R.drawable.offbutton);
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        camera.stopPreview();
                        isOn = false;
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityFlash1.this);
                    builder.setTitle("Error...");
                    builder.setMessage("FLASH IS NOT AVAILABLE");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }


                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(camera!=null){
            camera.release();
            camera=null;
        }
    }
}