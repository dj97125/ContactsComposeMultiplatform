package com.plcoding.contactscomposemultiplatform.contacts.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.contactscomposemultiplatform.contacts.domain.Contact
import com.plcoding.contactscomposemultiplatform.contacts.presentation.ContactListEvent
import com.plcoding.contactscomposemultiplatform.core.presentation.BottomSheetFromWish

@Composable
fun ContactDetailSheet(
    isOpen: Boolean,
    selectedContact: Contact?,
    onEvent: (ContactListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheetFromWish(
        visible = isOpen,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(60.dp))
                ContactPhoto(
                    contact = selectedContact,
                    iconSize = 50.dp,
                    modifier = Modifier
                        .size(150.dp)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "${selectedContact?.firstName} ${selectedContact?.lastName}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Spacer(Modifier.height(16.dp))
                EditRow(
                    onEditClick = {
                        selectedContact?.let {
                            onEvent(ContactListEvent.EditContact(it))
                        }
                    },
                    onDeleteClick = {
                        onEvent(ContactListEvent.DeleteContact)
                    }
                )
                Spacer(Modifier.height(16.dp))
                ContactInfoSection(
                    title = "Phone number",
                    value = selectedContact?.phoneNumber ?: "-",
                    icon = Icons.Rounded.Phone,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                ContactInfoSection(
                    title = "Email",
                    value = selectedContact?.email ?: "-",
                    icon = Icons.Rounded.Email,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            IconButton(
                onClick = {
                    onEvent(ContactListEvent.DismissContact)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}

@Composable
private fun EditRow(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        FilledTonalIconButton(
            onClick = onEditClick,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Edit contact"
            )
        }
        FilledTonalIconButton(
            onClick = onDeleteClick,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete contact"
            )
        }
    }
}

@Composable
private fun ContactInfoSection(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Spacer(Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp
            )
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp
            )
        }
    }
}