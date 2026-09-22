package ru.detmir.kmp.core.network.itis.android.inception26

import android.app.ComponentCaller
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import ru.detmir.kmp.core.network.itis.android.inception26.ui.theme.Itisandroidinception26Theme

class MainActivity : ComponentActivity() {

    private var textToShow = "Hello Android!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLog("onCreate")
        enableEdgeToEdge()
        setContent {
            Itisandroidinception26Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("textToShowKey", textToShow)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textToShow = savedInstanceState.getString("textToShowKey") ?: "Hello Android"
    }

    override fun onStart() {
        super.onStart()
        showLog("onStart")
    }

    override fun onResume() {
        super.onResume()
        showLog("onResume")
    }

    override fun onPause() {
        super.onPause()
        showLog("onPause")
    }

    override fun onStop() {
        super.onStop()
        showLog("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("onDestroy")
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Column {
            val buttonText = remember { mutableStateOf("$name") }

            Text(
                text = "Hello ${buttonText.value}",
                modifier = modifier
            )
            "http".toUri()

            Intent().apply {
                action = Intent.ACTION_SEND
                addCategory(Intent.CATEGORY_DEFAULT)
            }
            Button(onClick = {
                // buttonText.value = "New value Hello!"
                val explicit = Intent(this@MainActivity, SecondActivity::class.java)
                    .apply {
                        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    }
                val intent = Intent().apply {
                    action = Intent.ACTION_MEDIA_SHARED
                    addCategory("")
                }
                startActivity(explicit)
            }) {
                Text("Click me")
            }
        }
    }

    override fun onNewIntent(intent: Intent, caller: ComponentCaller) {
        super.onNewIntent(intent, caller)
    }

    private fun showLog(message: String) {
        println("TEST TAG - Hello from: $message")
    }

    private class InnerClassExample() {

        private var localContext: Context? = null

        fun initContext(ctx: Context) {
            localContext = ctx

            localContext?.getString(R.string.title_activity_main)
        }
    }

    private class AppContextExample(
        private val appCtx: Context
    ) {

    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Itisandroidinception26Theme {
//        Greeting("Android")
//    }
//}