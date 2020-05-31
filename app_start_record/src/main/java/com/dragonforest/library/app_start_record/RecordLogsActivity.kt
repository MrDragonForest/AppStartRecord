package com.dragonforest.library.app_start_record

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.dragonforest.library.app_start_record.database.TimeTraceDatabase
import kotlinx.android.synthetic.main.activity_record_logs.*

class RecordLogsActivity : AppCompatActivity() {

    var adapter: LogsAdapter = LogsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_logs)

        initView()
        initData()
    }

    private fun initView() {
        rv_logs.layoutManager = LinearLayoutManager(this)
        rv_logs.adapter = adapter

        iv_delete.setOnClickListener { showDeleteDialog() }
    }

    private fun showDeleteDialog() {
        AlertDialog.Builder(this)
            .setTitle("清空")
            .setMessage("确定删除所有？")
            .setPositiveButton("ok", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    deleteAll()
                    Toast.makeText(this@RecordLogsActivity, "删除成功",Toast.LENGTH_SHORT).show()
                }
            })
            .setNegativeButton("cancel", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {

                }
            })
            .show()
    }

    private fun deleteAll() {
        adapter.list?.forEach {
            TimeTraceDatabase.getDatabase(this).timeTraceDao().delete(it)
        }
        initData()
    }

    private fun initData() {
        val list = TimeTraceDatabase.getDatabase(this).timeTraceDao().getAll()
        adapter.list = list
        adapter.notifyDataSetChanged()
    }
}
