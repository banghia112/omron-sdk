package com.omron

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.os.Build
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.omronhealthcare.OmronConnectivityLibrary.OmronLibrary.LibraryManager.OmronPeripheralManager
import com.omronhealthcare.OmronConnectivityLibrary.OmronLibrary.OmronUtility.OmronConstants
import com.facebook.react.bridge.Promise;

class OmronExampleModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext)
{
    override fun getName() = "OmronExampleModule"

    @ReactMethod
    fun initialize(apiKey: String, promise: Promise) {
        println(apiKey)
        try {
            OmronPeripheralManager.sharedManager(currentActivity?.applicationContext).setAPIKey(apiKey, null);
            promise.resolve(OmronConstants.OMRONConfigurationStatus.OMRONConfigurationPartnerAuthenticationError.toString())
        }catch (e: Throwable) {
            promise.reject("Create Event Error", e)
        }

    }

    @ReactMethod
    fun getConfig(): Any? {
        return OmronPeripheralManager.sharedManager(currentActivity?.applicationContext).retrieveManagerConfiguration(currentActivity?.applicationContext)[OmronConstants. OMRONBLEConfigDeviceKey]
    }

    @ReactMethod
    fun startDiscovering(): List<BluetoothDevice> {

    }
}