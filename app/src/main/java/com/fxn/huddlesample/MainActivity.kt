package com.fxn.huddlesample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fxn.huddle.Huddle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val list2 = arrayListOf("ak", "sh", "ay", "sh", "ar", "ma")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test.background = Utility.getRoundRect("#1E88E5")
        switchCompat.setOnCheckedChangeListener { compoundButton, b ->
            huddle.apply {
                orientation = if (b) {
                    Huddle.VERTICAL
                } else {
                    Huddle.HORIZONTAL
                }
            }
        }
        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                huddle.apply {
                    spacing = p1
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        huddle.apply {
            huddleAdapter = object : Huddle.HuddleAdapter {
                override fun onclickItem(view: View, pos: Int) {
                    Log.e("pos", "onclick $pos")
                }

                override fun getItemCount(): Int {
                    return list2.size
                }

                override fun getBindData(view: View, pos: Int) {
                    if (pos == list2.size - 1) {
                        view.findViewById<TextView>(R.id.user_name_text).text = "+30"
                        //return
                    }
                    Log.e("pos", "0> " + pos)
                    var color = when (pos) {
                        0 -> "e53835"
                        1 -> "D81B60"
                        2 -> "8E24AA"
                        3 -> "5E35B1"
                        4 -> "3949AB"
                        5 -> "1E88E5"
                        else -> "039BE5"
                    }
                    view.findViewById<ImageView>(R.id.user_name).background =
                        Utility.getRoundRect("#" + color)
                    //var url = "https://dummyimage.com/100/${color}/fff&text="+list2[pos]
                    /* var url = "https://placekitten.com/100/" + (100 + pos)
                     Glide.with(context).load(url).apply(RequestOptions.circleCropTransform())
                         .into(view.findViewById<ImageView>(R.id.user_name));*/
                }
            }
        }
    }
}
