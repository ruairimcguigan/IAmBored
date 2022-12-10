package i.am.bored.ui

import androidx.annotation.StringRes
import i.am.bored.R
import model.IdeaDomain

val IdeaDomain.accessibilityLabelRes: Int
    @StringRes
    get() {
        return when (accessibility) {
            in 0f..0.3f -> R.string.label_easy
            in 0.3f..0.7f -> R.string.label_moderate
            else -> R.string.label_hard
        }
    }

val IdeaDomain.priceLabelRes: Int
    @StringRes
    get() {
        return when (price) {
            0.0f -> R.string.label_free
            in 0f..0.3f -> R.string.label_cheap
            in 0.3f..0.7f -> R.string.label_pricey
            else -> R.string.label_expensive
        }
    }
