package com.example.kotlintests

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var str = "123"
        Log.d("MyTag", "str: $str -> result atoi = ${atoi(str)}")
        Log.d("MyTag", "str: $str -> result atoi2 = ${atoi2(str)}")
        Log.d("MyTag", "str: $str -> result atoi3 = ${atoi3(str)}")

        str = "123f"
        Log.d("MyTag", "str: $str -> result atoi = ${atoi(str)}")
        Log.d("MyTag", "str: $str -> result atoi2 = ${atoi2(str)}")
        Log.d("MyTag", "str: $str -> result atoi3 = ${atoi3(str)}")

        str = "1"
        Log.d("MyTag", "str: $str -> result atoi = ${atoi(str)}")
        Log.d("MyTag", "str: $str -> result atoi2 = ${atoi2(str)}")
        Log.d("MyTag", "str: $str -> result atoi3 = ${atoi3(str)}")

        str = "f"
        Log.d("MyTag", "str: $str -> result atoi = ${atoi(str)}")
        Log.d("MyTag", "str: $str -> result atoi2 = ${atoi2(str)}")
        Log.d("MyTag", "str: $str -> result atoi3 = ${atoi3(str)}")

        str = "f1234"
        Log.d("MyTag", "str: $str -> result atoi = ${atoi(str)}")
        Log.d("MyTag", "str: $str -> result atoi2 = ${atoi2(str)}")
        Log.d("MyTag", "str: $str -> result atoi3 = ${atoi3(str)}")

        str = "12f34"
        Log.d("MyTag", "str: $str -> result atoi = ${atoi(str)}")
        Log.d("MyTag", "str: $str -> result atoi2 = ${atoi2(str)}")
        Log.d("MyTag", "str: $str -> result atoi3 = ${atoi3(str)}")
    }

    private fun atoi(str: String) : Int? {
        val charArray = str.toCharArray()
        val asciiArray = charArray.map { element -> element.code }

        val max = 57
        val min = 48
        var result: Int = 0

        for(i in 0..asciiArray.size - 1){
            if (asciiArray[i] in min..max) {
                result += (asciiArray[i] - 48) * 10.0.pow(asciiArray.size - i - 1).toInt()
            }
            else return null
        }
        return result
    }

    private fun atoi2(str: String) : Int? {
        val charArray = str.toCharArray()

        var result: Int = 0

        for(i in 0..charArray.size - 1){
            if (charArray[i].isDigit()) {
                result += charArray[i].digitToInt() * 10.0.pow(charArray.size - i - 1).toInt()
            }
            else return null
        }
        return result
    }

    private fun atoi3(str: String) : Int? {
        val charArray = str.toCharArray()
        val asciiArray = charArray.map { element -> element.code }

        val digitArray: MutableList<Int> = mutableListOf()

        asciiArray.forEach { element ->
            if (element in MIN_DIGIT_CODE..MAX_DIGIT_CODE)
                digitArray.add(element - MIN_DIGIT_CODE)
        }
        if (digitArray.isEmpty()) return null

        var result: Int = 0

        for(i in 0..digitArray.size - 1){
            result += digitArray[i]* DECIMAL_BASE.pow(digitArray.size - i - 1).toInt()
        }
        return result
    }

    private companion object {
        const val MIN_DIGIT_CODE = 48
        const val MAX_DIGIT_CODE = 57
        const val DECIMAL_BASE = 10.0
    }
}