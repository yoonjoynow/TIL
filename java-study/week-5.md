## 5주차 스터디
### 목표
자바의 클래스에 대해서 학습하세요.
### 학습할 것 (필수)
- 클래스를 정의하는 방법
- 객체를 만드는 방법(new 키워드 이해하기)
- 메소드 정의하는 방법
- 생성자 정의하는 방법
- this 키워드 이해하기
### 과제
- int 값을 가지고 있는 이진 트리를 나타내는 Node 라는 클래스를 정의하세요.
- int value, Node left, right를 가지고 있어야 합니다.
- BinrayTree라는 클래스를 정의하고 주어진 노드를 기준으로 출력하는 bfs(Node node)와 dfs(Node node) 메소드를 구현하세요.
- DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요.
___
___

## 1. 클래스란?
클래스란 객체를 생성할 때 사용되며, 객체의 형태를 정의해놓은 설계도라고 이해하면 쉽습니다. 여기서 객체란 무엇일까요?

객체는 소프트웨어 세계에서 어떠한 역할을 갖고 그 역할을 수행하는 개념적인 존재입니다. 맥도날드 매장에 가 보면 버거를 주문하러 온 사람, 주문을 받는 캐셔, 감자튀김을 만드는 사람, 버거를 만드는 사람 등 여러 역할을 하는 사람들이 서로 협력해서 맥도날드 세계 혹은 시스템을 이루는 것처럼, 소프트웨어 세계에서도 이러한 역할들을 맡아서 수행하는 존재가 바로 객체입니다. 그리고 자바에서 이 객체를 어떻게 만들 것이고 어떠한 행동을 하고 어떠한 상태를 가질 수 있는지 정의되어 있는 곳이 바로 클래스입니다. 여기서 행동과 상태를 각각 메소드와 필드라고 부릅니다.

### 1-1. 클래스의 구조
- **필드(Field)**
    객체의 상태에 대한 정보를 저장하는 공간(변수)입니다. 객체의 상태는 행동(메소드)의 결과입니다. 필드에는 클래스 변수, 인스턴스 변수, 지역 변수 총 3 종류의 변수가 선언될 수 있습니다.
    - **클래스 변수**
    클래스 변수는 인스턴스 변수 앞에 static 키워드가 붙은 변수입니다. 클래스가 로딩될 때 클래스 수준의 정보로 JVM 메모리의 메소드 영역에 저장되며, 이 클래스의 인스턴스들이 공통적으로 사용할 변수를 클래스 변수로 선언합니다. 클래스 변수는 인스턴스 생성없이 사용할 수 있고, 프로그램이 종료시까지 메모리에 유지됩니다.
    - **인스턴스 변수**
    인스턴스 변수는 클래스가 인스턴스화될 때 heap 영역에 저장되며, 인스턴스마다 서로 다른 값을 가지고 있습니다. 클래스 변수처럼 공유할 수 없으며, GC에 의해 해당 인스턴스가 소멸될 때 함께 heap 메모리에서 제거됩니다.
    - **멤버 변수**
    멤버 변수는 클래스 변수와 인스턴스 변수 모두를 통칭합니다.
    - **지역 변수**
    지역변수는 메소드 블럭 내부나 생성자 내부 등 클래스 영역 이외의 공간에 선언된 변수입니다. 해당 블럭이 실행될 때 생성되고, 프로그램 실행 지점이 블럭 외부로 나가면 소멸됩니다.
- **메소드(Method)**
    객체의 행동 방식에 대해 정의해 놓은 것으로, 여러 코드 문장들로 이루어져 있습니다. 이 행동의 결과를 변수에 저장하기도 합니다.
- **생성자(Constructor)**
    인스턴스를 생성할 때 호출되는 것으로 객체의 초기 상태를 설정할 때 사용합니다. 메소드와 형태는 유사하지만 반환 타입이 없으므로 메소드라고 부를 순 없습니다.
- **초기화 블럭(Initialize Block)**

### 1-2. 클래스를 정의하는 방법
기본적으로 클래스를 정의하는 방법은 다음과 같습니다.

>**접근제한자 클래스명 {
}**

```java 
public class MyClass {

}
```

MyClass라는 클래스를 정의했지만 껍데기만 만들었을 뿐 이 클래스의 객체가 어떠한 행동과 상태를 갖는지 정의해주지 않았습니다. 따라서 클래스에 적절한 행동과 상태를 정의해 주어야 합니다. 아까 예시로 들었던 맥도날드의 고객을 한번 클래스로 정의해보겠습니다.

