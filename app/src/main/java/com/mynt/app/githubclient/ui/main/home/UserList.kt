package com.mynt.app.githubclient.ui.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mynt.app.githubclient.model.User
import com.mynt.app.githubclient.ui.theme.GithubClientTheme

@Composable
fun UserList(
    users: List<User>,
    onUserClick: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = users,
            key = { it.id }
        ) { user ->
            UserItem(
                user = user,
                onUserClick = onUserClick
            )
        }
    }
}

@Composable
private fun UserItem(
    modifier: Modifier = Modifier,
    user: User,
    onUserClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                onUserClick(user.id)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            model = user.avatar,
            contentDescription = "avatar",
            contentScale = ContentScale.Crop
        )
        Text(text = user.name)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewUserItem() {
    val user = User(
        id = "user1",
        name = "Nguyen Thien My",
        avatar = "https://avatars.githubusercontent.com/u/2",
        followers = 0,
        following = 0
    )

    GithubClientTheme {
        UserItem(
            user = user,
            onUserClick = {}
        )
    }
}
