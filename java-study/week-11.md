## 11주차 스터디
### 목표
자바의 열거형에 대해 학습하세요
### 학습할 것 (필수)
- enum 정의하는 방법
- enum이 제공하는 메소드 (values()와 valueOf())
- java.lang.Enum
- EnumSet

# 1. Enum (열거형)이란?
열거형이란 프로그래밍 언어에서 명명된 상수들을 한 곳에 모아둔 일종의 자료형입니다. 자바에서는 JDK 1.5버전부터 열거형 타입(enum)으로서 지원되고 있습니다.

서로 연관이 있는, 카테고리 성격성의, 그룹으로써 관리해야하는 값들을 상수로 만들어 한 곳에 모아둔 자료형을 바로 열거형이라고 합니다.

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

**하지만 위와 같이 클래스를 단순히 상수 모음집 용도로 쓰는 방법은 객체지향적이지 못하며, 여러 가지의 문제점들도 갖고 있습니다.**

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

위 식의 결과는 참입니다. FruitGroup.APPLE과 Company.APPLE의 리터럴은 둘 다 1로 동일하기 때문입니다. 하지만 이것은 우리가 의도하는 것이 아닙니다. 실제 할당받은 값은 같더라도, 우리가 과일 Apple과 기업 Apple을 다르다고 인식하는 것 처럼, 위 식의 결과도 false가 나와야합니다. 하지만 그렇지 않습니다. 

또한 printFruitName 메소드는 파라미터로 int 타입 하나를 받으므로, 인자로 열겨형 클래스에 모아둔 상수가 아닌 그 어떤 정수라도 전달할 수 있습니다. 이것 또한 우리가 의도한 것이 아닙니다. 

개발자는  이러한 상황을 **type-safety하지 않다**라고 말합니다.

```java
printFruitName(-1); // 컴파일 통과
```

#### No namespace
FruitGroup과 Company그룹의 상수 중 APPLE은 중복됩니다.

#### Brittleness
정적 상수 변수는 컴파일 타임 상수(Static)이므로 상수의 값이 바뀌면 해당 상수를 참조하는 모든 소스를 재컴파일해야 합니다.

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
    ORDERED, READY, DELIVERED, CANCLED
}
```

## 1-2. enum(열거형)의 특징
- **enum에 정의된 상수들은 해당 enum 타입의 객체이다**
    - enum 안에 정의된 상수들은 해당 enum 타입의 참조변수이다
    - 상수명이나 값이 같아도 참조값이 다르므로 == 비교시 컴파일 에러가 발생하지 않는다
- **IDE의 여러가지 지원을 받을 수 있다**
    - 자동완성, 오타검증, 텍스트 리팩토링 등
    - 허용 가능한 값을 제한할 수 있다
- **리팩토링시 변경 범위가 최소화된다**
    - 상수 변경시, enum 안에서만 변경이 이루어진다
- **문맥(Context)를 담을 수 있다.**
    - 과일의 Apple과 회사 Apple은 동음이의어다
    - 철자는 같아도 문맥에 따라 이를 구별해야 한다
    - 문자열은 이를 구분할 수 없지만, enum은 가능하다
    - enum은 타입으로 관리되기 때문이다
- **재컴파일하지 않아도 된다**
    - 상수가 변경되어도 해당 상수를 참조하는 쪽의 소스를 재컴파일하지 않아도 된다
- **열거형 상수 간 == 로 비교 가능하다**
    - equals( )를 사용하는 것 보다 조금 더 빠르다
    - 하지만 이외의 비교 연산자는 사용할 수 없다
    - compareTo( )로도 비교 가능하다
- **다른 어떠한 클래스도 상속할 수 없다**
    - 정확히 말하면 Enum 클래스 이외에 상속받을 수 있는 클래스가 없다

## 1-3. enum의 사용
상품 주문 상태를 표현하는 DeliveryStatus 열거형과 주문 서비스를 처리하는 DeliveryService 클래스가 있습니다. enum은 필드로 선언될 수 있습니다. 이후 메소드를 통해 필드값을 대입할 수 있습니다.

```java
public enum DeliveryStatus {
    ORDERED, READY, DELIVERED
}

public class DeliveryService {
    DeliveryStatus deliveryStatus;

