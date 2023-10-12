package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.DataSource
import com.example.superheroes.model.Superhero
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroesApp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesApp() {
    Scaffold(
        topBar = {
            SuperheroTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(DataSource.superheroes) {
                SuperheroItem(
                    superhero = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun SuperheroItem(
    superhero: Superhero,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column() {
                    Text(
                        text = stringResource(superhero.name),
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = stringResource(superhero.description),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Column() {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp)
                            .clip(MaterialTheme.shapes.small),
                        contentScale = ContentScale.Crop,

                        painter = painterResource(superhero.imageResourceId),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

            }
            SuperheroVuln(
                superhero.vuln,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            )
        }
    }
}

@Composable
private fun SuperheroItemButton(
    expand: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun SuperheroVuln(
    @StringRes superheroVuln: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(superheroVuln),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SuperheroPreview() {
    SuperheroesTheme {
        SuperheroesApp()
    }
}

@Preview(showBackground = true)
@Composable
fun SuperheroDarkThemePreview() {
    SuperheroesTheme(darkTheme = true) {
        SuperheroesApp()
    }
}