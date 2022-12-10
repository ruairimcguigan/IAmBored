package i.am.bored.testmodels

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
    name = "Pet a dog",
    type = Type.Relaxation,
    participantCount = 1,
    price = 0.0f,
    accessibility = 0.1f,
    key = "223344",
    link = "www.dog.com"
)

val responseIdea = IdeaDomain(
    name = "Go to a music festival with some friends",
    type = Type.Social,
    participantCount = 4,
    price = 0.4f,
    accessibility = 0.2f,
    key = "6482790",
    link = ""
)

