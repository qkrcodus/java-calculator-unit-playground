package Calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String input){
        int sum=0;

        // 빈 문자열
        if(input==null || input.isEmpty()){
            return 0;
        }

        String delimiters= "[,:]";

        // 커스텀 구분자
        if(input.startsWith("//")){
            int idx=input.indexOf("\n");
            String customDelimiter=input.substring(2,idx);
            delimiters = Pattern.quote(customDelimiter) + "|" + delimiters;
            input=input.substring(idx+1);
        }

        String[] tokens=input.split(delimiters);
        for(String token:tokens){
            // 숫자 이외 혹은 빈 토큰 검사
            if (!token.matches("\\d+")) {
                throw new RuntimeException("Invalid number: " + token);
            }
            int num = Integer.parseInt(token);
            // 음수 검사
            if (num < 0) {
                throw new RuntimeException("Negative number: " + num);
            }
            sum += num;
        }
        return sum;
    }
}
