# 9주차 스터디
## 목표
자바의 **예외 처리**에 대해서 학습하세요.
## 학습할 것 (필수)
- 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
- 자바가 제공하는 예외 계층 구조
- Exception과 Error의 차이는?
- RuntimeException과 RE가 아닌 것의 차이는?
- 커스텀한 예외 만드는 방법

# 1. 프로그램에서의 오류와 예외
프로그램에서의 **오류(error)란 프로그램이 오작동을 하거나 비정상적으로 종료되는 것**을 뜻합니다. 여기엔 하드웨어적인 문제와 소프트웨어적인 문제가 포함됩니다.

에러의 종류는 크게 3 가지가 있습니다.
- **컴파일 에러** : 컴파일 타임에 발생하는 에러 (문법 오류 등)
- **런타임 에러** : 런타임에 발생하는 에러 (하드웨어 고장 등)
- **논리적 에러** : 프로그램이 실행은 되지만 의도와 다르게 동작하는 에러

자바에서는 애플리케이션 실행시 발생하는 에러를 다시 **에러**(error)와 **예외**(exception)으로 구분합니다. 왜 에러와 예외를 구분할까요?

## 1-1. 에러와 예외의 구분
에러와 예외는 아래와 같은 차이가 있습니다.

- **에러** : 코드로 수습할 수 없는 심각한 오류. 시스템 레벨에서 특별한 작업을 하는 것이 아니라면 애플리케이션에서는 이 에러에 대한 처리를  신경쓰지 않아도 된다.
- **예외** : 코드로 수습할 수 있는 상대적으로 미약한 오류. 예외는 개발자가 적절히 처리해야 한다.

에러는 프로그램 외부의 문제로, 메모리가 부족할 때, 하드웨어 고장의 문제 등 예측할 수 없고 일단 발생하면 복구할 수 없는 경우를 말합니다. 

**예외는 "Exceptional Event"의 약자로, 프로그램 실행 중에 발생하는 프로그램의 일반적인 흐름을 방해하는 이벤트**입니다. 예외는 프로그램 내부의 문제로, 에러에 비해 정도가 낮고 개발자가 코드 상으로 적절히 처리해서 프로그램이 비정상적으로 종료되지 않고 정상 흐름으로 복구시킬 수 있습니다.

개발자는 **적절한 조치를 통해 프로그램의 비정상 종료를 막고 정상적인 실행 상태를 유지해줘야 합니다.**

## 1-2. 예외 클래스의 계층구조
<img width="800" alt="스크린샷 2021-09-28 오후 2 23 32" src="https://user-images.githubusercontent.com/80696862/135028054-686503f5-005a-46af-890e-5dc2c0ff064c.png">

<br>
<br>

- **Object** : 모든 클래스의 최상위 클래스
- **Throwable** : 모든 에러의 최상위 클래스
- **Error** : 프로그램 외부 요인으로 발생하는 심각한 오류
- **Exception** : 프로그램 내부 요인으로 발생하는 경미한 오류 

## 1-3. '시점' 에 따른 예외의 종류
자바에서는 시점에 따른 예외의 종류가 다릅니다. 자바의 시점은 두 가지가 있습니다.
- **컴파일 타임** : 소스코드가 컴파일되어 바이트코드로 변환되는 과정
- **런타임** :  JVM 위에서 애플리케이션이 사용자에 의해 실행 중인 시점


시점에 따른 예외 종류 두 가지
- **Checked Exception** : 컴파일 타임에 발생하는 예외
- **Unchecked Exception** : 런타임에 발생하는 예외

### 1-3-1. Checked Exception이란?
- RuntimeException 클래스를 상속하지 않은 Exception 클래스의 서브 클래스
- 프로그램 외부의 문제로 발생할 수 있는 것들로서, 프로그램의 사용자(클라이언트)측의 잘못된 동작에 의해 발생하는 경우가 많음
- 예외 처리가 강제됨 (컴파일 에러 발생)

