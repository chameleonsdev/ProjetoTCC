package entidades;

import java.util.List;

import javafx.beans.property.LongProperty;
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
public class Curso {

	private LongProperty id_curso;
	
	private StringProperty nome_curso;
	
	private StringProperty tipo_curso;
	
	List<Professor> professores;
	
	List<Materia> materias;
	
	List<Horario> horarios;
	
	public Curso() {
		
		id_curso = new SimpleLongProperty();
		nome_curso = new SimpleStringProperty();
		tipo_curso = new SimpleStringProperty();
		
	}

	//Get e Set do id do curso
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId_curso() {
		return id_curso.get();
	}

	public LongProperty Id_curso() {
		return id_curso;
	}
	
	public void setId_curso(long id_curso) {
		this.id_curso.set(id_curso);
	}

	//Get e Set do Nome do curso
	@Column
	public String getNome_curso() {
		return nome_curso.get();
	}

	public void setNome_curso(String nome_curso) {
		this.nome_curso.set(nome_curso);
	}

	//Get e Set do tipo do curso
	@Column
	public String getTipo_curso() {
		return tipo_curso.get();
	}

	public void setTipo_curso(String tipo_curso) {
		this.tipo_curso.set(tipo_curso);
	}
	
	//Get e set da lista de professores do curso
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "Professor_Curso", 
			joinColumns = { @JoinColumn(name = "id_curso")	},
			inverseJoinColumns = { @JoinColumn(name = "id_professor") }
	)
	public List<Professor> getProfessores() {
		return professores;
	}
	
	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "Curso_Materia", 
			joinColumns = { @JoinColumn(name = "id_curso")	},
			inverseJoinColumns = { @JoinColumn(name = "id_materia") }
	)
	public List<Materia> getMaterias() {
		return materias;
	}
	
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_curso")
	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	//Retornar string no objeto (TESTE)
	@Override
    public String toString() {
        return this.getNome_curso();
    }

	
	
	
}
