package controles;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import entidades.Professor;
import janelas.JanelaEditarMateriasCursos;
import janelas.JanelaEditarProfessores;

public class CelulaComboBox implements
		Callback<TableColumn<Professor, String>, TableCell<Professor, String>> {

	@Override
	public TableCell<Professor, String> call(TableColumn<Professor, String> arg0) {
		return new celulaButton();
	}

	class celulaButton extends TableCell<Professor, String> {

		private ComboBox<Professor> cmbCelula;


		public celulaButton() {

			this.criarBotao();
			this.setText(this.recuperarTexto());
			this.setGraphic(null);
			this.setAlignment(Pos.CENTER);

			cmbCelula.setOnMouseClicked(value -> {
				if(this.getTableColumn().getText().equals("Cursos e Materias")){
					JanelaEditarMateriasCursos janelaEProf = null;
					if(janelaEProf == null){
						janelaEProf = new JanelaEditarMateriasCursos((Professor) this.getTableRow().getItem());
					}
					janelaEProf.show();
				}
			});

		}



		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				setGraphic(this.cmbCelula);
				setText(null);
			}
		}

		private String recuperarTexto() {
			return getItem() == null ? "" : getItem();
		}

		private void criarBotao() {
			this.cmbCelula = new ComboBox<Professor>();
			this.cmbCelula.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			//this.cboxAWTEhMelhor.setOnMouseClicked(this);
		}

		}

}
