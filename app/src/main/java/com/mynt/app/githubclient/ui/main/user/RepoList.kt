package com.mynt.app.githubclient.ui.main.user

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mynt.app.githubclient.model.Repo
import com.mynt.app.githubclient.ui.theme.GithubClientTheme

@Composable
fun RepoList(
    modifier: Modifier = Modifier,
    repos: List<Repo>
) {
    val context = LocalContext.current

    val openRepoUrl: (String) -> Unit = { url ->
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    LazyColumn(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = repos,
            key = { it.id }
        ) { repo ->
            RepoItem(repo = repo, onRepoClick = openRepoUrl)
        }
    }
}

@Composable
private fun RepoItem(
    modifier: Modifier = Modifier,
    repo: Repo,
    onRepoClick: (String) -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        onClick = { onRepoClick(repo.url) }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = repo.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            val description = repo.description.ifEmpty { "No description" }
            Text(
                text = description,
                fontSize = 14.sp,
                fontStyle = if (repo.description.isEmpty()) FontStyle.Italic else FontStyle.Normal
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = repo.language,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Light
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${repo.stars} ⭐",
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRepoList(modifier: Modifier = Modifier) {
    val repos = mutableListOf<Repo>()
    repeat(3) { idx ->
        repos.add(
            Repo(
                id = "$idx",
                name = "Repo $idx, this is a very long name but display in only one line",
                description = "Repo description $idx",
                language = "Kotlin",
                stars = 100,
                url = ""
            )
        )
    }

    GithubClientTheme {
        RepoList(repos = repos)
    }
}