package bruh;

import java.sql.*; //�ҷ����� ���� ��. ��

public class JDBC_EX01 {

	public static void main(String[] args) {
		
		// �̷��� ���� �� ���� �����ϴ� �� ���� �ִ� �ڵ� ��ȭ�� �Ѵ�.
		String driverName = "com.mysql.jdbc.Driver"; //mySQL jdbc ����̹� ȣ���� �ִ� ��. ö�� Ʋ���� �ʰ� ���� ��
		String url = "jdbc:mysql://localhost:3306/student_db"; //localhost�� �츮 ��ǻ�ʹ�. 3306�� ��Ʈ ��ȣ����.
		String username = "root";
		String password = "12345";
		
		String query = "select * from smembers";
//		String query2 = "delete from smembers where id = 'tiger'";
		String query2 = "insert into smembers (num, name, id, pw, email) values (3, '�̼���', 'jaguar', '11111', 'crane@formation.org')";
		
		ResultSet ret = null; // "�츮�� �� �Ϸ� ���� �̷� �� �޾� �ַ� �� �� �ƴ��ݾƿ�." �׷���? ResultSet�� ��¥ DB '�̷� �� �޾� �ַ�' �Դ�.
		Connection conn = null; // DB ������ �ִ� ��ü ����
		Statement stmt = null; // SQL ������ �ִ� ��ü ����
		
		try {		
			Class.forName(driverName); 
			conn =	DriverManager.getConnection(url, username, password);
					
			System.out.println("DB ���� �Ϸ�!!!");
			
			stmt = conn.createStatement(); //SQL ���� �� statement ��ü ����
			ret = stmt.executeQuery(query); //SQL ���� & ��ȯ�Ǵ� ������� ResultSet���� ���� ��
			// (query�� select�� �ƴϰ� �� 3���������� executeQuery ���� executeUpdate ���� ���� ���� ��
			
			while(ret.next()) {
				String pid = ret.getString("id"); // student_db�� �ִ� entry���� id�� ��� �ʵ常 �� �̾� ���� �� 
				String ppw = ret.getString("pw");
				
				System.out.println(pid);
				System.out.println(ppw);			
			}
			
			int rnum = stmt.executeUpdate(query2); // select�� �ƴ� �ٸ� ������ ���� ���θ� ��ȯ�Ѵ�. int 1�̸� ����, 0�̸� Ż��!
			
			if (rnum == 1) {
//				System.out.println("ȸ��Ż�𼺰�!!!");
				System.out.println("ȸ�����Լ���!!!");
			}			
			else {
//				System.out.println("ȸ��Ż�����!!!");
				System.out.println("ȸ�����Խ���!!!");
			}
		
		} catch (ClassNotFoundException e) { // ClassNotFoundException�� �̸���� �� Ŭ������ �� ã�� :( �ϴ� ������.
			System.out.println("��. JDBC ����̹� �ε� ����!!!");
		} catch (SQLException e) {
			System.out.println("��. DB ���� ����!!!");
		}
		
	}

}
