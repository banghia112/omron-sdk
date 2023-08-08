package com.omron

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

@RequiresApi(Build.VERSION_CODES.M)

@SuppressLint("MissingPermission")

class BluetoothController (
    private val context: Context
    ): BluetoothControllerInterface
{

    private val bltManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }

    private val bltAdapter by lazy {
        bltManager?.adapter
    }

    private val _scannedDevice :MutableList<BluetoothDevice> = mutableListOf()

    override val scannedDevice: List<BluetoothDevice>
        get() = _scannedDevice

    private val _pairedDevice :MutableList<BluetoothDevice> = mutableListOf()

    override val pairedDevice: List<BluetoothDevice>
        get() = _pairedDevice

    init {
        updatePairedDevice()
    }

    fun requestPermission() {

    }

    private fun updatePairedDevice() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {return}

        bltAdapter?.bondedDevices?.forEach{ println(it) }
    }

    override fun startDiscorvery() {
        if(!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) {
            return
        }

        updatePairedDevice()

        bltAdapter?.startDiscovery()
    }

    override fun stopDiscovery() {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    private fun hasPermission(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}