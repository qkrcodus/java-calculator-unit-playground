package Calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BasicCalculatorTest {
    private BasicCalculator basicCalculator=new BasicCalculator();

    @ParameterizedTest
    @CsvSource({
            "2, +, 3, 5",
            "5, -, 2, 3",
            "4, *, 2, 8",
            "9, /, 3, 3"
    })
    @DisplayName("사칙 연산")
    void test_calculate(int a, String op, int b, int expected) {
        int result = calculate(a, op, b);
        assertThat(result).as("사칙 연산 테스트").isEqualTo(expected);
    }

    @Test
    @DisplayName("0으로 나눌 수 없음")
    void divideByZero() {
      assertThatThrownBy(()->basicCalculator.divide(1,0)).as("0으로 나누면 IllegalArgumentException").isInstanceOf(IllegalArgumentException.class);
    }

    private int calculate(int a, String op, int b) {
        return switch (op) {
            case "+" -> basicCalculator.add(a, b);
            case "-" -> basicCalculator.subtract(a, b);
            case "*" -> basicCalculator.multiply(a, b);
            case "/" -> basicCalculator.divide(a, b);
            default -> throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + op);
        };
    }
}
