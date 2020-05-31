package com.dragonforest.library.app_start_record

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.dragonforest.library.app_start_record.database.entity.TimeTraceEntity
import com.dragonforest.library.app_start_record.timetrace.TraceChain
import com.dragonforest.library.app_start_record.util.format
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.item_logs.view.*
import kotlin.collections.LinkedHashMap

/***
 *  create by DragonForest on 2020/5/29
 */
class LogsAdapter : RecyclerView.Adapter<LogsAdapter.LogViewHolder>() {

    inner class LogViewHolder(item: View) : RecyclerView.ViewHolder(item)

    var list: List<TimeTraceEntity>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_logs, parent, false)
        return LogViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.itemView.tv_log_date.text = list?.get(position)?.traceDate?.format()
        list?.get(position)?.traceContent?.let {
            var type = object : TypeToken<LinkedHashMap<String, TraceChain>>() {}.type
            val chains = Gson().fromJson<LinkedHashMap<String, TraceChain>>(it, type)
            holder.itemView.tv_log_content.setChainsData(chains)
        }
        list?.get(position)?.title?.let {
            holder.itemView.tv_log_title.text = it
        }
        list?.get(position)?.startMode?.let {
            if(it == 1){
                // 热启动
                holder.itemView.tv_log_title.setTextColor(Color.RED)
            }else{
                // 冷启动
                holder.itemView.tv_log_title.setTextColor(holder.itemView.context.getColor(R.color.colorPrimary))
            }
        }
        holder.itemView.tv_log_title.setOnClickListener {
            holder.itemView.iv_toggle.performClick()
        }
        holder.itemView.iv_toggle.setOnClickListener {
            holder.itemView.tv_log_content.visibility = if(holder.itemView.tv_log_content.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            holder.itemView.iv_toggle.setImageResource(if(holder.itemView.tv_log_content.visibility == View.VISIBLE) R.drawable.icon_svg_tag_down else R.drawable.icon_svg_tag_right)
        }
        holder.itemView.iv_toggle.setImageResource(if(holder.itemView.tv_log_content.visibility == View.VISIBLE) R.drawable.icon_svg_tag_down else R.drawable.icon_svg_tag_right)
    }


}