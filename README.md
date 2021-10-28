# SpringBoot로 다시 구현하는 부코 네이버 예약 시스템

## 기술 스택을 바꿔서 다시 구현해보는 이유
1. springboot,JPA를 안 쓴지 꽤 지나서 까먹을 것 같다는 걱정.
2. 레거시 이외의 방식으로 진행하고 싶어서 <br>
   어플리케이션 코드에 더 집중하며 더 나은 코드를 만들어 보고 싶다는 생각
3. 학습 효과가 좋은 프로젝트인 것 같아서
4. 배포까지 해볼까 싶어서

## 기획서
- [swagger api](http://49.236.147.192:9090/swagger-ui.html#/%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC%20API/getCategoriesUsingGET)
- [기획서 ppt](https://docs.google.com/presentation/d/1i2IC1yIH5ACFCvCH4EMVv_3Zw2oltRvHK94amyNEKbs/edit#slide=id.p5)

## 기술 변경
- Spring => SpringBoot
- jsp => mustache
- spring jdbc template => spring jpa
- vanilla javascript => jquery

## 참고 자료
### 백엔드
- [Optional[pageRequest] not found in annotated query 오류](https://stackoverflow.com/questions/54620809/how-to-get-pagerequest-to-work-in-spring-boot-with-annotated-query)
- [컨트롤러에서 상태 코드 포함하여 응답하기](https://recordsoflife.tistory.com/501)
- JPA dto projection (interface or constructor expression)

### 프론트엔드
- [mustache docs](https://mustache.github.io/mustache.5.html)
- [Handlebars 강의](https://www.boostcourse.org/web316/lecture/254357?isDesc=false)
- [jQuery 객체](https://ktko.tistory.com/entry/jQuery-%EC%9D%98-%EC%9D%98%EB%AF%B8)
- [태그의 클래스 조작하기](https://webstudynote.tistory.com/95)
- [jQuery html 요소 삽입](https://mjmjmj98.tistory.com/29)
- [html hidden input type](http://tcpschool.com/html-input-types/hidden)
- [client side validation](https://developer.mozilla.org/en-US/docs/Web/CSS/:invalid)
