package bitcamp.util;

import java.io.PrintWriter;
//문자열 출력하는 클래스

//요청에 맞는 서블릿을 찾아서 실행하는 역할

public class DispatcherServlet implements Servlet {
	//Servlet 인터페이스 구현

  ApplicationContext iocContainer;
  //iocContainer = 스프링 프레임워크 컨테이너 = 객체관리 및 의존성 주입기능

  //생성자 정의
  public DispatcherServlet(ApplicationContext iocContainer) throws Exception {
    this.iocContainer = iocContainer;
  }

  //servelet 인터페이스의 service 메소드 구현
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Servlet servlet = (Servlet) iocContainer.getBean("/" + request.path());
    //iocContainer에서 요청에 해당하는 서블릿 객체를 가져오기 위해 getBean() 메소드 호출
    //request.path() = 요청 경로 문자열 반환
    
    if (servlet == null) { //요청에 해당하는 서블릿 객체가 없을 경우
      response.setContentType("text/plain;charset=UTF-8"); //텍스트(plain)형식 문자열 UTF-8로 처리
      PrintWriter out = response.getWriter();
      //response 객체로부터 출력 스트림 얻어옴
      out.println("해당 요청을 처리할 수 없습니다!"); 
      return;
    }
    servlet.service(request, response);
    //
  }
}