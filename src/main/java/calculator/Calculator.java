package calculator;

import java.util.Arrays;
import java.util.regex.Pattern;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Calculator {
    public void calculate() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String str = readLine();
        int result = parseAndSum(str);
        System.out.println("결과 : " + result);
    }

    public int parseAndSum(String str) {
        if(str == null) {
            throw new IllegalArgumentException("string is null");
        }

        if(str.isEmpty()) {
            return 0;
        }

        if(str.startsWith("//")) {
            // "//"로 시작하는 경우
            int idx = str.indexOf("\\n");
            if (idx == -1) {
                throw new IllegalArgumentException("커스텀 구분자 설정은 \"//\" 로 시작하여 \"\\n\"로 끝나야 합니다.");
            }
            String delimiter = Pattern.quote(str.substring(2, idx));
            if(delimiter.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
            }
            if(idx == str.length()-1) {
                throw new IllegalArgumentException("계산할 숫자가 비어있습니다.");
            }
            String num = str.substring(idx + 2);

            return sum(delimiter, num);
        }
        else if ('0' <= str.charAt(0) &&  str.charAt(0) <= '9') {
            // 숫자로 시작하는 경우
            String delimiter = ",|:";
            return sum(delimiter, str);
        }

        throw new IllegalArgumentException("숫자나 \"//\"로 시작해야 합니다.");
    }

    public int sum(String delimiter, String nums){
        String[] numbers = nums.split(delimiter);
        int sum = 0;

        for(String num : numbers) {

            int n;
            try {
                n = Integer.parseInt(num);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("올바른 숫자 입력이 아닙니다.");
            }
            if (n < 0) throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            sum += n;


        }
        return sum;
    }
}