    public void changeStatus(DeliveryStatus status) {
        this.deliveryStatus = status;
    }

}
```

# 2. enum이 제공하는 메소드
enum 선언시 사용할 수 메소드는 아래와 같습니다.

|메소드 명|설명|
|--|--|
|Class\<E> getDeclaringClass( )|해당 열거형의 Class 타입 객체를 반환한다|
|int ordinal( )|해당 열거형 상수의 순서를 반환한다 (0부터 시작)|
|String name( )|해당 열거형 상수의 이름을 리턴한다|
|T valueOf(Class<T> enumType, String name)|지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다|

### Class\<E> getDeclaringClass( )
해당 열거형 상수의 Class 타입 객체를 반환한다

모든 클래스는 해당 클래스 타입의 Class 객체가 생성됩니다.

```java
public enum DeliveryStatus {
    ORDERED, READY, DELIVERED, CANCELED
}

public class EnumTest {

    public static void main(String[] args) {
        Class<DeliveryStatus> declaringClass = DeliveryStatus.READY.getDeclaringClass();
    }
}
```

### int ordinal( )
해당 열거형 상수의 순서를 반환한다

enum 클래스에 상수를 선언하면, 상수가 선언된 순서대로 각자 인덱스 값을 가지고 있습니다. 배열의 인덱스처럼 0번부터 시작합니다. 메소드의 사용 결과는 아래와 같습니다.

```java
public class EnumTest {

    public static void main(String[] args) {
        int ordinal1 = DeliveryStatus.READY.ordinal();
        int ordinal2 = DeliveryStatus.CANCELED.ordinal();

        System.out.println(orinal1); // 1
        System.out.println(orinal2); // 3
    }
}
```

하지만 우리 개발자는 이 메소드를 사용하는 것을 피해야 합니다. 만약 enum 클래스의 상수가 추가되었다고 생각해봅시다.

**기존 비행기 좌석 등급**
```java
public enum SeatLevel {
    ECONOMY, FIRST
}

System.out.println("ECONOMY : " + SeatLevel.ECONOMY.ordinal()); // 0
System.out.println("FIRST : " + SeatLevel.FIRST.ordinal()); // 1
```

출력 결과는 각각 0과 1입니다. 그리고 BUSINESS 클래스가 사이에 추가되었다고 가정해 봅시다.

**변경된 비행기 좌석 등급**
```java
public enum SeatLevel {
    ECONOMY, BUSINESS, FIRST
}

System.out.println("ECONOMY : " + SeatLevel.ECONOMY.ordinal()); // 0
System.out.println("FIRST : " + SeatLevel.FIRST.ordinal()); // 1
System.out.println("BUSINESS : " + SeatLevel.BUSINESS.ordinal()); // 2
```

BUSINESS 클래스가 사이에 생겼으므로 FIRST 클래스는 1칸 밀린 2가 출력됩니다. 이처럼 ordinal()은 언제든 상수의 위치가 변경되면 결과가 바뀌게 됩니다. 따라서 이 ordinal( )을 사용하는 코드는 위험합니다. 이 메소드는 EnumSet과 EnumMap과 같은 정교한 enum 기반 데이터 구조에 사용되는 메소드라는 것을 알아둡시다.

### String name( )
해당 enum의 상수명을 반환합니다.

```java
public class EnumTest {

    public static void main(String[] args) {
        String name = SeatLevel.FIRST.name();
        System.out.println(name); // FIRST
    }
}
```

### T valueOf(Class enumType, String name)
인자로 전달한 enum 타입의 Class 객체와 name과 일치하는 enum 상수를 반환합니다.

```java
public class EnumTest {

