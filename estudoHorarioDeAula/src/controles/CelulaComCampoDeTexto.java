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

public class CelulaComCampoDeTexto implements Callback<TableColumn<Professor, String>, TableCell<Professor, String>> {

	@Override
	public TableCell<Professor, String> call(TableColumn<Professor, String> param) {
		return new CelulaDeTexto();
	}

}

class CelulaDeTexto extends TableCell<Professor, String> {

	private TextField editorDeTexto;

	public CelulaDeTexto() {

		this.criarCampoDeTexto();
		this.setText(this.recuperarTexto());
		this.setGraphic(null);
		this.setAlignment(Pos.CENTER);
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