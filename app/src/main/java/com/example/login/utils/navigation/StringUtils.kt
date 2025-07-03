package com.example.login.utils.navigation

object StringUtils {

    data class ValidationResult(
        val isValid: Boolean,
        val errors: List<String>
    )

    fun isValidateCredentials(username: String, confirmPassword: String, password: String): ValidationResult {

        val errors = mutableListOf<String>()

        // Username validation
        if (username.length !in 8..16) {
            errors.add("Username must be between 8 and 16 characters.")
        }

        // Password validation
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+=-]).{8,}$")
        if (!passwordRegex.matches(password)) {
            errors.add("Password must be at least 8 characters long, contain 1 uppercase letter, 1 number, and 1 special character.")
        }

        // Confirm password
        if (password != confirmPassword) {
            errors.add("Passwords do not match.")
        }

        return ValidationResult(errors.isEmpty(), errors)
    }
}
