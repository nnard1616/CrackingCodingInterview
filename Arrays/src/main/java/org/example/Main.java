package org.example;

import org.apache.commons.lang3.StringUtils;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static final int ENCODED_CHAR_LENGTH = 3;
    private static final int CHAR_LENGTH = 1;
    private static boolean PERMUTATION_EXISTS = false;

    public static void main(String[] args) {
        System.out.println(oneOrZeroEditsAway("pale", "ple") == true);
        System.out.println(oneOrZeroEditsAway("ales", "pales") == true);
        System.out.println(oneOrZeroEditsAway("pales", "pale") == true);
        System.out.println(oneOrZeroEditsAway("pale", "bale") == true);
        System.out.println(oneOrZeroEditsAway("pale", "bake") == false);
    }


    private static char[] urlify(char[] url, int actualLength) {
        char[] result = new char[url.length]; //Assumption: url has sufficient capacity to hold %20 for each space

        int result_i = 0;

        for (int i = 0 ; i < actualLength; i++) {

            if (url[i] == ' ') {
                result[result_i] = '%';
                result[result_i+1] = '2';
                result[result_i+2] = '0';
                result_i += ENCODED_CHAR_LENGTH;
            } else {
                result[result_i] = url[i];
                result_i += CHAR_LENGTH;
            }
        }


        return result;
    }

    private static boolean hasPalindromePermutation(String query) {
        PERMUTATION_EXISTS = false;
        iteratePermutations(StringUtils.lowerCase(query.replace(" ", "")), "");
        return PERMUTATION_EXISTS;
    }

    private static void iteratePermutations(String remainingLetters, String prefix) {

        if (PERMUTATION_EXISTS) {
            return;
        }

        if (remainingLetters.equals("")) {
            if (isPalindrome(prefix)) {
                PERMUTATION_EXISTS = true;
                return;
            }
        }

        for (int i = 0 ; i < remainingLetters.length(); i++) {
            iteratePermutations(
                    remainingLetters.substring(0, i) + remainingLetters.substring(i+1),
                    prefix + remainingLetters.charAt(i)
            );
        }
    }

    private static boolean isPalindrome(String query) {
        String queryReversed = StringUtils.reverse(query);
        return query.equals(queryReversed);
    }

    /**
     * Given two strings, check if they are one or zero edits away from being equivalent.
     *
     * Assumptions:
     * Edits include: addition of chars, deletion of chars, replacement of chars
     * Addition vs deletion are essentially the same (complimentary), so  only need to determine one.
     *
     *
     * @param query1
     * @param query2
     * @return
     */
    private static boolean oneOrZeroEditsAway(String query1, String query2) {
        boolean result = false;

        if (zeroEditsAway(query1,query2)) {
            result = true;
        }

        if (oneEditAway(query1, query2)) {
            result = true;
        }

        return result;
    }

    /**
     * Determines whether the two strings are 1 edit away from being equivalent
     * @param query1
     * @param query2
     * @return
     */
    private static boolean oneEditAway(String query1, String query2) {
        boolean result = false;

        if (oneAddAway(query1, query2)) {
            result = true;
        }

        if (oneAddAway(query2, query1)) {
            result = true;
        }

        if (oneReplaceAway(query1, query2)) {
            result = true;
        }

        return result;
    }

    /**
     * Determines if the two strings are one replacement away from being equivalent
     * O(N)
     * @param query1
     * @param query2
     * @return
     */
    private static boolean oneReplaceAway(String query1, String query2) {
        boolean result = false;

        if (query1.length() == query2.length()) {
            int differentCharCount = 0;
            for (int i = 0 ; i < query1.length(); i++) {
                if (query1.charAt(i) != query2.charAt(i)) {
                    differentCharCount++;
                }
            }
            if (differentCharCount == 1) {
                result = true;
            }
        }

        return result;
    }

    /**
     * Determines if the two strings are one add away from being equivalent.
     * O(N)
     * @param longerQuery
     * @param shorterQuery
     * @return
     */
    private static boolean oneAddAway(String longerQuery, String shorterQuery) {
        boolean result = false;

        if (longerQuery.length() - shorterQuery.length() == 1) {
            int diffcount = 0;
            int j = 0;
            for (int i = 0; i < longerQuery.length(); i++) {
                if (j < shorterQuery.length()
                        && longerQuery.charAt(i) == shorterQuery.charAt(j)) {
                    j++;
                } else {
                    diffcount++;
                }
                if (diffcount > 1) {
                    break;
                }
            }
            if (diffcount == 1) {
                result = true;
            }
        }

        return result;
    }

    /**
     * Determines whether the two strings are zero edits away from being equivalent.
     * O(N)
     *
     * @param query1
     * @param query2
     * @return
     */
    private static boolean zeroEditsAway(String query1, String query2) {
        return query1.equals(query2);
    }
}