```java
public class Customer {
    
    int money = 25_000;

    public void buy(int pay) {
        int result = 25_000 - pay;
        if (result < 0)
            System.out.println("잔돈이 부족합니다.")
            return; // 메소드 실행 종료
        
        money = result;
    }
}
```
Customer라는 클래스에는 객체가 어떤 역할을 하고 어떤 상태를 갖는지 정의되어 있습니다. 아주 간단히 버거를 주문하는 buy 메소드와 주문의 결과인 money 인스턴스 변수가 있습니다. 이 인스턴스가 어떤 메뉴를 주문하느냐에 따라 상태값인 money의 값이 변할 것입니다. 이번엔 객체를 만드는 방법을 알아보겠습니다.

___

## 2. 객체를 만드는 방법 (new 키워드 이해하기)
자바에서 객체를 만드는 것을 **인스턴스화**라고 부르며, 인스턴스화된 객체를 **인스턴스**라고 부릅니다. 그리고 이 인스턴스를 **해당 클래스의 타입**이라고 부릅니다. 인스턴스는 **new 키워드를 통해서 생성**되며, 클래스에 정의된 **생성자를 통해서 초기화**됩니다. 인스턴스를 만드는 방법은 간단합니다.
```java
Customer customer = new Customer();
int change = customer.buy(7000);
```

#### 클래스와 인스턴스는 대체 무슨 차이?
위에서 클래스는 설계도라고 했습니다. 그리고 인스턴스는 이 설계도를 바탕으로 실제로 생산된 양산품 정도로 생각하면 됩니다. 클래스 안에는 한개의 생성자가 있을 수 있고 두개 이상의 생성자가 있을 수 있습니다. new 키워드 뒤에 오는 것이 생성자인데, 이 생성자의 인자로 어떤 것이 들어가느냐에 따라서 호출되는 생성자가 다릅니다. 

클래스는 처음 클래스가 로딩될 때 JVM의 메소드 영역에 올라가며, 인스턴스는 new 키워드를 통해 인스턴스화되었을 때 heap 영역에 올라갑니다.

___

## 3. 메소드 정의하는 방법
메소드는 어떠한 기능을 하는 블럭 내 코드들의 집합이며, 수학에서의 함수와 비슷합니다. 인풋값인 인자를 받아서 아웃풋값으로 객체의 상태를 변경할 수 있습니다. 

메소드는 메소드 시그니처라는 메소드의 선언부를 보고 어떤 값을 넣으면 어떤 값을 반환받는지 알 수 있습니다. 그래서 메소드 시그니처에 정의된 반환 타입, 메소드 명, 파라미터 리스트를 통해 이 메소드 내부를 자세히 확인하지 않고도 이 메소드를 어떻게 사용해야하는지 유추할 수 있습니다.

### 3-1. 메소드의 장점
1. 높은 재사용성(reusability)
우리가 이미 만들어진 자바 API를 활용하듯이, 기능을 한 번 만들어놓고 언제든지 재사용할 수 있다.
2. 코드 중복 제거
같은 기능을 하는 코드 문장들을 다시 작성할 필요 없이 만들어놓은 메소드를 호출해서 사용한다.
3. 프로그램 구조화

### 3-2. 메소드의 선언과 구현
<img width="374" alt="스크린샷 2021-09-17 오후 12 14 02" src="https://user-images.githubusercontent.com/80696862/133718541-2109fc23-6f34-47c7-bac2-5fc285ba3fc4.png">

#### 메소드 선언부

메소드의 선언부는 다음과 같이 이루어져있습니다.
- **접근제어자(접근제한자)**
메소드의 외부 접근 허용 범위를 명시하는 곳입니다. 생략시 default 접근제한자가 자동으로 붙습니다.
- **반환타입**
메소드의 실행 결과로 어떤 타입의 값이 반환되는지 명시하는 곳입니다. 따로 반환할 데이터가 없는 단순 실행용 메소드일 경우, 데이터 타입 대신 void를 명시하면 됩니다.
- **메소드명**
메소드 이름은 동사형으로 짓는 것이 관례이고 lowerCamelCase를 따르며 메소드 이름이 명확해야 합니다. 
- **파라미터 리스트**
해당 메소드에 어떤 데이터들이 재료로 들어오는지 명시하는 곳입니다. 필요한 파라미터가 없다면 소괄호 내부를 비우면 됩니다.
- **메소드 시그니처**
 컴파일러는 메서드 시그니처를 보고 오버로딩(overloading)을 구별한다. 물론 리스트의 순서도 동일해야 한다.

 #### 메소드 구현부
