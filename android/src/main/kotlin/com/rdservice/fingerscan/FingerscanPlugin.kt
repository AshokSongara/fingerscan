package com.rdservice.fingerscan

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat.startActivityForResult
import com.rdservice.fingerscan.mantra.CommonUtil
import com.rdservice.fingerscan.mantra.PidData
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import org.simpleframework.xml.Serializer

/** FingerscanPlugin */
class FingerscanPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
  private val channelName = "channel"
  private lateinit var methodChannel: MethodChannel
  private var pidData: PidData? = null
  lateinit var serializer: Serializer
  lateinit var mResult: MethodChannel.Result
  private lateinit var packageManager: PackageManager
  private var activity: Activity? = null

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    methodChannel = MethodChannel(flutterPluginBinding.binaryMessenger, channelName)
    packageManager = flutterPluginBinding.applicationContext.packageManager
    methodChannel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "mantraDevice") {
      mResult =result;
      if (!CommonUtil.appInstalledOrNot(
          packageManager,
          "com.mantra.rdservice"
        ) || !CommonUtil.appInstalledOrNot(
          packageManager,
          "com.mantra.clientmanagement"
        )
      ) {
        try {

          val pidOption: String? = CommonUtil.getPIDOptions("MANTRA")
          print("#####pidoptions${pidOption}")
          if (pidOption != null) {
            val intent = Intent()
            intent.action = "in.gov.uidai.rdservice.fp.CAPTURE"
            intent.`package` = "com.mantra.rdservice"
            intent.putExtra("PID_OPTIONS", pidOption)

            activity?.let { startActivityForResult(it,intent, 1001,null) }
          }
        } catch (e: Exception) {
          //Crashlytics.logException(e)
        }
      } else {
        try {
          val pidOption: String? = CommonUtil.getPIDOptions("MANTRA")
          print("#####${pidOption}")
          if (pidOption != null) {
            val intent = Intent()
            intent.action = "in.gov.uidai.rdservice.fp.CAPTURE"
            intent.`package` = "com.mantra.rdservice"
            intent.putExtra("PID_OPTIONS", pidOption)
            activity?.let { startActivityForResult(it,intent, 1001,null) }
          }
        } catch (e: Exception) {
          Log.e("Error", e.toString());
        }

      }

    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    methodChannel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivityForConfigChanges() {
    activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivity() {
    activity = null
  }

  fun mantraDataSendBack(resultData: String?) {
    mResult.success(resultData)
  }
}




