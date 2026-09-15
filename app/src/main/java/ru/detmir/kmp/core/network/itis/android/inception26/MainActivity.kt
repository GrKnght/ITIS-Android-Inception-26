package ru.detmir.kmp.core.network.itis.android.inception26

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ru.detmir.kmp.core.network.itis.android.inception26.ui.theme.Itisandroidinception26Theme

class MainActivity : ComponentActivity() {

    val notificationManager: NotificationManager? =
        getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val error: GlobalError = GlobalError.UserDataError(secondField = "Field")

        val inputString = "Input"

        inputString.doubleLengthPlusArg(arg2 = 2, arg1 = 1, arg3 = 3)

        val defaultClass = DefaultClass()
        defaultClass.plusResult()


        DefaultStaticClass

        inputString.doubleLength()

        when (error) {
            is GlobalError.AuthorizationError -> {

            }

            is GlobalError.UserDataError -> {

            }
        }



        setContent {
            Itisandroidinception26Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                }
            }
        }
    }
}

private fun String.doubleLength(): Int {
    return this.length * 2
}


private fun String.doubleLengthPlusArg(arg1: Int, arg2: Int, arg3: Int): Int {
    return this.length * 2 + arg1
}

inline fun <reified E> sampleFun(): E {

}


private fun String?.doubleNullable(): Int {
    return this?.let { it.length * 2 } ?: -1
}

private fun GlobalError.someFunction(
    arg1: Int
): Int {

}

class DefaultClass {

    fun plusResult() {}

    fun showLog() {
        Log.e(LOG_TAG, "Some message to show")
    }

    private companion object {
        const val LOG_TAG = "DefaultClassLog"

        fun showSomeResult(): String {
            return "Sample"
        }
    }
}

object DefaultStaticClass {

    fun plusResult() {

    }
}




