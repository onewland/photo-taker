package com.example.oliver.phototaker;

import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.media.ImageReader;
import android.util.Log;
import android.view.Surface;

import com.google.common.collect.Lists;

import java.util.List;

public class FakerCallback extends CameraDevice.StateCallback{
    @Override
    public void onOpened(CameraDevice camera) {
        ImageReader imageReader = ImageReader.newInstance(680, 480, ImageFormat.JPEG, 1);
        List<Surface> outputs = Lists.newArrayList(imageReader.getSurface());
        try {
            camera.createCaptureSession(outputs, new StateCaptureCallback(imageReader), new StateCaptureHandler());
        } catch (CameraAccessException e) {
            Log.e("oliver-debug", "encountered error in FakerCallback");
        }
    }

    @Override
    public void onDisconnected(CameraDevice camera) {
        Log.e("oliver-debug", "onDisconnected");

    }

    @Override
    public void onError(CameraDevice camera, int error) {
        Log.e("oliver-debug", "onError" + error);
    }
}
