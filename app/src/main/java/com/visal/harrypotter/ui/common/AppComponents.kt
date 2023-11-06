package com.visal.harrypotter.ui.common

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.visal.harrypotter.R
import com.visal.harrypotter.data.model.HpCharacter
import com.visal.harrypotter.ui.common.theme.NiaIcons
import com.visal.harrypotter.ui.common.theme.customFontFamily

/**
 * A composable function for displaying a loading indicator (CircularProgressIndicator).
 */
@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp),
            color = Color.Black
        )
    }
}


/**
 * A custom Top App Bar with a back button.
 *
 * @param title The title to display in the app bar.
 * @param onBackClick Callback for handling the back button click.
 * @param isBackVisible Flag to control the visibility of the back button.
 * @param darkTheme Flag to indicate whether the dark theme is enabled.
 * @param onThemeUpdated Callback for updating the theme.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBack(
    title: String,
    onBackClick: () -> Unit,
    isBackVisible: Boolean = true,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.background(Color.Transparent),
        navigationIcon = {
            if (isBackVisible) {
                Icon(
                    Icons.Filled.ArrowBack, null,
//                    tint = Color.Gray, // Change the icon color as needed
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onBackClick() },
                )
            } else {
                null
            }
        },
        title = {
            Text(
                title,
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = customFontFamily
                )
            )
        },
        actions = {
            // Display the theme switcher for toggling between light and dark themes
            ThemeSwitcher(
                darkTheme = darkTheme,
                size = 35.dp,
                padding = 5.dp,
                onClick = onThemeUpdated
            )
        }
    )
}


/**
 * Custom composable for a top search bar.
 *
 * @param searchQuery The current search query text.
 * @param onSearchQueryChanged A callback function that is called when the search query changes.
 *                            It takes a single parameter, which is the updated search query.
 * @param onSearchTriggered A callback function that is called when the user triggers a search
 *                         action (e.g., pressing the search button).
 *                         It takes a single parameter, which is the current search query.
 */
@Composable
fun TopSearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(30.dp)
    ) {
        val query = remember { mutableStateOf("") }
        SearchTextField(
            onSearchQueryChanged = {
                query.value = it
                onSearchQueryChanged(it)
            },
            onSearchTriggered = {
                query.value = it
                onSearchTriggered(it)
            },
            searchQuery = query.value,
        )
    }
}


/**
 * A custom composable for a search text field.
 *
 * @param onSearchQueryChanged A lambda function that will be called when the search query text changes.
 *                            It takes a single parameter of type String, representing the current search query.
 *
 * @param searchQuery The current search query text that should be displayed in the text field.
 *
 * @param onSearchTriggered A lambda function that will be called when the search is triggered (e.g., when the user presses Enter/Return).
 *                         It takes a single parameter of type String, representing the search query to be triggered.
 */
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String,
    onSearchTriggered: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchQuery)
    }
    TextField(
        placeholder = { Text(text = stringResource(id = R.string.search)) },

        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
//            containerColor = colorResource(id = R.color.white),
        ),
        leadingIcon = {
            Icon(
                imageVector = NiaIcons.Search,
                contentDescription = stringResource(
                    id = R.string.search,
                ),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged("")
                    },
                ) {
                    Icon(
                        imageVector = NiaIcons.Close,
                        contentDescription = stringResource(
                            id = R.string.clear_search_text_content_desc,
                        ),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        onValueChange = {
            if (!it.contains("\n")) {
                onSearchQueryChanged(it)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearchExplicitlyTriggered()
                    true
                } else {
                    false
                }
            }
            .testTag("searchTextField"),
        shape = RoundedCornerShape(32.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )
    LaunchedEffect(Unit) {
//        focusRequester.requestFocus()
    }
}


/**
 * A custom TextComponent composable that displays text with customizable properties.
 *
 * @param value The text to display.
 * @param height The height of the text component (default: 24.dp).
 * @param fontSize The font size of the text (default: 24.sp).
 * @param fontWeight The font weight of the text (default: FontWeight.Normal).
 * @param fontStyle The font style of the text (default: FontStyle.Normal).
 * @param textAlign The text alignment (default: TextAlign.Start).
 * @param color The color of the text (default: R.color.black).
 */
@Composable
fun TextComponent(
    value: String,
    height: Dp = 24.dp,
    fontSize: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    fontStyle: FontStyle = FontStyle.Normal,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = colorResource(id = R.color.black)
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(height),
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
            fontStyle = fontStyle
        ),
        //color = color
    )
}


// A custom ListItemComponent composable , which represents a card item for a Harry Potter character.
// It takes two parameters: hpCharacter, which is the character data, and onClick, a callback to handle click events.
@Composable
fun CharacterItemComponent(
    hpCharacter: HpCharacter,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 124.dp)
            .padding(8.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
//                .heightIn(25.dp)
                //.background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thumbnail image on the left
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(size = 8.dp))
                    .background(MaterialTheme.colorScheme.primary),
                model = hpCharacter.image,
                contentDescription = "Image Description",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.def_image),
                error = painterResource(id = R.drawable.no_photo),
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Details on the right
            Column(modifier = Modifier.weight(1f)) {
                TextComponent(
                    value = hpCharacter.name,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                val actorText = hpCharacter.actor?.takeIf { it.isNotEmpty() }
                    ?: stringResource(id = R.string.not_available)
                TextComponent(
                    value = "${stringResource(id = R.string.actor)} : $actorText",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                val dobText = hpCharacter.dateOfBirth?.takeIf { it.isNotEmpty() } ?: stringResource(
                    id = R.string.not_available
                )
                TextComponent(
                    value = "${stringResource(id = R.string.dob)} : $dobText",
                    fontSize = 16.sp,
                    color = Color.Gray
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
 * Composable function to render a theme switcher button.
 *
 * @param darkTheme: Whether the dark theme is currently active.
 * @param size: The size of the theme switcher button.
 * @param iconSize: The size of the theme switcher icon.
 * @param padding: Padding around the theme switcher button.
 * @param borderWidth: The border width of the button.
 * @param parentShape: The shape of the parent container.
 * @param toggleShape: The shape of the theme switcher toggle button.
 * @param animationSpec: The animation specification for button animations.
 * @param onClick: A lambda function to handle button click events.
 */
@Composable
fun ThemeSwitcher(
    darkTheme: Boolean = false,
    size: Dp = 150.dp,
    iconSize: Dp = size / 3,
    padding: Dp = 10.dp,
    borderWidth: Dp = 1.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    onClick: () -> Unit
) {
    val offset by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else size,
        animationSpec = animationSpec,
        label = ""
    )
    Box(
        modifier = Modifier
            .width(size * 2)
            .height(size)
            .clip(shape = parentShape)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.secondaryContainer),

        ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {}
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = parentShape
                )
        ) {
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.Nightlight,
                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) MaterialTheme.colorScheme.secondaryContainer
                    else MaterialTheme.colorScheme.primary
                )
            }
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.LightMode,
                    contentDescription = "Theme Icon",
                    tint = if (darkTheme) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}