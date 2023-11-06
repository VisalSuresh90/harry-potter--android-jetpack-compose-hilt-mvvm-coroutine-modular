package com.visal.harrypotter.ui.common.util

import java.util.Locale

class StringUtil {
    companion object {

        /**
         * Converts a given input string into Upper CamelCase format.
         *
         * @param input The input string to be converted.
         * @return The input string in Upper CamelCase format.
         */
        fun toUpperCamelCase(input: String): String {
            // Split the input string by spaces, underscores, and hyphens, and process each word
            val words = input.split(" ", "_", "-")
                .map { it.trim() }
                .filter { it.isNotBlank() }
            return words.joinToString(" ") { it.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } }
        }


        /**
         * Joins a list of strings into a single string with commas as separators.
         *
         * @param list The list of strings to be joined.
         * @return The resulting string with comma-separated values.
         */
        fun joinListWithCommas(list: List<String>): String {
            return list.joinToString(", ")
        }
    }
}
