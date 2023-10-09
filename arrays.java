import com.sun.tools.javac.util.StringUtils;

public class arrays {

	private static final int ENCODED_CHAR_LENGTH = 3;
	private static final int CHAR_LENGTH = 1;
	private static boolean PERMUTATION_EXISTS = false;

	public static void main(String[] args) {
		System.out.println(hasPalindromePermutation("Tact Coa"));
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
		iteratePermutations(StringUtils.lower(query), "");
		return PERMUTATION_EXISTS;
	}

	private static void iteratePermutations(String remainingLetters, String prefix) {
		if (PERMUTATION_EXISTS) {
			return;
		}

		if (remainingLetters.equals("")) {
			if (isPalindrome(remainingLetters)) {
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
}
