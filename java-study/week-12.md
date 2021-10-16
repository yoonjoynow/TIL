## 12주차 스터디
### 목표
자바의 애노테이션에 대해 학습하세요.
### 학습할 것 (필수)
- 애노테이션 정의하는 방법
- @retention
- @target
- @documented
- 애노테이션 프로세서

# 1. 애너테이션이란
애너테이션이란 일종의 메타데이터로, 애플리케이션이 처리해야할 데이터가 아니라, 컴파일 과정이나 실행 과정에서 코드를 어떻게 컴파일하고 처리할 것인지에 대한 정보를 알려주는 주석입니다. **애노테이션 그 자체로는 프로그램에 대한 어떤 조작도 하지 않고 영향도 미치지 않습니다. 즉, 애너테이션은 어떠한 기능이 아니라는 것입니다.**

우리가 그동안 **@Override** 애너테이션을 보고 이것은 슈퍼 클래스의 메소드를 재정의한 메소드이구나를 알 수 있었던 것 처럼, 개발자에게도 가독성이 좋고 컴파일러를 통해 한번 더 체크(슈퍼 클래스의 메소드와 시그니처가 동일해야 함)를 할 수 있는 장점이 있습니다.

```java
@Override
public String toString() {
    return name;
}
```

## 1.1 애너테이션의 용도
- **컴파일러에게 코드 문법 오류 확인을 위한 정보 제공**
- **소프트웨어 개발 툴이 빌드나 배치 시 코드를 자동으로 생성하도록 정보 제공**
- **런타임 시 특정 기능을 실행하도록 정보 제공**

위처럼, 애너테이션은 어떠한 정보를 알려주는 주석입니다. 따라서 애너테이션엔 동적인 코드를 적용할 수 없습니다. 즉, 런타임 시에 알 수 있는 값은 애너테이션에 사용될 수 없습니다.
- 컴파일러 수준에서 해석되거나
- 완전히 정적이어야 한다

# 2. 애너테이션의 종류
자바의 애너테이션은 크게 두 종류로, JDK에서 기본적으로 제공되는 애너테이션인 빌트인 애너테이션과 메타 애너테이션으로 나뉘어지며, 이들은 모두 **java.lang.annotation** 패키지에 소속되어 있습니다. 두 종류의 애너테이션에 대해서 알아보겠습니다.

### 빌트인(표준) 애너테이션
빌트인 애너테이션은 JDK에서 기본적으로 제공되는 애너테이션들로, 

|애너테이션|설명|
|--|--|
|@Override|컴파일러에게 오버라이딩하는 메소드라는 것을 알림|
|@FunctionalIntereface|컴파일러에게 함수형 인터페이스라는 것을 알림|
|@Deprecated|앞으로 사용하지 않을 것을 권장하는 대상에 붙임|
|@SuppressWarnings|컴파일러의 특정 경고 메시지를 나타내지 않게함|
|@SafeVarags|제네릭스 타입의 가변인자에 사용한다 (JDK1.7)|                                           
|@Native|native 메소드에서 참조되는 상수 앞에 사용한다 (JDK1.8)|


먼저 몇 가지 빌트인 애너테이션에 대해 알아보겠습니다.

#### @Override
```java
@Override
public int tostring() {
    return 0; // 컴파일 에러
}
```

메소드 오버라이딩의 조건은 슈퍼 클래스의 메소드와 시그니처가 동일해야 한다는 것입니다. 하지만 실수로 오타 때문에 이 조건을 만족하지 못할 수 있는데, 이 때 **@Override** 애너테이션을 통해 컴파일러에게 해당 메소드가 오버라이딩할 메소드라는 것을 알릴 수 있습니다. 이후 컴파일러는 해당 메소드의 시그니처와 슈퍼 클래스의 메소드의 시그니처를 비교해서 시그니처가 동일하지 않으면 컴파일 에러를 발생시킵니다.

*생략할 수 있지만 메소드 명을 실수하면 컴파일러가 이를 알 수 없으므로 무조건 해놓은 습관을 들이자*

#### @FunctionalIntereface
함수형 인터페이스는 추상 메소드가 단 한 개만 존재하는 인터페이스입니다. **@FunctionalIntereface** 애노테이션을 인터페이스에 붙여서 해당 인터페이스가 함수형 인터페이스라는 것을 명시적으로 표현합니다. 

만약 두 개 이상의 추상메소드가 있다면 컴파일 에러를 발생시킵니다.

```java
@FunctionalInterface // 컴파일 에러
public interface Hello {

    void hello();
    void bye(); // 추상메소드 2개
}
```

#### @Deprecated
Deprecated는 사전적 의미로 **더 이상 사용되지 않는** 이라는 뜻입니다. JDK의 버전이 올라가면서, 기존 JDK에서 제공했던 API 보안이나 성능 등의 문제가 있어 새 버전의 JDK에서 대체 API를 제공합니다.

이 때, 새 버전의 JDK가 기존에 문제가 있던 API를 삭제된 채 배포되면 큰 문제가 생길 수 있습니다.

