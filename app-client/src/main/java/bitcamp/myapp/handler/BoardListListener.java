package bitcamp.myapp.handler;

import java.text.SimpleDateFormat;
import java.util.List;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardListListener implements ActionListener {

  BoardDao boardDao;
  //BoardDao 인터페이스를 구현한 객체를 담을 변수 boardDao 선언
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
  //SimpleDateFormat : 날짜와 시간을 원하는 형식으로 변환

  //생성자 정의
  //생성자의 역할 = BoardDao객체를 인자로 받아서 boardDao변수에 저장함
  public BoardListListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 제목, 작성자, 조회수, 등록일");
    System.out.println("---------------------------------------");

    List<Board> list = boardDao.list();
    //MySQLBoardDao의 list()메소드 호출 후 반환된 리스트를 list에 저장

    for (Board board : list) { //list변수에 저장된 board 객체들을 순회하면서
      System.out.printf("%d, %s, %s, %d, %s\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter().getName(), 
          board.getViewCount(),
          dateFormatter.format(board.getCreatedDate()));
      // 게시글 목록 (번호, 제목, 작성자이름, 조회수, 날짜) 출력
    }
  }

}











