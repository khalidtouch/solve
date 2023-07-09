package com.khalidtouch.solve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khalidtouch.solve.domain.Action
import com.khalidtouch.solve.domain.Operation
import com.khalidtouch.solve.ui.CalculatorViewModel
import com.khalidtouch.solve.ui.components.CalculatorButton
import com.khalidtouch.solve.ui.theme.SolveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolveTheme {
                val viewModel: CalculatorViewModel by viewModels<CalculatorViewModel>()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen(viewModel = viewModel)
                }
            }
        }
    }
}


@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel,
) {
    val buttonSpacing = 8.dp
    val state = viewModel.state

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        ) {
            Text(
                text = state.number1 + (state.op?.symbol ?: "") + state.number2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
                    .testTag("display:main"),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Light,
                fontSize = 80.sp,
                color = Color.White,
                maxLines = 2,
                lineHeight = 88.sp,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    color = Color.Magenta.copy(0.2f),
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                        .testTag("button:ac"),
                    onClick = { viewModel.onAction(Action.Clear) }
                )

                CalculatorButton(
                    symbol = "Del",
                    color = Color.Magenta.copy(0.2f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:del"),
                    onClick = { viewModel.onAction(Action.Delete) }
                )


                CalculatorButton(
                    symbol = "/",
                    color = Color.Cyan.copy(0.2f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:divide"),
                    onClick = { viewModel.onAction(Action.Op(Operation.Divide)) }
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:seven"),
                    onClick = { viewModel.onAction(Action.Number(7)) }
                )

                CalculatorButton(
                    symbol = "8",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:eight"),
                    onClick = { viewModel.onAction(Action.Number(8)) }
                )


                CalculatorButton(
                    symbol = "9",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:nine"),
                    onClick = { viewModel.onAction(Action.Number(9)) }
                )

                CalculatorButton(
                    symbol = "X",
                    color = Color.Cyan.copy(0.2f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:times"),
                    onClick = { viewModel.onAction(Action.Op(Operation.Multiply)) }
                )

            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:four"),
                    onClick = { viewModel.onAction(Action.Number(4)) }
                )

                CalculatorButton(
                    symbol = "5",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:five"),
                    onClick = { viewModel.onAction(Action.Number(5)) }
                )


                CalculatorButton(
                    symbol = "6",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:six"),
                    onClick = { viewModel.onAction(Action.Number(6)) }
                )

                CalculatorButton(
                    symbol = "-",
                    color = Color.Cyan.copy(0.2f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:minus"),
                    onClick = { viewModel.onAction(Action.Op(Operation.Subtract)) }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:one"),
                    onClick = { viewModel.onAction(Action.Number(1)) }
                )

                CalculatorButton(
                    symbol = "2",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:two"),
                    onClick = { viewModel.onAction(Action.Number(2)) }
                )


                CalculatorButton(
                    symbol = "3",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:three"),
                    onClick = { viewModel.onAction(Action.Number(3)) }
                )

                CalculatorButton(
                    symbol = "+",
                    color = Color.Cyan.copy(0.2f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:plus"),
                    onClick = { viewModel.onAction(Action.Op(Operation.Add)) }
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                        .testTag("button:zero"),
                    onClick = {viewModel.onAction(Action.Number(0))}
                )

                CalculatorButton(
                    symbol = ".",
                    color = Color.Gray.copy(0.5f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:decimal"),
                    onClick = {viewModel.onAction(Action.Decimal)}
                )


                CalculatorButton(
                    symbol = "=",
                    color = Color.Cyan.copy(0.2f),
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .testTag("button:equals"),
                    onClick = {viewModel.onAction(Action.Calculate)}
                )
            }
        }
    }
}