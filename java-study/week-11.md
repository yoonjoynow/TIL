## 11주차 스터디
### 목표
자바의 열거형에 대해 학습하세요
### 학습할 것 (필수)
- enum 정의하는 방법
- enum이 제공하는 메소드 (values()와 valueOf())
- java.lang.Enum
- EnumSet

# 1. Enum (열거형)이란?
열거형이란 프로그래밍 언어에서 명명된 상수들을 한 곳에 모아둔 일종의 자료형입니다. 자바에서는 JDK 1.5버전부터 열거형 타입(enum)으로서지원되고 있습니다.

서로 연관이 있는, 카테고리 성격 등을 갖고있는 상수들을 한 곳에 모아둔 자료형을 바로 열거형이라고 합니다.

## 1.1 JDK 1.5 이전 열거형을 사용하던 방식

```java
public class App {

    public static void main(String[] args) {
        App app = new App();
        app.printFruitName(2); // 메소드 사용 의도가 불명확
    }

    public void printFruitName(int fruit) {
        switch (fruit) {
            case 1 -> System.out.println("사과 입니다");
            case 2 -> System.out.println("바나나 입니다");
            case 3 -> System.out.println("포도 입니다");
            default -> throw new IllegalArgumentException("존재하지 않는 과일입니다");
        }
    }
}
```

과일의 종류를 출력해주는 printFruitName(int) 메소드가 있습니다. 우리가 이 메소드를 사용하기 위해서는 각 case에 해당하는 int 타입의 인자를 전달해야 합니다.

```java
app.printFruitName(2);
```

위 코드로는 printFruitName 메소드의 구현부를 확인하지 않고는 도저히 메소드 사용 의도를 명확히 알기 어렵습니다. 그래서 예전 자바 버전에서는 서로 관련있고 의미있는 상수들을 한 클래스에 모아서 사용했습니다. 아래 방법은 enum 타입이 나오기 전까지 사용되었습니다.

```java
import static me.yoon.algorithmstudy.FruitGroup.*;

public class FruitGroup {

    public static final int APPLE = 1;
    public static final int BANANA = 2;
    public static final int GRAPE = 3;
}

public class App {

    public static void main(String[] args) {
        App app = new App();
        app.printFruitName(FruitGroup.BANANA); // 바나나 입니다 출력
    }

    public void printFruitName(int fruit) {
        swith (fruit) {
            case APPLE -> System.out.println("애사과플 입니다");
            case BANANA -> System.out.println("바나나 입니다");
            case GRAPE -> System.out.println("포도 입니다");
            default -> throw new IllegalArgumentException("존재하지 않는 과일입니다");
        }
    }
}
```

```java
app.printFruitName(FruitGroup.BANANA);
```

JDK 1.5버전 이전에는 자바에서 열거형을 흉내내기 위한 표준 방식이 int Enum 패턴이었습니다.

한 클래스에 상수를 담아놓고, 클래스명으로 바로 상수에 접근해서 인자로 전달할 수 있기 때문에, 이전보다 메소드 사용 의도가 좀 더 명확해 집니다.

또한, 정적 상수 변수의 값을 수정하더라도 printFruitName 메소드를 호출하는 쪽에서는 인자로 리터럴이 아닌 이 상수 변수를 전달하므로 이 상수 변수를 참조한 쪽을 변경할 필요가 없으며, 정적 상수 변수들을 담고있는 클래스에서만 변경이 일어나기 때문에 유지보수성에서도 좋습니다.

**하지만 위와 같이 클래스를 객체가 아닌 단순히 상수 모음집 용도로 쓰는 방법은 객체지향적이지 못하며, 여러 가지의 문제점들도 갖고 있습니다.**

- **Not typesafe(타입 불안전한)**
- **No namespace**
- **Brittleness**
- **Printed values are uninformative**

#### Not type safe
두 개의 열거형 그룹이 있다고 가정해 봅시다.

```java
public class FruitGroup {
    public static final int APPLE = 1;
    public static final int BANANA = 2;
    public static final int GRAPE = 3;
}

public class CompanyGroup {
    public static final int APPLE = 1;
    public static final int AWS = 2;
    public static final int MICROSOFT = 3;
}
```

그리고 아래의 식의 결과는 무엇일까요?
```java
System.out.println(FruitGroup.APPLE == CompanyGroup.APPLE);
```

위 식의 결과는 참입니다. FruitGroup.APPLE과 Company.APPLE의 리터럴은 둘 다 1로 동일하기 때문입니다. 하지만 이것은 우리가 의도하는 것이 아닙니다. 실제 할당받은 값은 같더라도, 우리가 과일 Apple과 기업 Apple을 다르다고 인식하는 것 처럼, 위 식의 결과도 false가 나와야합니다. 하지만 그렇지 못합니다. 

또한 printFruitName 메소드는 파라미터로 int 타입 하나를 받으므로, 인자로 열겨형 클래스에 모아둔 상수가 아닌 그 어떤 정수라도 전달할 수 있습니다. 이것 또한 우리가 의도한 것이 아닙니다. 

개발자는  이러한 상황을 **type-safety하지 않다**라고 말합니다.

```java
printFruitName(-1); // 컴파일 통과
```

#### No namespace
FruitGroup과 Company그룹의 상수 중 APPLE은 중복됩니다.

#### Brittleness

#### Printed values are uninformative
한 클래스에 모아둔 상수들을 출력하면 정수가 출력됩니다. 하지만 우리는 이 숫자를 보고 무엇을 의미하는지, 어떤 타입인지 알 수 없습니다.

## 1.2 현재의 열거형을 사용하는 방식
JDK 1.5버전부터 자바에서 **열거형 타입(enum)을 공식적으로 지원**하고 있습니다. 자바에서 지원하는 enum은 다른 언어의 열거형보다 더 강력합니다.
- enum 키워드로 클래스를 정의함
- 예전의 단점을 모두 해결
- enum 타입에 어떤 메소드와 필드를 추가하고 인터페이스를 구현하는 등의 작업도 가능
- enum은 Object 클래스의 우수한 메소드를 제공함

```java
public enum DeliveryStatus {
    ORDERED,
    READY,
    DELIVERED;
}
```

## 1-2. 열거형의 특징

# 2. Enum이 제공하는 메소드
enum에서 제공하는 메소드는 아래와 같다

|메소드 명|설명|
|--|--|
|int ordinal|해당 열거형 상수의 순서를 반환한다|
|String name()|해당 열거형 상수의 이름을 리턴한다|
|T[ ] values()|df|
|T valueOf(Class<T> enumType, String name|지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다|


# 3. java.lang.Enum 클래스

# 4. EnumSet과 EnumMap

___

참고 : [자바의 정석 3판](http://www.yes24.com/Product/Goods/24259565?OzSrank=2), [enum in Java](https://www.geeksforgeeks.org/enum-in-java/)