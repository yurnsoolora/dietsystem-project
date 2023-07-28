package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class MemberDetailListener implements ActionListener {

  MemberDao memberDao;

  public MemberDetailListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
    	prompt.println("해당 번호의 회원이 없습니다!");
      return;
    }

    prompt.printf("이름: %s\n", m.getName());
    prompt.printf("아이디: %s\n", m.getId());
    prompt.printf("나이: %d\n", m.getAge());
    prompt.printf("키: %s\n", m.getHeight());
    prompt.printf("몸무게: %s\n", m.getWeight());
    prompt.printf("성별: %s\n", m.getGender() == 'M' ? "남성" : "여성");
    prompt.printf("가입일: %s\n", m.getCreatedDate());
  }
}
