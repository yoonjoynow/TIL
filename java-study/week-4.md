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
반복문은 반복적인 작업이 필요할 때 사용되며, 조건식이 true이면 반복문의 블럭 내부의 코드를 반복 실행합니다. 반복문의 종류는 for문, while문, do-while문이 있습니다.

### 2-1. for문
for문은 반복하고자 하는 횟수를 알 때 사용하는 것이 적합합니다. 가령 문장을 5번 연속으로 출력한다던지, 혹은 배열의 요소의 값을 인덱스 0번부터 10번까지 출력한다던지 등이 있습니다. 사용법은 아래와 같습니다.

> **for (변수 초기화; 조건식; 증감식) {
       // 반복하고자 하는 코드
   }**

```java
public class MyClass {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello");
        }
    }
}
```

위 코드는 Hello가 5번 찍히게 됩니다. 배열의 요소를 반복적으로 출력해보겠습니다.

```java
public class MyClass {

    public static void main(String[] args) {
        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        for (int i = 2; i < array.length(); i++) {
            System.out.println(array[i]);
        }
    }
}
```
출력 결과
> 30 40 50 60 70 80 90 100

### 2-2. 중첩 for문
for문 안에는 또 for문이 사용될 수 있습니다. 구구단을 찍는 예제같은 경우에 중첩 for문이 사용됩니다.
```java
public class MyClass {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                System.out.printf("%d x %d = %d\n", i, k, i*k);
            }
        }
    }
}
```

물론 for문 안에는 얼마든지 제어문을 사용할 수 있습니다. 배열의 짝수 요소만 출력하는 예제입니다.

```java
public class MyClass {

    public static void main(String[] args) {
        int[] array = {1, 3, 4, 5, 6, 7, 8, 9, 10};
            
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                System.out.println(array[i]);
            }
        }
    }
}
```

## 2-3. 향상된 for문
배열과 같은 인덱스를 가지는 자료구조를 다룰 때에는 for문을 항상 사용해야 합니다. 하지만 기존 for문으로 배열을 다루다 보면 **ArrayIndexOutOfBoundException**라는 예외를 발생시키기 쉽습니다. 예외는 추후 Exception을 공부할 때 더 자세히 알아보겠지만, 존재하지 않는 인덱스에 접근할 때 발생하는 에러라고 생각하면 됩니다. 배열의 길이가 4인데 5번 인덱스에 접근할 경우에 이런 에러가 생기게 됩니다. 그래서 JDK1.5 버전부터 배열의 모든 요소들에 동일한 작업을 진행할 때에는 인덱스를 사용하는 것이 아닌 향상된 for문을 통해 반복 작업을 처리할 수 있게 되었습니다. 사용법은 간단합니다.

> **for (타입 요소 : 배열) {
    반복 코드
}**

``` java
public class MyClass {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};

        for (int element : array) {
            element += 1;
            System.out.println(element);
        }
    }
}
```

향상된 for문을 이용해 예외를 피해 안전하게 작업할 수 있으며 코드도 한결 깔끔해 보입니다. 하지만 각 인덱스 별 작업해야 할 내용이 다르거나 특정 인덱스에만 작업이 필요한 경우엔 향상된 for문 대신 기존 for문을 사용해야 합니다.

### 2-4. while문
while문은 if문과 같이 조건식과 중괄호 블럭으로만 이루어져 있습니다. if문은 조건식이 true일 경우 블럭 내 코드들을 1번 실행하지만, while문은 조건식이 false가 될때까지 블럭 내 코드를 반복합니다.

>**while (조건식) {
    반복 코드
}**

```java
public class MyClass {

    public static void main(String[] args) {
        int number = 0;
        while (number < 10) {
            System.out.println(number);
            number++;
        }
    }
}
```

while문은 조건식이 true이면 무한반복 하므로 반드시 블럭 내에 조건식에 영향을 주는 조치가 필요합니다. *(위 코드에서 number++가 없을 경우 while문은 무한루프를 돌게 됩니다.)*

### 2-5. do-while 문
do-while문은 while문의 변형으로 조건식보다 블럭을 먼저 작성합니다. 골격은 아래와 같습니다.
```
do {
    // 반복 작업
} while (조건식)
```

기존 while문은 조건식의 결과가 false일 경우 블럭 내 코드가 실행이 안됩니다. 하지만 do-while문은 조건식보다 블럭이 먼저 나오므로 조건식의 결과와 상관없이 일단 1번 블럭을 실행한 후 조건식의 결과에 따라 블럭을 반복하게됩니다.

```java
public class MyClass {

    public static void main(String[] args) {
        int input = 0;
        int answer = 0;

        answer = (int) (Math.random() * 100) +1;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("업 앤 다운 게임!)
            System.out.println("정수를 입력하세요");
            input = scanner.nextInt();

            if (input > answer) {
                System.out.println("up");
            } else if (input < answer) {
                System.out.println("down");
            }
        } while (input != answer);
    }
}
```

### 2-6. for문과 while문을 선택하는 기준
for문은 순서를 가지며 순차적으로 반복 작업이 필요한 경우에 사용합니다. 배열이나 리스트 등 인덱스를 가지는 모든 자료구조에 대해서 처리할 경우 예외를 피하기 위해 향상된 for문을 사용을 사용할 수 있습니다. 대신 특정 인덱스에만 작업이 필요한 경우에는 기존 for문 사용해야만 합니다.

특정 조건시에만 반복시키고 싶은 코드가 있으면 while문 사용하고, 최초 1회는 무조건 실행한 후 반복해야 한다면 do-while문을 사용하면 됩니다.

