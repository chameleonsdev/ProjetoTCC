package controles;

import entidades.Horario;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("rawtypes")
public class TabelaHorario extends TableView{
	
	TableColumn horaColumn;
	TableColumn segundaColumn;
	TableColumn tercaColumn;
	TableColumn quartaColumn;
	TableColumn quintaColumn;
	TableColumn sextaColumn;
	
	@SuppressWarnings("unchecked")
	public TabelaHorario() {
		
		this.horaColumn = new TableColumn("---");
		this.segundaColumn = new TableColumn("Segunda");
		this.tercaColumn = new TableColumn("Terça");
		this.quartaColumn = new TableColumn("Quarta");
		this.quintaColumn = new TableColumn("Quinta");
		this.sextaColumn = new TableColumn("Sexta");
		
		this.getColumns().addAll(this.horaColumn, this.segundaColumn, this.tercaColumn, this.quartaColumn, this.quintaColumn, this.sextaColumn);
		
		this.horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora_disp"));
		this.segundaColumn.setCellFactory(new CelulaComboBox());
		this.tercaColumn.setCellFactory(new CelulaComboBox());
		this.quartaColumn.setCellFactory(new CelulaComboBox());
		this.quintaColumn.setCellFactory(new CelulaComboBox());
		this.sextaColumn.setCellFactory(new CelulaComboBox());
		
		
	}
	
}
