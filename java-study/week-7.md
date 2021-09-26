## 7주차 스터디
### 목표
자바의 패키지에 대해서 학습하세요.
### 학습할 것 (필수)
- package 키워드
- import 키워드
- 클래스패스
- CLASSPATH 환경변수
- -classpath 옵션
- 접근지시자

___
## 1. 패키지(package)란?
패키지의 사전적 의미는 묶음이라는 뜻입니다. 자바에서의 패키지는 **수 많은 클래스들을 체계적으로 관리하기 위해 사용하는 단위**입니다. 우리가 연관된 파일들을 폴더에 분산해서 관리하는 것처럼, 자바 패키지의 물리적인 형태는 '파일 시스템의 폴더'입니다.

우리가 지금껏 만들었든 클래스의 이름은 사실 클래스의 원래 이름이 아닙니다. 클래스의 이름은 **소속 패키지 명 + 클래스 파일 명**이며, 이것을 **FQCN**(**F**ully **Q**ualified **C**lass **N**ame)이라고 합니다. 광주가 경기도에도 있고 광역시 광주가 있는것 처럼, 클래스 파일명이 같더라도 패키지가 다르다면 다른 클래스로 인식합니다. 즉, 패키지는 **클래스를 유일하게 만들어주는 식별자 역할**을 합니다.

<img width="350" alt="스크린샷 2021-09-23 오후 6 48 34" src="https://user-images.githubusercontent.com/80696862/134487143-3eed9e7f-488f-45d1-8570-b86f568ac9e7.png">

<br>
<br>

Hello 클래스의 FQCN : **me.study.sample.Hello**

### 1-1. 패키지를 선언하는 법
컴파일러는 클래스의 패키지 선언문을 보고 파일 시스템의 폴더로 자동 생성시킵니다. 패키지는 항상 자바 파일의 최상단에 위치해야 하며, 단 1번만 선언할 수 있습니다. 선언 방법은 아래와 같습니다.

```java
package me.study.sample

public class Hello {

}
```

패키지 명을 짓는 데도 몇 가지 규칙이 있습니다.
- 숫자로 시작해선 안 되며, _과 $를 제외한 특수문자를 사용할 수 없다.
- java로 시작하는 패키지는 자바 표준 API에서만 사용하므로 사용해선 안 된다.
- 모두 소문자로 하는 것이 관례이다.
- 자바의 예약어를 사용할 수 없다.

여러 개발사가 참여하는 프로젝트의 경우나 다른 회사의 패키지를 이용할 경우 패키지가 겹칠 수 있으므로 자신의 회사 도메인 이름으로 패키지를 만듭니다. 이 때, 도메인 이름 역순으로 패키지 이름을 짓는데, 좀 더 포괄적인 이름이 상위 패키지에 위치하도록 하기 위함입니다.

**패키지 특징**
- 모든 클래스는 반드시 하나의 패키지에 속한다.
- 자바 파일의 맨 첫 번째 문장은 패키지 선언문이 위치한다.
- 패키지 선언은 단 1번만 가능하다
- 패키지는 닷(.)을 구분자로 계층구조를 이룬다.
- 패키지는 물리적으로 클래스 파일을 포함하는 하나의 디렉토리다.

#### 디폴트 패키지

#### 빌트인 패키지
빌트인 패키지는 **자바 API에서 기본적으로 제공하는 다수의 클래스가 포함된 패키지**입니다.

<img width="350" alt="스크린샷 2021-09-23 오후 8 31 59" src="https://media.geeksforgeeks.org/wp-content/uploads/java-types-of-packages.jpg">

빌트인 패키지 예시 :
- **java.lang** : 자바 프로그래밍에 가장 기본이 되는 클래스들을 포함
    - 자동으로 import되어서 따로 import문이 필요 없음
    - java.lang의 String 클래스
        - String text = "Hello"; //java.lang 생략
    - java.lang의 System 클래스
        - System.out.println(text); //java.lang 생략
- **java.util** :  자료 구조 구현을 위한 유틸리티 클래스를 포함
- **java.io** : 입출력 기능을 지원하는 클래스들을 포함
- **java.applet** : 자바 애플릿을 생성하기 위한 클래스들을 포함
- **java.awt** : GUI 컴포넌트를 구현하기 위한 클래스들을 포함
- **java.net** : 네트워킹 기능을 지원하기 위한 클래스들을 포함

빌트인 패키지를 제외한 우리 개발자가 만든 패키지를 사용자 정의 패키지라고 합니다.

