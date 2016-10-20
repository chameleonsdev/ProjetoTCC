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
public class Materia {

	private LongProperty id_materia;

	private StringProperty nome_materia;

	private DoubleProperty carga_materia;

	private Boolean divisivel_materia;

	private List<Professor> professores;

	private List<Curso> cursos;

	private List<DisponibilidadeHorario> Disponibilidades;

	public Materia() {

		id_materia = new SimpleLongProperty();
		nome_materia = new SimpleStringProperty();
		carga_materia = new SimpleDoubleProperty();

	}

	//Get e Set do Id Materia
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId_materia() {
		return id_materia.get();
	}

	public LongProperty Id_materia() {
		return id_materia;
	}

	public void setId_materia(long id_materia) {
		this.id_materia.set(id_materia);
	}

	//Get e Set do Nome da Materia
	@Column
	public String getNome_materia() {
		return nome_materia.get();
	}

	public void setNome_materia(String nome_materia) {
		this.nome_materia.set(nome_materia);
	}

	@Column
	public double getCarga_materia() {
		return carga_materia.get();
	}

	public void setCarga_materia(double carga_materia) {
		this.carga_materia.set(carga_materia);;
	}

	public DoubleProperty Carga_materia() {
		return carga_materia;
	}

	@Column
	public Boolean getDivisivel_materia() {
		return divisivel_materia;
	}

	public void setDivisivel_materia(Boolean divisivel_materia) {
		this.divisivel_materia = divisivel_materia;
	}

	//Get e Set da Lista de Professores da Materia
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "Professor_Materia",
			joinColumns = { @JoinColumn(name = "id_materia")	},
			inverseJoinColumns = { @JoinColumn(name = "id_professor") }
	)

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	//Get e Set da Lista de Cursos da Materia
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "Curso_Materia",
			joinColumns = { @JoinColumn(name = "id_materia")	},
			inverseJoinColumns = { @JoinColumn(name = "id_curso") }
	)
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	@OneToMany(mappedBy = "id_materia")
	public List<DisponibilidadeHorario> getDisponibilidades() {
		return Disponibilidades;
	}

	public void setDisponibilidades(List<DisponibilidadeHorario> disponibilidades) {
		Disponibilidades = disponibilidades;
	}

	//Mostrar Nome da materia (TESTE)
	@Override
    public String toString() {
        return this.getNome_materia();
    }



}
