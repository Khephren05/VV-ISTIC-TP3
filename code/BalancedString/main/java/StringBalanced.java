public class StringBalanced {

    public StringBalanced(){}

    /** isBalanced checks if the input string has matching brackets
     * @param str string to test
     * @return :
     *  - true if all [,{,( symbols have their matching ],},) symbols
     *  - false if not
     *  - NullPointerException if str is null */
    public static boolean isBalanced(String str) {
        str = str.replaceAll("([^\\{\\[\\(\\)\\]\\}])", "");
        while ( str.contains("()") ||
                str.contains("[]") ||
                str.contains("{}") ){
            str = str.replaceAll("(\\(\\))|(\\[\\])|(\\{\\})", "");
        }
        return (str.length() == 0);
    }
}