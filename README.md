# Spring_Study_1
### 인프런 김영한 강사님 - Spring 강의
#### 인프런 강의를 들으면서 기록한 필기와 클론코딩 업로드!

##### <로드맵>
1. 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술 ✔️
2. 스프링 핵심 원리 - 기본편
3. 모든 개발자를 위한 HTTP 웹 기본 지식 ✔️
4. 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술 (~ing)
5. 스프링 MVC 2편 - 백엔드 웹 개발 활용 기술
6. 스프링 DB 1편 - 데이터 접근 핵심 원리
7. 스프링 DB 2편 - 데이터 접근 활용 기술
8. 스프링 핵심 원리 - 고급편
9. 실전! 스프링 부트

----------

## 스프링 MVC 1편 - servlet 파일 

### 1. 웹 서버, 웹 애플리케이션 서버

#### 웹 서버
* HTTP 기반으로 동작
* 정적 리소스 제공

#### 웹 애플리케이션 서버(WAS)
* HTTP 기반으로 동작
* 웹 서버 기능을 포함
* 프로그램 코드를 실행하여 애플리케이션 로직을 수행 - 사용자에 따라 다른 화면을 보여줄 수 있음

#### 웹 서버 VS 웹 애플리케이션 서버
웹 서버는 정적 리소스 파일만 제공할 수 있고, 웹 애플리케이션 서버는 애플리케이션 로직까지 수행할 수 있다. 사실 웹 서버도 프로그램을 실행하는 기능을 포함하고, 웹 애플리케이션도 웹 서버의 기능을 제공한다. 그래서 이 둘은 모호하다. 분명한건 웹 애플리케이션 서버는 애플리케이션 코드를 실행하는데 더 특화되어있다!

#### WAS + DB 만으로 시스템 구성 가능
[문제점]
* WAS가 너무 많은 역할을 담당하면 서버 과부하가 우려됨
* 가장 비싼 애플리케이션 로직이 정적 리소스 때문에 수행이 어려울 수 있음
* WAS 장애 시 오류 화면 노출이 불가능

[해결책]
* 클라이언트 <-> 웹 서버 <-> 웹 애플리케이션 서버 <-> DB
* 장점: 각자의 자리에서 집중할 수 있음, 정적 리소스만 제공하는 웹 서버는 잘 죽지 않음
* 단점: 애플리케이션 로직이 동작하는 WAS는 잘 죽음.. 하지만 장애 시 오류 화면이 제공됨
* 만약 화면을 제공하는 것이 아니라 API로 데이터만 제공한다면 굳이 웹 서버를 사용할 필요는 없음
* 요즘에는 CDN이라는 서버를 사용하여 정적 리소스를 캐시함

### 2. 서블릿 

#### 서블릿 컨테이너
* 톰캣 처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라고 함
* 서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기를 관리
* 서블릿 객체는 싱글톤으로 관리: 고객의 요청이 올 때 마다 계속 객체를 생성하는 것은 비효율적, 최초 로딩 시점에 서블릿 객체를 미리 만들어두고 재활용하는 것이 효율적, 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근, 공유 변수 사용 주의, 서블릿 컨테이너 종료 시 함께 종료됨
* JSP도 서블릿으로 변환되어 사용
* 동시 요청을 위한 멀티 쓰레드 처리 지원!!