## 2. import 키워드
한 클래스에서 다른 패키지에 위치하는 클래스를 사용하기 위해 다음 두 가지 방법을 사용할 수 있습니다.

- FQCN을 사용
- 사용하고자 하는 클래스의 FQCN를 import

String class는 java.lang 패키지에 포함된 클래스입니다. 따라서 사용자 정의 패키지에서는 위 두 방법 중 하나를 이용해야지 사용할 수 있습니다.

**첫 번째 방법**
```java
package me.study.sample

public class Hello {

    public static void main(String[] args) {
        java.lang.String text = "Hello";
    }
}
```

첫 번째 방법을 사용해서 String 클래스를 사용했지만, 패키지명이 너무 길어질 경우 가독성도 떨어지고 불편합니다. 따라서 대부분은 두번째 방법을 사용합니다.

**두 번째 방법**
```java
package me.study.sample

import java.lang.String //lang 패키지는 생략 가능

public class Hello {

    public static void main(String[] args) {
        String text = "Hello";
    }
}
```

위에서 보시다시피 import문의 위치는 패키지 선언과 클래스 선언 사이입니다. 해당 패키지에서 많은 클래스들을 사용한다면, FQCN에서 클래스 이름을 생략하고 *를 사용하면 됩니다. 이것은 해당 패키지 하위의 모든 클래스를 사용한다는 뜻입니다. 이렇게 import문을 사용해서 간단하게 클래스 이름만 사용할 수 있습니다.

```java
import java.lang.*
import java.util.*
```

그리고 명시적으로 일일히 FQCN으로 import하거나 *를 사용하거나 성능상의 큰 차이는 없습니다. 팀에서 정해진 규칙을 따르거나 자신의 취향대로 취사선택 하면 될 것입니다.

#### 주의사항
위에서 두번째 방법을 통해 간편하게 클래스 이름만 사용할 수 있다고 했습니다. 하지만 다른 패키지에도 같은 클래스 이름이 있다면 어떨까요?

<img width="400" alt="스크린샷 2021-09-23 오후 8 31 59" src="https://user-images.githubusercontent.com/80696862/134499741-176a9b37-8d26-4a25-9042-ba13f3a4b8aa.png">

```java
package me.study.yoon

import me.study.sample.Hello
import me.study.sample2.Hello

public class MyClass {

    public static void main(String[] args) {
        Hello hello = new Hello(); // 컴파일 에러
    }
}
```

me.study.sample.Hello 클래스와 me.study.sample2.Hello 클래스가 있습니다. FQCN은 다르지만 클래스 파일명은 동일하기 때문에 컴파일러는 Hello를 보고 어느 패키지의 Hello인지 알 수 없습니다. 이럴땐 명시적(explicit)으로 첫 번째 방법을 사용해야합니다.

#### static import
static import는 자바 5버전부터 지원되는 기능이며, **임의의 패키지 내 클래서에서 public static으로 선언된 멤버(필드, 메소드)를 사용할 때 클래스명을 명시하지 않고도 사용할 수 있는 기능**을 말합니다. 이 static import는 테스트 코드에서 자주 만날 수 있습니다.

```java
package me.yoon.study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedListTest {

    @Test
    void add_success_test() {
        //arrange
        Linkedlist<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        //act
        int index1 = list.indexOf(1);
        int index2 = list.indexOf(2);

        //assert
        assertThat(index1).isEqualTo(0);
        assertThat(index2).isEqualTo(1);
    }
}
```

assertThat()이라는 메소드를 static import를 통해서 바로 사용할 수 있습니다.

## 3. 클래스패스
클래스패스란 클래스가 존재하고 있는 곳의 경로(path)입니다. 즉, JVM이 클래스를 찾을 때 어느 곳을 기준으로 클래스들을 찾아나갈지에 대해서 정해둔 경로입니다. java나 javac 명령어를 사용할 때 JVM에게 클래스패스를 알려주어야 하는데 이때 사용하는 옵션이 -classpath 옵션입니다.

우리가 클래스를 식별할 때 FQCN을 사용했습니다. JVM은 클래스를 찾을때 클래스패스를 기준으로 FQCN이 일치하는 클래스를 찾게됩니다. (즉, 클래스의 경로 == 클래스패스 + FQCN)

#### -classpath 옵션

