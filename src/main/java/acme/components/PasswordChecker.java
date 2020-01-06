
package acme.components;

public class PasswordChecker {

	public Boolean PasswordCheck(final String password, final int minLength, final int letters, final int digits, final int symbols) {

		Boolean result;

		result = password.length() >= minLength;
		result = result && password.replaceAll("^[A-Za-z]", "").length() >= letters;
		result = result && password.replaceAll("\\D", "").length() >= digits;
		result = result && password.replaceAll("[^.,:;!?]", "").length() >= symbols;

		return result;

	}

}
