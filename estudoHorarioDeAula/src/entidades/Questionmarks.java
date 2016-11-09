package entidades;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class Questionmarks {

	private LongProperty id_questionmark;

	private LocalTime hora_entrada;

	private LocalTime hora_saida;

	private Professor professor;

	private StringProperty diasemana;

	//List<Professor> professores;

	public Questionmarks() {

		id_questionmark = new SimpleLongProperty();
		diasemana = new SimpleStringProperty();

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId_questionmark() {
		return id_questionmark.get();
	}

	public LongProperty Id_Questionmark() {
		return id_questionmark;
	}

	public void setId_questionmark(long id_questionmark) {
		this.id_questionmark.set(id_questionmark);
	}

	@Column
	public LocalTime getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(LocalTime hora_entrada) {
		this.hora_entrada = hora_entrada;
	}

	@Column
	public LocalTime getHora_saida() {
		return hora_saida;
	}

	public void setHora_saida(LocalTime hora_saida) {
		this.hora_saida = hora_saida;
	}

	@ManyToOne()
	@JoinColumn(name = "id_professor")
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	@Column
	public String getDiasemana() {
		return diasemana.get();
	}

	public StringProperty Diasemana() {
		return diasemana;
	}

	public void setDiasemana(String diasemana) {
		this.diasemana.set(diasemana);
	}

	//Retornar string no objeto (TESTE)
			@Override
		    public String toString() {
		        return ("Das " + this.getHora_entrada() + " as " + this.getHora_saida());
		    }



}