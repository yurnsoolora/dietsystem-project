package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/form")
public class BoardFormServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

//    int category = Integer.parseInt(request.getParameter("category"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글</h1>");
//    out.println("<form action='/diet/form' method='post'>");
    out.println("시간 <input type='text' name='meal'><br>");
    out.println("내용 <textarea name='content'></textarea><br>");
    out.println("음식 선택: ");
    out.println("<select name='foodNo' id='foodNo'>");
    out.println("<option value='1'>바나나</option>");
    out.println("<option value='2'>계란</option>");
    out.println("<option value='3'>쌀밥</option>");
    out.println("<option value='4'>감자</option>");
    out.println("<option value='5'>사과</option>");
    out.println("</select><br>");
    out.println("<input type='hidden' name='calories' id='calories' value=''>");
    out.println("<button>등록</button>");
    out.println("</form>");

    // 자바스크립트를 통해 음식 선택 시 칼로리 값을 hidden input에 설정하는 코드 추가
    out.println("<script>");
    out.println("document.getElementById('foodNo').addEventListener('change', function() {");
    out.println("var selectedFood = document.getElementById('foodNo');");
    out.println("var selectedCalories = selectedFood.options[selectedFood.selectedIndex].getAttribute('data-calories');");
    out.println("document.getElementById('calories').value = selectedCalories;");
    out.println("});");
    out.println("</script>");

    out.println("</body>");
    out.println("</html>");

  }
}










