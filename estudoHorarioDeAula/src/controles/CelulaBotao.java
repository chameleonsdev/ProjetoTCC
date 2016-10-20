package controles;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import entidades.Horario;
import entidades.Professor;

public class CelulaBotao implements
		Callback<TableColumn<Professor, String>, TableCell<Professor, String>> {

	@Override
	public TableCell<Professor, String> call(TableColumn<Professor, String> arg0) {
		return new celulaButton();
	}
	
	class celulaButton extends TableCell<Professor, String> {
		
		private Button btnCelula;
		 
		
		public celulaButton() {

			this.criarBotao();
			this.setText(this.recuperarTexto());
			this.setGraphic(null);
			this.setAlignment(Pos.CENTER);
			
		}

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				this.btnCelula.setText(item);
				setGraphic(this.btnCelula);
				setText(null);
			}
		}

		private String recuperarTexto() {
			return getItem() == null ? "" : getItem();
		}

		private void criarBotao() {
			this.btnCelula = new Button();
			this.btnCelula.setText(this.recuperarTexto());
			this.btnCelula.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			//this.cboxAWTEhMelhor.setOnMouseClicked(this);
		}
		
		}

}
