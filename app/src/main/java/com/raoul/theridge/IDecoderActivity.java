package com.raoul.theridge;

import android.graphics.Bitmap;
import android.os.Handler;

import com.raoul.theridge.camera.CameraManager;
import com.google.zxing.Result;

public interface IDecoderActivity {

    public ViewfinderView getViewfinder();

    public Handler getHandler();

    public CameraManager getCameraManager();

    public void handleDecode(Result rawResult, Bitmap barcode);
}
