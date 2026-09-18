package com.vishwa.geoquiz

// Quiz state is independent of the Activity and its screen widgets.
class ExpeditionSession(private val stops: List<Question>) {
    var position = 0
    var responses = IntArray(stops.size) { -1 }
    val completed: Int get() = responses.count { it >= 0 }
    val points: Int get() = stops.indices.count {
        responses[it] >= 0 && (responses[it] == 1) == stops[it].answer
    }
    fun restart() {
        position = 0
        responses.fill(-1)
    }
}
