package com.freedom_man.standard

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.*
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() = SearchFragment()
    }

    lateinit var cameraDevice: CameraDevice

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 200)
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        val camera = activity?.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        camera.openCamera(camera.cameraIdList[0], object : CameraDevice.StateCallback() {

            override fun onOpened(cameraDevice: CameraDevice) {
                this@SearchFragment.cameraDevice = cameraDevice
//                createCameraPreviewSession()
            }

            override fun onDisconnected(cameraDevice: CameraDevice) {
                cameraDevice.close()
            }

            override fun onError(cameraDevice: CameraDevice, error: Int) {
                onDisconnected(cameraDevice)
                this@SearchFragment.activity?.finish()
            }
        }, null)
    }

//    private fun createCameraPreviewSession() {
//        try {
//            val texture = textureView.surfaceTexture
//            texture.setDefaultBufferSize(previewSize.width, previewSize.height)
//            val surface = Surface(texture)
//            val previewRequestBuilder = cameraDevice!!.createCaptureRequest(
//                CameraDevice.TEMPLATE_PREVIEW
//            )
//            previewRequestBuilder.addTarget(surface)
//            cameraDevice.createCaptureSession(
//                Arrays.asList(surface, imageReader?.surface),
//                object : CameraCaptureSession.StateCallback() {
//
//                    override fun onConfigured(cameraCaptureSession: CameraCaptureSession) {
//
//                        if (cameraDevice == null) return
//                        captureSession = cameraCaptureSession
//                        try {
//                            previewRequestBuilder.set(
//                                CaptureRequest.CONTROL_AF_MODE,
//                                CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE)
//                            previewRequest = previewRequestBuilder.build()
//                            captureSession?.setRepeatingRequest(previewRequest,
//                                null, Handler(backgroundThread?.looper)
//                            )
//                        } catch (e: CameraAccessException) {
//                            Log.e("erfs", e.toString())
//                        }
//
//                    }
//
//                    override fun onConfigureFailed(session: CameraCaptureSession) {
//                        //Tools.makeToast(baseContext, "Failed")
//                    }
//                }, null)
//        } catch (e: CameraAccessException) {
//            Log.e("erf", e.toString())
//        }
//    }
}
