import java.lang.IllegalArgumentException

/**
 * Проверяет является ли строка палиндромом
 */
fun String.isPalindrome(): Boolean {
    if (this.isEmpty()) {
        throw IllegalArgumentException("Can't determine if an empty string is a palindrome!")
    }
    return this.reversed() == this
}
