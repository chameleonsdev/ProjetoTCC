package entidades;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DisponibilidadeHorario {

	private LongProperty id_disphorario;

	private String diaDaSemana;

	private Professor id_professor;

	private Materia id_materia;

	private Horario id_horario;

	public DisponibilidadeHorario() {
		id_disphorario = new SimpleLongProperty();
	}

	//Get e Set do id da disponibilidade
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId_disphorario() {
		return id_disphorario.get();
	}

	public LongProperty Id_disphorario() {
		return id_disphorario;
	}

	public void setId_disphorario(long id_disphorario) {
		this.id_disphorario.set(id_disphorario);
	}

	//Get e Set do Dia da Semana
	@Column
	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	@ManyToOne
	public Professor getId_professor() {
		return id_professor;
	}

	public void setId_professor(Professor id_professor) {
		this.id_professor = id_professor;
	}

	@ManyToOne
	public Materia getId_materia() {
		return id_materia;
	}

	public void setId_materia(Materia id_materia) {
		this.id_materia = id_materia;
	}

	@ManyToOne
	public Horario getId_horario() {
		return id_horario;
	}

	public void setId_horario(Horario id_horario) {
		this.id_horario = id_horario;
	}



}
