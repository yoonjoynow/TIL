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

#### JDK 1.5 이전 열거형을 사용하던 방식

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
            case 3 -> System.out.println("포토 입니다");
            default -> throw new IllegalArgumentException("존재하지 않는 과일입니다");
        }
    }
}
```

int 타입의 

```java
public class FruitEnum {

    public static final int APPLE = 1;
    public static final int BANANA = 2;
    public static final int GRAPE = 3;
}

public class App {

    public static void main(String[] args) {
        App app = new App();
        app.printFruitName(FruitEnum.BANANA); // 바나나 입니다 출력
    }

    public void printFruitName(int fruit) {
        swith (fruit) {
            case 1 -> System.out.println("사과 입니다");
            case 2 -> System.out.println("바나나 입니다");
            case 3 -> System.out.println("포토 입니다");
            default -> throw new IllegalArgumentException("존재하지 않는 과일입니다");
        }
    }
}
```

자바에서 공식적으로 열거형 타입을 지원하기 이전에는 위처럼 하나의 클래스에 상수들을 모아두는 방식으로 열거형을 대체했었습니다.

type-safety (타입 안정성)

```java
public enum DeliveryStatus {
    ORDERED,
    READY,
    DELIVERED;
}
```

## TODO 인터페이스 안티패턴 언급할 것 : 이펙티브 자바

#### 현재의 열거형을 사용하는 방식

## 1-1. 왜 열거형을 쓰는가?

## 1-2. 열거형의 특징

# 2. Enum이 제공하는 메소드

# 3. java.lang.Enum 클래스

# 4. EnumSet과 EnumMap

___

참고 : [자바의 정석 3판](http://www.yes24.com/Product/Goods/24259565?OzSrank=2), [enum in Java](https://www.geeksforgeeks.org/enum-in-java/)