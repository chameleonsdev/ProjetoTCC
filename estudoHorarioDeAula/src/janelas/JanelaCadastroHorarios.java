package janelas;



import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

import javax.persistence.EntityManager;

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
public class JanelaCadastroHorarios extends Stage{

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

	private TimePicker horaInicio;
	private TimePicker horaTermino;
	private TimePicker horaDuracaoIntervalo;

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

	public JanelaCadastroHorarios() {

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

		this.horaInicio = new TimePicker();
		this.horaTermino = new TimePicker();
		this.horaDuracaoIntervalo = new TimePicker();

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
		this.TiposCurso = FXCollections.observableArrayList("ETIM", "Tecnico", "Medio");

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
		this.VBEscolhaHorarios.getChildren().addAll(this.lblHorario,this.lblInicio,this.horaInicio,this.lblTermino,this.horaTermino,this.lblIntervalo,this.horaDuracaoIntervalo,this.lblQtdAulas,this.txtQtdAulas,this.btnAdcCurso,this.btnVoltar);

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
				this.txtMateria.clear();
				this.txtMateria.requestFocus();
			}
		});

		//----------------> BOTï¿½O DELETE MATERIA; <----------------//
		this.btnRmvMateria.setOnAction(evento -> {

			ListMaterias.remove(this.listvMaterias.getSelectionModel().getSelectedIndex());
			this.txtMateria.requestFocus();


		});

		//----------------> BOTï¿½O ADD CURSO; <----------------//
		this.btnAdcCurso.setOnAction(evento -> {

		    //----------------> CRIA HORAS PARA INICIO E FIM DO CURSO; <----------------//
		    LocalTime horainicio;
		    LocalTime horafim;
		    EntityManager gerenciador = Conexao.gerarGerenciador();

		    //----------------> Define horas para o inicio e fim do curso; <----------------//
		    horainicio = this.horaInicio.getTime();
		    horafim = this.horaTermino.getTime();
		    LocalTime horaduracao = horafim.minusHours(horainicio.getHour()).minusHours(this.horaDuracaoIntervalo.getHoras()).minusMinutes(horainicio.getHour()).minusMinutes(this.horaDuracaoIntervalo.getMinutos());
	    	float dividehora = (((horaduracao.getHour()*60)+horaduracao.getMinute())/Integer.parseInt(this.txtQtdAulas.getText()));
	    	String horadividida = String.valueOf(dividehora / 60);
		    //----------------> Contador para definir o horario de cada aula do curso; <----------------//
		    for(int x = 0;x < Integer.parseInt(this.txtQtdAulas.getText());x++)
		    {
		    	//----------------> Verifica se ï¿½ hora do intervalo; <----------------//
		    	if(x == Integer.parseInt(this.txtQtdAulas.getText())/2)
		    	{
		    		//----------------> Adiciona tempo do intervalo na hora da proxima aula; <----------------//
		    		horainicio = horainicio.plusHours(this.horaDuracaoIntervalo.getHoras());
		    		horainicio = horainicio.plusMinutes(this.horaDuracaoIntervalo.getMinutos());
		    	}
		    	//----------------> Cria nova hora do curso; <----------------//
		    	Horario h = new Horario();
		    	h.setHora_disp(horainicio);

		    	//----------------> Adiciona nova aula na lista de horarios; <----------------//
		    	this.ListHorarios.add(h);
		    	//----------------> Soma a duração de uma aula nas horas; <----------------//

		    	System.out.println(horadividida.substring(0, horadividida.indexOf(".")));
		    	horainicio = horainicio.plusHours(Integer.parseInt(horadividida.substring(0, horadividida.indexOf("."))));
		    	horainicio = horainicio.plusMinutes((long) Math.ceil(Double.parseDouble("0."+horadividida.substring(horadividida.indexOf(".")+1))*60));
		    	System.out.println(horainicio.getHour()+":"+horainicio.getMinute());
		    }

		    //----------------> Cria um curso novo; <----------------//
		    //----------------> !!!!!!!!!!!!!!!!INCOMPLETO!!!!!!!!!!!!!!!!!; <----------------//

			Curso c = new Curso();

			c.setNome_curso(this.txtNomeCurso.getText());
			c.setTipo_curso(this.cmbTiposCurso.getSelectionModel().getSelectedItem().toString());
			c.setMaterias(this.ListMaterias);
			c.setHorarios(this.ListHorarios);

			//----------------> Da INSERT no curso; <----------------//
			gerenciador.getTransaction().begin();
			gerenciador.persist(c);
			gerenciador.getTransaction().commit();
			gerenciador.close();
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
		this.setScene(cena);
		//----------------> Mostra a Janela; <----------------//
		this.show();
	}

	//-------------------------------------------------------//
	//---------------->        FIM;        <----------------//
	//----------------> TA TUDO BEM AGORA; <----------------//
	//-------------------------------------------------------//

}