메소드 구현부는 메소드 블럭 내부의 공간을 의미합니다. 반환 타입이 void가 아닌 경우 return과 함께 반환타입과 일치하거나 반환타입으로 형 변환이 가능한 변수를 적어야 합니다. 반환타입이 void라면 return문은 생략할 수 있습니다.

 #### 접근 제어자란?
 접근 제어자란 접근 제어자 키워드가 붙어있는 클래스, 변수, 메소드를 해당 키워드가 허용하는 범위 내에서만 접근할 수 있도록 제한하는 역할을 합니다.
 |접근제한자|동일 클래스|동일 패키지|자식 클래스|그 외의 영역|
 |:--:|:--:|:--:|:--:|:--:|
 |public|O|O|O|O|
 |protected|O|O|O|X|
 (default)|O|O|X|X|
 |private|O|X|X|X|

 #### 접근 제어자를 사용하는 이유?
 다시 한번 맥도날드 매장을 생각해 봅시다. 맥도날드 매장의 재료들의 재고량은 매장 직원들, 좀 더 제한하면 버거를 만드는 사람만 알고 있으면 됩니다. 맥도날드에서 접근 제어자가 없다면 매장의 재고에 버거를 만드는 역할과 관련이 없는 사람들이 손댈 수 있다는 뜻입니다. 반대로 고객의 지갑에 매장 직원이 임의로 손댈 수도 있습니다. 자바에서 접근 제어자의 목적은 이렇게 **외부에서 데이터가 변질되는 것을 막거나 내부적으로만 사용되는 부분을 의도적으로 감추기 위해서 사용**합니다.

```java
public class Patty {
    public int stock = 10;
}
```

패티 클래스의 변수 재고의 값은 10입니다. 재고 변수의 접근 제어자는 public으로 누구나 접근이 가능한 상태입니다.

```java
public class Customer {
    
    private int cash = 100_000;

    public int pay(int money) {
        int result = cash - money;
        if (result < 0) {
            throw new NotEnoughCashException();
        }

        Patty patty  = new Patty();
        patty.stock = 0;

        return result;
    }
}
```

위의 고객 클래스를 보면, pay라는 가격을 지불하는 메소드 내부에서 임의적으로 patty의 재고에 손을 댄 것을 볼 수 있습니다. 그리고 변수로 바로 접근했기 때문에 그 의도를 알 수 없습니다. 이러한 상황을 막고자 접근 제어자를 사용하며, 필요시 접근 의도를 알 수 있는 메소드를 통해서 접근해야 합니다. 그리고 접근 제어자는 변수 뿐만이 아니라 클래스와 메소드에도 사용할 수 있습니다. 패티 클래스는 아래처럼 바꿀 수 있습니다.

```java
public class Patty {
    private int stock = 10;

    public int getStock() {
        return stock;
    }

    public void plusStock(int amount) {
        stock += amount;
    }

    public void useStock(int usage) {
        int result = stock - usage;
        if (result < 0) {
            throw new 
        }

        stock = result;
    }

}


public class BurgerMaker {

    private String name = "James";

    public void makeBurger() {
        Patty patty = new Patty();
        if (patty.getStock() > 0) {
            System.out.println("버거 제조 완료");
            patty.useStock(1);
        }
    }

}
```

버거를 만드는 직원은 버거를 만들 때 패티의 재고량을 확인하기 위해 getStock이라는 의도가 명확한 메소드를 사용합니다.

### 3-3. 메소드의 종류
변수에서 그랬던과 같이 메소드에도 static이 붙을 수 있습니다. static이 붙어있으면 클래스 메소드, 아니면 인스턴스 메소드라고 부릅니다.

#### 클래스 메소드(static 메소드)
인스턴스와는 관계없는, 즉 **인스턴스 변수나 인스턴스 메소드를 사용하지 않는 메소드**를 클래스 메소드라고 정의합니다.

#### 인스턴스 메소드
인스턴스 변수와 관련된 작업을 하는, 즉 **메소드의 작업을 수행하는데 인스턴스 변수가 사용되는 메소드**를 인스턴스 메소드라고 정의합니다.

### 3-4. 메소드 오버로딩(overloading)
오버로딩이란 같은 이름으로 여러개의 메소드를 선언할 수 있는 것을 말합니다. 메소드의 시그니처가 다르다면 얼마든지 오버로딩을 할 수 있습니다. 즉 다음과 같은 조건을 만족해야 합니다.

