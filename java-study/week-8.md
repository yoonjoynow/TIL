## 8주차 스터디
### 목표
자바의 인터페이스에 대해서 학습하세요.
### 학습할 것 (필수)
- 인터페이스 정의하는 방법
- 인터페이스 구현하는 방법
- 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
- 인터페이스 상속
- 인터페이스의 기본 메소드 (Default Method), 자바 8
- 인터페이스의 static 메소드, 자바 8
- 인터페이스의 private 메소드, 자바 9
___

##  1. 인터페이스란?
인터페이스란 어떤 두 사물이 마주치는 경계 지점에서 서로 상호작용할 수 있게 이어주는 방법이나 장치 등을 의미합니다. 예를 들어 우리가 버튼을 가지고 엘리베이터를 동작시킬 수 있고, 엑셀이나 핸들을 통해 자동차를 조작할 수 있습니다. 이 때 사용자는 엘리베이터나 자동차의 내부 구조를 몰라도 버튼, 엑셀, 핸들이라는 외부에 노출되어있는 **인터페이스**를 통해서 편리하게 다룰 수 있습니다. 인터페이스는 아래 3 가지의 특징을 가집니다.
- **인터페이스의 사용법만 익히면 사용 대상의 내부구조 동작 방식을 몰라도 쉽게 조작할 수 있다.** (운전자가 자동차의 내부 구조를 몰라도 운전할 수 있다)
- **인터페이스 자체는 변경하지 않고 단순히 내부 구성이나 작동 방식만을 변경하는 것은 인터페이스에 어떠한 영향도 미치지 않는다.**
(엑셀이 그대로라면, 자동차의 내부 부품이 바뀌어도 엑셀을 정상적으로 사용할 수 있다.)
- **대상이 변경되더라도 동일한 인터페이스를 제공하기만 하면 아무런 문제 없이 상호작용 할 수 있다.** (K3를 타다가 벤츠를 타더라도 핸들, 엑셀 등의 인터페이스는 동일하므로 운전 방법 또한 동일하다.)

<img width="500" alt="스크린샷 2021-09-26 오후 2 43 43" src="https://user-images.githubusercontent.com/80696862/134795219-68b1e1c5-c6ba-4965-b4ef-a71cd424e455.png">

객체지향 프로그래밍(**O**bject **O**riented **P**rograming)에서의 인터페이스는 객체가 해야할 **역할**을 정해둔 곳입니다. 그리고 이 역할을 수행하는 객체가 해당 인터페이스를 **구현**(implements)한다고 합니다. 이 인터페이스를 구현하는 각 객체들은 각자만의 방법으로(자율적으로) 역할에 대한 책임을 충실히 수행합니다. 이것을 **인터페이스와 구현의 분리**라고 합니다.

인터페이스는 **객체의 외부**로, 다른 객체와 상호작용할 때 사용되는 창구입니다. 반대로 인터페이스를 실제 구현하는 클래스가 **객체의 내부**이며, 해당 객체가 역할을 수행하는 방법을 메소드라고 부릅니다. 

객체가 다른 객체에 접근하는 방법을 메시지(message)라고 합니다. 메시지는 다른 객체에 접근할 수 있는 유일한 방법입니다. 인터페이스에 선언된 역할은 곧 해당 인터페이스를 구현하는 객체가 받을 수 있는 메시지의 목록과 같습니다.


객체지향 프로그래밍에서 객체들은 인터페이스를 통해 서로 **협력**합니다. 또한 인터페이스만 유지된다면 해당 객체의 내부 구조나 작동 방식을 변경하거나 다른 객체로 대체된다 하더라도 인터페이스를 사용하는 쪽에 영향을 미치지 않습니다. 역할만 수행할 수 있다면, 그 역할을 누가 수행하는지는 관심없습니다. 이렇게 인터페이스만 외부에 열어두고 실제 내부는 감추는 특징을 **캡슐화** 혹은 **정보의 은닉**라고 합니다.

