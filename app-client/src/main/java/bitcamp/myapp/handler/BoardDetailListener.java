package bitcamp.myapp.handler;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class BoardDetailListener implements ActionListener {

  BoardDao boardDao;
  //BoardDao 인터페이스를 구현한 객체를 담을 변수 boardDao 선언
  
  //생성자 정의
  //생성자의 역할 = BoardDao객체를 인자로 받아서 boardDao변수에 저장함
  public BoardDetailListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  //BoardDetailListener 클래스는 BoardDao를 통해 데이터베이스에 접근
  // 데이터를 조회하고, 해당 조회 결과를 출력
  @Override
  public void service(BreadcrumbPrompt prompt) {
    int boardNo = prompt.inputInt("번호? ");
    //사용자로부터 번호를 입력받아서 boardNo변수에 저장

    Board board = boardDao.findBy(boardNo);
    //MySQLBoardDao의 findBy()메소드 호출
    //boardNo를 매개변수로 사용
    //boardNo에 해당하는 게시글의 상세정보 
    
    if (board == null) { //findby()메소드 실행 후 반환값이 없다면(게시글이 없다면)
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    //반환값이 있다면 입력받은 번호에 해당하는 게시글정보(제목, 내용, 작성자이름, 조회수, 날짜) 출력
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter().getName());
    // .getWriter()
    // board객체의 getWriter() 메소드 호출 -> member 클래스 인스턴스들 사용할 수 있음
    // .getName()
    // member객체에서 getName()메소드 호출하여 이름 가져옴
    System.out.printf("조회수: %s\n", board.getViewCount());
    System.out.printf("등록일: %tY-%1$tm-%1$td\n", board.getCreatedDate());
    
    board.setViewCount(board.getViewCount() + 1);
    //입력받은 번호에 해당하는 게시글 조회수+1
    
    boardDao.update(board);
    //update()메소드 호출
    //board 객체에 이미 저장된 제목과 내용 정보는 데이터베이스에 그대로 반영
    //오직 조회수(viewCount)만 update
  }
}











