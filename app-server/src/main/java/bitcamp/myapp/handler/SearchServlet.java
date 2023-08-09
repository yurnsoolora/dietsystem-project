package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bitcamp.myapp.vo.Food;

@WebServlet("/calories/list")
public class SearchServlet extends HttpServlet {
    private Map<Integer, Food> foodMap = new HashMap<>();

    public void init() {
        // 음식 데이터 초기화
        foodMap.put(1, new Food(1, "바나나", 90));
        foodMap.put(2, new Food(2, "계란", 70));
        foodMap.put(3, new Food(3, "쌀밥", 200));
        foodMap.put(4, new Food(4, "감자", 120));
        foodMap.put(5, new Food(5, "사과", 100));
    }
    
    public Map<Integer, Food> getFoodMap() {
        return foodMap;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>음식 칼로리 검색</title></head><body>");
        out.println("<h1>음식 칼로리 검색</h1>");
        out.println("<form action='/calories/list' method='post'>");
        out.println("<label for='foodNo'>음식 선택:</label>");
        out.println("<select name='foodNo' id='foodNo'>");
        out.println("<option value='1'>바나나</option>");
        out.println("<option value='2'>계란</option>");
        out.println("<option value='3'>쌀밥</option>");
        out.println("<option value='4'>감자</option>");
        out.println("<option value='5'>사과</option>");
        out.println("</select>");
        out.println("<button type='submit'>검색</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
        int foodNo = Integer.parseInt(request.getParameter("foodNo"));
        Food selectedFood = foodMap.get(foodNo);

        HttpSession session = request.getSession();
        session.setAttribute("selectedCalories", selectedFood.getCalories());
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>음식 칼로리 검색 결과</title></head><body>");
        if (selectedFood != null) {
            out.println("<h1>검색 결과</h1>");
            out.println("<p>" + selectedFood.getFood() + "의 칼로리는 " + selectedFood.getCalories() + "입니다.</p>");
 
            
        } else {
            out.println("<p>음식을 찾을 수 없습니다.</p>");
        }
        out.println("</body></html>");
    }
}