<img width="660" alt="스크린샷 2021-09-26 오후 11 34 33" src="https://user-images.githubusercontent.com/80696862/134812276-b2d9856d-f7a0-4959-bf3f-d120289a6be2.png">


### 자바에서의 인터페이스는?
자바의 인터페이스는 일종의 추상 클래스이지만 추상 클래스보다 추상화 정도가 더 높아서 추상 클래스와 달리 멤버를 가질 수 없는 클래스입니다. 자바 8버전이 나오기 전까진 인터페이스는 추상 메소드와 상수만을 가질 수 있었습니다.

### 인터페이스를 정의하는 방법
인터페이스를 작성시 class 대신 interface 키워드를 사용합니다. 인터페이스의 접근제어자는 public과 default만 가능합니다. 인터페이스에는 역할들의 목록을 적어놓으면 됩니다.

**인터페이스 선언시 제약사항**
- 인터페이스는 상수만 가질 수 있으므로 public static final 생략 가능
- 인터페이스의 모든 메소드는 추상메소드이므로 public abstarct 생략 가능
- 단, JDK1.8부터 추가된 static 메소드와 default 메소드는 예외

```java
public interface Runnable {

    int MAX_SPEED = 200;
    int MINIMUM_SPEED = 0;

    void accelerate(int speed); //가속하다

    void decelerate(int speed); //감속하다

}
```

### 인터페이스를 구현하는 방법
인터페이스를 구현하는 방법은 클래스이름 **implements** 인터페이스이름입니다. 인터페이스도 추상 클래스처럼 인스턴스화될 수 없으며, 인터페이스를 구현하는 클래스도 인터페이스의 모든 추상 메소드를 오버라이딩해야 합니다.

```java
public class Car implements Runnable {

    private int currentSpeed;

    @Override
    public void accelerate(int speed) {
        validSpeed(speed);
        int result = speedUp(speed);
        System.out.println("현재 속도 = " + result);
    }

    @Override
    public void decelerate(int speed) {
        validSpeed(speed);
        int result = speedDown(speed);
        System.out.println("현재 속도 = " + result);
    }

    private void validSpeed(int speed) {
        if (! isValidSpeed(speed)) {
            throw new RuntimeException();
        }
    }

    private boolean isValidSpeed(int speed) {
        int result = currentSpeed + speed;
        return result <= MAX_SPEED && result >= MINIMUM_SPEED;
    }

    private int speedUp(int speed) {
        return currentSpeed += speed;
    }

    private int speedDown(int speed) {
        return currentSpeed -= speed;
    }

}
```

만약 인터페이스의 일부 추상 메소드만 구현한다면, 해당 클래스는 추상 클래스로 선언해야 합니다. 아직 오버라이딩하지 않은 추상 메소드는 추후 해당 추상 클래스를 상속하는 클래스가 구현해야합니다.
```java
public abstract class Car implements Runnable {

    private int currentSpeed;

    @Override
    public void accelerate(int speed) {
        //로직
    }

}
```

### 인터페이스의 상속
#### 인터페이스 다중 상속
상속과는 다르게 인터페이스는 다중 상속을 지원합니다. 인터페이스는 인터페이스를 여러 번 상속할 수 있습니다.

```java
public interface Car extends Runnable {

}
```

상속하는 인터페이스가 여러 개일 경우 콤마(,)를 이용해 구분합니다.

```java
public interface Car extends Runnable, Automatical {
    // Car 인터페이스는 Runnable과 Automatical의 역할을 포함합니다
}
```

#### 상속과 구현을 동시에
자바에서 클래스는 어떤 클래스를 상속함과 동시에 다른 인터페이스를 구현할 수도 있습니다. 예시는 기아의 전기차인 EV6 모델입니다.

**단 순서는 클래스 상속 이후에 구현입니다.**