![image](https://github.com/yujinchoi20/Spring_Study_1/assets/105353163/dbe81a1b-fb9f-4313-8e2f-1c4863af9b30)
-> 서블릿을 사용하면 위의 사진과 같이 개발자의 업무가 확 줄어든다. 

#### HTTP 요청 시
* WAS는 Request, Response 객체를 새로 만들어서 서블릿 객체를 호출
* 개발자는 Request 객체에서 HTTP 요청 정보를 편하게 꺼내서 사용하면 됨
* 개발자는 Response 객체에 HTTP 응답 정보를 편리하게 입력할 수 있음
* WAS는 Response 객체에 담겨 있는 내용으로 HTTP 응답 정보를 생성할 수 있음

![image](https://github.com/yujinchoi20/Spring_Study_1/assets/105353163/27e3b067-8c47-4874-80c2-74c8c44aa7e5)
-> HTTP 요청/응답 과정

#### 서블릿(Servlet)
![image](https://github.com/yujinchoi20/Spring_Study_1/assets/105353163/22d4ec46-4a20-4bdc-bad7-2171c299380a)
* localhost:8080/hello url으로 접속해서 요청이 왔을 때 클래스 안에 있는 service 메서드가 자동으로 실행됨
* HTTP 요청 정보를 편리하게 사용할 수 있는 HttpServletRequest, HTTP 응답 정보를 편리하게 사용할 수 있는 HttpServletResponse
* 정보를 request를 통해 가져오고, response를 통해 보냄

#### 동시 요청 - 멀티 쓰레드

클라이언트 요청 -> 연결 -> 서블릿 객체 호출 -> 서블릿 실행/종료 -> 응답 ==> 이 과정에서 서블릿 객체 호출은 누가 하지?? 쓰레드가 한다!!

__[쓰레드]__
* 애플리케이션 코드를 하나하나 순차적으로 실행하는 것은 쓰레드
* 자바 메인 메서드를 처음 실행하면 main이라는 이름의 쓰레드가 실행
* 쓰레드가 없다면 자바 애플리케이션 실행이 불가능
* 쓰레드는 한 번에 하나의 코드 라인만 수행
* 동시 처리가 필요하면 쓰레드를 추가로 생성

단일 요청: 요청 -> 연결 -> 쓰레드 할당 -> 서블릿 실행/종료 -> 응답 -> 쓰레드 휴식

다중 요청: 요청 -> 연결 -> 쓰레드 할당 -> 쓰레드가 없으면 서블릿 처리 지연, 다른 요청은 쓰레드 대기 -> 이러면 2개의 요청 모두 죽어버림

--> __[해결책]__ : 쓰레드 풀 사용!!

쓰레드 풀에 쓰레드를 생성해서 저장해두고 요청이 들어올 때 쓰레드를 할당해주고 요청이 끝나면 쓰레드를 반환함.

요청 -> 연결 -> 쓰레드 풀에 요청 -> 쓰레드 할당 -> 서블릿 처리 -> 응답 -> 쓰레드 반납

만약 만들어진 쓰레드 보다 많은 요청이 들어온다면? -> 쓰레드 대기 혹은 거절(이 부분은 애플리케이션 로직을 만들 때 원하는 방식으로 설정할 수 있음)

__[쓰레드 풀]__
* 특징
  * 필요한 쓰레드를 쓰레드 풀에 보관하고 관리
  * 쓰레드 풀에 쌩성 가능한 쓰레드의 최대치를 관리(톰캣은 최대 200개 기본 설정)
* 사용
  * 쓰레드가 필요하면 이미 생성되어 있는 쓰레드를 쓰레드 풀에서 꺼내서 사용
  * 사용을 종료하면 쓰레드 풀에 반납
  * 최대 쓰레드가 모두 사용중이어서 쓰레드 풀에 쓰레드가 없다면? -> 요청을 거정하거나 대기 시킴
* 장점
  * 쓰레드가 미리 생성되어 있기에 쓰레드를 생성하고 종료하는 비용이 절약되고 응답 시간이 빨라짐
  * 생성 가능한 쓰레드의 최대치가 있으므로 너무 많은 요청이 들어와도 기존 요청은 안전하게 처리 가능함

__[실무 팁!!]__
* WAS의 주요 튜닝 포인트는 최대 쓰레드 수이다!1
* 만약 이 값을 너무 낮게 설정하면 CPU 낭비로 AWS에 많은 돈을 쓰게 되고, 너무 높게 설정하면 서버가 죽어버릴 수도 있다.
* 장애 발생 시, 클라우드면 서버부터 늘리고 이후에 튜닝을 하면 됨. 클라우드가 아니면 바로 튜닝을 하는 수밖에 없음.

__핵심!__
* WAS는 멀티 쓰레드에 대한 부분을 처리해줌
* 개발자가 멀티 쓰레드 관련 코드를 신경쓰지 않아도 됨
* 개발자는 마치 싱글 쓰레드 프로그래밍을 하듯이 편리하게 소스 코드를 개발하면 됨
* 다만 멀티 쓰레드 환경이므로 싱글통 객체는 주의해서 사용해야 함 (공유 변수 조심)

-----------------

![image](https://github.com/yujinchoi20/Spring_Study_1/assets/105353163/24155bcc-63c8-4df7-a399-75d765a5d2ec)

-----------------

### Servlet과 JSP 차이, 한계

#### JSP

* java 언어를 기반으로 하는 Servlet Side 스크립트 언어이다. 
* Servlet은 java 코드 안에 html 코드가 존재하지만, JSP는 html 코드 안에 java 코드가 존재한다. 
* Servlet을 보완하고 기술을 확장한 스크립트 방식의 표준이다.
* Servlet의 모든 기능을 가지면서 추가적인 기능을 가진다.
* Servlet에 비해 요청 결과를 나타내는 뷰 화면을 작성하는데 유용하다.
* JSP 파일이 수정된 경우 재배포 할 필요 없이 WAS가 알아서 재배포한다.

![image](https://github.com/yujinchoi20/Spring_Study_1/assets/105353163/67270998-9c33-4342-a491-d1652cf069fa)
