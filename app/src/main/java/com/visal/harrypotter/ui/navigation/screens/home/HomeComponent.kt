
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.visal.harrypotter.R
import com.visal.harrypotter.data.model.HomeMenu
import com.visal.harrypotter.ui.common.theme.customFontFamily


/**
 * Composable function that represents a Card item.
 *
 * @param homeMenu The HomeMenu object containing menu data.
 * @param onClick A lambda function to be executed when the menu box is clicked.
 */
@Composable
fun MenuBox(
    homeMenu: HomeMenu,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 124.dp)
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .heightIn(25.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thumbnail image on the left
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(size = 8.dp))
                    .background(MaterialTheme.colorScheme.primary),
                model = homeMenu.image,
                contentDescription = "Image Description",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.def_image),
                error = painterResource(id = R.drawable.no_photo),
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Details on the right
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = homeMenu.name,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = customFontFamily
                    )
                )
            }
            // Icon at the right-center
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight, // Replace with your desired icon
                contentDescription = "Click to view details",
                tint = Color.Gray, // Change the icon color as needed
                modifier = Modifier.padding(8.dp)
            )


        }
    }
}




/**
 * Loads an image from a network URL using the Coil library and displays it.
 *
 * @param url The URL of the image to load and display.
 */
@Composable
fun ImageBox(
    url: String
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }

}



@Preview
@Composable
fun PreviewMenuBox() {
//    MenuBox()
}
