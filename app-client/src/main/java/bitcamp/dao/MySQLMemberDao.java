package bitcamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;

public class MySQLMemberDao implements MemberDao {

  Connection con;

  public MySQLMemberDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) {
    try (PreparedStatement stmt = con.prepareStatement(
          "insert into myapp_member(name, id, age, height, weight, gender, password)"
          + " values(?,?,?,?,?,?,sha1(?))")) {
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getId());
      stmt.setInt(3, member.getAge());
      stmt.setInt(4, member.getHeight());
      stmt.setInt(5,member.getWeight());
      stmt.setString(6, String.valueOf(member.getGender()));
      stmt.setString(7, member.getPassword());

      stmt.executeUpdate();
    
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (PreparedStatement stmt = con.prepareStatement(
            "select member_no, name, id, age, height, weight, gender"
            + " from myapp_member"
            + " order by name asc");
            ResultSet rs = stmt.executeQuery()) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setId(rs.getString("id"));
        m.setAge(rs.getInt("age"));
        m.setHeight(rs.getInt("height"));
        m.setWeight(rs.getInt("weight"));
        m.setGender(rs.getString("gender").charAt(0));

        list.add(m);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
            "select member_no, name, id, age, height, weight, gender, created_date"
            + " from myapp_member"
            + " where member_no=?")) {

      stmt.setInt(1,no);

      try (ResultSet rs = stmt.executeQuery()) {
      if (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setId(rs.getString("id"));
        m.setAge(rs.getInt("age"));
        m.setHeight(rs.getInt("height"));
        m.setWeight(rs.getInt("weight"));
        m.setGender(rs.getString("gender").charAt(0));
        m.setCreatedDate(rs.getDate("created_date"));
        
        return m;
      }
      return null;

    } 
  } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
 //MySQLMemberDao 클래스가 MemberDao 인터페이스를 구현했음
  //MemberDao 인터페이스에 정의된 findByIdAndPassword() 메서드를 오버라이딩
  public Member findByIdAndPassword(Member param) {
	//findByIdAndPassword() 메서드 정의 (게시글 목록)
	  //반환값 : member 객체
    try (PreparedStatement stmt = con.prepareStatement(
              "select member_no, name, id, age, height, weight, gender, created_date"
            + " from myapp_member"
            + " where id=? and password=sha1(?)")) {

    //param = LoginListener의 m
      stmt.setString(1, param.getId());
      //1번 물음표 = 사용자가 입력한 아이디
      stmt.setString(2, param.getPassword());
      //2번 물음표 = 사용자가 입력한 암호
      
      try (ResultSet rs = stmt.executeQuery()) {
    	//조회된 결과가 있을경우
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setName(rs.getString("name"));
          m.setId(rs.getString("id"));
          m.setAge(rs.getInt("age"));
          m.setHeight(rs.getInt("height"));
          m.setWeight(rs.getInt("weight"));
          m.setGender(rs.getString("gender").charAt(0));
          m.setCreatedDate(rs.getDate("created_date"));
          return m;
        }
        //조회된 결과가 없는 경우
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Member member) {
    try (PreparedStatement stmt = con.prepareStatement(
          "update myapp_member set"
              + " name=?,"
              + " id=?,"
              + " age=?,"
              + " height=?,"
              + " weight=?,"
              + " gender=?,"
              + " password=sha1(?)"
              + " where member_no=?")) {

          stmt.setString(1, member.getName());
          stmt.setString(2, member.getId());
          stmt.setInt(3, member.getAge());
          stmt.setInt(4, member.getHeight());
          stmt.setInt(5, member.getWeight());
          stmt.setString(6, String.valueOf(member.getGender()));
          stmt.setString(7, member.getPassword());
          stmt.setInt(8, member.getNo());

          return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
          "delete from myapp_member"
    		+ " where member_no=?")) {

            stmt.setInt(1, no);

            return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
