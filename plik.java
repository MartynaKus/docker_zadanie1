import java.sql.*;
import.java.util.Scanner;

public class Main {
	public static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	public static final String DB_URL="jdbc:mysql://10.0.10.3:3306/zadanie1";
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//String JDBC_Driver="com.mysql.jdbc.Driver";
		//String DB_URL="jdbc:mysql://10.0.10.3:3306/zadanie1";
		String USER="MKUser";
		String PASS="password"
		
		Statement stmt=null;
		Connection conn=null;
		
	 try{
      Class.forName("com.mysql.jdbc.Driver");

      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	  
		
	Scanner input = new Scanner(System.in);

    int wybierz;
    while(true){
        System.out.println("	   MENU	      ");
		System.out.println("  1.Dodaj rekord  ");
		System.out.println("2.Wyświetl rekordy");
		System.out.println("  3.Zmień rekord  ");
		System.out.println("  4.Usuń rekord   ");
        System.out.println("      5.Exit      ");
        System.out.println("  Wybierz z menu  ");

        wybierz = input.nextInt();




    switch(wybierz){

    case 1:
		String login, pass;
        System.out.println("Podaj login: ");
        login = input.nextLine();
        System.out.println("Podaj hasło: ");
        pass = input.nextLine();
        insert(conn, login, pass);
		break;

    case 2: 
        select(conn);
		break;

    case 3:
	String login, pass;
	int id;
		System.out.println("Podaj id: ");
        id = input.nextInt();
        System.out.println("Podaj login: ");
        login = input.nextLine();
        System.out.println("Podaj hasło: ");
        pass = input.nextLine();
        update(conn, id, login, pass);
        break;

    case 4: 
		System.out.println("Podaj id: ");
        id = input.nextInt();
        delete(conn, id);
        break;

    case 5: 
        System.out.println("Exiting Program...");
        System.exit(0);
         break;
    default :
             System.out.println("Błąd! Wybierz inną opcję z menu");
             break;

    }
	}
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
	 }
	}
	
	
	public static void insert(Connection conn String login, String pass) throws SQLException
	{
	
	  String sql="INSERT INTO Zadanie1 (LOGIN, PASS) VALUES (?,?)";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1,login);
		stat.setString(2,pass);
		
		int row = statement.executeUpdate();
		System.out.println(row);
		stat.close();
	}
	
	public static void select(Connection conn) throws SQLException
	{
	 String sql="SELECT ID, LOGIN, PASS FROM ";
	 PreparedStatement stat = conn.prepareStatement(sql);
	 ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
         int id  = rs.getInt("ID");
         String login = rs.getString("Login");
         String pass = rs.getString("Password");

         System.out.println("ID: " + id);
         System.out.println(", Login: " + login);
         System.out.println(", Password: " + pass);
      }
      rs.close();
      stat.close();
	}
	
	public static void update(Connection conn, int userId, String login, String pass) throws SQLException
	{
		String sql="UPDATE Zadanie1 SET LOGIN = ?, PASS = ? WHERE ID = ?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setString(1,login);
		stat.setString(2,pass);
		stat.setInt(3,userId);
		
		int row = statement.executeUpdate();
		System.out.println(row);
		stat.close();
	}
	
	public static void delete(Connection conn, int userId) throws SQLException
	{
		String sql="DELETE FROM Zadanie1 WHERE ID= ?";
		PreparedStatement stat=conn.prepareStatement(sql);
		stat.setInt(1,userId);
		
		int row = statement.executeUpdate();
		System.out.println(row);
		stat.close();
	
	}