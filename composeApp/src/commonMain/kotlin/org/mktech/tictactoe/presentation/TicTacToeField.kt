package org.mktech.tictactoe.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mktech.tictactoe.models.GameState

@Composable
fun TicTacToeField(
    modifier: Modifier,
    state: GameState,
    onTapInField: (x: Int, y: Int) -> Unit,
    playerXColor: Color = Color.Green,
    playerOColor: Color = Color.Red
) {

    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectTapGestures {
                    val x = (3 * it.x.toInt()) / size.width
                    val y = (3 * it.y.toInt()) / size.height
                    onTapInField(x, y)
                }
            }) {
        drawField()
        state.field.forEachIndexed { y, _ ->
            state.field[y].forEachIndexed { x, player ->
                val offSet = Offset(
                    x = x * size.width * (1 / 3f) + size.width / 6f,
                    y = y * size.height * (1 / 3f) + size.height / 6f
                )

                if (player == 'X') {
                    drawX(
                        color = playerXColor,
                        center = offSet
                    )
                } else if (player == 'O') {
                    drawO(
                        color = playerOColor,
                        center = offSet
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawO(
    color: Color,
    center: Offset,
    size: Size = Size(50.dp.toPx(), 50.dp.toPx())
) {
    drawCircle(
        color = color,
        radius = size.width / 2f,
        center = center,
        style = Stroke(
            width = 3.dp.toPx()
        )
    )
}

private fun DrawScope.drawX(
    color: Color,
    center: Offset,
    size: Size = Size(50.dp.toPx(), 50.dp.toPx())
) {

    drawLine(
        color = color,
        start = Offset(
            x = center.x - size.width / 2f,
            y = center.y - size.height / 2f
        ),
        end = Offset(
            x = center.x + size.width / 2f,
            y = center.y + size.height / 2f
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = color,
        start = Offset(
            x = center.x - size.width / 2f,
            y = center.y + size.height / 2f
        ),
        end = Offset(
            x = center.x + size.width / 2f,
            y = center.y - size.height / 2f
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
}

private fun DrawScope.drawField() {
    //1st vertical line
    drawLine(
        color = Color.Black,
        start = Offset(size.width * 0.33f, 0f),
        end = Offset(size.width * 0.33f, size.height),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )

    //2st vertical line
    drawLine(
        color = Color.Black,
        start = Offset(size.width * 0.66f, 0f),
        end = Offset(size.width * 0.66f, size.height),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )

    //1st horizontal line
    drawLine(
        color = Color.Black,
        start = Offset(0f, size.height * 0.33f),
        end = Offset(size.width, size.height * 0.33f),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )

    //2st horizontal line
    drawLine(
        color = Color.Black,
        start = Offset(0f, size.height * 0.66f),
        end = Offset(size.width, size.height * 0.66f),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )
}

@Preview()
@Composable
fun TicTacToeFieldPreview() {
    TicTacToeField(
        modifier = Modifier.size(300.dp),
        state = GameState(
            field = arrayOf(
                arrayOf('X', null, null),
                arrayOf(null, 'O', 'O'),
                arrayOf(null, null, null),
            )
        ),
        onTapInField = { _, _ -> }
    )
}