#### Checked Exception 예시
|클래스 명|설명|
|--|--|
|IOException|입출력 작업이 실패하거나 중단되었을 때 발생 |
|FileNotFoundException|해당 이름의 파일을 찾을 수 없을 때 발생|
|ClassNotFoundException|해당 이름의 클래스를 찾을 수 없을 때 발생|
|ParseException|텍스트를 파싱하는 과정에서 발생|

### 1-3-2. Unchecked Exception이란?
- RuntimeException 클래스를 상속한 Exception 클래스의 서브클래스
- 프로그램에서 오류가 생겼을 때 발생하도록 의도된 예외
- 피할 수 있지만 개발자의 부주의로 발생하며 자바의 프로그래밍 요소들과 관계가 깊다
- 예외 처리가 강제되지 않음

#### Unchecked Exception 예시
|클래스 명|설명|
|--|--|
|NullPointerException|객체를 필요로 하는 곳에서 null을 사용하려고 시도할때 발생|
|ArrayIndexOutOfBoundsException|배열의 크기를 벗어나는 인덱스에 접근시에 발생<br>(인덱스는 0 이상 크기 미만)|
|StringIndexOutOfBoundsException|문자열의 길이를 벗어나는 인덱스에 접근시에 발생<br> (인덱스는 0 이상 길이 미만)|
|ArithmeticException|숫자를 0으로 나누는 등 잘못된 산술 연산이 진행되었을 때 발생|
|NumberFormatException|숫자 형식으로 변환할 수 없는 문자열을 숫자로 변환시 발생|
|ClassCastException|런타임 시 객체를 다운 캐스팅할 때 타입 미스매치시 발생|
|IllegalArgumentException|메소드의 인수가 잘못되었을 때 발생|
IllegalStateException|메소드 호출 시점이 잘못되었을 때 발생|

컴파일 타임엔 컴파일러가 예외가 발생할 소지가 있는 곳을 명시적(explicit)으로 알려줍니다. 이를 **Checked Exception**이라고 합니다. Checked Exception은 예외 처리를 하지 않으면 컴파일 에러가 발생합니다. 주로 외부의 자원이나 API 등을 사용할 때 발생합니다.

컴파일된 애플리케이션을 실행하더라도 에러는 충분히 발생할 수 있습니다. 이때 발생하는 에러는 컴파일러가 체크를 못한 **Unchecked Exception**이라고 합니다. Unchecked Exception은 모두 **RuntimeException 클래스의 서브클래스**이고, 컴파일 에러가 발생하지 않아 개발자가 적절히 처리해줘야 합니다. 

|구분|Checked Exception|Unchecked Exception|
|:--:|:--:|:--:|
|발생 시점|컴파일 타임|런타임|
|처리 여부|반드시 예외 처리|명시적으로 하지 않아도 됨|
|예외 원인|메소드를 호출하는 쪽에서 잘못된 방법으로 메소드를 사용한 경우|개발자의 부주의가 대부분|


그렇다면 자바에서는 왜 예외의 종류를 두 종류로 굳이 구분할까요? [오라클 레퍼런스](https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html)에서는 이렇게 설명하고 있습니다.

**예외는 메소드의 파라미터와 리턴 값만큼이나 중요한 공용 인터페이스입니다.** 메소드를 호출하는 쪽에서는 해당 메소드가 어떠한 예외를 발생시킬 수 있는지 반드시 알아야 합니다. 따라서 컴파일러는 **Checked Exception**을 통해 해당 메소드가 발생시킬 수 있는 예외를 명세하도록 강제합니다.

하지만 Runtime Exception을 비롯한 Checked Exception은 왜 예외 명세를 하지 않아도 될까요? Runtime Exception은 프로그래밍을 잘못한 문제로, 메소드를 호출한 측에서는 해당 예외를 적절히 복구하거나 대처하기는 어렵습니다. *(대게 null 참조나 숫자를 0으로 나누는 등의 개발자의 실수입니다.)* 

또한, 이 예외는 프로그램 내부 어디에서든 발생할 수 있기 때문에 컴파일러는 Uncheckd Exception을 명세하도록 강제하지 않습니다. 오히려 프로그램 명확성을 떨어뜨릴 수 있기 때문입니다. (예외 처리 비용은 비싸다.)

