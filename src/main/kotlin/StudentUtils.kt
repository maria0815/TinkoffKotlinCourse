import java.lang.IllegalArgumentException

/**
 * Утилиты для работы с информацией о студентах
 */
object StudentUtils {

    /**
     * Проверяет по среднему баллу [averageScore], что у студента диплом с отличием.
     */
    fun isDiplomaWithHonours(averageScore: Double): Boolean {
        return when (averageScore) {
            in 0.0..4.8 -> false
            in 4.8..5.0 -> true
            else -> throw IllegalArgumentException("Incorrect value of the average score!")
        }
    }

    /**
     * Вовзращает оценку по количеству баллов [numberOfPoints] за задания
     */
    fun returnsScoreByNumberOfPointsForTask(numberOfPoints: Int): String {
        return when (numberOfPoints) {
            in 90..100 -> "Excellent!"
            in 76..90 -> "Good!"
            in 60..75 -> "Fair!"
            in 0..60 -> "Unsatisfactory!"
            else -> throw IllegalArgumentException("Incorrect number of points!")
        }
    }
}