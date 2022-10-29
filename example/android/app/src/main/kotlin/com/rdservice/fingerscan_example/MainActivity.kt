package com.rdservice.fingerscan_example

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.rdservice.fingerscan.FingerscanPlugin
import com.rdservice.fingerscan.mantra.PidData
import io.flutter.embedding.android.FlutterActivity
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister

class MainActivity: FlutterActivity() {
    private var pidData: PidData? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        try {
            Toast.makeText(context,"requestCode:-${requestCode}",Toast.LENGTH_SHORT).show()
            Toast.makeText(context,"resultCode${resultCode == Activity.RESULT_OK}",Toast.LENGTH_SHORT).show()
            //  when (requestCode) {
            if(resultCode == Activity.RESULT_OK){

               // mResult.success(data!!.getStringExtra("PID_DATA"))
                FingerscanPlugin().mantraDataSendBack(data!!.getStringExtra("PID_DATA"))

                Toast.makeText(context,"data${data!!.getStringExtra("PID_DATA")}",Toast.LENGTH_SHORT).show()
                val result: String = data!!.getStringExtra("PID_DATA")!!
                Toast.makeText(context,"Result${result}",Toast.LENGTH_SHORT).show()
                val serializer: Serializer = Persister()
                if (result != null) {

                    pidData = serializer.read(PidData::class.java, result)
                    Toast.makeText(context,"pidData${pidData.toString()}",Toast.LENGTH_SHORT).show()
                    if (pidData != null) {
                        if (pidData?._Resp?.errCode.equals(
                                "0",
                                ignoreCase = true
                            )
                        ) {

                            var RBL_POPUP = ""


//                                if (moduleSelected == 0) {
//                                    RBL_POPUP = pref.getString(Constants.AEPS_CW_AGGRE, "")!!
//                                } else if (moduleSelected == 2) {
//                                    RBL_POPUP = pref.getString(Constants.AEPS_BE_AGGRE, "")!!
//                                } else if (moduleSelected == 4) {
//                                    RBL_POPUP = pref.getString(Constants.AEPS_MS_AGGRE, "")!!
//                                } else if (moduleSelected == 6) {
//                                    RBL_POPUP = pref.getString(Constants.AEPS_CD_AGGRE, "")!!
//                                }
                            //if (requestCode == 1001 &&
                            //  pref.getString(Constants.FFactor, "")
                            if (requestCode == 1001 && RBL_POPUP.equals("RBL", ignoreCase = true)
                            // && pref.getString(Constants.RBL_POPUP_VISIBILITY, "")
                            //   .equals("Y", ignoreCase = true)
                            ) {
                                Toast.makeText(context,"#######${pidData!!._Skey.ci}",Toast.LENGTH_SHORT).show()
                                Toast.makeText(context,"#######${pidData!!._Skey.value}",Toast.LENGTH_SHORT).show()
                                Toast.makeText(context,"#######${pidData!!._DeviceInfo.mc}",Toast.LENGTH_SHORT).show()
                                Toast.makeText(context,"#######${pidData!!._Skey.ci}",Toast.LENGTH_SHORT).show()
                                // rblLoginPopup()
//                                    Toast.makeText(
//                                        context,
//                                        context.getString(R.string.default_password),
//                                        Toast.LENGTH_LONG
//                                    ).show()
                            } else {
                                Toast.makeText(context,"#######${pidData!!._Skey.ci}",Toast.LENGTH_SHORT).show()
                                Toast.makeText(context,"#######${pidData!!._Skey.value}",Toast.LENGTH_SHORT).show()
                                Toast.makeText(context,"#######${pidData!!._DeviceInfo.mc}",Toast.LENGTH_SHORT).show()
                                Toast.makeText(context,"#######${pidData!!._Skey.ci}",Toast.LENGTH_SHORT).show()
                                // getParamsforAmount(pidData)
                            }
                        } else {
                            Toast.makeText(
                                context,
                                pidData?._Resp?.errInfo,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }
            }
            //  }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "error",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
