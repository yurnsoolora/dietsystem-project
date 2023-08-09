package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.vo.Member;

@WebServlet("/bmi/list")
public class BMIListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>이름</th> <th>아이디</th> <th>나이</th> <th>키</th> <th>몸무게</th> <th>성별</th> <th>bmi</th> <th>비만도</th></tr>");
    out.println("</thead>");

    List<Member> list = InitServlet.memberDao.findAll();
    for (Member m : list) {
    	String bmiResult = ""; // 비만도 결과 문자열 초기화
        
        float bmi = m.calculateBMI();
        String formattedBmi = String.format("%.1f", bmi);
        
        if (bmi < 20) {
            bmiResult = "저체중";
        } else if (bmi >= 20 && bmi < 25) {
            bmiResult = "정상";
        } else if (bmi >= 25 && bmi < 30) {
            bmiResult = "과체중";
        } else {
            bmiResult = "비만";
        }
        
    	out.printf("<tr>"
    	          + " <td>%d</td>"
    	          + " <td><a href='/member/detail?no=%d'>%s</a></td>"
    	          + " <td>%s</td>" //id
    	          + " <td>%d세</td>" //age
    	          + " <td>%dcm</td>" //height
    	          + " <td>%dkg</td>" //wei
    	          + " <td>%s</td>" //gen
    	          + " <td>%s</td>" //bmi
    	          + " <td>%s</td></tr>\n", //res
    	          m.getNo(), m.getNo(), m.getName(), m.getId(), m.getAge(), m.getHeight(), m.getWeight(), m.getGender(), formattedBmi, bmiResult);
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }

}
