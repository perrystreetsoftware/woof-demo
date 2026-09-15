package com.perrystreet.woof.designsystem.atomic.atoms.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextColorRole
import com.perrystreet.woof.designsystem.atomic.atoms.text.roles.TextFontRole
import com.perrystreet.woof.designsystem.theme.Theme

@Composable
fun AtomTextField(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    onSubmit: () -> Unit,
    textFontRole: TextFontRole,
    colorRole: TextColorRole,
    placeholderColorRole: TextColorRole,
    modifier: Modifier = Modifier,
) {
    val textStyle = textFontRole.textStyle()
    BasicTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        textStyle = textStyle.copy(color = colorRole.color()),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(onSend = { onSubmit() }),
        cursorBrush = SolidColor(Theme.colors.primary),
        decorationBox = { innerTextField ->
            Box {
                when (text.isEmpty()) {
                    true -> Text(
                        text = placeholder,
                        style = textStyle,
                        color = placeholderColorRole.color(),
                        maxLines = 1,
                    )
                    false -> Unit
                }
                innerTextField()
            }
        },
    )
}
