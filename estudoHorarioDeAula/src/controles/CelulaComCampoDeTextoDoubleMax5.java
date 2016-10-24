package controles;

import entidades.Professor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class CelulaComCampoDeTextoDoubleMax5 implements Callback<TableColumn<Professor, String>, TableCell<Professor, String>> {

	String retorno;

	@Override
	public TableCell<Professor, String> call(TableColumn<Professor, String> param) {

		return new CelulaDeTextoDoubleMax5();

	}

}

class CelulaDeTextoDoubleMax5 extends TableCell<Professor, String> {

	private TextField editorDeTexto;

	private String editado;

	public CelulaDeTextoDoubleMax5() {


		this.criarCampoDeTexto();
		this.setText(this.recuperarTexto());
		this.setGraphic(null);
		this.setAlignment(Pos.CENTER);
		//professorRecebido.setNome_professor(editorDeTexto.getText());
		this.editorDeTexto.selectAll();
		this.editorDeTexto.setOnKeyTyped(arg0 -> {
			char teste = arg0.getCharacter().charAt(0);
			if(Character.isLetter(teste) && !Character.isDigit(teste))
			{
				arg0.consume();
			}else {
				if(this.editorDeTexto.getLength() >= 5 && this.editorDeTexto.getSelectedText().length() < 1) {
					arg0.consume();
				}
			}
		});
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();

		setText(this.getItem());
		setGraphic(null);
	}

	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				this.editorDeTexto.setText(item);
				setText(null);
				setGraphic(this.editorDeTexto);
			} else {
				setText(item);
				editado = item;
				setGraphic(null);
			}
		}

	}

	@Override
	public void startEdit() {
		super.startEdit();
		this.setText(null);
		this.editorDeTexto.setText(this.recuperarTexto());
		this.setGraphic(this.editorDeTexto);
		this.editorDeTexto.selectAll();
	}

	@Override
	public void commitEdit(String arg0) {
		super.commitEdit(arg0);

	}

	private String recuperarTexto() {
		return getItem() == null ? "" : getItem().toString();
	}

	public String getProfessorEditado() {
		return editado;
	}

	private void criarCampoDeTexto() {
		this.editorDeTexto = new TextField();
		this.editorDeTexto.setText(this.recuperarTexto());
		this.editorDeTexto.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		this.editorDeTexto.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (!arg2) {
					commitEdit(editorDeTexto.getText());
				}
			}
		});

		this.editorDeTexto.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
					commitEdit(editorDeTexto.getText());
				}

			}

		});
	}
}