package janelas;



import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

import javax.persistence.EntityManager;

import controles.MessageBox;
import controles.TimePicker;
import Banco.Conexao;
import entidades.Curso;
import entidades.Horario;
import entidades.Materia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


@SuppressWarnings("all")//----------------> SAI WARNINGS; <----------------//
public class JanelaCadastroCursos extends Stage{

	//--------------------------------------------------------------------------------------//

	//----------------> SAI WARNINGS; <----------------//

	private Label lblInicio;
	private Label lblTermino;
	private Label lblMateria;
	private Label lblHorario;
	private Label lblQtdAulas;
	private Label lblIntervalo;
	private Label lblNomeCurso;
	private Label lblTipoCurso;
	private Label lblCargaHoraria;

	private TextField txtMateria;
	private TextField txtHorario;
	private TextField txtQtdAulas;
	private TextField txtNomeCurso;
	private TextField txtCargaHoraria;

	private CheckBox ckbIsDivisible;

	//private TimePicker horaInicio;
	//private TimePicker horaTermino;
	//private TimePicker horaDuracaoIntervalo;

	private Button btnVoltar;
	private Button btnAdcCurso;
	private Button btnRmvMateria;
	private Button btnAdcMateria;
	private Button btnAdcHorario;

	private VBox VBGeral;
	private VBox VBNomeCurso;
	private VBox VBTipoCurso;
	private VBox VBEscolhaMaterias;
	private VBox VBEscolhaHorarios;
	private VBox VBCargaHoraria;

	private HBox HBBotoes;
	private HBox HBCheckbox;
	private HBox HBAlinhar;

	private ObservableList<String> TiposCurso;
	private ObservableList<Horario> ListHorarios;
	private ObservableList<Materia> ListMaterias;

	private ListView listvMaterias;
	private ListView listvHorarios;

	private AnchorPane painel;

	private ComboBox cmbTiposCurso;

	//--------------------------------------------------------------------------------------//

