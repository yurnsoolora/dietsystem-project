package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Food;
import bitcamp.myapp.vo.Member;

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }
    
//    Food diet = new Food();
//    int foodNo = Integer.parseInt(request.getParameter("foodNo"));
//    diet.setNo(foodNo);//이부분
    

    Board board = new Board();
    board.setMeal(request.getParameter("meal").charAt(0));
    board.setContent(request.getParameter("content"));
    board.setWriter(loginUser);
    
    int foodNo = Integer.parseInt(request.getParameter("foodNo"));

    // SearchServlet에서 초기화한 foodMap을 가져옴
    Map<Integer, Food> foodMap = new SearchServlet().getFoodMap();
    Food selectedFood = foodMap.get(foodNo);


    if (selectedFood != null) {
        int selectedCalories = selectedFood.getCalories();
        board.setCalories(selectedCalories);
    }

//    board.setDiet(diet);
 

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='1;url=/board/list?'>\n");
    out.println("<title>식단</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>식단 등록</h1>");
    try {
      InitServlet.boardDao.insert(board);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");

  }
}











