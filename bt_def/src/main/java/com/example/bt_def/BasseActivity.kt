package com.example.bt_def

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BasseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basse)
        //initRcView()
        supportFragmentManager.beginTransaction()
            .replace(R.id.plceHolder, DevicesListFragment()).commit()
    }
 }