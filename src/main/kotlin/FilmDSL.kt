data class Film(
    var name: String = "",
    var reviews: List<Review> = emptyList(),
) {
    /**
     * Возвращает количество отзывов, у которых оценка равна [rating]
     */
    fun numberOfReviews(rating: Int): Int {
        return reviews.count { it.rating == rating }
    }
}

data class Review(
    var rating: Int = 0,
    var comment: String = "",
)