package com.example.implicitintentexample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.implicitintentexample.ui.theme.ImplicitIntentExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImplicitIntentExampleTheme {
                Scaffold { innerPadding ->
                    ImplicitIntents(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ImplicitIntents(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val uri = "https://anbo-easj.dk/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                startActivity(context, intent, null)
            }) { Text(text = "Open browser") }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+45123456"))
                startActivity(context, intent, null)
            }) { Text(text = "Open phone app") }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:55.6305952,12.0784041"))
                startActivity(context, intent, null)
            }) { Text(text = "Open map") }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivity(context, intent, null)
            }) { Text(text = "Open camera app") }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse("mailto:")
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello World!")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi! I am sending you a test email.")
                startActivity(context, emailIntent, null)
            }) { Text("Open email app") }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW)
                // Nothing else specified!
                startActivity(context, intent, null)
            }) { Text(text = "Generic intent") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImplicitIntentExampleTheme {
        ImplicitIntents()
    }
}