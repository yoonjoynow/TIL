## 3주차 스터디 : 연산자
### 학습 목표(필수)
- 산술 연산자
- 비트 연산자
- 관계 연산자
- 논리 연산자
- instanceof 연산자
- assignment(=) operator
- 화살표(->) 연산자
- 3항 연산자
- 연산자 우선 순위
- (optional) Java 13. switch 연산자

___
___
## 1. 연산자(Operator)
연산자는 프로그래밍에서 연산을 하는데 사용되는 기호입니다. 연산시엔 연산자와 피연산자가 필요합니다. a + b를 연산한다고 하면 연산자는 '+', 연산의 대상이 되는 피연산자는 'a'와 'b'입니다. 연산이 실행되면 연산의 결과인 반환값이 생깁니다. 개발자는 이 반환값을 적절히 사용해야 합니다. 그럼 각 연산자 종류를 알아보겠습니다.

### 1-1. 산술 연산자
산술연산자는 사칙연산을 수행할 때 사용됩니다.
|연산자|예시|설명|
|:--:|:--:|:--:|
|+|10 + 2|더하기|
|-|4 - 1|빼기|
|*|3 * 10|곱하기|
|/|7 / 2|나누기|
|%|5 % 2|나머지|

더하기, 빼기 곱하기는 우리가 알던 그대로입니다. 산술 연산자에서 주의할 것은 나누기와 나머지를 구분해야 한다는 것입니다. 나누기 연산자는 나눗셈을 실행한 후의 몫을 결과값으로 반환하고 나머지는 버립니다. 반대로 나머지 연산자는 나눗셈 결과의 몫을 버리고 나머지만 반환합니다. 산술 연산자를 숙달하지 않을경우 상당히 헷갈리니 숙달해 놓아햐 합니다.

```java
public class MyClass {

    public static void main(String[] args) {
        System.out.println(10 + 2);
        System.out.println(4 - 1);
        System.out.println(3 * 10);
        System.out.println(7 / 2);
        System.out.println(5 % 2);
    }
}
```

### 1-2. 증감 연산자
증감 연산자는 변수값을  1씩 증가 혹은 감소시킬 때 사용합니다. 증감 연산자는 연산자의 위치에 따라 연산이 실행되는 시점이 달라집니다.

|연산자|예시|설명|
|:--:|:--:|:--:|
|++|++a|1 증가하기(전위)|
||b++|1 증가하기(후위)|
|--|--c|1 감소하기(전위)|
||d--|1 감소하기(후위)|

증가 연산자가 변수의 앞에 존재하면 **전위(prefix)**, 뒤에 위치하면 **후위(postfix)** 라고 부릅니다. 전위 증가 연산과 후위 증가 연산 모두 변수의 값을 1씩 증가시키는 데에는 동일하지만, 전위 연산은 해당 변수가 참조되기 전에 값을 미리 증가시키고, 후위 연산은 값이 참조된 다음에 증가시키는 차이가 있습니다. 코드로 확인해 보겠습니다.

```java
public class MyClass {

    public static void main(String[] args) {
        int a = 10;
        int b = 4;

        System.out.println(++a);
        System.out.println(b++);
    }
}
```
위 코드의 실행결과는 11과 4입니다. 증가 연산자가 앞에 붙은 a는 값이 증가되어 출력되었고 b는 4가 그대로 출력되었습니다. 변수 a와 b 모두 ++연산자가 붙어있으므로 모두 참조된 상태입니다. 하지만 a는 ++가 변수 앞에 위치하므로 미리 a = a + 1 이 수행됩니다. 그래서 출력값이 11이 나온 것입니다.

하지만 b의 경우엔 ++가 변수 뒤에 위치하므로 b = b + 1 이 출력문이 끝난 이후에 수행됩니다. 그래서 b의 출력값은 4였으나, 출력문이 종료된 직후에는 b에는 값이 1 증가된 5가 할당되게 됩니다. 하나를 더 살펴보면,

```java
public class MyClass {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        ++a;
        b++;

        System.out.println(a);
        System.out.println(b);
    }
}
```

이렇게 된다면 a와 b의 출력값은 어떻게 될까요? a는 전위, b는 후위로 증감하였습니다. a는 전위이므로 ++연산자를 만나자마자 1이 늘어난 11이 되지만, b는 후위이므로 ++연산자가 뒤에 있어도 값이 10으로 그대로입니다. 하지만 다음 출력문에서 b를 출력할 때에는 1이 증가된 21이 출력됩니다.

