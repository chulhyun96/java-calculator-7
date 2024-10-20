# java-calculator-precourse

## 요구 사항
입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

1. 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
    - 예시 :
        - 입력 : "" => 출력 : 0
        - 입력 : "1,2" => 출력 : 3
        - 입력 : "1,2,3" => 출력 : 6
        - 입력 : "1,2:3" => 출력 : 6

2. 커스텀 구분자를 지정할 수 있다.
    - 커스텀 구분자는 "//"와 "\\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
    - 예시 :
        - 입력 : "//;\n1;2;3" => 출력 : 6

3. 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.
    - 음수를 입력하였거나, 숫자가 아닌 경우 예외 발생

4. 사용자가 잘못된 값을 입력하였지만 종료되지 않는 경우
    - 입력값이 없는 경우 (공백일 경우) 0을 반환.
    - 하나의 숫자만 입력하였을 경우 해당 숫자를 그대로 반환.
___

## 구현할 기능 목록
- [x] 입력이 빈 문자열일 경우 0을 반환한다.
- [x] 단일 문자 입력 시 값을 그대로 반환한다. 
- [x] 쉼표와 콜론 구분자를 이용하여 숫자를 추출하고 합하여 반환한다.
- [x] 커스텀 구분자를 통해 숫자 추출 및 합할 수 있도록 한다. 조건은 (`//`와 `\\n` 사이에 위치하는 문자를 커스텀 구분자로 사용한다.)
- [x] 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException` 발생 후 애플리케이션을 종료시킨다.(음수 입력 or 숫자가 아닌 경우)

