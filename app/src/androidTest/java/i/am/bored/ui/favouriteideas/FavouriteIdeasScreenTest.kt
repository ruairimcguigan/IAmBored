//package i.am.bored.ui.favouriteideas
//
//import android.content.Context
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithContentDescription
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.test.core.app.ApplicationProvider
//import i.am.bored.ui.favourite.IdeaCard
//import i.am.bored.ui.favourite.IdeasList
//import i.am.bored.ui.newidea.NewIdeaCard
//import i.am.bored.R
//import i.am.bored.ui.testmodels.androidFirstIdea
//import i.am.bored.ui.testmodels.androidSecondIdea
//import model.IdeaDomain
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito.verify
//import org.mockito.kotlin.mock
//import org.mockito.kotlin.times
//
//class FavouriteIdeasScreenTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun ideasListDisplaysAllIdeas() {
//        val ideasList = listOf(androidFirstIdea, androidSecondIdea)
//        composeTestRule.setContent {
//            IdeasList(
//                modifier = Modifier.fillMaxWidth(),
//                ideaList = ideasList,
//                onDeleteClick = { }
//            )
//        }
//
//        ideasList.forEach { idea ->
//            composeTestRule.onNodeWithText(idea.name)
//                .assertIsDisplayed()
//        }
//    }
//
//    @Test
//    fun ideaNameDisplayedOnAListCard() {
//        composeTestRule.setContent {
//            IdeaCard(
//                modifier = Modifier.fillMaxWidth(),
//                idea = androidFirstIdea,
//                onDeleteClick = { }
//            )
//        }
//
//        composeTestRule
//            .onNodeWithText(androidFirstIdea.name)
//            .assertIsDisplayed()
//    }
//
//    @Test
//    fun onDeleteClickCallbackIsTriggered() {
//        val onDeleteClick: (IdeaDomain) -> Unit = mock()
//
//        composeTestRule.setContent {
//            IdeaCard(
//                modifier = Modifier.fillMaxWidth(),
//                idea = androidFirstIdea,
//                onDeleteClick = onDeleteClick
//            )
//        }
//
//        val contentDescription = ApplicationProvider.getApplicationContext<Context>()
//            .getString(R.string.cd_delete_idea)
//
//        composeTestRule.onNodeWithContentDescription(contentDescription)
//            .performClick()
//
//        verify(onDeleteClick, times(1)).invoke(androidFirstIdea)
//    }
//
//    @Test
//    fun onFavouriteClickCallbackIsTriggered() {
//        val onFavouriteClick: (isFavourite: Boolean) -> Unit = mock()
//        val isFavourite = false
//
//        composeTestRule.setContent {
//            NewIdeaCard(
//                modifier = Modifier.fillMaxWidth(),
//                idea = androidFirstIdea,
//                isFavourite = isFavourite,
//                onFavouriteClick = onFavouriteClick,
//                onLinkClick = { }
//            )
//        }
//
//        val contentDescription = ApplicationProvider.getApplicationContext<Context>()
//            .getString(R.string.cd_save_idea)
//
//        composeTestRule.onNodeWithContentDescription(contentDescription)
//            .performClick()
//
//        verify(onFavouriteClick, times(1)).invoke(!isFavourite)
//    }
//}