	public JanelaCadastroCursos() {

		//--------------------------------------------------------------------------------------//

		//----------------> INSTANCIANDO AS PARADAS; <----------------//

		this.lblInicio = new Label("Inicio:");
		this.lblTermino = new Label("Término:");
		this.lblMateria = new Label("Materia:");
		this.lblIntervalo = new Label("Duração do Intervalo:");
		this.lblNomeCurso = new Label("Nome do Curso:");
		this.lblTipoCurso = new Label("Tipo do Curso:");
		this.lblHorario = new Label("Horario do Curso");
		this.lblQtdAulas = new Label("Quantidade de Aulas:");
		this.lblCargaHoraria = new Label("Carga Horaria da Materia:");

		this.txtMateria = new TextField();
		this.txtHorario = new TextField();
		this.txtQtdAulas = new TextField();
		this.txtNomeCurso = new TextField();
		this.txtCargaHoraria = new TextField();

		this.ckbIsDivisible = new CheckBox("A materia é divisivel?");

		//this.horaInicio = new TimePicker();
		//this.horaTermino = new TimePicker();
		//this.horaDuracaoIntervalo = new TimePicker();

		this.btnVoltar = new Button("Voltar");
		this.btnAdcCurso = new Button("Adicionar Curso");
		this.btnRmvMateria = new Button("Remover Materia");
		this.btnAdcMateria = new Button("Adicionar Materia");
		this.btnAdcHorario = new Button("Adicionar Horario");

		this.VBGeral = new VBox(5);
		this.VBNomeCurso = new VBox(5);
		this.VBTipoCurso = new VBox(5);
		this.VBEscolhaMaterias = new VBox(5);
		this.VBEscolhaHorarios = new VBox(5);
		this.VBCargaHoraria = new VBox(5);

		this.HBBotoes = new HBox(5);
		this.HBAlinhar = new HBox(5);
		this.HBCheckbox = new HBox(5);

		this.ListHorarios = FXCollections.observableArrayList();
		this.ListMaterias = FXCollections.observableArrayList();
		this.TiposCurso = FXCollections.observableArrayList("ETIM", "Tecnico Vespertino", "Tecnico Noturno", "Medio");

		this.listvMaterias = new ListView();
		this.listvHorarios = new ListView();

		this.painel = new AnchorPane();

		this.cmbTiposCurso = new ComboBox();

		//--------------------------------------------------------------------------------------//

		//----------------> PREENCHENDO LISTAS E BOXES; <----------------//

		//----------------> Preenchendo as comboxes e listas; <----------------//
		this.cmbTiposCurso.setItems(TiposCurso);
		this.listvMaterias.setItems(ListMaterias);
		this.listvHorarios.setItems(ListHorarios);

		//----------------> Adicionando componentes nas H/Vboxes; <----------------//
		this.VBCargaHoraria.getChildren().addAll(this.lblCargaHoraria, this.txtCargaHoraria);
		this.VBGeral.getChildren().addAll(this.VBNomeCurso,this.VBTipoCurso);
		this.HBCheckbox.getChildren().addAll(this.VBCargaHoraria, this.ckbIsDivisible);
		this.HBBotoes.getChildren().addAll(this.btnAdcMateria,this.btnRmvMateria);
		this.VBNomeCurso.getChildren().addAll(this.lblNomeCurso,this.txtNomeCurso);
		this.VBTipoCurso.getChildren().addAll(this.lblTipoCurso,this.cmbTiposCurso);
		this.VBEscolhaMaterias.getChildren().addAll(this.lblMateria,this.txtMateria, this.HBCheckbox,this.HBBotoes,this.listvMaterias);
		this.VBEscolhaHorarios.getChildren().addAll(this.lblHorario,this.lblInicio,this.lblQtdAulas,this.txtQtdAulas,this.btnAdcCurso,this.btnVoltar);

		//----------------> Adicionando tudo na HBox Principal; <----------------//
		this.HBAlinhar.getChildren().addAll(this.VBGeral,this.VBEscolhaMaterias,this.VBEscolhaHorarios);

		//----------------> Adicionando hbox Principal no painel; <----------------//
		this.painel.getChildren().addAll(this.HBAlinhar);

		//--------------------------------------------------------------------------------------//

		//----------------> DANDO Aï¿½ï¿½O PARA OS BOTï¿½ES; <----------------//

		//----------------> BOTï¿½O ADD MATERIA; <----------------//
		this.btnAdcMateria.setOnAction(evento -> {
			//----------------> Verifica se o texto da materia nï¿½o estï¿½ vazio; <----------------//
			if(!this.txtMateria.getText().equals("")){
				//----------------> Cria um novo objeto materia para ser adicionado ï¿½ lista de materias; <----------------//
				Materia m = new Materia();
				m.setNome_materia(this.txtMateria.getText());
				m.setCarga_materia(Double.parseDouble(this.txtCargaHoraria.getText()));
				m.setDivisivel_materia(this.ckbIsDivisible.isSelected());
				this.ListMaterias.add(m);
				limparCamposMateria();
			}
		});

		//----------------> BOTï¿½O DELETE MATERIA; <----------------//
		this.btnRmvMateria.setOnAction(evento -> {

			ListMaterias.remove(this.listvMaterias.getSelectionModel().getSelectedIndex());
			this.txtMateria.requestFocus();


		});

		//----------------> BOTï¿½O ADD CURSO; <----------------//
		this.btnAdcCurso.setOnAction(evento -> {

			/*if(this.txtNomeCurso.getText().isEmpty()) {

			}else if(this.cmbTiposCurso.getSelectionModel().getSelectedIndex() < 0) {

			}else if(this.listvMaterias.getItems().size() < 5) {

			}else if(this.horaInicio.getTime().getHour() < 4 || this.horaInicio.getTime().getHour() > 21 || this.horaTermino.getTime().getHour() < 6 || this.horaTermino.getTime().getHour() > 0) {

			}else if(this.txtQtdAulas.getText().isEmpty() || Integer.parseInt(this.txtQtdAulas.getText()) > 3) {

			}else if(this.horaDuracaoIntervalo.getTime().getHour() > 1) {

			}else {*/
			    //'	----------------> CRIA HORAS PARA INICIO E FIM DO CURSO; <----------------//
				LocalTime horaInicio = null;
				LocalTime horaFim = null;
				LocalTime horaduracao = null;
				LocalTime horaIntervalo = null;
				LocalTime horaAulaAtual = null;

				int numeroDeAulas = 0;

				if (cmbTiposCurso.getSelectionModel().getSelectedItem().equals("Medio")){

					horaInicio = LocalTime.of(07, 00);
					horaFim = LocalTime.of(11, 30);
					horaIntervalo = LocalTime.of(00, 20);
					numeroDeAulas = 5;
					horaduracao = LocalTime.of(horaFim.minusHours(horaInicio.plusHours(horaIntervalo.getHour()).getHour()).getHour(), horaFim.minusMinutes(horaInicio.plusMinutes(horaIntervalo.getMinute()).getMinute()).getMinute());
					float horasEmMinutos = (horaduracao.getHour() * 60) + horaduracao.getMinute();
					float horaDividida = (horasEmMinutos / numeroDeAulas) / 60;

					horaduracao = LocalTime.of(Integer.parseInt(String.valueOf(horaDividida).substring(0, String.valueOf(horaDividida).indexOf("."))), (int) Math.ceil(Double.parseDouble("0." + String.valueOf(horaDividida).substring(String.valueOf(horaDividida).indexOf(".")+1))*60));

					horaAulaAtual = horaInicio;
					//horainicio.plusMinutes((long) Math.ceil(Double.parseDouble("0."+horadividida.substring(horadividida.indexOf(".")+1))*60));
				}else if (cmbTiposCurso.getSelectionModel().getSelectedItem().equals("ETIM")){


					horaInicio = LocalTime.of(07, 00);
					horaFim = LocalTime.of(14, 55);
					horaIntervalo = LocalTime.of(01, 20);
					numeroDeAulas = 8;
					horaduracao = LocalTime.of(horaFim.minusHours(horaInicio.plusHours(horaIntervalo.getHour()).getHour()).getHour(), horaFim.minusMinutes(horaInicio.plusMinutes(horaIntervalo.getMinute()).getMinute()).getMinute());
					float horasEmMinutos = (horaduracao.getHour() * 60) + horaduracao.getMinute();
					float horaDividida = (horasEmMinutos / numeroDeAulas) / 60;

					horaduracao = LocalTime.of(Integer.parseInt(String.valueOf(horaDividida).substring(0, String.valueOf(horaDividida).indexOf("."))), (int) Math.ceil(Double.parseDouble("0." + String.valueOf(horaDividida).substring(String.valueOf(horaDividida).indexOf(".")+1))*60));

					horaAulaAtual = horaInicio;
				}else if(cmbTiposCurso.getSelectionModel().getSelectedItem().equals("Tecnico Vespertino")){

					horaInicio = LocalTime.of(13, 15);
					horaFim = LocalTime.of(17, 45);
					horaIntervalo = LocalTime.of(00, 20);
					numeroDeAulas = 4;
					horaduracao = LocalTime.of(horaFim.minusHours(horaInicio.plusHours(horaIntervalo.getHour()).getHour()).getHour(), horaFim.minusMinutes(horaInicio.plusMinutes(horaIntervalo.getMinute()).getMinute()).getMinute());
					float horasEmMinutos = (horaduracao.getHour() * 60) + horaduracao.getMinute();
					float horaDividida = (horasEmMinutos / 5) / 60;

					horaduracao = LocalTime.of(Integer.parseInt(String.valueOf(horaDividida).substring(0, String.valueOf(horaDividida).indexOf("."))), (int) Math.ceil(Double.parseDouble("0." + String.valueOf(horaDividida).substring(String.valueOf(horaDividida).indexOf(".")+1))*60));

					horaAulaAtual = horaInicio;
				}else if(cmbTiposCurso.getSelectionModel().getSelectedItem().equals("Tecnico Noturno")){

					horaInicio = LocalTime.of(18, 50);
					horaFim = LocalTime.of(22, 50);
					horaIntervalo = LocalTime.of(00, 20);
					numeroDeAulas = 4;
					horaduracao = LocalTime.of(horaFim.minusHours(horaInicio.plusHours(horaIntervalo.getHour()).getHour()).getHour(), horaFim.minusMinutes(horaInicio.plusMinutes(horaIntervalo.getMinute()).getMinute()).getMinute());
					float horasEmMinutos = (horaduracao.getHour() * 60) + horaduracao.getMinute();
					float horaDividida = (horasEmMinutos / 5) / 60;

					horaduracao = LocalTime.of(Integer.parseInt(String.valueOf(horaDividida).substring(0, String.valueOf(horaDividida).indexOf("."))), (int) Math.ceil(Double.parseDouble("0." + String.valueOf(horaDividida).substring(String.valueOf(horaDividida).indexOf(".")+1))*60));

					horaAulaAtual = horaInicio;
				}



				for(int contador = 1; contador <= numeroDeAulas; contador ++) {
					if(contador > 1 && cmbTiposCurso.getSelectionModel().getSelectedItem() == "ETIM"){
						if(contador == 4){
							horaAulaAtual = horaAulaAtual.plusMinutes(0).plusMinutes(20);
						}
						else if (contador == 7){
							horaAulaAtual = horaAulaAtual.plusMinutes(0).plusMinutes(55);
						}
						horaAulaAtual = horaAulaAtual.plusMinutes(horaduracao.getMinute());
					}else if(contador > 1 && cmbTiposCurso.getSelectionModel().getSelectedItem() == "Medio"){
						if(contador == 4){
							horaAulaAtual = horaAulaAtual.plusMinutes(0).plusMinutes(20);
						}
						horaAulaAtual = horaAulaAtual.plusMinutes(horaduracao.getMinute());
					}else if(contador > 1 && cmbTiposCurso.getSelectionModel().getSelectedItem() == "Tecnico Vespertino"){
						if(contador == 3){
							horaAulaAtual = horaAulaAtual.plusMinutes(0).plusMinutes(17);
						}
						horaAulaAtual = horaAulaAtual.plusMinutes(horaduracao.getMinute()).plusMinutes(horaduracao.getMinute() / 4);
					}else if(contador > 1 && cmbTiposCurso.getSelectionModel().getSelectedItem() == "Tecnico Noturno"){
						if(contador == 3){
							horaAulaAtual = horaAulaAtual.plusMinutes(0).plusMinutes(13);
						}
						horaAulaAtual = horaAulaAtual.plusMinutes(horaduracao.getMinute());
					}
					System.out.println(horaAulaAtual);
					Horario h = new Horario();
					h.setHora_disp(horaAulaAtual);
					ListHorarios.add(h);
				}

					System.out.println("separador -------------------------");

				for (Horario horario : ListHorarios) {

					System.out.println(horario.getHora_disp());
				}

			    //----------------> Define horas para o inicio e fim do curso; <----------------//
			//}
		});

		//--------------------------------------------------------------------------------------//

	    //----------------> UM POUCO DE DESIGN; <----------------//

	    //----------------> Centraliza H/Vboxes; <----------------//
		this.HBBotoes.setAlignment(Pos.CENTER);
		this.HBAlinhar.setAlignment(Pos.CENTER);
		this.VBTipoCurso.setAlignment(Pos.CENTER);
		this.VBNomeCurso.setAlignment(Pos.CENTER);
		this.VBEscolhaMaterias.setAlignment(Pos.CENTER);
		this.VBEscolhaHorarios.setAlignment(Pos.CENTER);
		this.VBCargaHoraria.setAlignment(Pos.CENTER);
		this.HBCheckbox.setAlignment(Pos.CENTER);

		//--------------------------------------------------------------------------------------//

		//----------------> Cria cena contendo o painel; <----------------//
		Scene cena = new Scene(painel);
		//----------------> Define a cena na janela; <----------------//
		this.setTitle("Cadastro Curso");
		this.setScene(cena);
		//----------------> Mostra a Janela; <----------------//
		this.show();
	}

	private void limparCamposCurso() {
		this.txtNomeCurso.clear();
		this.cmbTiposCurso.getSelectionModel().select(-1);
		/*horaInicio.clear();
		horaTermino.clear();
		horaDuracaoIntervalo.clear();*/
		txtQtdAulas.clear();
		listvMaterias.getItems().clear();
	}

	private void limparCamposMateria() {
		this.txtMateria.clear();
		this.txtCargaHoraria.clear();
		ckbIsDivisible.setSelected(false);
		this.txtMateria.requestFocus();
	}

	//-------------------------------------------------------//
	//---------------->        FIM;        <----------------//
	//----------------> TA TUDO BEM AGORA; <----------------//
	//-------------------------------------------------------//

}
