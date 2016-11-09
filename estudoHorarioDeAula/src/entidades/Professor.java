package entidades;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Professor {

	private LongProperty id_professor;

	private StringProperty num_professor;

	private StringProperty nome_professor;

	private DoubleProperty pontuacao;

	List<Materia> materias;

	List<Curso> cursos;

	List<DisponibilidadeHorario> Disponibilidades;

	List<Questionmarks> Questionmarks;

	public Professor() {

		id_professor = new SimpleLongProperty();

		nome_professor = new SimpleStringProperty();

		num_professor = new SimpleStringProperty();

		pontuacao = new SimpleDoubleProperty();

	}

	//Get e Set do Id Professor;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId_professor() {
		return id_professor.get();
	}

	public LongProperty Id_professor() {
		return id_professor;
	}

	public void setId_professor(long id_professor) {
		this.id_professor.set(id_professor);
	}

	//Get e Set do Numero do Professor;

	@Column
	public String getNum_professor() {
		return num_professor.get();
	}

	public StringProperty Num_professor() {
		return num_professor;
	}

	public void setNum_professor(String num_professor) {
		this.num_professor.set(num_professor);
	}

	//Get e Set do Nome Professor;
	@Column
	public String getNome_professor() {
		return nome_professor.get();
	}

	public StringProperty Nome_professor() {
		return nome_professor;
	}

	public void setNome_professor(String nome_professor) {
		this.nome_professor.set(nome_professor);
	}

	//Get e Set da Pontuação Professor;
	@Column
	public double getPontuacao() {
		return pontuacao.get();
	}

	public DoubleProperty Pontuacao() {
		return pontuacao;
	}

	public void setPontuacao(double pontuacao) {
		this.pontuacao.set(pontuacao);
	}

	//Get e Set da Lista de Professores_Materia;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "Professor_Materia",
			joinColumns = { @JoinColumn(name = "id_professor")	},
			inverseJoinColumns = { @JoinColumn(name = "id_materia") }
	)
	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "Professor_Curso",
			joinColumns = { @JoinColumn(name = "id_professor")	},
			inverseJoinColumns = { @JoinColumn(name = "id_curso") }
	)
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}


	//Get e Set da Lista de Disponibilidades;
	@OneToMany(mappedBy = "id_professor")
	public List<DisponibilidadeHorario> getDisponibilidades() {
		return Disponibilidades;
	}

	public void setDisponibilidades(List<DisponibilidadeHorario> disponibilidades) {
		Disponibilidades = disponibilidades;
	}

	//Get e Set da Lista de Questionmarks;

	@OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Questionmarks> getQuestionmarks() {
		return Questionmarks;
	}

	public void setQuestionmarks(List<Questionmarks> questionmarks) {
		Questionmarks = questionmarks;
	}


	//Retornar string no objeto (TESTE)
		@Override
	    public String toString() {
	        return this.getNome_professor();
	    }



}
