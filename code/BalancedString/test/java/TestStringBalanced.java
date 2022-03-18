import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class TestStringBalanced {

    /** Test on isBalanced functions with correct strings
     *  Init : corrects strings
     *  Expected : isBalanced return true. */
    @ParameterizedTest
    @ValueSource(strings = {
            "{}[]()",
            "{\\t(e)s(t)*[{abc}]}",
            "([{}])",
            "(as[ae{bvf}*]o()\\f)",
            "",
            "()",
            "[]",
            "{}"
    })
    @DisplayName("test isBalanced with correct strings")
    void testIsBalancedCorrectStrings(String str) {
        assertTrue(StringBalanced.isBalanced(str));
    }

    /** Test on isBalanced functions with incorrect strings
     *  Init : wrongs strings.
     *  Expected : isBalanced return false or Exception.*/
    @ParameterizedTest
    @ValueSource(strings = {
            "([)]",
            "m(o[ashgr)ki]",
            "][",
            "aa]ef[bfe",
            "()]",
            "(test)**]"
    })
    @DisplayName("test isBalanced with wrong strings")
    void testIsBalancedWrongStrings(String str) {
        assertFalse(StringBalanced.isBalanced(str));
    }

    /** Test on isBalanced functions with a null string
     *  Init : null string
     *  Expected : isBalanced return Exception.*/
    @Test
    @DisplayName("test isBalanced with null string")
    void testIsBalancedNullString() {
        assertThrows(NullPointerException.class, () -> {
            StringBalanced.isBalanced(null);
        });
    }
}