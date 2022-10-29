package com.rdservice.fingerscan.mantra

import android.content.Context
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import android.util.Log
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister
import java.io.StringWriter

class CommonUtil {
    companion object {
        fun appInstalledOrNot(packageManager: PackageManager, packageName: String): Boolean {
            return try {
                packageManager.getPackageInfo(packageName, 0)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }

        fun getPIDOptions(deviceSelected: String): String? {
            try {
                var fingerCount: String? = null
                var fingerType: String? = null
                var iCount: String? = null
                var pCount: String? = null
                var fingerFormat: String? = null
                var pidVer: String? = null
                var timeOut: String? = null
                var posh: String? = null
                var env: String? = null
                var wadh: String? = null
                var optVer: String? = null
                var otp: String? = ""
                when {
                    deviceSelected.equals("MANTRA") -> {

//                        if (pref.getString(Constants.AEPS_FINGER_ENABLED, "").equals("Y", ignoreCase = false)) {
//                            fingerCount = useFingerCount.toString();
//                        } else {
                            when (0) {
                                0 -> fingerCount = "1"
//                                2 -> fingerCount =
//                                    pref.getString(Constants.MANTRA_FINGER_COUNT_BE, "").toString()
//                                4 -> fingerCount =
//                                    pref.getString(Constants.MANTRA_FINGER_COUNT_MS, "").toString()
//                                6 -> fingerCount =
//                                    pref.getString(Constants.MANTRA_FINGER_COUNT_CD, "").toString()
                            }
                 //       }
                        when (0) {
                            0 -> fingerType = "0"
                        }
                        when (0) {
                            0 -> fingerFormat = "0"
                        }
                        when (0) {
                            0 -> pidVer = "2.0"
                        }
                        when (0) {
                            0 -> timeOut = "10000"
                        }
                        when (0) {
                            0 -> posh = "UNKNOWN"
                        }
                        when (0) {
                            0 -> env = "P"
                        }
                        when (0) {
                            0 -> iCount = "0"
                        }
                        when (0) {
                            0 -> pCount = "0"
                        }
                        when (0) {
                            0 -> optVer = "1.0"
                        }
                    }
                }

                val opts: Opts = Opts()
                opts.fCount = fingerCount
                opts.fType = fingerType
                opts.iCount = iCount
                opts.pCount = pCount
                opts.format = fingerFormat
                opts.pidVer = pidVer
                opts.timeout = timeOut
                opts.posh = posh
                opts.env = env
                opts.wadh = wadh
                opts.otp = otp
                val pidOptions: PidOptions = PidOptions()
                pidOptions.ver = optVer
                pidOptions.Opts = opts
                val serializer: Serializer = Persister()
                val writer: StringWriter = StringWriter()
                serializer.write(pidOptions, writer)
                return writer.toString()
            } catch (e: Exception) {
                //Crashlytics.logException(e)
            }
            return null
        }

        fun getPIDOptionsMantra(context: Context): String? {
            try {
//            int fingerCount = 1;
//            int fingerType = 0;
//            int fingerFormat = 1;
                val pidVer = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_PID_VER_AP, "")
                val timeOut = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_TIME_OUT_AP, "")
                val posh = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_POS_AP, "")
                val opts = Opts()
                opts.fCount = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_FINGER_COUNT_AP, "").toString()
                opts.fType = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_FINGER_TYPE_AP, "").toString()
                opts.iCount = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_I_COUNT_AP, "")
                //            opts.iType = "0";
                opts.pCount = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_P_COUNT_AP, "")
                //            opts.pType = "0";
                opts.format = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_FORMAT_AP, "").toString()
                opts.pidVer = pidVer
                opts.timeout = timeOut
                //            opts.otp = "123456";
//            opts.wadh = "Hello";
//            opts.wadh = getWadh("2.1FYYNN").trim();
                opts.posh = posh
                opts.env = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_ENV_AP, "")
                val pidOptions = PidOptions()
                pidOptions.ver = PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(Constants.MANTRA_OPT_VER_AP, "")
                pidOptions.Opts = opts
                val serializer: Serializer = Persister()
                val writer = StringWriter()
                serializer.write(pidOptions, writer)
                return writer.toString()
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
            return null
        }
    }

}