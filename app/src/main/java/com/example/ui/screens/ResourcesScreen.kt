package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.EmeraldGreen

@Composable
fun ResourcesScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            // Ignored if no browser
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("resources_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // App Bar & Title
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Column {
                        Text(
                            text = "Official Exam Resources",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "CBSE Academic Portal & Verification Hub (2026–27)",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Official CBSE Portal Direct Links Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "OFFICIAL CBSE CURRICULUM PORTALS",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    ResourceLinkItem(
                        title = "CBSE Academic Official Portal",
                        description = "Official curriculum documents, notifications, and examination bye-laws for Class 10.",
                        url = "https://cbseacademic.nic.in",
                        onClick = { openUrl("https://cbseacademic.nic.in") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ResourceLinkItem(
                        title = "Sample Question Papers & Marking Scheme",
                        description = "Official Class 10 SQPs with verified answer keys and step-marking distribution.",
                        url = "https://cbseacademic.nic.in/SQP_CLASSX_2026.html",
                        onClick = { openUrl("https://cbseacademic.nic.in/SQP_CLASSX_2026.html") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ResourceLinkItem(
                        title = "CBSE Question Bank & Competency Items",
                        description = "Practice item banks for 50% competency-focused questions across Math, Science, SST, and English.",
                        url = "https://cbseacademic.nic.in/qbclass10.html",
                        onClick = { openUrl("https://cbseacademic.nic.in/qbclass10.html") }
                    )
                }
            }
        }

        // Exam Day Time Management & Step-Marking Guide
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Timer, contentDescription = null, tint = EmeraldGreen)
                        Text(
                            text = "3-Hour Exam Time Budget (80 Marks)",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Total Exam Time: 180 Minutes (plus 15 minutes initial reading time)\n\n" +
                                "• Section A (20 MCQs - 20 Marks): Allocate 30 minutes (~1.5 min per question).\n" +
                                "• Section B (6 Very Short - 12 Marks): Allocate 20 minutes (~3 min each).\n" +
                                "• Section C (7 Short - 21 Marks): Allocate 40 minutes (~5 min each).\n" +
                                "• Section D (3 Long - 15 Marks): Allocate 35 minutes (~10-12 min each for detailed proofs and diagrams).\n" +
                                "• Section E (3 Case-Based - 12 Marks): Allocate 30 minutes (~10 min per case).\n" +
                                "• Final Revision & Proofing: Reserve the final 25 minutes to re-check calculations, question numbers, and units.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // CBSE Step Marking Secrets
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Verified, contentDescription = null, tint = AmberGold)
                        Text(
                            text = "Official Step-Marking Guidelines",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "1. Formula Stating: Always write the general formula first (e.g. Area = (θ/360) × πr² or Ohm's Law V = IR). Carrying the formula guarantees 0.5 to 1 mark even if calculation has an error.\n\n" +
                                "2. Units & Dimensions: Omitting units in final numerical answers (e.g., cm², Joules, Ohms) incurs a penalty of 0.5 marks.\n\n" +
                                "3. Ray Diagrams & Physics: Optical ray diagrams without light propagation arrows receive zero marks for that sub-part as per CBSE marking scheme.\n\n" +
                                "4. Point-Wise Social Science Answers: Examiners strongly prefer distinct bullet points with underlined keywords rather than dense paragraphs.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun ResourceLinkItem(
    title: String,
    description: String,
    url: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = url,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                contentDescription = "Open Link",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