따라서 **클라이언트가 예외를 적절히 복구할 수 있을 것이라고 예상되는 경우 Checked Exception으로 만들고, 그렇지 않은 경우 Unchecked Exception으로 만드는 것이 좋습니다**. 그리고 단순히 예외 처리가 귀찮아서 Runtime Exception을 던지거나 서브클래스를 만드는 것을 피해야합니다.

# 2. 예외 처리하기
예외 처리란 무엇일까요? 
- **프로그램 실행 시 발생할 수 있는 예외를 대비한 코드를 작성하는 것**
- **프로그램의 비정상적인 종료를 막고, 정상적인 실행 상태를 유지하는 것이 목표**

### try-catch문
자바에서 예외를 처리하기 위한 기본적인 방법은 try-catch문으로, 그 구조는 아래와 같습니다.
```java
public class Example {

    try {
        // 예외가 발생할 가능성이 있는 코드를 위치
    } catch (ExceptionType1 name1) {
        // ExceptionType1 예외가 발생 시, 이를 처리하기 위한 코드 작성
    } catch (ExceptionType2 name2) {
        // ExceptionType2 예외 발생 시, 이를 처리하기 위한 코드 작성
    }
}
```

하나의 try 블럭 다음에는 여러 종류의 예외를 처리할 수 있도록 하나 이상의 catch 블럭이 위치할 수 있습니다. try-catch문의 실행 흐름은 아래와 같습니다.

- **try 블럭 내에서 예외가 발생한 경우**
    1. 발생한 예외하 일치하는 타입을 잡는 catch 블럭이 있는지 확인
    2. 일치하는 블럭이 있다면 그 블럭 내의 코드를 실행하고 프로그램 흐름은 try-catch문을 빠져나감
    3. 일치하는 블럭이 없다면 예외는 처리되지 못함
- **try 블럭 내에서 예외가 발생하지 않은 경우(정상 작동)**
    1. try 블럭 내 코드가 모두 실행된 뒤 catch문을 거치지 않고 try-catch문 밖으로 프로그램 흐름이 빠져나감

하지만 여기서 주의할 점이 있습니다. 위 코드는 컴파일 에러가 발생할까요?

```java
public class Example {

    public static void main(String[] args) {

        try {
            int a = 5 % 0; // ArithmeticException 발생 지점
        } catch (Exception e1) {
            System.out.println("Exception 발생");
        } catch (ArithmeticException e2) {
            System.out.println("ArithmeticException 발생");
        }

        System.out.println("프로그램 실행 종료");
    }

}
```

정답은 발생한다입니다. 코드의 흐름은 위에서부터 아래방향입니다. 이 예외처리 구문에서 Exception은 모든 예외 클래스들의 부모 클래스입니다. 

따라서 ArithmeticException을 포함한 모든 예외 상황이 첫 번째 catch 블럭에서만 잡히게 되어 두 번째 catch블럭은 사용될 일이 없어 컴파일 에러가 발생합니다. 항상 예외를 잡을 때에는 좀 더 상위 타입의 예외부터 차례대로 catch해주어야 합니다. 

위 코드는 아래처럼 바꿔주어야 합니다.

**'여러 개의 catch 문을 사용할 경우 좀 더 상위타입인 순서대로 작성한다'.**

```java
public class Example {

    public static void main(String[] args) {

        try {
            int a = 5 % 0; // ArithmeticException 발생 지점
        } catch (ArithmeticException e2) {
            System.out.println("ArithmeticException 발생");
        } catch (Exception e1) {
              System.out.println("Exception 발생");
        }

        System.out.println("프로그램 실행 종료");
    }

}
```

### 예외 정보 확인하기
예외가 발생했을 때 생기는 인스턴스는 발생한 예외의 대한 정보가 담겨있는데, 이를 확인하기 위해 자주 사용되는 메소드는 아래와 같습니다.

- **printStackTrace()** : 예외발생 시 호출 스택(Call Stack)에 있던 메소드 정보와 예외 메시지를 출력
- **getMessage()** : 발생한 예외 클래스의 인스턴스에 저장된 예외 메시지 획득