> **1. 메소드 이름이 동일해야 합니다.
> 2. 파라미터의 개수 혹은 타입이 달라야 합니다.**

컴파일러는 메소드 시크니처를 보고 오버로딩을 구분합니다. 이는 생성자에서도 해당됩니다. 오버로딩의 예를 살펴보겠습니다.

```java
public class MyClass {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    int add(int a, int b, int c, int d) {
        return a + b + c + d;
    }
}
```

위처럼 메소드명은 add로 동일하지만 파라미터 리스트가 다르므로 오버로딩을 사용할 수 있습니다. 오버로딩을 통해 이렇게 메소드명을 재활용할 수 있습니다.

#### 가변인자(varargs)와 오버로딩
위 add 메소드의 오버로딩의 단점이 무엇일까요? 정수를 더해주는 add메소드의 파라미터가 늘어날수록 새 메소드를 작성해주어야 합니다. 개발자의 이러한 반복노동을 줄여주기 위해 JDK 1.5 버전부터는 같은 타입의 파라미터가 늘어날 경우 이를 동적으로 만들어줄 수 있는데, 이를 가변인자라고 합니다. 사용 방법은 아래와 같습니다.

```java
public int add(int... numbers) {
    int result = 0;
    for (int number : numbers) {
        result += number;
    }
    return result;
}
```

가변인자들은 가변인자명으로 해당 타입의 배열로 생성됩니다. 이 배열을 루프를 돌려서 더한 후 리턴할 수 있습니다.

> **주의사항 : 가변인자는 항상 파라미터 리스트의 맨 마지막에 와야 합니다. 컴파일러는 파라미터 리스트의 맨 마지막에서만 가변인자를 구분할 수 있습니다.**
___

## 4. 생성자 정의하는 방법
생성자를 정의하는 방법은 메소드와 유사하지만 생성자는 메소드가 아니기 때문에 반환 타입을 명시하지 않습니다. 
>**접근제한자 클래스명(){
}**

기본적으로 위와 같은 형태를 갖는 생성자를 기본 생성자라고 부릅니다. 위의 Customer 클래스에서는 생성자를 정의하지 않았지만, 정의된 생성자가 하나도 없을 경우 컴파일러는 기본 생성자를 자동으로 만들어줍니다. 따라서 컴파일된 클래스 파일을 디컴파일해보면 기본 생성자가 만들어져있는 것을 확인할 수 있습니다. 

하지만 기본 생성자가 아닌 인자를 갖는 생성자를 작성하면, 기본 생성자는 자동으로 생기지 않습니다. 이 땐 기본 생성자를 명시해주어야 합니다.

생성자 또한 메소드처럼 오버로딩할 수 있습니다. 
```java
public class Hamburger {
    private String name; //this.name
    private int price; // this.price
    private boolean sale; //this.sale

    public Hamburger() {

    }

    public Hamburger(String name, int price, boolean sale) {
        this.name = name;
        this.price = price;
        this.sale = sale;
    }
}
```

Hamburger 클래스에는 인자가 하나도 없는 기본 생성자가 3개의 인자를 갖는 생성자가 있습니다. 인자가 없는 기본 생성자를 통해 Hamburger 인스턴스를 만드는 경우는 모든 필드의 값은 기본값들이 들어있을 것입니다. 하지만 모든 필드를 인자로 받는 생성자를 통해 인스턴스를 만드는 경우엔 인스턴스의 변수들의 값엔 인자들의 값이 들어가있을 것입니다. 여기서 처음 보는 것이 this 입니다. this는 무엇일까요?

## 5. this 키워드 이해하기
this 키워드는 인스턴스 자기 자신을 호출하는 키워드입니다. this를 통해 해당 인스턴스의 참조값을 획득할 수 있습니다. this 키워드는 3가지 형태로 사용됩니다.

#### 멤버 변수와 매개변수의 이름이 같을 때

```java
public class Hamburger {
    private String name; //this.name
    private int price; // this.price
    private boolean sale; //this.sale

    public Hamburger() {

    }

    public Hamburger(String name, int price, boolean sale) {
        this.name = name;
        this.price = price;
        this.sale = sale;
    }

}
```

