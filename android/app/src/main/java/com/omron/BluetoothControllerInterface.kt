package com.omron

import android.bluetooth.BluetoothDevice

interface BluetoothControllerInterface {
    val scannedDevice: List<BluetoothDevice>
    val pairedDevice: List<BluetoothDevice>

    fun startDiscorvery()

    fun stopDiscovery()

    fun release()
}