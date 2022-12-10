package i.am.bored.ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.Heart
import compose.icons.evaicons.outline.Heart
import i.am.bored.R
import i.am.bored.ui.UiTags
import i.am.bored.ui.accessibilityLabelRes
import i.am.bored.ui.priceLabelRes
import i.am.bored.ui.theme.*
import model.IdeaDomain

@Composable
fun NewIdeaCard(
    modifier: Modifier,
    idea: IdeaDomain,
    isFavourite: Boolean,
    onFavouriteClick: (isFavourite: Boolean) -> Unit,
    onLinkClick: (link: String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val sizeScale by animateFloatAsState(if (isPressed) 0.5f else 1f)

    Card(
        modifier = modifier.aspectRatio(1.33f),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = grid_1,
        shape = RoundedCornerShape(grid_5)
    ) {
        Column(
            modifier = Modifier.padding(all = grid_4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(grid_2))
                Text(
                    text = idea.type.toString(),
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W800,
                        fontSize = sp_6
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { onFavouriteClick(!isFavourite) },
                    modifier = Modifier.graphicsLayer(
                        scaleX = sizeScale,
                        scaleY = sizeScale
                    ),
                    interactionSource = interactionSource,
                ) {
                    Icon(
                        modifier = Modifier.size(grid_11),
                        imageVector = if (isFavourite) EvaIcons.Fill.Heart else EvaIcons.Outline.Heart,
                        tint = Red300,
                        contentDescription = stringResource(
                            id = if (isFavourite) {
                                R.string.cd_delete_idea
                            } else {
                                R.string.cd_save_idea
                            }
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.7f))

            Text(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                text = idea.name,
                style = TextStyle(fontFamily = FontFamily.Cursive),
                textAlign = TextAlign.Center,
                fontSize = sp_7,
                fontWeight = FontWeight.W300
            )

            if (idea.link.isNotBlank()) {
                Spacer(modifier = Modifier.height(grid_4))

                TextButton(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .testTag(UiTags.IdeaLink),
                    onClick = {
                        onLinkClick(idea.link)
                    }
                ) {
                    Text(
                        text = idea.link,
                        textAlign = TextAlign.Center,
                        fontSize = sp_4
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Divider(startIndent = grid_2, thickness = 1.dp, color = Grey)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = grid_5, bottom = grid_2, start = grid_2, end = grid_2)
            ) {
                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                    Icon(
                        Icons.Default.Face,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(grid_1/2))
                    Text(
                        text = idea.participantCount.toString(),
                        fontWeight = FontWeight.W500,
                    )
                }

                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = idea.priceLabelRes),
                    fontWeight = FontWeight.W500,
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = stringResource(id = idea.accessibilityLabelRes),
                    fontWeight = FontWeight.W500,
                )
            }
        }
    }
}