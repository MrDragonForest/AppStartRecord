package com.dragonforest.library.app_start_record.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dragonforest.library.app_start_record.R
import com.dragonforest.library.app_start_record.timetrace.TraceChain
import com.dragonforest.library.app_start_record.timetrace.TraceTag
import java.util.*
import kotlin.collections.LinkedHashMap

/**
 *
 * create by DragonForest at 2020/5/31
 */
class TraceChainView : LinearLayout {

    var chainTextColor:Int = Color.BLUE
    var allTimeTextColor:Int = Color.RED
    var tagTextColor:Int = Color.GRAY
    var timeDivideTextColor = Color.YELLOW
    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView(context)
        initAttrs(context,attributeSet)
    }

    private fun initAttrs(context: Context, attributeSet: AttributeSet) {
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.TraceChainView)
        chainTextColor = obtainStyledAttributes.getColor(R.styleable.TraceChainView_mchainNameTextColor,Color.BLUE)
        allTimeTextColor = obtainStyledAttributes.getColor(R.styleable.TraceChainView_mchainAllTimeTextColor,Color.RED)
        tagTextColor = obtainStyledAttributes.getColor(R.styleable.TraceChainView_mtagTextColor,Color.GRAY)
        timeDivideTextColor = obtainStyledAttributes.getColor(R.styleable.TraceChainView_mdivideTimeTextColor,context.resources.getColor(android.R.color.holo_orange_dark))
        obtainStyledAttributes.recycle()
    }

    private fun initView(context: Context) {
        orientation = VERTICAL
        setPadding(10, 5, 10, 5)
    }

    fun setChainsData(chains: LinkedHashMap<String, TraceChain>) {
        removeAllViews()
        chains.forEach {
            var chainName = it.key
            var chain = it.value
            addChainTitleView(chainName)
            addAllTimeView(it.value.allTime())
            for (index in 0 until chain.size()) {
                addTagView(index, chain?.tagList)
            }
        }
    }

    private fun addChainTitleView(chainName: String) {
        var tv_chain_name = TextView(context)
        tv_chain_name.textSize = 18f
        tv_chain_name.setPadding(8, 5, 8, 5)
        tv_chain_name.setTextColor(chainTextColor)
        var drawableLeft = context.resources.getDrawable(R.drawable.icon_svg_tag_down)
        drawableLeft.setBounds(0, 0, drawableLeft.minimumWidth, drawableLeft.minimumHeight)
        tv_chain_name.setCompoundDrawables(drawableLeft, null, null, null)
        tv_chain_name.text = chainName
        var lp =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        addView(tv_chain_name, lp)
    }

    private fun addAllTimeView(allTime: Long) {
        var tv_all_time = TextView(context)
        tv_all_time.textSize = 13f
        tv_all_time.setPadding(8, 2, 8, 5)
        tv_all_time.setTextColor(allTimeTextColor)
        tv_all_time.text = "(${allTime}ms)"
        var lp =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.leftMargin = 10
        addView(tv_all_time, lp)
    }

    private fun addTagView(index: Int, tagList: LinkedList<TraceTag>?) {
        tagList?.let {
            if ((index - 1) >= 0) {
                // 添加时间间隔view
                var timeDivide = it[index].tagTimeStamp - it[index - 1].tagTimeStamp
                addTimeDivideView(timeDivide)
            }
            var tv_tag_name = TextView(context)
            tv_tag_name.textSize = 16f
            tv_tag_name.setPadding(8, 5, 8, 5)
            tv_tag_name.setTextColor(tagTextColor)
            var drawableLeft = context.resources.getDrawable(R.drawable.icon_svg_tag_down)
            drawableLeft.setBounds(0, 0, drawableLeft.minimumWidth, drawableLeft.minimumHeight)
            tv_tag_name.setCompoundDrawables(drawableLeft, null, null, null)
            tv_tag_name.text = it[index].tagName
            var lp = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            lp.leftMargin = 50
            addView(tv_tag_name, lp)
        }
    }

    private fun addTimeDivideView(timeDivide: Long) {
        var tv_time_divide = TextView(context)
        tv_time_divide.textSize = 13f
        tv_time_divide.setPadding(8, 5, 8, 5)
        tv_time_divide.setTextColor(timeDivideTextColor)
        var drawableLeft = context.resources.getDrawable(R.drawable.icon_svg_down)
        drawableLeft.setBounds(0, 0, drawableLeft.minimumWidth, drawableLeft.minimumHeight)
        tv_time_divide.setCompoundDrawables(drawableLeft, null, null, null)
        tv_time_divide.text = "耗时${timeDivide}ms"
        var lp =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.leftMargin = 70
        addView(tv_time_divide, lp)
    }
}