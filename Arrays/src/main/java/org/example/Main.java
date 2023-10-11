package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static final int ENCODED_CHAR_LENGTH = 3;
    private static final int CHAR_LENGTH = 1;
    private static boolean PERMUTATION_EXISTS = false;

    public static void main(String[] args) {
//        System.out.println(Arrays.deepToString(rotate90cw(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}})));
        System.out.println(Arrays.deepToString(zeroExplode(new int[][]{{1, 2, 0, 4, 5}, {0, 7, 8, 9, 10}, {11, 12, 13, 14, 0}, {16, 17, 18, 19, 20}})));
    }

    /**
     * Given an M by N matrix, if any element is 0, then this shall return a matrix where each columnn and row
     * that had a 0 in it is transformd to 0's
     *
     * @param matrix
     * @return
     */
    private static int[][] zeroExplode(int[][] matrix) {
        int X = matrix.length;
        int Y = matrix[0].length;

        Set<Integer> is = new HashSet<>();
        Set<Integer> js = new HashSet<>();

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (matrix[i][j] == 0) {
                    is.add(i);
                    js.add(j);
                }
            }
        }

        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (is.contains(i) || js.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }

        return matrix;
    }

    /**
     * Takes a N by N matrix and returns it rotated by 90 degrees clockwise
     *
     * Assumptions:
     * rotating by 90 clockwise
     * matrix is square
     *
     * @param ints
     * @return
     */
    private static int[][] rotate90cw(int[][] ints) {
        int N = ints.length;
        int[][] result = new int[N][N];
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = ints[N-j-1][i];
            }
        }
        return result;
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

    /**
     * given a string, reduce to a compressed string that prints each  character
     * and its number of repeitions in sequence in the original string.
     *
     * If the compressed string is not shorter than the original string, return the original string.
     *
     * Assumptions: only upper case and lower case english characters.
     * lower case letters are distinct from upper case letters
     *
     * @param input
     * @return
     */
    private static String compress(String input) {
        StringBuilder result = new StringBuilder();

        char currentCharacter = input.charAt(0);
        int currentCharacterCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == currentCharacter) {
                currentCharacterCount++;
            } else {
                result.append(currentCharacter).append(currentCharacterCount);
                currentCharacter = input.charAt(i);
                currentCharacterCount = 1;
            }
        }

        result.append(currentCharacter).append(currentCharacterCount);

        if (result.length() < input.length()) {
            return result.toString();
        } else {
            return input;
        }
    }
}
