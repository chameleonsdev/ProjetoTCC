package controles;
import entidades.Horario;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class CelulaCBox implements Callback<TableColumn<Horario, String>, TableCell<Horario, String>> {

	
	@Override
	public TableCell<Horario, String> call(TableColumn<Horario, String> param) {
		return new CelulaBonitassa();
	}

	class CelulaBonitassa extends TableCell<Horario, String>{
		
		private CheckBox cboxAWTEhMelhor;
		 
		
		public CelulaBonitassa() {

			this.criarBotao();
			this.setText(this.recuperarTexto());
			this.setGraphic(null);
			this.setAlignment(Pos.CENTER);
			this.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
				
				
				if (this.cboxAWTEhMelhor.isSelected())
				this.cboxAWTEhMelhor.setSelected(false);
				else{
					this.cboxAWTEhMelhor.setSelected(true);
				}
				
			}));
		}

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				this.cboxAWTEhMelhor.setText(item);
				setGraphic(this.cboxAWTEhMelhor);
				setText(null);
			}
		}

		private String recuperarTexto() {
			return getItem() == null ? "" : getItem();
		}

		private void criarBotao() {
			this.cboxAWTEhMelhor = new CheckBox();
			this.cboxAWTEhMelhor.setText(this.recuperarTexto());
			this.cboxAWTEhMelhor.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			//this.cboxAWTEhMelhor.setOnMouseClicked(this);
		}
		
		}
		
		
		
		
		
	}
	