새 JDK로 마이그레이션하기 전의 소스코드에서는 이미 여러 곳에서 문제가 있는 API를 참조하고 있는데, 해당 API가 삭제되어버린 새 버전의 JDK로 마이그레이션하게 되면, 문제가 있는 API를 참조하고 있는 모든 부분에서 컴파일 에러가 발생하게 됩니다.

이러한 경우에 **새 JDK에서는 문제가 있는 API를 삭제하는 것 대신, @Deprecated 애노테이션을 해당 메소드에 붙여서 개발자에게 이 메소가 곧 지원되지 않을 것임을 알립니다.** 또한 개발자가 **@Deprecated**된 메소드를 참조할 경우 컴파일러 경고가 발생합니다.

**Deprecated된 메소드 예**
<img width="400" alt="스크린샷 2021-10-15 오후 2 15 18" src="https://user-images.githubusercontent.com/80696862/137435899-69f71a69-6abc-4b6e-80d9-d8f94c77bfb1.png">

void suspend( ) 메소드는 JDK 1.2버전부터 Deprecated되었습니다.
forRemoval은 이후 해당 메소드가 삭제될 것인지를 알려주는 요소입니다.
true이므로 언젠가 이 메소드는 삭제될 예정입니다.

#### @SuppressWarnings

#### @Native
네이티브 메소드에 의해 참조되는 상수 필드(constant field)에 붙이는 애너테이션입니다.

아래는 java.lang.String 클래스에서 사용된 예 입니다.

<img width="350" alt="스크린샷 2021-10-15 오후 4 47 21" src="https://user-images.githubusercontent.com/80696862/137451369-b66d645e-02b1-4474-b158-e3471437e99d.png">

### 메타 애너테이션
메타 애너테이션은 말 그대로 애너테이션을 위한 애너테이션으로, 애너테이션에 붙이는 애너테이션입니다. 메타 애너테이션은 애너테이션의 **적용 대상**(target)이나 **유지기간**(retention)등을 지정할 때 사용하는 애너테이션입니다.

|애너테이션|설명|
|--|--|
|**@Target**|애너테이션이 적용가능한 대상을 지정하는데 사용한다|
|**@Retention**|애너테이션이 유지되는 범위를 지정하는데 사용한다|
|**@Documented**|애너테이션 정보가 javadoc으로 작성된 문서에 포함되게 한다|
|**@Inherited**|애너테이션이 자손 클래스에 상속되도록 한다|
|**@Repeatable**|애너테이션을 반복해서 적용할 수 있게 한다 (JDK1.8)|

# 3. 애너테이션 정의하기
애터네이션을 정의하는 방법은 인터페이스를 정의하는 것과 유사하며, **@interface** 키워드를 사용해서 정의합니다.

```java
접근제어자 @interface 애노테이션명 {
    타입 요소명() 기본값;
}
```

**애너테이션은 element(요소)라는 일종의 메소드를 가질 수 있으며**, 각 엘리먼트는 타입과 요소명으로 구성되고 디폴트 값을 가질 수 있습니다. 그 형태는 추상 메소드와 유사합니다.

- 반환값을 갖는다
- 매개변수 없는 추상 메소드의 형태
- 디폴트 값으로 null을 제외한 모든 리터럴을 선언 가능
- 요소의 접근제어자는 항상 public (생략 가능)

```java
public @interface TestInfo {
    int count();
    String say() default "Hello"; 
    String[] messages() default null; // 컴파일 에러
}
```

#### 애노테이션 요소의 규칙
- 요소의 타입은 기본형, String, enum, 애너테이션, Class\<T> 타입만 허용
- 괄호( )안에 매개변수를 선언 불가
- 예외를 선언 불가
- 요소를 타입 매개변수로 정의 불가

## 요소 개수에 때란 애너테이션 분류
### 1. 마커 애너테이션 (Marker Annotation)
**마커 애너테이션이란 정의된 요소가 하나도 없는 애너테이션**을 말합니다. 

우리가 흔히 아는 **@Override**가 대표적입니다.

<img width="550" alt="스크린샷 2021-10-15 오후 12 52 02" src="https://user-images.githubusercontent.com/80696862/137429575-550494a9-8432-4c70-b50d-337059475b72.png">

마커 애너테이션의 역할은

### 2. 단일값 애너테이션 (Single Value Annotation)
단일값 애너테이션은 요소가 단 한 개 존재하는 애너테이션을 말합니다.

### 3. 다중값 애너테이션 (Multi Value Annotation)
다중값 애너테이션은 요소가 두 개 이상 존재하는 애너테이션을 말합니다.

## 2-1. 애노테이션 적용 대상
### 적용 대상 설정하기
애너테이션을 정의하면 이 애너테이션을 어디에 적용시킬 것인지에 대해 설정해야 합니다. 애테이션을 적용할 수 있는 대상은 java.lang.annotation.ElementType enum 클래스에 정의되어 있습니다.

해당 애너테이션의 적용 범위를 설정하기 위해서는 **@Target** 애너테이션의 값으로 ElementType의 상수를 전달하면 됩니다. 여러 개의 값을 전달할 경우엔 배열 형식으로 전달합니다.

