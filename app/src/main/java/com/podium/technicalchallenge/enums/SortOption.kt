package com.podium.technicalchallenge.enums

import android.content.Context
import com.podium.technicalchallenge.R

enum class SortOption(val displayName: (Context) -> String) {
    TITLE(displayName = { context -> context.getString(R.string.title_asc) }),
    TITLE_DESC(displayName = { context -> context.getString(R.string.title_desc) }),
    RELEASE_DATE(displayName = { context -> context.getString(R.string.release_date_asc) }),
    RELEASE_DATE_DESC(displayName = { context -> context.getString(R.string.release_date_desc) }),
    RATING(displayName = { context -> context.getString(R.string.rating_asc) }),
    RATING_DESC(displayName = { context -> context.getString(R.string.rating_desc) });
}