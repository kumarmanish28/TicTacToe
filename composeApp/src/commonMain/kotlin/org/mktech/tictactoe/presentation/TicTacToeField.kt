package org.mktech.tictactoe.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
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

    Canvas(modifier = modifier) {
        drawField()
        drawX(
            color = playerXColor,
            center = Offset(
                x = size.width * (1 / 6f),
                y = size.height * (1 / 6f)
            )
        )
        drawO(
            color = playerOColor,
            center = Offset(
                x = size.width * (3 / 6f),
                y = size.height * (3 / 6f)
            )
        )
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
                arrayOf('X', null, 'X'),
                arrayOf('X', 'O', null),
            )
        ),
        onTapInField = { _, _ -> }
    )
}