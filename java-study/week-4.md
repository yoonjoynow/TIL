## 4주차 스터디
### 목표
자바가 제공하는 제어문을 학습하세요.
### 학습할 것 (필수)
- 선택문
- 반복문
### 과제(옵션)
- JUnit 5 학습하세요.
    - IntelliJ에서 JUnit 5로 테스트 코드 작성하는 방법에 익숙해 질 것.
- LinkedList를 구현하세요.
    - LinkedList에 대해 공부하세요.
    - 정수를 저장하는 ListNode 클래스를 구현하세요.
    - ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.
    - ListNode remove(ListNode head, int positionToRemove)를 구현하세요.
    - boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.
- Stack을 구현하세요.
    - int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
    - void push(int data)를 구현하세요.
    - int pop()을 구현하세요.
- 앞서 만든 ListNode를 사용해서 Stack을 구현하세요.
    - ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.
    - void push(int data)를 구현하세요.
    - int pop()을 구현하세요.
- Queue를 구현하세요.
    - 배열을 사용해서 한번
    - ListNode를 사용해서 한번.
___
___

## 1. 조건문(선택문)
자바에서 조건문은 if문과 switch문을 제공하며 주로 if문이 많이 사용됩니다.

### 1-1. if문
if문은 조건식과 중괄호{}로 이루어져있습니다. 조건식의 결과가 true일때만 중괄호 블럭 내의 코드들을 실행합니다.

```java
public class MyClass {

    public static void main(String[] args) {
        int number = 10;

        if (number > 5) {
            System.out.println("Hello");
        }
    }
}
```
조건식의 결과가 true이므로 출력값이 찍히게 됩니다. 하지만 이렇게 if문 조건이 하나일 경우 아래처럼 블럭을 생략할 수 있습니다.

```java
public class MyClass {

    public static void main(String[] args) {
        int number = 10;

        if (number > 5) System.out.println("Hello");
    }
}
```

### 1-2. if-else문
조건이 거짓일 때 무언가를 실행하고 싶으면 if-else문을 사용해야 합니다.
```java
public class MyClass {

    public static void main(String[] args) {
        int numer = 10;

        if (number > 100) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
```
조건식의 결과가 false이므로 no가 출력됩니다. 이 if-else문은 저번에 배운 삼항연산자로 대체할 수 있습니다.

```java
public class MyClass {

    public static void mian(String[] args) {
        int number = 10;
        String result = number > 100 ? "yes" : "no";

        System.out.println(result);
    }
}
```

### 1-3. if-else if문
if-else문은 단지 두 가지 경우에 대해서만 논리적 처리를 할 수 있지만, 그 이상의 논리적 처리가 필요하다면 if-else if 문을 사용하면 됩니다.

```java
public class MyClass {

    public static void main(String[] args) {
        int score = 75;

        if (score > 90) {
            System.out.println("A");
        } else if (score > 80) {
            System.out.println("B");
        } else if (score > 70) {
            System.out.println("C");
        } else if (score > 60) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }
    } 
}
```

추가로, 마지막 else문은 생략할 수 있습니다. 만약 true인 조건문이 하나도 없다면 모든 조건문의 블럭 내용이 실행되지 않습니다.

### 1-4. 중첩 if문
if문 블럭 내의 또다른 논리적 처리가 필요하면 중첩 if문을 사용하면 됩니다. 중첩 if문 여러번 사용할 수 있습니다. 하지만 가독성을 위해 많은 중첩 if문 사용은 지양하는 것이 좋습니다.

```java
public class MyClass {

    public static void main(String[] args) {
        int mathScore = 100;
        int minScore = 30;

        if (maxScore > 100) {
            if (minScore > 30) {

            }
        }
    }
}
```

## 1-5. switch문
검사해야할 조건이 많다면 if-else if문보다 switch문을 사용하는게 더 편할 수 있습니다. switch문은 하나의 조건식으로 여러가지의 경우의 수를 처리할 수 있습니다. 단, switch문은 사용에 제약이 있습니다.

**switch문의 실행 순서**
1. 조건식을 실행
2. 조건식의 결과와 일치하는 case문으로 이동
3. case문에 해당하는 코드들 실행
4. break문이나 switch문의 끝을 만나면 프로그램 실행 플로우가 swith문 밖으로 나간다.

**switch문의 제약조건**
- switch문의 조건식 결과는 정수 혹은 문자열이여야 함
- casse문의 값은 정수 상수만 가능하며, 중복되면 안됨
    
기본적인 switch문 사용법은 아래와 같습니다.

```java
public class MyClass {

    public static void main(String[] args) {
        System.out.println("이번 달을 입력하세요");

        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();

        switch (month) {
            case 3:
            case 4:
            case 5:
                System.out.println("Spring");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("Summer");
                break;
            case 9 :
            case 10 :
            case 11 :
                System.out.println("Fall");
                break;
            case 12:
            case 1:
            case 2:
                System.out.println("Winter");
                break;
            default:
                System.out.println("없는 달입니다.");
    }
}
```
Scanner를 이용해 현재 월을 콘솔에서 입력받습니다. 입력값이 1 ~ 12에 해당할 경우엔 해당하는 계절이 출력됩니다. 입력값이 12보다 크면 해당하는 case가 없으면 default문이 실행됩니다. 

Java 12버전에서는 이것보다 좀 더 간추려서 표현할 수 있도록 case문에 ,를 지원합니다.

```java
public class MyClass {

    public static void main(String[] args) {

        switch (month) {
            case 3, 4, 5:
                System.out.println("Spring");
            case 6, 7, 8:
                System.out.println("Summer");
                break;
            case 9, 10, 11:
                System.out.println("Fall");
                break;
            case 12, 1, 2:
                System.out.println("Winter");
                break;
            default:
                System.out.println("없는 달입니다.");
        }
    }
}
```

자바13에서는 좀 더 진화하여 break문이 yield 대체되었으며 반환값도 리턴할 수 있게되었습니다. 이렇게 switch문을 좀 더 간결하게 사용할 수 있습니다.
```java
public class MyClass {

    public static void main(String[] args) {
       System.out.println("이번 달을 입력하세요");

        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();

        String season = switch (month) {
            case 3, 4, 5:
                yield "Spring";
            case 6, 7, 8:
                yield "Summer";
            case 9, 10, 11:
                yield "Fall";
            case 12, 1, 2:
                yield "Winter";
            default:
                yield "없는 달입니다.";
        };
    }
}
```

이번엔 제약사항에 해당하는 경우를 살펴보겠습니다.
```java
public class MyClass {

    public static void mian(String[] args) {
        float aFloat = 3.141592f;

        switch(aFloat) {
            case 3.14:
                System.out.println("Hello");
        }
    }
}
```
aFloat은 정수나 문자열이 아니므로 컴파일 에러가 발생합니다. 이렇게 제약사항에 위배되는 경우엔 if-else문을 사용해야 합니다.




__

## 2. 반복문
