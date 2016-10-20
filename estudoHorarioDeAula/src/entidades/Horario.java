package entidades;

import java.time.LocalTime;
import java.util.List;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Horario {

	private LongProperty id_horario;

	private LocalTime hora_disp;

	List<DisponibilidadeHorario> HorarioDisponibilidade;

	public Horario() {
		id_horario = new SimpleLongProperty();
	}

	//Get e Set do Id Horario
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId_horario() {
		return id_horario.get();
	}

	public LongProperty Id_horario() {
		return id_horario;
	}

	public void setId_horario(long id_horario) {
		this.id_horario.set(id_horario);
	}

	//Get e Set da Hora Disp
	@Column
	public LocalTime getHora_disp() {
		return hora_disp;
	}

	public void setHora_disp(LocalTime hora_disp) {
		this.hora_disp = hora_disp;
	}

	//Get e Set da Lista de Disponibilidade Horarios
	@OneToMany(mappedBy = "id_horario")
	public List<DisponibilidadeHorario> getHorarioDisponibilidade() {
		return HorarioDisponibilidade;
	}

	public void setHorarioDisponibilidade(
			List<DisponibilidadeHorario> horarioDisponibilidade) {
		HorarioDisponibilidade = horarioDisponibilidade;
	}

}