### catch문에서 주의사항
Checked Exception이 발생하지 않는 곳, 즉 컴파일 에러가 발생하지 않는 곳에서 임의로 try-catch문으로 Chekced Exception을 잡을 경우엔 컴파일 에러가 발생합니다.    

```java
try {
    String text = "Hello";
} catch (ClassNotFoundException e1) {
    // 컴파일 에러
} catch (IOException e2) {
    // 컴파일 에러
} catch (FileNotFoundException e3) {
    // 컴파일 에러
}
```

### 멀티 catch 블럭
JDK 1.7부터는 catch 블럭을 '|'을 이용해서 하나의 catch 블럭으로 처리할 수 있게 되었는데 이를 멀티 catch 블럭이라고 부릅니다. '|' 기호로 연결할 수 있는 예외 클래스의 개수는 제한이 없습니다. 여러 종류의 예외에 대해 하나의 변수로 받을 수 있습니다.

- 여기서 사용되는 | 는 논리 연산자가 아닌 기호입니다

```java
public class Example {

    public static void main(String[] args) {

        try {
            int a = 5 % 0;
        } catch (ArithmeticException |
                 NumberFormatException | 
                 NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println("프로그램 실행 종료");
    }
}
```

#### 멀티 catch 사용시 주의사항
**1. 부모 - 자식 관계인 예외 클래스를 같이 선언 불가**
```java
try {

} catch (ParentException | ChildExceptoin e) {
    // 컴파일 에러 발생
}
```

위에서 설명했던 것처럼, 모두 부모타입에서 자식타입의 예외도 받기 때문에 컴파일 에러가 발생합니다. 멀티 catch의 파라미터는 순서에 상관이 없으므로 모두 컴파일 에러가 생깁니다.

```java
try {

} catch (ChildExceptoin | ParentException e) {
    // 이것 역시 컴파일 에러
}
```

**2. catch문에 선언된 예외들의 공통된 멤버만 사용 가능**
```java
try {

} catch (ExceptionA | ExceptionB e) {
    e.printStackTrace();
    e.methodA(); // 컴파일 에러
}
```

- printStackTrace 메소드는 공통 멤버이므로 사용 가능
- methodA는 ExceptionA에만 메소드로 컴파일 에러
- 따라서 공통 변수를 ExceptionA로 형 변환 뒤 methodA 사용 가능

```java
try {

} catch (ExceptionA | ExceptionB e) {
    if (e instanceOf ExceptionA) 
        ExceptionA e1 = (ExceptionA) e;
    e1.methodA();
}
```

### 중첩 try-catch 문
if문처럼, try 블럭 내부에 try-catch을 하나 더 선언할 수 있습니다. 중첩은 몇개든 상관없이 할 수 있습니다. 

```java
public class Example {

    public static void main(String[] args) {

        try {
            try {

            } catch (Exception e) {

            }
        } catch (Exception e2) {
            
        }
    }
}
```

### try-catch-finally 문
try-catch문의 특징은 try 블럭 내에서 예외가 발생시 무조건 catch 블럭으로 코드의 흐름이 넘어간다는 것입니다. 이 경우, try문에서 무조건 실행되어야 하는 구문이 실행되지 못한 채 try-catch문 밖으로 코드 흐름이 넘어가는 문제가 발생합니다. 

그래서 **'예외 발생 여부와 상관없이 무조건 실행시켜야 하는 중요한 코드'는 finally 블럭 안에 작성해야 합니다.**

try-catch-finally문은 아래와 같은 자원(Resource) 등을 사용할 때 주로 사용됩니다.
- 파일 입출력
- JDBC 연동
- 소켓 프로그래밍

아래는 JDBC 연동 예제입니다.
*(여기서는 자바에서 자원을 사용시 이렇게 많은 예외 처리가 필요하구나에 집중하시기 바랍니다.)*

```java
public class JdbcExample {

    private static final String CREATE_USERS_SQL = "create table users (id varchar(10) primary key)";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hello";
    private static final String DB_USERNAME= "root";
    private static final String DB_PASSWORD = "1234";

    Connection connection = null; // 자원
    Statement statement = null; // 자원

    public void create(User user) {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); // 자원 생성
            statement = connection.createStatement(); // 자원 사용 & 생성
            statement.execute(CREATE_USERS_SQL); // 자원 사용
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close(); // 예외 발생 가능성, 자원 해제
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // 예외 발생 가능성, 자원 해제
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
```

