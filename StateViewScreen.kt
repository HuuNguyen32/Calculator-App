package eu.tutorial.bai5

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun mainView(){
    val style1 = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF444444),
        contentColor = Color.White,
        disabledContainerColor = Color.Black.copy(0.3f),
        disabledContentColor = Color.White.copy(0.4f)
        // copy(): phuong thuc sao chep, alpha: chỉ số độ trong suốt
        // độ trong từ 0f (trong suốt) -> 1f (không trong suốt)
    )

    val style2 = ButtonDefaults.buttonColors(
        containerColor = Color(0xFFFF9900),
        contentColor = Color.White,
        disabledContainerColor = Color.Black.copy(0.3f),
        disabledContentColor = Color.White.copy(0.4f)
        // copy(): phuong thuc sao chep, alpha: chỉ số độ trong suốt
        // độ trong từ 0f (trong suốt) -> 1f (không trong suốt)
    )

    val style3 = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF777777),
        contentColor = Color.White,
        disabledContainerColor = Color.Black.copy(0.3f),
        disabledContentColor = Color.White.copy(0.4f)
        // copy(): phuong thuc sao chep, alpha: chỉ số độ trong suốt
        // độ trong từ 0f (trong suốt) -> 1f (không trong suốt)
    )
    val style4 = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF3333FF),
        contentColor = Color.White,
        disabledContainerColor = Color.Black.copy(0.3f),
        disabledContentColor = Color.White.copy(0.4f)
        // copy(): phuong thuc sao chep, alpha: chỉ số độ trong suốt
        // độ trong từ 0f (trong suốt) -> 1f (không trong suốt)
    )

    val style5 = ButtonDefaults.buttonColors(
        containerColor = Color(0xFF66CCFF),
        contentColor = Color.White,
        disabledContainerColor = Color.Black.copy(0.3f),
        disabledContentColor = Color.White.copy(0.4f)
        // copy(): phuong thuc sao chep, alpha: chỉ số độ trong suốt
        // độ trong từ 0f (trong suốt) -> 1f (không trong suốt)
    )



    val textStyle1 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    )

    var display by remember { mutableStateOf("0") }
    var operand1 by remember { mutableStateOf("") }
    var operand2 by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf<String?>(null) }
    var mode by remember { mutableStateOf(false) }

    val onClickNumber: (String) -> Unit = { number ->
        if (operation == null) {
            operand1 += number
            display = operand1
        } else {
            operand2 += number
            display = operand2
        }
    }

    val onClickOperation: (String) -> Unit = { op ->
        operation = op
    }

    val onClickEquals: () -> Unit = {
        val result = when (operation) {
            "+" -> if (!operand1.contains(".") && !operand2.contains(".")) operand1.toInt() + operand2.toInt() else operand1.toDouble() + operand2.toDouble()
            "-" -> if (!operand1.contains(".") && !operand2.contains(".")) operand1.toInt() - operand2.toInt() else operand1.toDouble() - operand2.toDouble()
            Icons.Filled.Clear.toString() -> if (!operand1.contains(".") && !operand2.contains(".")) operand1.toInt() * operand2.toInt() else operand1.toDouble() * operand2.toDouble()
            "a/b" -> if (!operand1.contains(".") && !operand2.contains(".")) operand1.toInt() / operand2.toInt() else operand1.toDouble() / operand2.toDouble()
            "%" -> if (!operand1.contains(".") && !operand2.contains(".")) operand1.toInt() % operand2.toInt() else operand1.toDouble() % operand2.toDouble()
            else -> if(operand1 == "") 0 else operand1
        }
        display = result.toString()
        operand1 = ""
        operand2 = ""
        operation = null
    }

    val onClickClear: () -> Unit = {
        display = "0"
        operand1 = ""
        operand2 = ""
        operation = null
    }

    val onClickDelete: () -> Unit = {
        // Tao danh sach chuoi so
        var opList1 = operand1.toList().map { it.toString() }
        var opList2 = operand2.toList().map { it.toString() }

        if (operation == null) {
            var op1New = opList1.dropLast(1) // Xoa phan tu cuoi cung
            operand1 = op1New.joinToString("") // Noi lai thanh mot chuoi
            display = operand1
        } else {
            var op2New = opList2.dropLast(1)
            operand2 = op2New.joinToString("")
            display = operand2
        }
    }
    val bgrTheme:(Boolean) -> Color = { mode ->
        if (!mode) Color.Black
        else Color.White
    }
    val LightAndDark:(Boolean) -> Color = {
        mode -> if (!mode) Color.Red
        else Color.Yellow
    }
   Surface(
       modifier = Modifier.fillMaxSize(),
       color = bgrTheme(mode)
   ) {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(8.dp)
               .background(
                   color = bgrTheme(mode)
               )

       ) {
           Spacer(modifier = Modifier.height(32.dp))
           Button(
               onClick = {
                   mode = !mode
               },
               modifier = Modifier
                   .size(48.dp)
                   .border(3.dp,LightAndDark(mode), shape = CircleShape),
               shape = CircleShape,
               colors = ButtonDefaults.buttonColors(
                   containerColor = bgrTheme(!mode)
               )
           ) {
               Text(text = "")
           }
           Column(
               modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Bottom
           ) {
               Text(
                   text = display,
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(end = 14.dp, bottom = 14.dp)
                   ,
                   color = bgrTheme(!mode),
                   fontFamily = FontFamily.Default,
                   fontSize = 96.sp,
                   fontWeight = FontWeight.W300,
                   maxLines = 1,
                   textAlign = TextAlign.End
               )
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   Button(onClick = {
                       onClickClear()
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style3 else style5,
                       shape = CircleShape
                   ) {
                       Text("AC", style = textStyle1)
                   }
                   Button(onClick = {
                       onClickDelete()
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style3 else style5,
                       shape = CircleShape
                   ) {
                       Text("DE",style = textStyle1)
                   }
                   Button(onClick = {
                       onClickOperation("%")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style3 else style5,
                       shape = CircleShape
                   ) { Text("%",style = textStyle1) }
                   Button(onClick = {
                       onClickOperation("a/b")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style2 else style4,
                       shape = CircleShape
                   ) { Text("a/b",style = textStyle1) }
               }
               space()
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   Button(onClick = {
                       onClickNumber("7")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("7",style = textStyle1) }
                   Button(onClick = {
                       onClickNumber("8")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("8",style = textStyle1) }
                   Button(onClick = {
                       onClickNumber("9")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("9",style = textStyle1) }
                   Button(onClick = {
                       onClickOperation(Icons.Filled.Clear.toString())
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style2 else style4,
                       shape = CircleShape
                   ) { Icon(imageVector = Icons.Filled.Clear, contentDescription = null,Modifier.size(32.dp)) }
               }
               space()
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   Button(onClick = {
                       onClickNumber("4")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("4",style = textStyle1) }
                   Button(onClick = {
                       onClickNumber("5")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("5",style = textStyle1) }
                   Button(onClick = {
                       onClickNumber("6")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("6",style = textStyle1) }
                   Button(onClick = {
                       onClickOperation("-")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style2 else style4,
                       shape = CircleShape
                   ) {
                       Text("-",style = textStyle1)
                   }
               }
               space()
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   Button(onClick = {
                       onClickNumber("1")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("1",style = textStyle1) }
                   Button(onClick = {
                       onClickNumber("2")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("2",style = textStyle1) }
                   Button(onClick = {
                       onClickNumber("3")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("3",style = textStyle1) }
                   Button(onClick = {
                       onClickOperation("+")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style2 else style4,
                       shape = CircleShape
                   ) { Text("+",style = textStyle1) }
               }
               space()
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   Button(onClick = {
                       onClickNumber("0")
                   },
                       modifier = Modifier.size(182.dp,88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text("0",style = textStyle1) }
                   Button(onClick = {
                       onClickNumber(".")
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style1 else style3,
                       shape = CircleShape
                   ) { Text(".",style = textStyle1) }
                   Button(onClick = {
                       onClickEquals()
                   },
                       modifier = Modifier.size(88.dp)
                       , colors = if (!mode) style2 else style4,
                       shape = CircleShape
                   ) { Text("=",style = textStyle1) }
               }
               Spacer(modifier = Modifier.height(40.dp))
           }
       }
   }
}

@Composable
fun space(){
    Spacer(modifier = Modifier.height(8.dp))
}
@Preview(showBackground = true)
@Composable
fun myPreview(){
    mainView()
}