```java
public class Ev6 extends KiaCar implements ElectricVehicle {

}

public class KiaCar implements Car {

}
```

#### 다중 상속의 문제점
만약 한 클래스가 두 개의 인터페이스를 구현하는데, 각 인터페이스에 동일한 메소드 시그니처가 존재한다면 어떻게 될까요?

```java
public interface Radio {
    void turnOn();
}

public interface Television {
    void turnOn();
}
```

메소드 시그니처가 동일한 경우엔 컴파일러가 오버라이딩을 강제해서 문제를 해결합니다.

```java
public class MyClass implements Radio, Television {

    @Override
    public void turnOn() {
        System.out.println("전원을 켭니다");
    }
}
```

### 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
자식 클래스를 부모 클래스 타입으로 받을 수 있던 것처럼, 인터페이스의 구현체도 해당 인터페이스 타입으로 받을 수 있습니다. 이것을 **다형성**이라고 합니다.

```java
public interface Car extends Runnable {

}

public abstract class KiaCar implements Car {

}

public class Ev6 extends KiaCar {

}
```

```java
Runnable a = new Ev6();
Car b = new Ev6();
KiaCar c = new Ev6();
Ev6 d = new Ev6();
```

위처럼 Ev6 클래스는 KiaCar, Car, Runnable 타입으로 받을 수 있습니다.

인터페이스 구현체는 반드시 인터페이스 타입으로 받아야 한다

## 2. 자바 8 이후의 인터페이스
JDK1.8 버전 이후 자바는 큰 변화를 겪었습니다. 

### 인터페이스의 기본(default) 메소드
인터페이스는 추상 메소드만 갖는다는 특징이 있습니다. 버전업이 되면서 인터페이스에 추상 메소드가 하나씩 추가되자 해당 인터페이스 구현체 클래스들은 추가된 추상메소드를 오버라이딩하지 않았으므로 컴파일 에러가 여기저기 발생했습니다. 

또한, 해당 인터페이스의 구현체들이 동일한 방법으로 구현하는 추상 메소드도 빈번히 있었는데, 이 경우 구현체들이 모두 해당 추상 메소드를 구현해야하는 불편함이 생겼습니다.

이에, 자바 8버전 부터 인터페이스가 기본적으로 제공하는 디폴트 메소드가 도입되었습니다. 디폴트 메소드는 인터페이스 내부에서 구현된 메소드로, 해당 인터페이스 구현체들은 디폴트 메소드를 바로 사용할 수 있습니다.

```java
public interface Car {

    void run();

    default void showCurrentSpeed() {
        System.out.println
    }
}


```



### 함수형 인터페이스

### 인터페이스의 static 메소드
static 메소드는 디폴트 메소드와 마찬가지로 인터페이스 내부에서 이미 구현된 메소드이지만, 구현체에서 오버라이딩할 수 없는 특징이 있습니다.

### 인터페이스의 private 메소드
자바 9부터는 인터페이스에 private 메소드를 구현할 수 있게되었습니다.

**자바 9 이후 인터페이스에서 사용 가능한 멤버**
- 상수
- 추상 메소드
- default 메소드
- static 메소드
- private 메소드
- private static 메소드

### 추상 클래스가 필요한가요?
is-a, has a

## 3. 인터페이스의 안티 패턴
상수 모음


참고 : [자바의 정석](http://www.yes24.com/Product/Goods/24259565?OzSrank=2), [객체지향의 사실과 오해](http://www.yes24.com/Product/Goods/18249021?OzSrank=1), [더 자바, Java 8](https://www.inflearn.com/course/the-java-java8/dashboard), [모던 자바 인 액션](http://www.yes24.com/Product/Goods/77125987?OzSrank=1)

<img width="250" alt="스크린샷 2021-09-26 오후 8 27 16" src="https://user-images.githubusercontent.com/80696862/134805715-5a2f2a89-0d73-4036-982e-9e1aa8c6628f.png">