### 3-1. CLASSPATH 환경변수
환경변수란 운영체제에 지정하는 변수로, 프로세스나 애플리케이션 등이 환경변수의 값을 참조해서 사용할 수 있습니다. 자바는 클래스패스를 **CLASSPATH**라는 환경변수에 할당하며, JVM은 이 **CLASSPATH**값을 참조해서 클래스들을 찾습니다. 우리는 CLASSPATH를 통해서 매번 명령(java, javac, javap 등)을 수행할때마다 -classpath 옵션을 사용하지 않고도 어느 디렉토리에서든 명령을 사용할 수 있습니다.

이 CLASSPATH를 포함한 환경변수들은 운영체제를 변경하면 클래스패스가 사라지기 때문에 최근에는 클래스패스를 환경변수로 지정하는 대신 IDE나 빌드도구를 통해 클래스패스를 설정합니다.

#### IDE로 클래스패스 설정하기
File - Project Structure - Modules 
단축키(mac 기준) : cmd + ;

<img width="660" alt="스크린샷 2021-09-25 오후 10 50 05" src="https://user-images.githubusercontent.com/80696862/134773924-4b6ac691-786c-482f-a491-3cad39879a22.png">

왜 클래스패스를 사용할까요? 클래스패스라는 값이 없다면 JVM과 컴파일러는 해당 클래스가 어디에 있는지 하드웨어 내부를 모두 뒤지거나 매번 java, javac 명령을 실행할때마다 해당 클래스의 위치를 알려주는 수고를 해야할 것입니다. 따라서 우리가 개발하는 클래스들이 위치하는 기준을 정해두고 이를 클래스패스 변수에 등록해두면, JVM은 매번 명령을 실행할때마다 클래스패스 변수를 참고해서 해당 클래스를 찾습니다.

## 4. 접근 제어자
접근 제어자란 접근 제어자 키워드가 붙어있는 클래스, 변수, 메소드를 해당 키워드가 허용하는 범위 내에서만 접근할 수 있도록 제한하는 역할을 합니다.

 |접근제한자|동일 클래스|동일 패키지|자식 클래스|그 외의 영역|
 |:--:|:--:|:--:|:--:|:--:|
 |public|O|O|O|O|
 |protected|O|O|O|X|
 (default)|O|O|X|X|
 |private|O|X|X|X|

 #### 접근 제어자를 사용하는 이유
 - 외부러부터 데이터를 숨기기 위해서
 - 외부에 노출할 필요가 없는 내부적인 로직을 감추기 위해서

 이렇게 접근 제어자를 사용해 객체에 대한 외부의 접근을 제한할 수 있는 특징을 객체의 **캡슐화**(Encapsulation)이라고 합니다. 객체 내부의 값에 대해 직접 접근을 제한하며, 값의 조회나 변경이 필요시엔 해당 객체의 의도가 명확한 이름의 메소드를 통해서 접근해야 합니다.

 ### 4-1. 생성자의 접근 제어자
 보통 생성자의 접근 제어자로는 public을 사용합니다. 하지만 애플리케이션에서 인스턴스가 딱 1개만 존재해야 할 때 생성자의 접근 제어자를 **private**로 선언해서 인스턴스 생성을 제한할 수 있습니다. 이러한 패턴을 **싱글턴(Singleton) 패턴**이라고 부릅니다. 보통 시스템 설계상 유일해야 하는 설정 클래스나 DBCP(DataBase Connection Pool)처럼 

 싱글턴 패턴으로 클래스를 구현하기 위해선 아래 3가지 사항을 지켜야 합니다.
 - private 생성자
 - static 변수로 객체 생성
 - 객체의 getter 구현

```java
package me.study.yoon

public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```

```java
package me.study.yoon

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonTest {

    @Test
    void isSameInstance() {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        assertThat(instance1).isEqualTo(instance2);
    }

}
```

위의 테스트로 두 인스턴스가 동일함을 알 수 있습니다. 추가적으로, pirvate 생성자를 가진 클래스는 다른 클래스의 부모가 될 수 없습니다. 자식 인스턴스가 생길 때 super()를 호출할 수 없기 때문입니다. 따라서 **final** 클래스로 명시적(explicit)으로 상속할 수 없는 클래스라고 알리는 것도 좋습니다.
___

참고 : [자바의 정석](http://www.yes24.com/Product/Goods/24259565?OzSrank=2), [자바의 패키지와 클래스패스](https://ahnyezi.github.io/java/javastudy-7-package/), [Packages in Java](https://www.geeksforgeeks.org/packages-in-java/), [이펙티브 자바 3/E](http://www.yes24.com/Product/Goods/65551284?OzSrank=1)