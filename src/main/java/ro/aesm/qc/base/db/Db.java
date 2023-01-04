package ro.aesm.qc.base.db;

import java.util.ArrayList;
import java.util.List;

public class Db {

	/**
	 * Translate an SQL statement with named parameters to plain JDBC statement
	 * using `?` for parameter placeholder. Return a list where the first item is
	 * the parsed statement and the rest of the elements are the named parameters
	 * found in their order of occurrence.
	 * 
	 * @param sql
	 * @return
	 */
	public static List<String> translateSql(String sql) {
		List<String> paramNames = new ArrayList<String>();
		int length = sql.length();
		StringBuffer parsedQuery = new StringBuffer(length);
		boolean inSingleQuote = false;
		boolean inDoubleQuote = false;
		boolean inSingleLineComment = false;
		boolean inMultiLineComment = false;

		for (int i = 0; i < length; i++) {
			char c = sql.charAt(i);
			if (inSingleQuote) {
				if (c == '\'') {
					inSingleQuote = false;
				}
			} else if (inDoubleQuote) {
				if (c == '"') {
					inDoubleQuote = false;
				}
			} else if (inMultiLineComment) {
				if (c == '*' && sql.charAt(i + 1) == '/') {
					inMultiLineComment = false;
				}
			} else if (inSingleLineComment) {
				if (c == '\n') {
					inSingleLineComment = false;
				}
			} else if (c == '\'') {
				inSingleQuote = true;
			} else if (c == '"') {
				inDoubleQuote = true;
			} else if (c == '/' && sql.charAt(i + 1) == '*') {
				inMultiLineComment = true;
			} else if (c == '-' && sql.charAt(i + 1) == '-') {
				inSingleLineComment = true;
			} else if (c == ':' && i + 1 < length && Character.isJavaIdentifierStart(sql.charAt(i + 1))) {
				int j = i + 2;
				while (j < length && Character.isJavaIdentifierPart(sql.charAt(j))) {
					j++;
				}
				String name = sql.substring(i + 1, j);
				paramNames.add(name);
				c = '?';
				i += name.length();
			}
			parsedQuery.append(c);
		}
		paramNames.add(0, parsedQuery.toString());
		return paramNames;
	}

}