    public static void main(String[] args) {
        SeatLevel seatLevel = Enum.valueOf(SeatLevel.class, "BUSINESS");

        System.out.println(seatLevel); // BUSINESS
    }
}
```

# 3. java.lang.Enum 클래스
자바에서 enum 안에 상수만 만들어 놓아도 여러가지 메소드를 사용할 수 있습니다. 그 이유는 자바에서 모든 enum은 java.lang.Enum 클래스를 상속하기 때문입니다. **사실, enum도 클래스입니다.** 단지 선언시에 class 키워드 대신 enum 키워드를 사용하는 특수한 형태의 클래스인 것 뿐입니다.

<img width="600" alt="스크린샷 2021-10-12 오후 8 29 54" src="https://user-images.githubusercontent.com/80696862/136948043-dd84e579-2b66-45b4-90bf-b9bc58fd5471.png">

### 유일한 생성자

<img width="580" alt="스크린샷 2021-10-12 오후 8 30 58" src="https://user-images.githubusercontent.com/80696862/136948201-ea9ae23e-6069-4bc6-8227-fe8466e91726.png">

Enum 클래스의 생성자는 1 개가 있고 개발자는 이 생성자를 호출할 수 없습니다. enum 클래스를 선언하면 컴파일러가 해당 enum을 Enum으로부터 상속시킬 때 이 생성자가 사용됩니다. 따라서 우리가 신경쓸 것은 없습니다.

### enum의 구현
그렇다면 enum 타입 클래스는 어떻게 생겼을까요? 비행기 좌석 등급을 나타내는 SeatLevel enum 클래스의 바이트 코드는 아래와 같습니다.

```java
public enum SeatLevel {
    ECONOMY, BUSINESS, FIRST
}

// 바이트코드
// class version 60.0 (60)
// access flags 0x4031
// signature Ljava/lang/Enum<Lme/yoon/study/SeatLevel;>;
// declaration: me/yoon/study/SeatLevel extends java.lang.Enum<me.yoon.study.SeatLevel>
public final enum me/yoon/study/SeatLevel extends java/lang/Enum {

    // compiled from: SeatLevel.java

    // access flags 0x4019
    public final static enum Lme/yoon/study/SeatLevel; ECONOMY

    // access flags 0x4019
    public final static enum Lme/yoon/study/SeatLevel; BUSINESS

    // access flags 0x4019
    public final static enum Lme/yoon/study/SeatLevel; FIRST

