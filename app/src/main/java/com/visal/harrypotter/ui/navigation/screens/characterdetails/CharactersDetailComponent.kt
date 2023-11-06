package com.visal.harrypotter.ui.navigation.screens.characterdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.visal.harrypotter.R
import com.visal.harrypotter.ui.common.util.StringUtil


/**
 * Composable function to display an image for a character.
 *
 * @param imageUrl The URL of the image to be displayed.
 */
@Composable
fun CharImage(
    imageUrl: String
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(6.dp))
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}



/**
 * Composable function for displaying a row with a label and associated text value.
 *
 * @param label The label to display.
 * @param text The text value to display (nullable).
 * @param modifier Modifier for customizing the composable.
 */
@Composable
fun LabelTextRow(
    label: String,
    text: String?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = StringUtil.toUpperCamelCase(label),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            // color = MaterialTheme.colorScheme.primary
        )
//        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.width(124.dp)) {
            val textValue = text?.takeIf { it.isNotEmpty() }
                ?: "-${stringResource(id = R.string.not_available)}-"
            Text(
                text = StringUtil.toUpperCamelCase(textValue),
                fontSize = 16.sp,
                // color = Color.Black,
                textAlign = TextAlign.Start
            )
        }

    }
}