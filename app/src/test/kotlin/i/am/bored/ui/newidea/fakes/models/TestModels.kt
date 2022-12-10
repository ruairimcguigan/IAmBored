package i.am.bored.ui.newidea.fakes.models

import model.IdeaDomain
import model.Type

val firstIdea = IdeaDomain(
    name = "Learn to dance",
    type = Type.Recreational,
    participantCount = 2,
    price = 0.1f,
    accessibility = 0.2f,
    key = "112233",
    link = "www.dance.com"
)

val secondIdea = IdeaDomain(
    name = "Learn guitar",
    type = Type.Education,
    participantCount = 1,
    price = 0.0f,
    accessibility = 0.1f,
    key = "223344",
    link = "www.guitar.com"
)