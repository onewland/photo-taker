package com.example.oliver.phototaker;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.util.Log;

/**
 * Created by oliver on 10/7/15.
 */
public class StateCaptureCallback extends CameraCaptureSession.StateCallback {
    private final ImageReader ir;
    public StateCaptureCallback(ImageReader imageReader) {
        ir = imageReader;
    }

    @Override
    public void onConfigured(CameraCaptureSession session) {
        try {
            CaptureRequest.Builder requestBuilder = session.getDevice().createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            requestBuilder.addTarget(ir.getSurface());
            CaptureRequest request = requestBuilder.build();
            session.capture(request, new CaptureSessionCaptureCallback(ir), new StateCaptureHandler());
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        Log.d("oliver-debug", "onConfigured");
    }

    @Override
    public void onConfigureFailed(CameraCaptureSession session) {

    }

    private static class CaptureSessionCaptureCallback extends CameraCaptureSession.CaptureCallback {
        private final ImageReader ir;

        public CaptureSessionCaptureCallback(ImageReader ir) {
            this.ir = ir;
        }

        @Override
        public void onCaptureCompleted(CameraCaptureSession session,
                                       CaptureRequest request, TotalCaptureResult result) {
            Log.d("oliver-debug", "captureCompleted");
            Image latest = ir.acquireLatestImage();
            
        }
    }
}
