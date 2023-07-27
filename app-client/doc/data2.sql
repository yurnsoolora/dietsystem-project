-- myapp_member 테이블 예제 데이터
insert into myapp_member(member_no, name, id, age, height, weight, gender, password) 
  values(1, 'aaa', 'aaa111', '24', '164', '55', 'W', sha1('1111'));
insert into myapp_member(member_no, name, id, age, height, weight, gender, password) 
  values(2, 'bbb', 'bbb222', '27', '150', '30', 'M', sha1('1111'));
insert into myapp_member(member_no, name, id, age, height, weight, gender, password) 
  values(3, 'ccc', 'ccc333', '56', '179', '22', 'W', sha1('1111'));
insert into myapp_member(member_no, name, id, age, height, weight, gender, password) 
  values(4, 'ddd', 'ddd444', '15', '189', '26', 'M', sha1('1111'));
insert into myapp_member(member_no, name, id, age, height, weight, gender, password) 
  values(5, 'eee', 'eee555', '70', '145', '15', 'W', sha1('1111'));
insert into myapp_member(member_no, name, id, age, height, weight, gender, password) 
  values(6, 'fff', 'fff666', '44', '160', '45', 'M', sha1('1111'));


-- myapp_board 테이블 예제 데이터
insert into myapp_board(board_no, title, content, writer, password)
  values(11, '아침', '바나나', '1', sha1('1111'));
insert into myapp_board(board_no, title, content, writer, password)
  values(12, '점심', '닭가슴살', '1', sha1('1111'));
insert into myapp_board(board_no, title, content, writer, password)
  values(13, '저녁', '토마토', '3', sha1('1111'));
insert into myapp_board(board_no, title, content, writer, password)
  values(14, '아침', '식단도시락', '4', sha1('1111'));
insert into myapp_board(board_no, title, content, writer, password)
  values(15, '점심', '바나나', '5', sha1('1111'));
insert into myapp_board(board_no, title, content, writer, password)
  values(16, '저녁', '굶음', '6', sha1('1111'));