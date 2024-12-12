package com.example.convertidor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.convertidor.ui.theme.ConvertidorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertidorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    convertidor()
                }
            }
        }
    }
}

@Composable
fun convertidor(){

   var input by remember {mutableStateOf("")}
   var output by remember {mutableStateOf("")}
   var inputUnit by remember {mutableStateOf("Meters")}
   var outputUnit by remember {mutableStateOf("Meters")}
   var dropDownInput by remember {mutableStateOf(false)}
   var dropDownOutput by remember {mutableStateOf(false)}
   var inputConversionFactor by remember { mutableStateOf(0.0)}
   var outputConversionFactor by remember{ mutableStateOf(1.0) }

   fun fillDropDownInput(inputUnitFun: String,conversionFactorFun: Double){
       inputUnit = inputUnitFun
       inputConversionFactor = conversionFactorFun
   }

    fun fillDropDownOutput(inputUnitFun: String,conversionFactorFun: Double){
        outputUnit = inputUnitFun
        outputConversionFactor= conversionFactorFun
    }


    fun convertUnits(){
       //elvis operator
       val inputValueDouble = input.toDoubleOrNull() ?: 0.0
       val result = (inputValueDouble * inputConversionFactor *100.0/outputConversionFactor).roundToInt()/100.0
       output = result.toString()
   }

   Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = input,
            onValueChange = {input = it},
            label = {Text(text = "Enter value")}
        )
        Text(text = "Resultado $output $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
        Row {
            //input button
           Box{
               Button(onClick = {dropDownInput = true}) {
                   Text(text = inputUnit)
                   Icon(Icons.Default.ArrowDropDown,
                       contentDescription = "Arrow Down")
               }
               DropdownMenu(expanded = dropDownInput,
                   onDismissRequest = {dropDownInput = false}) {
                        DropdownMenuItem(text = {Text("Centimeters")}, onClick = {
                            dropDownInput = false
                            fillDropDownInput("Centimeters",0.01)
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Meters")}, onClick = {
                            dropDownInput = false
                            fillDropDownInput("Meters",1.0)
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Feet")}, onClick = {
                            dropDownInput = false
                            fillDropDownInput("Feet",0.3048)
                            convertUnits()
                        })
                        DropdownMenuItem(text = {Text("Inches")}, onClick = {
                            dropDownInput = false
                            fillDropDownInput("Inches",0.0254)
                            convertUnits()

                        })
               }
           }
            Spacer(modifier = Modifier.width(16.dp))
            //output button
           Box {
               Button(onClick = {dropDownOutput = true}) {
                   Text(text = outputUnit)
                   Icon(
                       Icons.Default.ArrowDropDown,
                       contentDescription = "Arrow Down"
                   )
               }
               DropdownMenu(expanded = dropDownOutput,
                   onDismissRequest = {dropDownOutput = false}) {
                   DropdownMenuItem(text = {Text("Centimeters")}, onClick = {
                       dropDownInput = false
                       fillDropDownOutput("Centimeters", 0.01)
                       convertUnits()
                   })
                   DropdownMenuItem(text = {Text("Meters")}, onClick = {
                       dropDownInput = false
                       fillDropDownOutput("Meters", 1.0)
                       convertUnits()
                   })
                   DropdownMenuItem(text = {Text("Feet")}, onClick = {
                       dropDownInput = false
                       fillDropDownOutput("Feet", 0.3048)
                       convertUnits()
                   })
                   DropdownMenuItem(text = {Text("Inches")}, onClick = {
                       dropDownInput = false
                       fillDropDownOutput("Inches", 0.0254)
                       convertUnits()
                   })

               }
           }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun convertidorPreview() {
    convertidor()
}