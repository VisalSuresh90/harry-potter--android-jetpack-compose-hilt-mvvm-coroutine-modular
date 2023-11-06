package com.visal.harrypotter.ui.navigation.screens.allspells

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.visal.harrypotter.R
import com.visal.harrypotter.data.model.Spell
import com.visal.harrypotter.ui.common.TextComponent

@Composable
fun SpellItemComponent(spell: Spell) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp)
                .padding(8.dp)
        ) {
            TextComponent(
                value = spell.name,
                //color = Color.Blue,
                fontWeight = FontWeight.SemiBold,

                )
            Spacer(modifier = Modifier.height(8.dp))
            val descriptionText = spell.description?.takeIf { it.isNotEmpty() }
                ?: stringResource(id = R.string.not_available)
            TextComponent(
                value = descriptionText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic
            )
        }
    }
}