|enum 상수|적용 대상|
|--|--|
|TYPE|클래스, 인터페이스, enum 타입|
|FIELD|필드 (enum 상수 포함)|
|METHOD|메소드|
|PARAMETER|파라미터|
|CONSTRUCTOR|생성자|
|LOCAL_VARIABLE|지역 변수|
|ANNOTATION_TYPE|애너테이션 타입|
|PACKAGE|패키지|
|TYPE_PARAMETER|타입 파라미터 (JDK 1.8)|
|TYPE_USE||
|MODULE|모듈 (JDK 9)|
|RECORD_COMPONENT|레코드 타입 (JDK 16)`|

## 2-2. 애노테이션 유지 정책

### 유지기간 설정하기
애너테이션 정의 시 한가지 더 추가해야 할 내용은 사용 용도에 따라 어느 범위까지 애노테이션을 유지할 것인지에 대해서 설정하는 것입니다.

애노테이션 유지 정책을 위한 값은 java.lang.annotation.RetentionPolicy enum 클래스에 상수로 정의되어 있으며, **@Retention** 애너테이션의 값으로 사용됩니다.

|유지 정책|유지 범위|
|--|--|
|**SOURCE**|소스 상에서만 애노테이션 정보 유지. 소스 코드를 분석할 때에만 의미가 있으며, 바이트코드 상에는 흔적이 남지 않음|
|**CLASS**|바이트코드까지 애너테이션 정보를 유지. 런타임 시에는 정보가 없음. (기본 설정)|
|**RUNTIME**|바이트코드까지 애너테이션 정보를 유지하며, 런타임 시에도 사용 가능|

## 2-3. 빌트인 애너테이션  살펴보기
빌트인 애너테이션 몇 개를 한번 살펴보겠습니다.

### @Override
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```
**적용 범위** : 메소드
**유지 범위** : 소스 파일 : 컴파일 시 삭제

선언된 요소가 없으므로 마커 애너테이션

### @Deprecated
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
public @interface Deprecated {
    /**
     * Returns the version in which the annotated element became deprecated.
     * The version string is in the same format and namespace as the value of
     * the {@code @since} javadoc tag. The default value is the empty
     * string.
     *
     * @return the version string
     * @since 9
     */
    String since() default "";

    /**
     * Indicates whether the annotated element is subject to removal in a
     * future version. The default value is {@code false}.
     *
     * @return whether the element is subject to removal
     * @since 9
     */
    boolean forRemoval() default false;
}
```

**적용 범위** : {생성자, 필드, 지역 변수, 메소드, 패키지, 모듈, 파라미터, 타입}
**유지 범위** : 소스 파일 : 컴파일 시 삭제

#### 요소 목록 :
- **forRemoval( ) default false;**
    - @Deprecated가 달린 대상이 향후 삭제될 것인지에 대한 여부를 나타냄
    - 기본값 false

애너테이션 Deprecated는 **@Documented** 애너테이션이 붙어 있으므로 javadoc 문서화 대상입니다. 따라서 /** ~ */ 범위의 주석들이 javadoc으로 만들어집니다.

선언된 요소가 1개 이므로 단일값 애너테이션

### @Inherited
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Inherited {
}
```

**적용 범위** : 애너테이션 타입
**유지 범위** : 런타임
**javadoc 적용** : 적용

@Inherited의 적용 범위는 애너테이션 타입이므로 메타 애너테이션입니다.
또한, 요소가 없으므로 메타 애너테이션입니다.

정말 @Inherited가 붙어있는 애너테이션의 정보가 서브 클래스까지 전파되는지 확인해 보겠습니다.

```java
@Inherited
public @interface MyAnnotation {
    String hello() default "Hello~!";
}

@MyAnnotation
class Fruit {

}

class Apple extends Fruit {

}

class KoreanApple extends Apple {

}
```

### @Target
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    /**
     * Returns an array of the kinds of elements an annotation interface
     * can be applied to.
     * @return an array of the kinds of elements an annotation interface
     * can be applied to
     */
    ElementType[] value();
}
```

**적용 범위** : 애너테이션 타입
**유지 범위** : 런타임
**javadoc 적용** : 적용

### @Retention
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention {
    /**
     * Returns the retention policy.
     * @return the retention policy
     */
    RetentionPolicy value();
}
```

**적용 범위** : 애너테이션 타입
**유지 범위** : 런타임
**javadoc 적용** : 적용

## 리플렉션

### 모든 애너테이션의 조상

## 2-3. 런타임 시 애너테이션 정보 사용하기 (이것이 자바다 273)

# 3. 애너테이션 프로세서
___

참고 : [자바의 정석 3판](http://www.yes24.com/Product/Goods/24259565?OzSrank=2), [이것이 자바다](http://www.yes24.com/Product/Goods/15651484?OzSrank=2), [The Java Tutorial-Annotations](https://docs.oracle.com/javase/tutorial/java/annotations/index.html)
