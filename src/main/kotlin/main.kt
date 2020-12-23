import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

// TODO: Keyboard input
fun main() = Window("Calculator", IntSize(400, 630)) {
    MaterialTheme {
        Column(Modifier.fillMaxSize().background(Color.DarkGray), Arrangement.spacedBy(5.dp)) {
            Text(
                Calculator.toString(),
                Modifier.fillMaxSize().weight(1.0F).background(Color.White),
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.Bold
            )
            Divider()
            Row(Modifier.fillMaxSize().weight(3.0F).padding(5.dp), Arrangement.spacedBy(5.dp)) {
                Column(Modifier.fillMaxSize().weight(1.0F), Arrangement.spacedBy(5.dp)) {
                    Button({ Calculator.appendSymbol("1") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("1")
                    }
                    Button({ Calculator.appendSymbol("4") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("4")
                    }
                    Button({ Calculator.appendSymbol("7") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("7")
                    }
                    Button({ Calculator.appendSymbol("0") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("0")
                    }
                }
                Column(Modifier.fillMaxSize().weight(1.0F), Arrangement.spacedBy(5.dp)) {
                    Button({ Calculator.appendSymbol("2") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("2")
                    }
                    Button({ Calculator.appendSymbol("5") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("5")
                    }
                    Button({ Calculator.appendSymbol("8") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("8")
                    }
                    Button({ Calculator.appendSymbol(".") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text(".")
                    }
                }
                Column(Modifier.fillMaxSize().weight(1.0F), Arrangement.spacedBy(5.dp)) {
                    Button({ Calculator.appendSymbol("3") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("3")
                    }
                    Button({ Calculator.appendSymbol("6") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("6")
                    }
                    Button({ Calculator.appendSymbol("9") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("9")
                    }
                    Button({ Calculator.calculate() }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("=")
                    }
                }
                Column(Modifier.fillMaxSize().weight(1.0F), Arrangement.spacedBy(5.dp)) {
                    Button({ Calculator.deleteLastSymbol() }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("⟵")
                    }
                    Button({ Calculator.appendSymbol("+") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("+")
                    }
                    Button({ Calculator.appendSymbol("-") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("-")
                    }
                    Button({ Calculator.appendSymbol("×") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("×")
                    }
                    Button({ Calculator.appendSymbol("÷") }, Modifier.fillMaxSize().weight(1.0F)) {
                        Text("÷")
                    }
                }
            }
        }
    }
}