|타입|설명|
|:-:|:-:|
|전위|값이 참조되기 전에 증가시킨다.|
|후위|값이 참조된 이후 증가시킨다.|

### 1-3. 비교 연산자(관계 연산자)
비교 연산자는 계산식의 결과가 참인지 거짓인지 판단할 때 사용하는 연산자입니다. 

#### 대소비교 연산자
|연산자|예시|설명|
|:-:|:-:|:-:|
|>|a > b|a가 b보다 큰 값인지 판단|
|<|a < b|a가 b보다 작은 값인지 판단|
|>=|a >= b|a가 b보다 크거나 같은 값인지 판단|
|<=|a <= b|a가 b보다 작거나 같은 값인지 판단|

#### 등가비교 연산자
|연산자|예시|설명|
|:-:|:-:|:-:|
|==|a == b|a와 b가 같은 값인지 판단|
|!=|a != b|a와 b가 다른 값인지 판단|

### 1-4. 논리 연산자
비교 연산자는 단 하나의 상황에 대해서만 논리적인 판단의 값을 얻을 수 있습니다. 두 가지 이상의 상황(a는 10보다 크거나 5보다는 작다)에 대해서 논리적인 판단이 필요할 땐 논리 연산자를 사용해야 합니다.

#### AND 연산자
AND 연산자는 &나 &&로 연결된 모든 조건식이 true일때만 최종 결과값이 true이며, 하나라도 false이면 결과값도 false입니다.

|연산자|예시|설명|
|:-:|:-:|:-:|
|&|height > 175 & weight < 65|키가 175보다 크고 몸무게는 65보다 작은지?|
|&&|height > 175 && weight < 65|키가 175보다 크고 몸무게는 65보다 작은지?|

```java
public class MyClass {

    public static void main(String[] args) {
        int height = 187;
        int weight = 70;

        System.out.println(height > 180 & weight < 80);
        System.out.println(height > 180 && weight < 80);
    }
}
```

위 코드의 출력값은 모두 true입니다. 둘 다 같은 AND 연산을 하는데 왜 따로 존재할까요? 

&는 조건식을 처음부터 끝까지 모두 실행합니다. 위에 식은 height > 180 조건식과 weight < 80 조건식이 모두 true입니다. 만약 height가 170이라고 한다면 첫번째 조건식은 false가 됩니다. AND 연산은 모든 조건식들의 결과값이 true일 때 true입니다. 하지만 이미 첫번째 조건식이 false이므로 이 연산은 최종적으로 false이게 됩니다. 하지만 $는 나머지 조건식을 전부 실행합니다. 하지만 &&의 경우 이미 첫번째 조건식이 false이므로 거기서 AND 조건식을 종료합니다.

#### OR 연산자
OR 연산자는 |나 ||로 연결된 모든 조건식 중에서 단 하나라도 true인 결과가 있다면 최종 결과값이 true인 연산자입니다.

|연산자|예시|설명|
|:-:|:-:|:-:|
|\||height > 160 \| weight < 80|키가 160보다 크거나 몸무게는 80보다 작은지?|
|\|\||height > 160 \|\| weight < 80|키가 160보다 크거나 몸무게는 80보다 작은지?|

```java
public class MyClass{

    public static void main(String[] args) {

        int height = 165;
        int weight = 90;

        System.out.println(height > 160 | weight < 80);
        System.out.println(height > 160 || weight < 80);
    }
}
```

위의 출력값도 모두 true입니다. |의 경우 height > 160가 true여도 뒤의 조건식을 실행하지만, ||의 경우 이미 첫번째 조건만으로 true를 만족하므로 OR 연산을 종료합니다.

## 1-5. instanceof 연산자
instanceof는 참조형 변수의 타입을 확인하는 연산자입니다. 형 변환 가능여부를 확인해 true, false값을 반환합니다. 주로 상속 관계에서 부모 객체인지 자식 객체인지 사용됩니다. 사용 방법은 다음과 같습니다.

> **참조 변수 instanceof 참조 타입**


```java
public class MyClass {

    public static void main(String[] args) {
        String text = "Hello";
        MyClass myClass = new MyClass();
        
        System.out.println(text instanceof String);
        System.out.println(myClass instanceof MyClass);
    }
}
```

String 타입의 text는 String이므로 true, myClass 또한 MyClass의 인스턴스이므로 true입니다. 상속관계에서는 a instanceof B가 true일 경우엔 (B) a 로 형변환이 가능합니다.

___

참고 : [처음 해보는 자바 프로그래밍](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=179702789), [자바의 정석](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=76083001)