자원은 대게 비싸며, 자원을 사용후 해제해주지 않으면 예측할 수 없는 큰 성능 문제가 발생할 수 있습니다.(자원 누수로 인한 성능이슈) 따라서 자원 해제는 항상 finally문에서 해주어야 합니다.

하지만 자원 해제시에도 예외가 발생할 수 있습니다. 자원을 해제하는 메소드 close()는 Exception을 던지기 때문입니다. 그래서 finally문 안에서도 일일이 try-catch 문으로 감싸주어야 합니다.

그리고 try문과 finally문에서 모두 예외가 발생한다면, try문의 예외는 무시되는 큰 문제가 있습니다.

위와 같은 코드는 보기도 지저분하고 가독성도 떨어집니다. 또한 실수로 자원을 해제해주지 않는다면, 이후에 엄청난 성능이슈로 돌아올 수 있습니다. 여러 곳에서 자원을 사용한다면, 정말이지 자원을 사용하는 곳의 모든 코드를 일일이 확인해서 닫지 않은 부분을 찾아야 할 것입니다.

### try-with-resource 문
try-with-resource문은 위와 같은 문제를 해결하기 위해 JDK 1.7부터 추가되었습니다. 자원을 사용하는 경우, 자원을 획득하는 코드를 try 문 다음에 소괄호에 넣어주면 됩니다. **try-with-resource 문을 통해 예외 발생 유무와 상관없이 자원을 해제할 수 있습니다.** 자원의 갯수가 복수일 경우엔 자원 사이에 ;를 적어야합니다.

```java
public class JdbcExample {

    private static final String CREATE_USERS_SQL = "create table users (id varchar(10) primary key)";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hello";
    private static final String DB_USERNAME= "root";
    private static final String DB_PASSWORD = "1234";

    public void create(User user) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            Class.forName("org.postgresql.Driver");
            statement.execute(CREATE_USERS_SQL);

        } catch (ClassNotFoundException |  SQLException e) {
            e.printStackTrace();
        }

    }

}
```



#### AutoCloseable 인터페이스
try-with-resource 문을 사용할 수 있는 자원은 AutoCloseable 인터페이스를 구현한 클래스여야 합니다.

```java
public interface AutoCloseable {
    // 자원을 해제할 수 없을때 예외 발생
    void close() throws Exception;
}  
```

### 향상된 try-with-resource 문
하지만 여전히 try-with-resource 문에도 약간의 문제가 보입니다. 해제해야하는 자원의 수가 많을 경우 또다시 소괄호 안이 지저분해집니다.

이에 JDK 9버전부터는 try-with-resource 문을 좀 더 유연하게 사용할 수 있도록 소괄호에 참조 변수를 사용할 수 있습니다.

```java
public class JdbcExample {

    private static final String CREATE_USERS_SQL = "create table users (id varchar(10) primary key)";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hello";
    private static final String DB_USERNAME= "root";
    private static final String DB_PASSWORD = "1234";

    public void create(User user) throws Exception{

        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD;
        Statement statement = connection.createStatement();

        try (connection; statement) {

            Class.forName("org.postgresql.Driver");
            statement.execute(CREATE_USERS_SQL);

        } catch (ClassNotFoundException |  SQLException e) {
            e.printStackTrace();
        }

    }

}
```

### 예외 던지지(회피)

### 예외 처리 비용
https://www.notion.so/3565a9689f714638af34125cbb8abbe8

# 3. 예외 발생시키기

# 4. 사용자 정의 예외

# 5. 예외의 전파

참고 : [자바의 정석](http://www.yes24.com/Product/Goods/24259565?OzSrank=2), [[Java] 자바의 예외](https://ahnyezi.github.io/java/javastudy-9-exception/), [토비의 스프링 3.1 1권 5장 예외](http://www.yes24.com/Product/Goods/75738557?OzSrank=3), [오라클 레퍼런스](https://docs.oracle.com/javase/tutorial/java/TOC.html)