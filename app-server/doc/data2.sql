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
insert into myapp_board(board_no, meal, diet, writer, password)
  values(11, 'm', '1', '1', sha1('1111'));
insert into myapp_board(board_no, meal, diet, writer, password)
  values(12, 'l', '2', '1', sha1('1111'));
insert into myapp_board(board_no, meal, diet, writer, password)
  values(13, 'd', '3', '3', sha1('1111'));
insert into myapp_board(board_no, meal, diet, writer, password)
  values(14, 'm', '4', '4', sha1('1111'));
insert into myapp_board(board_no, meal, diet, writer, password)
  values(15, 'l', '5', '5', sha1('1111'));
insert into myapp_board(board_no, meal, diet, writer, password)
  values(16, 'd', '2', '6', sha1('1111'));
  
-- myapp_food 테이블 예제 데이터
insert into myapp_food(food_no, food, calories)
  values(1, 'banana', 90);
insert into myapp_food(food_no, food, calories)
  values(2, 'egg', 70);
insert into myapp_food(food_no, food, calories)
  values(3, 'rice', 200);
insert into myapp_food(food_no, food, calories)
  values(4, 'potato', 120);
insert into myapp_food(food_no, food, calories)
  values(5, 'apple', 100);
  