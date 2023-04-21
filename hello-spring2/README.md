## Spring 입문

### 1. Controllor

#### 1 ) thymeleaf 템플릿 엔진 동작 확인 

![image](https://user-images.githubusercontent.com/105353163/233531677-3a318715-4db7-4f2b-99af-7d81f7bdcaf9.png)
* localhost:8080/hello 접속 (@GetMapping("hello"))
* HelloController 에서 리턴 값("hello")과 model 객체를 받는다. 
* 리턴 값에 따라 hello.html의 ${data} 에 "hello!!"를 넣어준다.
* viewResolver가 컨트롤러에서 리턴 값을 받아 문자로 반환하여 hello.html를 완성한 후 웹 브라우저에 전달한다.  

#### 2) 정적 컨텐츠
![image](https://user-images.githubusercontent.com/105353163/233533091-d73c5054-5c8f-4dea-91d8-bd47a2463ae3.png)
* localhost:8080/hello-static.html 접속 -> 컨트롤러와 viewResolver를 거치지 않고 바로 웹 브라우저에 화면을 전달한다. 

#### 3) MVC와 템플릿 엔진
![image](https://user-images.githubusercontent.com/105353163/233533354-9781eb4f-feab-4217-a365-4a9c2e72ee0a.png)
* @RequestParam("가져올 데이터명") 데이터타입 데이터변수 -> 'localhost:8080/hello-mvc?name=입력값' 접속
* 데이터변수인 name 에 아무 값을 넣지 않은채로 접속하면 오류가 난다. 

#### 4) @ResponseBody 사용 (viewResolver 사용x)
![image](https://user-images.githubusercontent.com/105353163/233533774-d74aff62-c112-4298-a2c7-f0c142c6c1b5.png)
* HTTP의 BODY에 문자 내용을 직접 반환 
* 'localhost:8080/hello-api' 접속 
* viewResolver 대신 HttpMessageConverter가 동작된다.
* @ResponseBody를 사용하고 객체를 반환하면 객체가 json 형태로 변환된다. 