위 코드에서 Hamberger 생성자의 파라미터 명과 인스턴스 변수 명이 모두 일치합니다. 이 때 변수명을 동일하게 사용하기 위해 this 키워드를 사용하는데, **this.변수명**은 인스턴스 변수를 가르키고 파라미터의 변수명은 **지역변수**를 가르킵니다. 따라서 this.name = name 코드의 의미는 인자로 받은 name을 인스턴스 변수 name에 할당한다는 뜻입니다.

#### 생성자에서 다른 생성자를 호출할 때

```java
public class Hamburger {
    private String name;
    private int price;
    private boolean sale;

    public Hamburger() {
        this("big mac", 5500, true);
    }

    public Hamberger(String name) {
        this(name, 6800, true);
    }

    public Hamburger(String name, int price, boolean sale) {
        this.name = name;
        this.price = price;
        this.sale = sale;
    }
}
```

한 생성자에서 오버로딩된 다른 생성자를 호출해서 변수를 초기화하는 부분을 축약할 수 있습니다. 이 때, this 키워드는 항상 생성자 블럭 내부의 첫번째 줄에 와야합니다. 그렇지 않으면 컴파일 에러가 발생합니다.


#### 자기 자신의 참조값을 반환할 때

```java
public class Patty {
    int stock = 100;

    public Patty getReference() {
        return this;
    }

    public static void main(String[] args) {
        Patty patty = new Patty();
        Patty reference = parry.getReference();
        System.out.println(patty == reference); //true
    }
}
```

위처럼 자기 자신의 참조값을 반환할 때 사용하기도 합니다.

> **this - 인스턴스 자기 자신을 가르키는 참조변수의 주소가 저장돼 있음
this(), this(매개변수) - 동일 클래스 내부의 다른 생성자를 호출할 때 사용**
___

## 6. 변수의 초기화
멤버변수 (클래스 변수, 인스턴스 변수)는 초기화하지 않아도 자동으로 기본값으로 초기화됩니다. 하지만 지역변수는 반드시 초기화를 해주어야 합니다. 클래스 변수는 클래스가 로딩될 때, 인스턴스 변수는 인스턴스가 생성될 때 각각 기본값으로 초기화되지만, 지역 변수는 그렇지 않기 때문에 반드시 초기화를 해주어야 합니다.

### 6-1. 명시적 초기화(explicit initialization)
변수를 선언함과 동시에 초기화하는 것을 명시적 초기화라고 합니다. 초기화 방법 중 가장 기본적이면서 간단하므로 가장 우선적으로 고려되는 초기화 방법입니다. 하지만, 단순이 값을 할당하는 것이 아닌 보다 복잡한 상황에서 초기화를 해야하는 경우엔 어떻게 할까요? 자바에서는 초기화 블럭을 통해서 복잡한 상황의 초기화를 할 수 있습니다.


### 6-2. 초기화 블럭
초기화 블럭은 클래스 블럭과 인스턴스 블럭 2가지가 있습니다.

> **클래스 블럭 - 클래스 변수의 복잡한 초기화에 사용됨
인스턴스 블럭 - 인스턴스 변수의 복잡한 초기화에 사용됨**

초기화 블럭 내에서는 메소드처럼 여러가지 조건문 반복문 예외처리 등을 할 수 있으므로 메소드에서 하던것 처럼 논리적 설계를 해서 변수의 초기화를 하면 됩니다.

```java
public class Hamburger {
    private String name;
    private int price;
    private boolean sale;
    private int count;

    //인스턴스 블럭
    {
        count++;
    }

    public Hamburger() {
        this("big mac", 5500, true);
    }

    public Hamberger(String name) {
        this(name, 6800, true);
    }

    public Hamburger(String name, int price, boolean sale) {
        this.name = name;
        this.price = price;
        this.sale = sale;
    }
}
```

위 코드대로, 햄버거 객체가 생성될 때 마다 count는 1씩 증가됩니다. 인스턴스 생성시에 공통적으로 사용하고 싶은 코드를 인스턴스 블럭에 작성하면 됩니다. 이것은 클래스 블럭에도 마찬가지입니다.

### 6-3. 맴버변수의 초기화 시기와 순서

- **클래스 변수의 초기화 시점** : 클래스가 처음 로딩될 때 단 한번만 초기화됨
- **클래스 변수의 초기화 순서** : 기본값 --> 명시적 초기화 --> 클래스 블럭
<br>
- **인스턴스 변수의 초기화 시점** : 인스턴스가 생성될때마다 각 인스턴스 별로 초기화
- **인스턴스 변수의 초기화 순서** : 기본값 --> 명시적 초기화 --> 인스턴스 블럭 --> 생성자