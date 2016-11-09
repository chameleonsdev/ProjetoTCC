package controles;

import java.util.List;

import entidades.Horario;
import entidades.Professor;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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

		this.horaColumn = new TableColumn<Horario, String>("---");
		this.segundaColumn = new TableColumn<Professor, String>("Segunda");
		this.tercaColumn = new TableColumn("Terça");
		this.quartaColumn = new TableColumn("Quarta");
		this.quintaColumn = new TableColumn("Quinta");
		this.sextaColumn = new TableColumn("Sexta");

		this.getColumns().addAll(this.horaColumn, this.segundaColumn, this.tercaColumn, this.quartaColumn, this.quintaColumn, this.sextaColumn);

		this.horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora_disp"));
		this.segundaColumn.setCellValueFactory(new PropertyValueFactory<>("nome_professor"));
		Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>()
        {
            @Override
            public TableCell call( TableColumn p )
            {
                return new CelulaComboBox();
            }
        };
		this.segundaColumn.setCellFactory(cellFactory);
		//this.tercaColumn.setCellFactory(new CelulaComboBox());
		//this.quartaColumn.setCellFactory(new CelulaComboBox());
		//this.quintaColumn.setCellFactory(new CelulaComboBox());
		//this.sextaColumn.setCellFactory(new CelulaComboBox());


	}

}
