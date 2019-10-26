package felix.practice.morsevibrate.ui

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import felix.practice.morsevibrate.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    // region fields
    private val SINGLE_SPACE = " "
    private val FORWARD_SLASH = "/"

    // Example array for SOS
    val pattern = longArrayOf(
        200, //off
        200, 200,
        200, 200,
        200, 600,

        600, 200,
        600, 200,
        600, 600,

        200, 200,
        200, 200,
        200, 200
    )
    private val morseLetters = arrayOf(
        ". - ",
        "- . . . ",
        "- . - . ",
        "- . . ",
        ". ",
        ". . - . ",
        "- - . ",
        ". . . . ",
        ". . ",
        ". - - - ",
        "- . - ",
        ". - . . ",
        "- - ",
        "- . ",
        "- - - ",
        ". - - . ",
        "- - . - ",
        ". - . ",
        ". . . ",
        "- ",
        ". . - ",
        ". . . - ",
        ". - - ",
        "- . . - ",
        "- . - - ",
        "- - . . "
    )
    private val morseNumbers = arrayOf(
        "- - - - - ",
        ". - - - - ",
        ". . - - - ",
        ". . . - - ",
        ". . . . - ",
        ". . . . . ",
        "- . . . . ",
        "- - . . . ",
        "- - - . . ",
        "- - - - . "
    )
    // endregion

    override fun onStart() {
        super.onStart()
        initialize()
    }

    private fun initialize() {
        startButton.setOnClickListener {
            val encoded = encodeSentence(input.text.toString())
            morseMessage.text = encoded.second
            val timings = morseStringToTimings(encoded.first)

            val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            v.vibrate(VibrationEffect.createWaveform(timings, -1))
        }
    }

    /**
     * Encodes the given string to morse code
     * @return first = for morseStringToTimings method, second to display
     */
    private fun encodeSentence(sentence: String): Pair<String, String> {
        // Remove unknown chars
        val sentenceClean = sentence.toLowerCase().replace(Regex("[^a-z 0-9]"), "")
        println("Cleaned: $sentenceClean")
        val words = sentenceClean.split(' ')
        var result = ""
        words.forEach {
            val encodedWord = encodeWord(it)
            result += "$encodedWord$SINGLE_SPACE"
        }
        var displayString = result
        displayString = displayString.replace(Regex("\\s{3}"), "A")
        displayString = displayString.replace(Regex("\\s{2}"), "Z")
        displayString = displayString.replace(Regex("\\s"), "")
        displayString = displayString.replace(Regex("A+"), "       ")
        displayString = displayString.replace(Regex("Z+"), "   ")

        result.trim()
        result = result.replace(Regex("\\s{3}"), FORWARD_SLASH)
        result = result.replace(Regex("\\s{2}"), "B")
        return Pair(result, displayString)
    }

    private fun encodeWord(word: String): String {
        var encWord = ""
        for (char in word) {
            val code = when (val charCode = char.toInt()) {
                in 48..57 -> morseNumbers[charCode - 48]
                in 97..122 -> morseLetters[charCode - 97]
                else -> ""
            }
            encWord += "$code$SINGLE_SPACE" // Result: double space at end of encoded letter
        }
        return encWord
    }

    private fun morseStringToTimings(morseString: String): LongArray {
        val timingsList = morseString.map {
            when (it) {
                '.' -> 200L
                '-' -> 600L
                ' ' -> 200L
                '/' -> 1400L
                'B' -> 600L
                else -> 0L
            }
        }
        val ll = LinkedList(timingsList)
        ll.push(100) // Push single 100 ms for the first 'off' timing
        return ll.toLongArray()
    }
}
