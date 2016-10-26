package controles;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageBox {


	public static void ShowInfo(String title, String message) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle(title);
		info.setHeaderText(null);
		info.setContentText(message);

		info.showAndWait();
	}

	public static void ShowError(String title, String message) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle(title);
		error.setHeaderText(null);
		error.setContentText(message);

		error.showAndWait();
	}


}
