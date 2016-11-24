package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CadastroAlunoJdbcModel {

  private static Connection obterConexao() throws SQLException {
    //Estabelecer uma conexão com o banco de dados.
    String url = "jdbc:mysql://localhost:3306/pw";
    String user = "pwuser";
    String password = "12345678";
    Connection conn = DriverManager
        .getConnection(url, user, password);
    return conn;
  }

  public static void salvar(Aluno aluno) throws SQLException {
    Connection conn = obterConexao();
    if (aluno.hasCodigo()) {
      alterar(conn, aluno);
    } else {
      inserir(conn, aluno);
    }
  }


  public static void excluir(Aluno aluno) throws SQLException {
    Connection conn = obterConexao();
    
    //Obter sentença SQL.
    String sql = "delete from usuario where codigo = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, aluno.getCodigo());
    pstmt.execute();
  }

  public static List<Aluno> listar() throws SQLException {
    Connection conn = obterConexao();
    
    Statement stmt = conn.createStatement();
    String sql = "select codigo, nome from usuario order by codigo";
    ResultSet rs = stmt.executeQuery(sql);

    List<Aluno> listaDeAlunos = new ArrayList<Aluno>();
    while (rs.next()) {
      // Cria um usuário para o registro.
      Aluno aluno = new Aluno();
      aluno.setCodigo(rs.getString("codigo"));
      aluno.setNome(rs.getString("nome"));
      // Adiciona o usuário na lista de usuários.
      listaDeAlunos.add(aluno);
    }
    return listaDeAlunos;
  }

  private static void inserir(Connection conn, Aluno aluno) throws SQLException {
    //Obter sentença SQL.
    String sql = "insert into usuario (nome, senha) values (?, ?)";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, aluno.getNome());
    pstmt.setString(2, aluno.getSenha());
    pstmt.execute();
  }

  private static void alterar(Connection conn, Aluno aluno) throws SQLException {
    //Obter sentença SQL.
    String sql = "update usuario set nome = ?, senha = ? where codigo = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, aluno.getNome());
    pstmt.setString(2, aluno.getSenha());
    pstmt.setString(3, aluno.getCodigo());
    pstmt.execute();
  }

}
