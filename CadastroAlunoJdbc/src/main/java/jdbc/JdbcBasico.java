
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcBasico {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
//    //Carregar o driver.
//    Class.forName("com.mysql.jdbc.Driver");

    //Estabelecer uma conexão com o banco de dados.
    String url = "jdbc:mysql://localhost:3306/aluno";
    String user = "root";
    String password = "";
    Connection conn = DriverManager.getConnection(url, user, password);

    //Obter sentença SQL.
    Statement stmt = conn.createStatement();
    //stmt.execute("insert into aluno (nome, curso) values (\"João\", \"Odontologia\")");

    //Executar SELECT.
    ResultSet rs = stmt.executeQuery("select matricula, nome, curso from aluno");
    while (rs.next()) {
      System.out.println("matricula = " + rs.getString("matricula"));
      System.out.println("nome = " + rs.getString("nome"));
      System.out.println("curso = " + rs.getString("curso"));
    }
  }
}


