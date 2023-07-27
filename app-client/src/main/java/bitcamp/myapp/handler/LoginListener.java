package bitcamp.myapp.handler;

import bitcamp.myapp.ClientApp;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class LoginListener implements MemberActionListener {

  MemberDao memberDao;
//MemberDao 인터페이스를 구현한 객체를 담을 변수 memberDao 선언
  
  //생성자 정의
  //생성자의 역할 = MemberDao객체를 인자로 받아서 memberDao변수에 저장함
  public LoginListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) {
    while (true) { //로그인 성공할때 까지 무한루프
      Member m = new Member();
      //Member 객체 생성 (로그인할 아이디, 비밀번호 저장할 객체)
      m.setId(prompt.inputString("아이디? "));
      //사용자로부터 아이디 입력받고 member객체의 아이디에 저장
      m.setPassword(prompt.inputString("암호? "));
    //사용자로부터 암호 입력받고 member객체의 암호에 저장
      
      Member loginUser = memberDao.findByIdAndPassword(m);
      // findeByIdAndPassword() 메소드 호출
      // 매개변수 = 방금 입력받은 아이디, 암호 정보가 담긴 객체 m
      // 반환받은 객체 m을 loginUser에 담기
      
      if (loginUser == null) { //findeByIdAndPassword()메소드 실행결과가 null(조회된 결과가 없다 = 아이디나 암호가 틀릴경우)
        System.out.println("회원 정보가 일치하지 않습니다.");
      } else {
    	  //조회된 결과가 있을 경우
    	  //clientApp 클래스의 loginUser 멤버변수에 조회된 회원정보 객체 loginUser를 할당
    	  // 로그인된 회원정보가 ClientApp 클래스에 저장됨
        ClientApp.loginUser = loginUser;
        break;
      }
    }
  }
}