    // access flags 0x101A
    private final static synthetic [Lme/yoon/study/SeatLevel; $VALUES

    // access flags 0x9
    public static values()[Lme/yoon/study/SeatLevel;
     L0
      LINENUMBER 3 L0
      GETSTATIC me/yoon/study/SeatLevel.$VALUES : [Lme/yoon/study/SeatLevel;
      INVOKEVIRTUAL [Lme/yoon/study/SeatLevel;.clone ()Ljava/lang/Object;
      CHECKCAST [Lme/yoon/study/SeatLevel;
      ARETURN
      MAXSTACK = 1
      MAXLOCALS = 0

    // access flags 0x9
    public static valueOf(Ljava/lang/String;)Lme/yoon/study/SeatLevel;
      // parameter mandated  name
     L0
      LINENUMBER 3 L0
      LDC Lme/yoon/study/SeatLevel;.class
      ALOAD 0
      INVOKESTATIC java/lang/Enum.valueOf (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;
      CHECKCAST me/yoon/study/SeatLevel
      ARETURN
    L1
      LOCALVARIABLE name Ljava/lang/String; L0 L1 0
      MAXSTACK = 2
      MAXLOCALS = 1

    // access flags 0x2
    // signature ()V
    // declaration: void <init>()
    private <init>(Ljava/lang/String;I)V
      // parameter synthetic  $enum$name
      // parameter synthetic  $enum$ordinal
     L0
      LINENUMBER 3 L0
      ALOAD 0
      ALOAD 1
      ILOAD 2
      INVOKESPECIAL java/lang/Enum.<init> (Ljava/lang/String;I)V
      RETURN
    L1
     LOCALVARIABLE this Lme/yoon/study/SeatLevel; L0 L1 0
     MAXSTACK = 3
     MAXLOCALS = 3

    // access flags 0x100A
    private static synthetic $values()[Lme/yoon/study/SeatLevel;
     L0
      LINENUMBER 3 L0
      ICONST_3
      ANEWARRAY me/yoon/study/SeatLevel
      DUP
      ICONST_0
      GETSTATIC me/yoon/algorithstudymstudy/SeatLevel.ECONOMY : Lme/yoon/algoristudythmstudy/SeatLevel;
      AASTORE
      DUP
      ICONST_1
      GETSTATIC me/yoon/study/SeatLevel.BUSINESS : Lme/yoon/study/SeatLevel;
      AASTORE
      DUP
      ICONST_2
      GETSTATIC me/yoon/study/SeatLevel.FIRST : Lme/yoon/study/SeatLevel;
      AASTORE
      ARETURN
      MAXSTACK = 4
      MAXLOCALS = 0

    // access flags 0x8
    static <clinit>()V
     L0
      LINENUMBER 4 L0
      NEW me/yoon/study/SeatLevel
      DUP
      LDC "ECONOMY"
      ICONST_0
      INVOKESPECIAL me/yoon/study/SeatLevel.<init> (Ljava/lang/String;I)V
      PUTSTATIC me/yoon/study/SeatLevel.ECONOMY : Lme/yoon/study/SeatLevel;
      NEW me/yoon/study/SeatLevel
      DUP
      LDC "BUSINESS"
      ICONST_1
      INVOKESPECIAL me/yoon/study/SeatLevel.<init> (Ljava/lang/String;I)V
      PUTSTATIC me/yoon/study/SeatLevel.BUSINESS : Lme/yoon/study/SeatLevel;
      NEW me/yoon/study/SeatLevel
      DUP
      LDC "FIRST"
      ICONST_2
      INVOKESPECIAL me/yoon/study/SeatLevel.<init> (Ljava/lang/String;I)V
      PUTSTATIC me/yoon/study/SeatLevel.FIRST : Lme/yoon/study/SeatLevel;
     L1
      LINENUMBER 3 L1
      INVOKESTATIC me/yoon/study/SeatLevel.$values ()[Lme/yoon/study/SeatLevel;
      PUTSTATIC me/yoon/study/SeatLevel.$VALUES : [Lme/yoon/study/SeatLevel;
      RETURN
      MAXSTACK = 4
      MAXLOCALS = 0
}
```

위 코드에서 알 수 있는것처럼, enum 클래스인 SeatLevel에 인스턴스 변수로 각각 SeatLevel 타입의 상수들이 선언되어 있습니다.

여기서 알 수 있는 것을 정리해보겠습니다.
- **우리가 만든 enum은 클래스이며 java.lang.Enum 클래스를 상속한다**
- **우리가 선언한 상수들은 모두 SeatLevel 타입의 참조 변수다**
- **values( )와 valueOf(String name) 메소드가 추가되어 있다**
- **우리가 만든 enum 클래스의 생성자의 접근제어자가 private이다**

모든 enum 클래스가 Enum을 상속한다는 것은 위에서 언급했으니, 나머지 세 특징을 살펴보겠습니다.

### 상수들은 모두 SeatLevel 타입의 참조 변수이다
SeatLevel는 클래스이므로 JVM의 Method 영역에 로딩됩니다. 그리고 SeatLevel 클래스 내부에 선언되어 있는 상수들은 모두 SeatLevel 타입의 참조 변수로서 JVM Heap 영역에 로딩됩니다.


### values( )와 valueOf(String name) 메소드가 추가되어 있다
values( )와 valueOf(String name) 메소드는 우리가 만든 것도, Enum으로부터 상속받은 것도 아닌 메소드입니다. 이 두 메소드는 컴파일러가 컴파일 시 자동으로 바이트코드에 추가시켜주는 메소드입니다.

*상속받은 valueOf(Class enumType, String name)와는 다릅니다*

|메소드 명|설명|
|--|--|
|T[ ] values( )|해당 열거형에 선언된 상수들을 배열로 반환한다|
|T valueOf(String name)|해당 열거형에서 name과 일치하는 상수를 반환한다|

### enum의 생성자의 접근제어자가 private이다

```java
// access flags 0x2
// signature ()V
// declaration: void <init>()
private <init>(Ljava/lang/String;I)V
```

우리가 정의한 enum 클래스들은 모두 java.lang.Enum 클래스를 상속합니다. 이 때 Enum 클래스의 유일한 생성자는 protected 접근제어자를 가져서 enum 클래스가 생성시 호출됩니다. 앞서 정의한 SeatLevel 클래스의 계층도는 아래와 같습니다.

<img width="550" alt="스크린샷 2021-10-13 오후 11 47 32" src="https://user-images.githubusercontent.com/80696862/137157203-6fe4284f-609a-45c1-8360-815557f8edd2.png">

그리고 SeatLevel의 바이트코드를 보면 생성자의 접근제어자가 private인 것을 확인할 수 있습니다. 즉 모든 enum 클래스는 **싱글톤**입니다.

### 열거형 특징 정리

# 4. Enum 구현해보기

# 5. EnumSet과 EnumMap

___

참고 : [자바의 정석 3판](http://www.yes24.com/Product/Goods/24259565?OzSrank=2), [enum in Java](https://www.geeksforgeeks.org/enum-in-java/)