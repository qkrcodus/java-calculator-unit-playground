package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
            int result=stringCalculator.add(input);
            assertThat(result).as(", 또는 : 구분자를 사용한 계산").isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("커스텀 구분자 테스트")
    class CustomDelimiterTest {
        @Test
        @DisplayName("//;\n1;2;3 -> 6")
        void testCustomDelimiter() {
            int result=stringCalculator.add("//;\n1;2;3");
            assertThat(result).as("커스텀 구분자를 사용한 계산").isEqualTo(6);
        }
    }

    @Nested
    @DisplayName("잘못된 입력 테스트")
    class ExceptionTest {
        @Test
        @DisplayName("숫자가 아닌 토큰")
        void checkToken(){
            assertThatThrownBy(()->stringCalculator.add("1,token,2")).as("토근이 숫자가 아니면 RuntimeException").isInstanceOf(RuntimeException.class);
        }

        @ParameterizedTest(name = "음수 토큰")
        @CsvSource({
                "'1,-2,3'",
                "//;\n-1;3"
        }
        )
        void checkToken2(String input){
            assertThatThrownBy(()->stringCalculator.add(input)).as("음수 토근 입력시 RuntimeException").isInstanceOf(RuntimeException.class);
        }
    }

}
