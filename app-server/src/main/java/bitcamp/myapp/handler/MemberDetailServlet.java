package bitcamp.myapp.handler;

import java.io.PrintWriter;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/member/detail")
public class MemberDetailServlet implements Servlet {

  MemberDao memberDao;

  public MemberDetailServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Member member = memberDao.findBy(Integer.parseInt(request.getParameter("no")));

	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원</h1>");

    if (member == null) {
      out.println("<p>해당 번호의 회원이 없습니다!</p>");

    } else {
        out.println("<form action='/member/update' method='post'>");
        out.println("<table border='1'>");
        out.printf("<tr><th style='width:120px;'>번호</th>"
                + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n", member.getNo());
            out.printf("<tr><th>이름</th>"
                + " <td><input type='text' name='name' value='%s'></td></tr>\n", member.getName());
            out.printf("<tr><th>아이디</th>"
                + " <td><input type='text' name='id' value='%s'></td></tr>\n", member.getId());
            out.printf("<tr><th>나이</th>"
                    + " <td><input type='text' name='age' value='%s'></td></tr>\n", member.getName());
            out.printf("<tr><th>키</th>"
                    + " <td><input type='text' name='height' value='%s'></td></tr>\n", member.getName());
            out.printf("<tr><th>몸무게</th>"
                    + " <td><input type='text' name='weight' value='%s'></td></tr>\n", member.getName());
            out.printf("<tr><th>성별</th>\n"
                + " <td><select name='gender'>\n"
                + " <option value='M' %s>남자</option>\n"
                + " <option value='W' %s>여자</option></select></td></tr>\n",
                (member.getGender() == 'M' ? "selected" : ""),
                (member.getGender() == 'W' ? "selected" : ""));
            out.println("<tr><th>암호</th>"
                    + " <td><input type='password' name='password'></td></tr>");
            out.println("</table>");
            
            out.println("<div>");
            out.println("<button>변경</button>");
            out.println("<button type='reset'>초기화</button>");
            out.printf("<a href='/member/delete?no=%d'>삭제</a>\n", member.getNo());
            out.println("<a href='/member/list'>목록</a>\n");
            out.println("</div>");
            out.println("</form>");
          }

          out.println("</body>");
          out.println("</html>");

        }
}
        
//    prompt.printf("이름: %s\n", m.getName());
//    prompt.printf("아이디: %s\n", m.getId());
//    prompt.printf("나이: %d\n", m.getAge());
//    prompt.printf("키: %s\n", m.getHeight());
//    prompt.printf("몸무게: %s\n", m.getWeight());
//    prompt.printf("성별: %s\n", m.getGender() == 'M' ? "남성" : "여성");
//    prompt.printf("가입일: %s\n", m.getCreatedDate());
