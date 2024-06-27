//package i.am.bored.ui.newidea
//
//import android.content.Context
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.test.*
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.test.core.app.ApplicationProvider
//import i.am.bored.R
//import i.am.bored.ui.UiTags
//import i.am.bored.ui.testmodels.androidFirstIdea
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito
//import org.mockito.kotlin.mock
//import org.mockito.kotlin.times
//
//class NewIdeasScreenKtTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun activityNameDisplayedOnACard() {
//        composeTestRule.setContent {
//            NewIdeaCard(
//                modifier = Modifier.fillMaxWidth(),
//                idea = androidFirstIdea,
//                isFavourite = false,
//                onFavouriteClick = { },
//                onLinkClick = { }
//            )
//        }
//
//        composeTestRule.onNodeWithText(androidFirstIdea.name)
//            .assertIsDisplayed()
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
//        Mockito.verify(onFavouriteClick, times(1)).invoke(!isFavourite)
//    }
//
//    @Test
//    fun onLinkClickCallbackIsTriggered() {
//        val onLinkClick: (link: String) -> Unit = mock()
//
//        composeTestRule.setContent {
//            NewIdeaCard(
//                modifier = Modifier.fillMaxWidth(),
//                idea = androidFirstIdea,
//                isFavourite = false,
//                onFavouriteClick = { },
//                onLinkClick = onLinkClick
//            )
//        }
//
//        composeTestRule.onNodeWithTag(UiTags.IdeaLink).performClick()
//
//        Mockito.verify(onLinkClick, times(1)).invoke(androidFirstIdea.link)
//    }
//}
