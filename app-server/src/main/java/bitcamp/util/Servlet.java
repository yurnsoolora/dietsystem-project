package bitcamp.util;

//servlet 인터페이스 정의
// 1) 서블릿 동작정의
// 2) 요청,응답 객체 전달 (HttpServletRequest request와 HttpServletResponse response 객체)
// 3) 예외처리

public interface Servlet {
  void service(HttpServletRequest request, HttpServletResponse response) throws Exception;
}