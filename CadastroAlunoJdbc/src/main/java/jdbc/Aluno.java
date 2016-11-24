package jdbc;

public class Aluno {
	  private String matricula;
	  public String getMatricula() {
	    return matricula;
	  }
	  public void setMatricula(String matricula) {
	    this.matricula = matricula;
	  }

	  private String nome;
	  public String getNome() {
	    return nome;
	  }
	  public void setNome(String nome) {
	    this.nome = nome;
	  }

	  private String curso;
	  public String getCurso() {
	    return curso;
	  }
	  public void setCurso(String curso) {
	    this.curso = curso;
	  }
	  public boolean hasMatricula() {
	    return matricula != null && !matricula.trim().equals("");
	  }
}
