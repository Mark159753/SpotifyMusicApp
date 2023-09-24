package com.example.home.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    isLoading:Boolean = false,
    @StringRes
    title:Int
){

    if (isLoading){
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(horizontal = 16.dp)
                .height(20.dp)
                .placeholder(
                    visible = true,
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(6.dp),
                    highlight = PlaceholderHighlight.fade()
                )
        )
    }else{
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier.padding(horizontal = 16.dp)
        )
    }

}