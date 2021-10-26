# 따라하며 배우는 도커와 CI 환경
> 인프런 강의 [따라하며 배우는 도커와 CI 환경](https://www.inflearn.com/course/%EB%94%B0%EB%9D%BC%ED%95%98%EB%A9%B0-%EB%B0%B0%EC%9A%B0%EB%8A%94-%EB%8F%84%EC%BB%A4-ci/dashboard)를 보고 정리한 자료입니다.

# 1. 도커 기본
## 1-1. 도커와 컨테이너의 정의
### 도커란 무엇인가?
[도커](https://www.docker.com/resources/what-container)는 컨테이너를 사용하며 응용 프로그램을 더 쉽게 만들고 배포하고 실행할 수 있도록 설계된 도구이자 컨테이너 기반의 오픈소스 가상화 플랫폼이며 생태계이다.

- 도커를 통해 응용 프로그램을 인프라에서 분리 가능
- 도커를 통해 응용 프로그램을 관리하듯이 인프라 관리 가능
- 컨테이너들은 서로 독립적이다

### 컨테이너란 무엇인가?
컨테이너는 응용 프로그램이 한 컴퓨팅 환경에서 다른 컴퓨팅 환경에서 빠르고 안정적으로 실행될 수 있도록 코드와 모든 의존성을 패키징하는 소프트웨어의 표준 단위이다.

<img width="360" alt="스크린샷 2021-10-19 오후 4 59 52" src="https://2.bp.blogspot.com/-7WgG-2vqQXg/Wz2i-jk-yCI/AAAAAAAABbU/nhvE7oRjaL0fmAjnfKgCvmdsPWZMKOQrQCLcBGAs/s1600/run%2Bdocker%2Bcontainer.jpg">

### 컨테이너 이미지란?
컨테이너 이미지란 코드, 런타임, 시스템 도구, 시스템 라이브러리 및 설정과 같이 응용 프로그램을 실행하는데 필요한 모든 것을 포함하는 가볍고 독립적이며 실행 가능한 소프트웨어 패키지이다.

- 컨테이너 이미지는 런타임에 컨테이너로 인스턴스화된다
- 도커의 경우 컨테이너 이미지가 도커 엔진에 의해 실행될 때 도커 컨테이너가 된다.

## 1-2. 도커의 구조
- 도커는 기본적으로 클라이언트 - 서버 패턴을 사용
- 도커 데몬은 도커 컨테이너를 빌드, 실행 및 배포등의 무거운 작업을 담당
- 도커 클라이언트는 도커 데몬과 통신

<img width="700" alt="스크린샷 2021-10-19 오후 4 59 52" src="https://docs.docker.com/engine/images/architecture.svg">

### 도커 데몬 (Docker deamon)
도커 데몬(dockerd)은 도커 API대한 요청을 처리하고 도커 이미지와 컨테이너, 네트워크 및 볼륨과 같은 도커의 객체들을 관리하는 지속적 백그라운드 프로세스이다.

### 도커 클라이언트 (Docker client)
도커 클라이언트는 도커 API에 서비스를 요청하는 주체로, 실제 도커 사용자가 도커를 사용하는 방법이다.

- 도커 사용자는 도커 클라이언트를 통해 dorkerd에 명령을 보낸다
- 도커 명령어는 도커 데몬이 받아서 처리한다
- 도커 클라이언트는 둘 이상의 도커 데몬과 통신할 수 있다

### 도커 레지스트리 (Docker registries)
도커 레지스트리는 도커 이미지를 저장하고 있는 저장소이다.

### 도커 허브 (Docker Hub)
[도커 허브](https://hub.docker.com/)는 도커에서 제공하는 클라우드 기반 공식 이미지 저장소이다.

### 도커 객체 (Docker objects)
도커 객체는 이미지, 컨테이너, 네트워크, 볼륨, 플러그인 등 도커를 구성하는 요소들을 말한다.

### 도커 이미지 (Docker images)
도커 이미지는 도커 컨테이너를 생성하는데 필요한 파일과 설정값 등이 포함되어 있는 읽기전용 템플릿이다. 상태값을 갖지 않고 불변이다. 동일 이미지로 여러 개의 컨테이너를 실행할 수 있지만, 컨테이너의 상태가 바뀌거나 컨테이너가 삭제되더라도 이미지는 변하지 않고 그대로이다

### 도커 컨테이너 (Docker container)
도커 컨테이너는 도커 이미지의 인스턴스이다.
- **docker ps** 명령어를 사용해서 현재 실행중인 인스턴스 목록을 확인할 수 있다

### 도커 엔진 (Docker engine)
도커의 핵심으로 컨테이너를 생성하고 실행하는 클라이언트 - 서버 기술이다. 도커 엔진에는 백그라운드에서 지속적으로 실행되고 있는 프로세스인 도커 데몬과, 도커 API, 도커 CLI 등을 포함한다.

<img width="450" alt="스크린샷 2021-10-20 오후 6 31 18" src="https://user-images.githubusercontent.com/80696862/138067765-eb86e0ca-38e5-4971-b2f8-d08296a94b13.png">

# 2 가상 머신과 도커의 차이점
## 2-1. 하이퍼 바이저 기반의 가상 머신 구조

<img width="600" alt="스크린샷 2021-10-19 오후 9 33 37" src="https://user-images.githubusercontent.com/80696862/137909940-3ac2a36b-4a06-4ebf-a2b7-3c3f16d67b0d.png">

#### 하이퍼 바이저란?
- 하이퍼바이저는 호스트 시스템에서 다수의 게스트 OS를 구동할 수 있게 하는 소프트웨어
- 하드웨어를 가상화하면서 하드웨어와 각 가상 머신 사이에서 동작하는 중간 관리자 역할
- 일반적인 소프트웨어처럼 호스트 OS 위에서 동작함

#### 하이퍼바이저 기반 VM 특징
- 각 VM은 서로 독립된 가상 하드웨어 자원을 할당받는다
- 각 VM은 서로 논리적으로 구분된 다른 공간이다
- **한 VM 영역에서 에러가 발생하도 나머지 VM 영역에 전파되지 않는다**
- 대표적으로 VMware, Virtual Box

## 2-2. 도커 기술이 왜 탄생되었는가?
### 가상 머신은 무겁다
- 각 가상머신은 또 하나의 운영체제(Guest OS)가 필요하다
- 각 가상머신에는 Guest OS, 응용 프로그램과 여러 라이브러리들이 포함되어 있으며 많이 무겁다
- 이 방법은 일반적으로 대형이고, 느리고, 유연하지 않으며 유지보수가 까다롭다
- 부팅 속도도 상대적으로 느리다

<img width="400" alt="스크린샷 2021-10-19 오후 9 23 20" src="https://user-images.githubusercontent.com/80696862/137908370-98fd8951-df16-4716-a004-1cee2c4be45c.png">

### 도커의 장점, 도커는 유연하고 가볍다
- **빠른 시작과 종료 속도**
    - 도커의 컨테이너들은 커널을 공유한다
    - 운영체제 입장에서는 컨테이너를 생성하는 것은 프로세스를 생성하는 것과 같다
    - 따라서 빠르게 시작하고 종료할 수 있다
- **높은 집적도**
    - 컨테이너는 커널이 직접 프로세스를 조작하여 격리된 공간을 구성하기 때문에 컴퓨터상에서 동작하는 OS는 하나
    - 최소 2개 이상의 OS가 동작하는 VM에 비해 소비하는 자원은 적다
    - 여러 개의 컨테이너를 만들어 실행중이라고 해도 OS는 하나이므로 고밀도화가 가능
    *(추후, 수 많은 컨테이너들을 관리하기 위한 기술로 쿠버네티스가 등장)*
    - 또한 컨테이너에서 실행되는 프로세스를 위한 메모리만 필요하기 때문에 더 낮은 사양의 환경에서 더욱 활용도가 높아짐
- **낮은 오버헤드**
    - 가상화를 위한 하드웨어 애뮬레이트 단계 없이 격리된 공간을 만들기 때문에 오버헤드가 줄어든다
- **애플리케이션 컨테이너 지원**
    - 컨테이너는 목적에 적합한 프로세스만 존재하는 환경을 만들 수 있다
    - 예를 들어 웹서버용 컨테이너라면 Apache Httpd 프로세스만 존재하는 컨테이너를 만들 수 있다
    - 이러한 환경을 애플리케이션 컨테이너라고 부른다
<br>

**기존의 가상화 기술들은 하드웨어 환경을 가상화하는데 비해 컨테이너 방식은 OS에서 관리되기 때문에 “OS 수준의 가상화”라고도 불린다.**
<br>
<img width="900" alt="스크린샷 2021-10-20 오후 12 05 43" src="https://user-images.githubusercontent.com/80696862/138021633-85832a66-a76d-4599-847c-17e0c351131d.png">

### 도커의 단점
도커의 컨테이너 구조 특성에서 비롯된 단점들도 있다.

- **호스트 OS에 종속적**
    - 실행 중인 커널은 항상 호스트 OS의 커널임
    - 컨테이너에 CentOS를 설치하여도 호스트 OS가 ununtu면 실행중인 커널도 ubuntu의 커널
- **컨테이너별 커널 구성 불가**

### 도커와 가상머신의 차이점
- **애뮬레이션 차이**
    - 가상 머신은 OS 위에 하드웨어를 애뮬레이션하여 그 위에 Guest OS를 올리고 프로세스를 실행
    - 도커는 하드웨어 애뮬레이션 없이 커널을 공유해서 바로 프로세스를 실행
- **격리 수준의 차이**
    - 가상 머신에서 실행되고 있는 프로세스를 호스트 OS에서 확인 불가
    - 도커 컨테이너에서 실행되고 있는 프로세스를 호스트 OS에서 확인 가능
- **프로세스 차이**
    - 가상 머신에서 실행되는 프로세스는 호스트 OS에서 확인 불가
    - 도커 컨테이너 내부에서 실행되는 프로세스를 호스트 OS에서 확인 가능
- **속도 차이**
    - 가상 머신은 각각 별도의 커널과 라이브러리 등을 위한 공간을 할당받아서 무겁고 Guset OS를 추가적으로 부팅하므로 느리다
    - 도커는 모든 컨테이너가 한 커널을 공유하므로 가볍고 빠르다

## 2-3. 도커가 컨테이너를 격리하는 방법
[이 글을 많이 참고했습니다](https://doitnow-man.tistory.com/180)

도커는 **리눅스 커널**과 함께 Cgroups 및 네임스페이스와 같은 커널의 기능을 사용해 프로세스들을 분리함으로써 서로 독립적으로 실행될 수 있도록 만든다. 이 때 사용되는 기술은 3 가지이다.

<img width="400" alt="스크린샷 2021-10-20 오후 12 05 43" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile25.uf.tistory.com%2Fimage%2F99E5CD435C50083036DEB3">

**1. libcontainer**
- 도커 엔진이 OS에서 독립적이기 위해 제공되는 드라이버
    - 기존의 도커는 LNX 기반으로 Linux 플랫폼에 종속적이었음
    - 하지만 도커를 Linux 이외의 OS에서도 작동시키기 위해 사용됨
    - 도커 0.9버전 이후부터 기본적으로 지원됨
    - Go언어로 제작됨

**2. Cgroup(컨트롤 그룹)**
CPU, 시스템 메모리, 네트워크 대역폭과 같은 자원이나 이러한 자원의 조합을 시스템에서 실행 중인 사용자 정의 작업 그룹(프로세스) 간에 할당하는 리눅스 커널 기술

- 호스트 OS가 관리하는 여러 자원을 중앙에서 제어하기 위한 도구
- cgroup을 사용하여 시스템 관리자는 시스템 자원 할당, 우선 순위 지정, 거부, 관리, 모니터링과 같은 세밀한 제어 가능
- 하드웨어 자원은 작업 및 사용자 간을 신속하게 분배하여 전체적인 효율성을 향상
- 자원 사용량이 많은 애플리케이션을 cgroup에 넣어 자원 사용량 제한도 가능

<img width="440" alt="스크린샷 2021-10-20 오후 12 05 43" src="https://media.vlpt.us/images/shlee7131/post/25dcfb77-1beb-4025-89c0-8160c73ed8bd/image.png">

**3. namespaces**
- 시스템 자원을 해당 프로세스의 전용 자원처럼 보이게 하고, 다른 프로세스와 격리시키는 기술
- 프로세스간 격리된 환경을 제공하는 경량 프로세스 가상화 기술

<img width="440" alt="스크린샷 2021-10-20 오후 12 05 43" src="https://media.vlpt.us/images/shlee7131/post/b03ff253-57d6-4c38-9efc-2503818ece64/image.png">

### 내 컴퓨터는 macOS인데 어떻게 설치도 안한 리눅스를 사용하는가?

Cgroup과 namespaces 기능은 Linux 커널의 기능인데 내 컴퓨터의 OS는 mac이다. 그런데 어떻게 Linux 커널의 기능을 사용할 수 있을까? 위에서 설명했듯이 도커는 **libcontainer**를 통해 리눅스 커널의 기능들을 이용한다. 하지만 이것을 사용하려면 리눅스 커널이 존재해야 한다. 그렇다면 리눅스 OS는 어디에 설치되어있을까?

도커는 어떤 운영체제든 리눅스 VM 위의 리눅스 커널을 통해 동작한다. 그래서 도커에서도 Cgroup과 namespaces 기능을 사용할 수 있는 것이다. 도커를 설치하면 같이 리눅스 VM도 설치된다.

**docker version 명령어를 통해 도커 엔진의 OS를 확인할 수 있다**

<img width="600" alt="스크린샷 2021-10-20 오후 7 35 58" src="https://user-images.githubusercontent.com/80696862/138077912-647f0d5f-b470-4c40-bb09-c00603e242c5.png">

### 리눅스 컨테이너와 도커와의 차이점

<img width="650" alt="스크린샷 2021-10-20 오후 12 05 43" src="https://www.redhat.com/cms/managed-files/traditional-linux-containers-vs-docker_0.png">

# 3. 도커 컨테이너와 생명 주기
## 3-1. 이미지로 컨테이너 만들기
> docker run 이미지명

- **docker run 명령어 진행 순서**
    1. 도커 클라이언트가 명령어를 도커 데몬에 요청
    2. 도커 데몬이 해당 이미지명과 일치하는 이미지가 캐시되어 있는지 확인
    3. 없다면 도커 허브에서 해당 이미지를 pull하고, 있으면 컨테이너로 생성

### 도커 이미지가 저장되는 장소

## 3-2. 컨테이너의 생명 주기
### 컨테이너를 실행하는 과정
<img width="800" alt="스크린샷 2021-10-26 오후 5 52 36" src="https://user-images.githubusercontent.com/80696862/138844899-a01ea7e2-9284-44cb-86af-c26b44887833.png">

1. 도커 컨테이너를 생성(create)한 후 
    - docker create 이미지명
2. 도커 컨테이너를 시작(start)한다
    - docker start -a 컨테이너ID

### 컨테이너를 중지하는 과정
<img width="470" alt="스크린샷 2021-10-26 오후 5 52 17" src="https://user-images.githubusercontent.com/80696862/138844670-a7305126-a444-483e-ac78-dc5a1cbfd981.png">

**도커 컨테이너를 중지시키는 방법은 두 가지가 있다**
1. **stop**
    - 컨테이너를 graceful하게 중지시킨다
    - 기존에 하던 작업들을 모두 수행하고 컨테이너를 중지시킨다 (마음의? 정리 시간을 준다)
2. **kill**
    - kill 명령 즉시 해당 컨테이너를 중지시킨다

### 컨테이너를 삭제하는 과정
<img width="500" alt="스크린샷 2021-10-26 오후 5 51 24" src="https://user-images.githubusercontent.com/80696862/138844549-6d55d6a3-64f4-452b-9566-d5f3931583c0.png">

- 실행중인 컨테이너를 삭제하려면 우선 해당 컨테이너를 중지시켜야 한다
- **docker ps** 명령어로 실행중인 컨테이너들을 우선 중지시켜준다

#### 중지된 컨테이너 삭제하기
> docker rm 아이디/이름

#### 모든 컨테이너 삭제하기
> docker rm `docker ps -a -q`

#### 이미지 삭제하기
> docker rmi 이미지ID

#### 사용하지 않는 리소스 일괄 삭제하기
> docker system prune [OPTIONS]
- 도커를 정리하고 싶을 때 사용
- 하지만 이미 실행중인 컨테이너에게는 영향을 주지 않는다

## 3-3. 실행중인 컨테이너에 명령어 전달하기
### 레디스 컨테이너에 명령어 전달하기
#### 1. 레디스 컨테이너 실행하기
> docker run redis

#### 2, 레디스 CLI 접속하기
> redis-cli
- 터미널에서 위의 명령어를 입력하면 에러가 발생한다
- 왜냐하면 터미널은 레디스 컨테이너 외부에 존재하기 때문이다
- 따라서 터미널에서 입력하는 명령어를 컨테이너 내부로 전달해야 한다
- 이 때 사용되는 옵션이 **exec**이다
    - 이미 실행중인 컨테이너에 명령어를 전달할 때 사용되는 명령어
    - exec과 함께 **-it**도 붙여주어야 함
        - -it는
            - **i** : iteractive, 상호적인
            - **t** : terminal
            - -it 옵션이 없으면 redis-cli를 키기만 하고 바로 빠져나와 사용하지 못한다

> docker exec -it 컨테이너ID redis-cli

docker ps로 레디스의 컨테이너 아이디를 확인한 후 명령어를 전달한다


# 4. 도커 이미지 만들기

___
**참고문헌** :
- [따라하며 배우는 도커와 CI 환경 - 인프런 강의](https://www.inflearn.com/course/%EB%94%B0%EB%9D%BC%ED%95%98%EB%A9%B0-%EB%B0%B0%EC%9A%B0%EB%8A%94-%EB%8F%84%EC%BB%A4-ci/dashboard)
- [What is a Container?](https://www.docker.com/resources/what-container)
- [초보를 위한 도커 안내서 - 도커란 무엇인가?](https://subicura.com/2017/01/19/docker-guide-for-beginners-1.html)
- [서버 가상화 기술의 진화: VM과 컨테이너](https://library.gabia.com/contents/infrahosting/7426/)
- ["컨테이너 혁명을 주도하는" 도커의 의미와 장단점](https://www.itworld.co.kr/t/62076/%EA%B0%80%EC%83%81%ED%99%94/203644)
- [Docker 개념 및 정리](https://dejavuhyo.github.io/posts/docker/)
- [컨테이너란? 리눅스의 프로세스 격리 기능](https://www.44bits.io/ko/keyword/linux-container#%EB%A6%AC%EB%88%85%EC%8A%A4-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88%EB%9E%80)
- [Docker Hub and Docker Registries: A beginner’s guide](https://jfrog.com/knowledge-base/docker-hub-and-docker-registries-a-beginners-guide/)
- [리눅스 컨테이너(Linux Container): 개념, 종류, 장점, 설치](https://www.redhat.com/ko/topics/containers/whats-a-linux-container)
- [Docker 그리고 Linux 컨테이너 기술들](http://www.opennaru.com/openshift/container/what-is-the-difference-between-docker-lxd-and-lxc/)
- [도커 컨테이너는 가상머신인가요? 프로세스인가요?](https://www.44bits.io/ko/post/is-docker-container-a-virtual-machine-or-a-process)
- [1. Docker 가상화 서버 개념 (리눅스용)](https://doitnow-man.tistory.com/180)
- [Linux) Doker와 Container의 탄생과 설명, 차이점](https://hwan-shell.tistory.com/116)
