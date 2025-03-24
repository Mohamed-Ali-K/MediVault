package com.meditrack.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meditrack.ui.theme.MediTrackTheme

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Profile icon
        Surface(
            modifier = Modifier.size(120.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.primary.copy(alpha = 0.1f)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                modifier = Modifier
                    .padding(24.dp)
                    .size(72.dp),
                tint = MaterialTheme.colors.primary
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // No profile message
        Text(
            text = "No profile set up yet",
            style = MaterialTheme.typography.h6
        )
        
        Text(
            text = "Create your profile to track your medical records",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )
        
        Button(
            onClick = { /* Create profile action */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create My Profile")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = { /* Add family member action */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Family Member")
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Settings options can be added here
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MediTrackTheme {
        ProfileScreen()
    }
} 