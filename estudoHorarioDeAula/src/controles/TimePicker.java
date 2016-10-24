package controles;

import java.time.LocalTime;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TimePicker extends HBox{

	private TextField HORAS = new TextField();
	private TextField MINUTOS = new TextField();
	private Label label = new Label(":");
	private Button btnmaish = new Button("+");
	private Button btnmenosh = new Button("-");
	private Button btnmaism = new Button("+");
	private Button btnmenosm = new Button("-");
	private HBox hbos = new HBox();
	private HBox hbbtnmais = new HBox(3);
	private HBox hbbtnmenos = new HBox(3);
	private VBox vbox = new VBox(2);

	public TimePicker() {
		hbos.getChildren().addAll(HORAS,label,MINUTOS);
		hbbtnmais.getChildren().addAll(btnmaish,btnmaism);
		hbbtnmenos.getChildren().addAll(btnmenosh,btnmenosm);
		vbox.getChildren().addAll(hbbtnmais,hbos,hbbtnmenos);
		vbox.setAlignment(Pos.CENTER);
		hbos.setAlignment(Pos.CENTER);
		hbbtnmais.setAlignment(Pos.CENTER);
		hbbtnmenos.setAlignment(Pos.CENTER);

		HORAS.setMaxWidth(32);
		MINUTOS.setMaxWidth(32);
		HORAS.setMinWidth(32);
		MINUTOS.setMinWidth(32);

		btnmaish.setMaxWidth(32);
		btnmenosh.setMaxWidth(32);
		btnmaish.setMinWidth(32);
		btnmenosh.setMinWidth(32);

		btnmaism.setMaxWidth(32);
		btnmenosm.setMaxWidth(32);
		btnmaism.setMinWidth(32);
		btnmenosm.setMinWidth(32);


		HORAS.setText("00");
		MINUTOS.setText("00");

		HORAS.setOnKeyTyped(arg0 -> {
			char teste = arg0.getCharacter().charAt(0);
			if(Character.isLetter(teste) && !Character.isDigit(teste) || HORAS.getText().length() == 2 && HORAS.selectionProperty().get().getLength() == 0)
			{
				arg0.consume();
			}else if(Character.isDigit(teste)){
				if(Character.getNumericValue(teste) > 2 && HORAS.getText().length() == 0) {
					arg0.consume();
					HORAS.setText("2");
					HORAS.positionCaret(+1);
				}else if(HORAS.getText().length() == 1 && Integer.parseInt(HORAS.getText().substring(0, 1)) == 2 && Character.getNumericValue(teste) > 3){
					arg0.consume();
					HORAS.setText("00");
					HORAS.positionCaret(+2);
				}
			}
		});

		HORAS.focusedProperty().addListener(new ChangeListener<Boolean>()
				{
				    @Override
				    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
				    {
				        if (!newPropertyValue)
				        {
				        	if(HORAS.getText().length() < 2){
				    			String guarda = HORAS.getText();
				    			HORAS.setText("0"+guarda);
				    		}
				        	if(getHoras() > 23){
				        		HORAS.setText("00");
				        	}

				        }
				    }
				});



		MINUTOS.setOnKeyTyped(arg0 -> {
			char teste = arg0.getCharacter().charAt(0);
			if(Character.isLetter(teste) && !Character.isDigit(teste) || MINUTOS.getText().length() == 2 && MINUTOS.selectionProperty().get().getLength() == 0)
			{
				arg0.consume();
			}else if(Character.isDigit(teste)){
				if(Character.getNumericValue(teste) > 6 && MINUTOS.getText().length() == 0) {
					arg0.consume();
					MINUTOS.setText("6");
					MINUTOS.positionCaret(+1);
				}else if(MINUTOS.getText().length() == 1 && Integer.parseInt(MINUTOS.getText().substring(0, 1)) == 6 && Character.getNumericValue(teste) >= 0){
					arg0.consume();
					MINUTOS.setText("00");
					somaremhora();
					MINUTOS.positionCaret(+2);
				}
			}
		});

		MINUTOS.focusedProperty().addListener(new ChangeListener<Boolean>()
				{
				    @Override
				    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
				    {
				        if (!newPropertyValue)
				        {
				        	if(MINUTOS.getText().length() < 2){
				    			String guarda = MINUTOS.getText();
				    			MINUTOS.setText("0"+guarda);
				    		}
				        	if(getMinutos() > 59){
				        		somaremhora();
				        		MINUTOS.setText("00");
				        	}
				        }
				    }
				});

		btnmaish.setOnAction(evento -> {

			somaremhora();

		});

		btnmenosh.setOnAction(evento -> {

			subtrairemhora();

		});

		btnmaism.setOnAction(evento -> {

			somaremminuto();

		});

		btnmenosm.setOnAction(evento -> {

			subtrairemminuto();

		});

		this.setAlignment(Pos.CENTER);
		this.getChildren().add(vbox);
		this.getStylesheets().add("CSS/TimePicker.css");
	}

	public LocalTime getTime() {
		LocalTime time;
		time = LocalTime.of(Integer.parseInt(HORAS.getText()), Integer.parseInt(MINUTOS.getText()));
		return time;
	}

	public void setTime(LocalTime hora) {
		HORAS.setText(String.valueOf(hora.getHour()));
		MINUTOS.setText(String.valueOf(hora.getMinute()));
	}

	public int getHoras() {
		int hora;
		hora = Integer.parseInt(HORAS.getText());
		return hora;
	}

	public int getMinutos() {
		int minuto;
		minuto = Integer.parseInt(MINUTOS.getText());
		return minuto;
	}

	private void somaremhora(){
		int hora = Integer.parseInt(HORAS.getText());
		if(hora == 23)
		{
			hora = 00;
		}else{
			hora += 1;
		}
		if(hora >= 10)
		{
			HORAS.setText(""+hora);
		}else{
			HORAS.setText("0"+hora);
		}
	}

	private void subtrairemhora(){
		int hora = Integer.parseInt(HORAS.getText());
		if(hora == 00)
		{
			hora = 23;
		}else{
			hora -= 1;
		}
		if(hora >= 10)
		{
			HORAS.setText(""+hora);
		}else{
			HORAS.setText("0"+hora);
		}
	}

	private void somaremminuto(){
		int minutos = Integer.parseInt(MINUTOS.getText());
		if(minutos == 59)
		{
			somaremhora();
			minutos = 00;
		}else{
			minutos += 1;
		}
		if(minutos >= 10)
		{
			MINUTOS.setText(""+minutos);
		}else{
			MINUTOS.setText("0"+minutos);
		}
	}
	private void subtrairemminuto(){
		int minutos = Integer.parseInt(MINUTOS.getText());
		if(minutos == 00)
		{
			subtrairemhora();
			minutos = 59;
		}else{
			minutos -= 1;
		}
		if(minutos >= 10)
		{
			MINUTOS.setText(""+minutos);
		}else{
			MINUTOS.setText("0"+minutos);
		}
	}


}
