package Calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {
    private StringCalculator stringCalculator=new StringCalculator();
    
    @Nested
    @DisplayName(", 또는 : 구분자 테스트")
    class DefaultDelimiterTest {
        @ParameterizedTest
        @CsvSource({
                "'',0",
                "'1',1",
                "'1,2',3",
                "'1,2:3',6"
        })
        void testDefaultDelimiter(String input, int expected) {
            assertEquals(expected,stringCalculator.add(input));
        }
    }

    @Nested
    @DisplayName("커스텀 구분자 테스트")
    class CustomDelimiterTest {
        @Test
        @DisplayName("//;\n1;2;3 -> 6")
        void testCustomDelimiter() {
            assertEquals(6,stringCalculator.add("//;\n1;2;3"));
        }
    }

    @Nested
    @DisplayName("잘못된 입력 테스트")
    class ExceptionTest {
        @Test
        @DisplayName("숫자가 아닌 토큰")
        void checkToken(){
            assertThrows(RuntimeException.class,()->stringCalculator.add("1,token,2"));
        }

        @ParameterizedTest(name = "음수 토큰")
        @CsvSource({
                "'1,-2,3'",
                "//;\n-1;3"
        }
        )
        void checkToken2(String input){
            assertThrows(RuntimeException.class,()->stringCalculator.add(input));
        }
    }

}
