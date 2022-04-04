package bruh;

import java.sql.*; //불러오면 좋은 놈. ㅋ

public class JDBC_EX01 {

	public static void main(String[] args) {
		
		// 이렇게 위에 다 쓰고 시작하는 게 스웩 있는 코딩 문화라 한다.
		String driverName = "com.mysql.jdbc.Driver"; //mySQL jdbc 드라이버 호출해 주는 놈. 철자 틀리지 않게 조심 ㅋ
		String url = "jdbc:mysql://localhost:3306/student_db"; //localhost가 우리 컴퓨터다. 3306은 포트 번호였고.
		String username = "root";
		String password = "12345";
		
		String query = "select * from smembers";
//		String query2 = "delete from smembers where id = 'tiger'";
		String query2 = "insert into smembers (num, name, id, pw, email) values (3, '이순신', 'jaguar', '11111', 'crane@formation.org')";
		
		ResultSet ret = null; // "우리가 랩 하러 왔지 이런 거 받아 주러 온 거 아니잖아요." 그러나? ResultSet은 진짜 DB '이런 거 받아 주러' 왔다.
		Connection conn = null; // DB 연결해 주는 객체 선언
		Statement stmt = null; // SQL 실행해 주는 객체 선언
		
		try {		
			Class.forName(driverName); 
			conn =	DriverManager.getConnection(url, username, password);
					
			System.out.println("DB 연결 완료!!!");
			
			stmt = conn.createStatement(); //SQL 실행 용 statement 객체 생성
			ret = stmt.executeQuery(query); //SQL 실행 & 반환되는 결과값을 ResultSet으로 받음 ㅋ
			// (query가 select문 아니고 딴 3가지였으면 executeQuery 말고 executeUpdate 문을 썼을 거임 ㅋ
			
			while(ret.next()) {
				String pid = ret.getString("id"); // student_db에 있는 entry들의 id와 비번 필드만 함 뽑아 보자 ㅋ 
				String ppw = ret.getString("pw");
				
				System.out.println(pid);
				System.out.println(ppw);			
			}
			
			int rnum = stmt.executeUpdate(query2); // select문 아닌 다른 쿼리면 성공 여부만 반환한다. int 1이면 성공, 0이면 탈락!
			
			if (rnum == 1) {
//				System.out.println("회원탈퇴성공!!!");
				System.out.println("회원가입성공!!!");
			}			
			else {
//				System.out.println("회원탈퇴실페!!!");
				System.out.println("회원가입실페!!!");
			}
		
		} catch (ClassNotFoundException e) { // ClassNotFoundException은 이름대로 헐 클래스를 못 찾음 :( 하는 에러다.
			System.out.println("ㅋ. JDBC 드라이버 로드 에러!!!");
		} catch (SQLException e) {
			System.out.println("ㅋ. DB 연결 에러!!!");
		}
		
	}

}
