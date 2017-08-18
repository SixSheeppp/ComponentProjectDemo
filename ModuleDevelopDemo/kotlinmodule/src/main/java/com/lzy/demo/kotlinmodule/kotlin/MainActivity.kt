package com.lzy.demo.kotlinmodule.kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.lzy.demo.kotlinmodule.R
import com.lzy.demo.library.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : BaseActivity(), View.OnClickListener {


    var name: String = "11"
    var textview: TextView? = null
    var button: Button? = null
    var userinfo = UserInfo()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        textview = findViewById(R.id.kotlinmodule_textview) as TextView?
//        button = findViewById(R.id.kotlinmodule_button) as Button?

        init()
    }


    override fun instantiation() {
        super.instantiation()
        Log.d("LZY", "${sum(1, 4)}")

        Log.d("LZY", "" + name?.length)
    }


    fun partoInt(x: UserInfo): String? {
        return x.name
    }


    fun sum(a: Int, b: Int): String {
        return (a + b).toString()
    }


    override fun eventbind() {
        super.eventbind()
        activity_main.kotlinmodule_textview.setOnClickListener(this)
        activity_main.kotlinmodule_button.setOnClickListener(this)
    }


    override fun databind() {
        super.databind()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.kotlinmodule_textview -> Toast.makeText(this, "111", Toast.LENGTH_SHORT).show()
            R.id.kotlinmodule_button -> Toast.makeText(this, "222", Toast.LENGTH_SHORT).show()

            else -> {
                Toast.makeText(this, "333", Toast.LENGTH_LONG).show()
            }
        }
    }

}
