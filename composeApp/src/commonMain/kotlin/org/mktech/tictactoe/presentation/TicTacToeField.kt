package org.mktech.tictactoe.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mktech.tictactoe.models.GameState
import org.mktech.tictactoe.theme.buttonColor
import org.mktech.tictactoe.theme.dialogColor
import org.mktech.tictactoe.theme.oColor
import org.mktech.tictactoe.theme.primary
import org.mktech.tictactoe.theme.xColor

@Composable
fun TicTacToeField(
    modifier: Modifier,
    state: GameState,
    onTapInField: (x: Int, y: Int) -> Unit,
    playerXColor: Color = xColor,
    playerOColor: Color = oColor
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
    size: Size = Size(45.dp.toPx(), 45.dp.toPx())
) {
    drawCircle(
        color = color,
        radius = size.width / 2f,
        center = center,
        style = Stroke(
            width = 6.dp.toPx()
        )
    )
}

private fun DrawScope.drawX(
    color: Color,
    center: Offset,
    size: Size = Size(40.dp.toPx(), 40.dp.toPx())
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
        strokeWidth = 6.dp.toPx(),
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
        strokeWidth = 6.dp.toPx(),
        cap = StrokeCap.Round
    )
}

private fun DrawScope.drawField() {
    // 🌈 Draw gradient background first
    drawRoundRect(
        brush = Brush.radialGradient(
            colors = listOf(primary, buttonColor),
            center = center,
            radius = size.minDimension * 2.5f
        ),
        size = size,
        cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx()),
    )

    val stroke = 1.dp.toPx()
    val verticalMargin = 1.5.dp.toPx()     // margin from top and bottom
    val horizontalMargin = 1.5.dp.toPx()   // margin from left and right


    // 🟦 Border lines (Top, Left, Bottom, Right)
    /*  drawLine(
          color = buttonColor,
          start = Offset(0f, 0f),
          end = Offset(size.width, 0f),
          strokeWidth = stroke,
          cap = StrokeCap.Round
      )

      drawLine(
          color = buttonColor,
          start = Offset(0f, 0f),
          end = Offset(0f, size.height),
          strokeWidth = stroke,
          cap = StrokeCap.Round
      )

      drawLine(
          color = buttonColor,
          start = Offset(0f, size.height),
          end = Offset(size.width, size.height),
          strokeWidth = stroke,
          cap = StrokeCap.Round
      )

      drawLine(
          color = buttonColor,
          start = Offset(size.width, 0f),
          end = Offset(size.width, size.height),
          strokeWidth = stroke,
          cap = StrokeCap.Round
      )*/

    // 🧩 Grid lines (Vertical)
    drawLine(
        color = buttonColor,
        start = Offset(size.width * 0.33f, verticalMargin),
        end = Offset(size.width * 0.33f, size.height - verticalMargin),
        strokeWidth = stroke,
        cap = StrokeCap.Round
    )

    drawLine(
        color = buttonColor,
        start = Offset(size.width * 0.66f, verticalMargin),
        end = Offset(size.width * 0.66f, size.height - verticalMargin),
        strokeWidth = stroke,
        cap = StrokeCap.Round
    )

    // 🧩 Grid lines (Horizontal)
    drawLine(
        color = buttonColor,
        start = Offset(horizontalMargin, size.height * 0.33f),
        end = Offset(size.width - horizontalMargin, size.height * 0.33f),
        strokeWidth = stroke,
        cap = StrokeCap.Round
    )

    drawLine(
        color = buttonColor,
        start = Offset(horizontalMargin, size.height * 0.66f),
        end = Offset(size.width - horizontalMargin, size.height * 0.66f),
        strokeWidth = stroke,
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