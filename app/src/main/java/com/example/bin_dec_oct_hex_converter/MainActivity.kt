package com.example.bin_dec_oct_hex_converter

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var resetButton : Button
    private lateinit var binarButton : Button
    private lateinit var octalButton : Button
    private lateinit var deciButton : Button
    private lateinit var hexaButton : Button
    private lateinit var convertedNum : TextView
    private lateinit var conversionNum : TextView
    private lateinit var binarButtonRes : RadioButton
    private lateinit var octalButtonRes : RadioButton
    private lateinit var deciButtonRes : RadioButton
    private lateinit var hexaButtonRes : RadioButton
    var currentOperation: Operation? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initiView()
        addCallBacks()
    }

    private fun initiView() {
        resetButton = findViewById(R.id.resetButton)
        binarButton = findViewById(R.id.binaryButton)
        octalButton = findViewById(R.id.octaButton)
        deciButton = findViewById(R.id.decButton)
        hexaButton = findViewById(R.id.hexButton)
        convertedNum = findViewById(R.id.convertedText)
        conversionNum = findViewById(R.id.conversionText)
        binarButtonRes = findViewById(R.id.binaryButtonResult)
        octalButtonRes = findViewById(R.id.octaButtonResult)
        deciButtonRes = findViewById(R.id.decButtonResult)
        hexaButtonRes = findViewById(R.id.hexButtonResult)

    }

    private fun addCallBacks() {
        resetButton.setOnClickListener {
            clearInput()
        }

        binarButtonRes.setOnCheckedChangeListener { compoundButton , isSelected ->
            if (isSelected){
                addCallBackBin()
                binarButton.isEnabled=false
            }
            else{
                binarButton.isEnabled=true
            }
        }

        octalButtonRes.setOnCheckedChangeListener { compoundButton , isSelected ->
            if (isSelected){
                addCallBackOct()
                octalButton.isEnabled =false
            }
            else {
                octalButton.isEnabled=true
            }

        }
        deciButtonRes.setOnCheckedChangeListener { compoundButton , isSelected ->
            if(isSelected){
                addCallBackDec()
                deciButton.isEnabled = false
            }
            else{
                deciButton.isEnabled = true
            }

        }
        hexaButtonRes.setOnCheckedChangeListener { compoundButton , isSelected ->
            if (isSelected){
                addCallBackHex()
                hexaButton.isEnabled =false
            }
            else{
                hexaButton.isEnabled =true
            }

        }
    }

    private fun addCallBackBin() {

            octalButton.setOnClickListener {
                currentOperation = Operation.Oct
                resultButtonBinary()
            }
            deciButton.setOnClickListener {
                currentOperation = Operation.Dec
                resultButtonBinary()
            }
            hexaButton.setOnClickListener {
                currentOperation = Operation.Hex
                resultButtonBinary()
            }

    }

    private fun addCallBackOct() {

            binarButton.setOnClickListener {
                currentOperation = Operation.Bin
                resultButtonOctal()
            }
            deciButton.setOnClickListener {
                currentOperation = Operation.Dec
                resultButtonOctal()
            }
            hexaButton.setOnClickListener {
                currentOperation = Operation.Hex
                resultButtonOctal()
            }


    }

    private fun addCallBackDec() {
            binarButton.setOnClickListener {
                currentOperation = Operation.Bin
                resultButtonDecimal()
            }
            octalButton.setOnClickListener {
                currentOperation = Operation.Oct
                resultButtonDecimal()
            }
            hexaButton.setOnClickListener {
                currentOperation = Operation.Hex
                resultButtonDecimal()
            }

    }

    private fun addCallBackHex() {
            binarButton.setOnClickListener {
                currentOperation = Operation.Bin
                resultButtonHexaDecimal()
            }
            octalButton.setOnClickListener {
                currentOperation = Operation.Oct
                resultButtonHexaDecimal()
            }
            deciButton.setOnClickListener {
                currentOperation = Operation.Dec
                resultButtonHexaDecimal()
            }

    }

    private fun showMsglimitation() {
        val msg = "Please enter a valid number"
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun showMsg() {
        val msg = "Please enter a number"
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun clearInput() {
        convertedNum.text=""
        conversionNum.text=""
    }

    private fun resultButtonBinary() {
        val firstNumber = convertedNum.text
          if (firstNumber.isEmpty()) {
            showMsg()
              clearInput()
        }else if (!firstNumber.matches("[01]+".toRegex())) {
             showMsglimitation()
              clearInput()
        } else{  conversionNum.text = doCurrentOperationBinary() }
    }

    private fun resultButtonOctal() {
        val firstNumber = convertedNum.text
        if (firstNumber.isEmpty()) {
            showMsg()
            clearInput()
        }else if (!(firstNumber.matches("[0-7]+".toRegex()))) {
            showMsglimitation()
            clearInput()
        } else{  conversionNum.text = doCurrentOperationOctal() }
    }

    private fun resultButtonDecimal() {
        val firstNumber = convertedNum.text
        if (firstNumber.isEmpty()) {
            showMsg()
            clearInput()
        }else if (!(firstNumber.matches("[0-9]+".toRegex()))) {
            showMsglimitation()
            clearInput()
        } else{  conversionNum.text = doCurrentOperationDecimal() }
    }

    private fun resultButtonHexaDecimal() {
        val firstNumber = convertedNum.text
        if (firstNumber.isEmpty()) {
            showMsg()
            clearInput()
        }else if (!firstNumber.matches("[0-9A-Fa-f]+".toRegex())) {
            showMsglimitation()
            clearInput()
        } else{  conversionNum.text = doCurrentOperationHexaDecimal() }
    }

    private fun doCurrentOperationBinary(): String {
        val firstNumber = convertedNum.text.toString()

             return when (currentOperation) {
                Operation.Bin -> firstNumber

                Operation.Oct -> convertBinToOct(firstNumber).toString()
                Operation.Dec -> convertBinToDec(firstNumber).toString()
                Operation.Hex -> convertDecToHex(convertBinToDec(firstNumber).toString())
                null -> "0"
            }
    }

    private fun doCurrentOperationOctal(): String {
        val firstNumber = convertedNum.text.toString()

             return when (currentOperation) {
                Operation.Bin -> convertOctToBin(firstNumber)
                Operation.Oct -> firstNumber
                Operation.Dec -> convertOctToDec(firstNumber).toString()
                Operation.Hex -> convertDecToHex(convertOctToDec(firstNumber).toString())
                null -> "0"
            }

    }

    private fun doCurrentOperationDecimal(): String {
        val firstNumber = convertedNum.text.toString()
            return when (currentOperation) {
                Operation.Bin -> firstNumber.toLong().toString(2)
                Operation.Oct -> convertDecToOct(firstNumber).toString()
                Operation.Dec -> firstNumber
                Operation.Hex -> convertDecToHex(firstNumber)
                null -> "0"
            }
    }

    private fun doCurrentOperationHexaDecimal(): String {
        val firstNumber = convertedNum.text.toString()

            return when (currentOperation) {
                Operation.Bin -> firstNumber.toLong(16).toString(2)
                Operation.Oct -> firstNumber.toLong(16).toString(8)
                Operation.Dec -> firstNumber.toLong(16).toString(10)
                Operation.Hex -> firstNumber
                null -> "0"
            }
    }


    private fun convertBinToDec(bin: String): Int {
        var temp = bin.toInt()
        var base = 1
        var decValue = 0
        while (temp != 0) {
            val lastDigit = temp % 10
            temp /= 10
            decValue += lastDigit * base
            base *= 2
        }; return decValue

    }

    private fun convertBinToOct(bin: String): Int {
        var octValue=0
        var temp = bin.toInt()
        var decValue = 0
        var base = 0.0
        while (temp != 0) {
            decValue += (temp % 10) * 2.0.pow(base).toInt()
            ++base
            temp /= 10 }
            base = 1.0
            while (decValue != 0) {
                octValue += (decValue % 8) * base.toInt()
                decValue /= 8
                base *= 10
            }; return octValue
    }

    private fun convertOctToDec(oct: String): Int {
        var decValue = 0

        var base = 1
        var temp = oct.toInt()
        while (temp > 0) {

            val lastDigit = temp % 10
            temp /= 10

            decValue += lastDigit * base
            base *= 8
        }
        return decValue
    }

    private fun convertOctToBin(oct: String): String {
        var i: Long = 0
        var binaryValue = ""
        while (i < oct.length) {
            val c = oct[i.toInt()]
            when (c) {
                '0' -> binaryValue += "000"
                '1' -> binaryValue += "001"
                '2' -> binaryValue += "010"
                '3' -> binaryValue += "011"
                '4' -> binaryValue += "100"
                '5' -> binaryValue += "101"
                '6' -> binaryValue += "110"
                '7' -> binaryValue += "111"
                else -> println(
                    """
                    
                    Invalid Octal Digit ${oct[i.toInt()]}
                    """.trimIndent()
                )
            }
            i++
        }
        return binaryValue
    }

    private fun convertDecToHex(oct: String): String {
        var oct = oct.toInt()
        val hexaDeciNum = CharArray(100)
        var i = 0
        while (oct !== 0) {
            var temp = 0
            temp = oct % 16
            if (temp < 10) {
                hexaDeciNum[i] = (temp + 48).toChar()
                i++
            } else {
                hexaDeciNum[i] = (temp + 55).toChar()
                i++
            }
            oct /= 16
        }
        var hexValue = ""
        for (j in i - 1 downTo 0) {
            hexValue += hexaDeciNum[j]
        }
        return hexValue
    }

   private fun convertDecToOct(dec: String): Int {
        var deciNum = dec.toInt()
        var octalValue = 0
        var count = 1
        while (deciNum != 0) {
            val remainder = deciNum % 8
            octalValue += remainder * count
            count *= 10
            deciNum /= 8
        }
        return octalValue
    }

}