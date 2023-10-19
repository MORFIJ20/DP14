package com.example.bt_def

import android.bluetooth.BluetoothDevice

data class Listitem(
    val device: BluetoothDevice,
    val isChecked: Boolean
)
