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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	private Label lblMateria;
	private Label lblHorario;
	private Label lblNomeCurso;
	private Label lblTipoCurso;
	private Label lblCurso;
	private Label lblModulo;
	private Label lblCargaHoraria;

	private TextField txtMateria;
	private TextField txtHorario;
	private TextField txtNomeCurso;
	private ComboBox<String> txtCurso;
	private ComboBox<String> txtModulo;
	private TextField txtCargaHoraria;

	private CheckBox ckbIsDivisible;

	//private TimePicker horaInicio;
	//private TimePicker horaTermino;
	//private TimePicker horaDuracaoIntervalo;

	private Button btnVoltar;
	private Button btnAdcCurso;
	private Button btnRmvMateria;
	private Button btnAdcMateria;
	private Button btnEditarMateria;

	private VBox VBGeral;
	private VBox VBNomeCurso;
	private VBox VBTipoCurso;
	private VBox VBCurso;
	private VBox VBModulo;
	private VBox VBEscolhaMaterias;
	private VBox VBEscolhaHorarios;
	private VBox VBCargaHoraria;
	private VBox VBAlinhar;

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

	private String tipo;
	private String curso;
	private String modulo;

	private Materia materiaEditar = new Materia();

	//--------------------------------------------------------------------------------------//

	public JanelaCadastroCursos() {

		//--------------------------------------------------------------------------------------//

		//----------------> INSTANCIANDO AS PARADAS; <----------------//

		this.lblMateria = new Label("Materia:");
		this.lblNomeCurso = new Label("Nome do Curso:");
		this.lblTipoCurso = new Label("Tipo do Curso:");
		this.lblCurso = new Label("Curso:");
		this.lblModulo = new Label("Modulo:");
		this.lblCargaHoraria = new Label("Carga horaria da materia:");

		this.txtMateria = new TextField();
		this.txtHorario = new TextField();
		this.txtNomeCurso = new TextField();
		this.txtCurso = new ComboBox<String>();
		this.txtModulo = new ComboBox<String>();
		this.txtCargaHoraria = new TextField();

		this.ckbIsDivisible = new CheckBox("A materia é divisivel?");

		//this.horaInicio = new TimePicker();
		//this.horaTermino = new TimePicker();
		//this.horaDuracaoIntervalo = new TimePicker();

		this.btnVoltar = new Button("Voltar");
		this.btnAdcCurso = new Button("Adicionar Curso");
		this.btnRmvMateria = new Button("Remover Materia");
		this.btnAdcMateria = new Button("Adicionar Materia");
		this.btnEditarMateria = new Button("Editar Materia");
		this.btnEditarMateria.setDisable(true);

		this.VBGeral = new VBox(5);
		this.VBNomeCurso = new VBox(5);
		this.VBTipoCurso = new VBox(5);
		this.VBEscolhaMaterias = new VBox(5);
		this.VBEscolhaHorarios = new VBox(5);
		this.VBCargaHoraria = new VBox(5);
		this.VBModulo = new VBox(5);
		this.VBCurso = new VBox(5);
		this.VBAlinhar = new VBox(5);

		this.HBBotoes = new HBox(5);
		this.HBAlinhar = new HBox(5);
		this.HBCheckbox = new HBox(5);

		this.ListHorarios = FXCollections.observableArrayList();
		this.ListMaterias = FXCollections.observableArrayList();
		this.TiposCurso = FXCollections.observableArrayList("ETIM", "Tecnico Vespertino", "Tecnico Noturno", "Medio");

		this.txtNomeCurso.setEditable(false);

		ObservableList<String> cursosETIM = FXCollections.observableArrayList("Eletrotécnica","Logistica");
		ObservableList<String> cursosTecnico = FXCollections.observableArrayList("Administração","Informatica");
		ObservableList<String> cursosTecnicoN = FXCollections.observableArrayList("Eletrotécnica","Logistica","Informatica para internet");
		ObservableList<String> modulos = FXCollections.observableArrayList("Primeiro", "Segundo", "Terceiro");
		ObservableList<String> modulosE = FXCollections.observableArrayList("Primeiro", "Segundo", "Terceiro","Quarto");

		ObservableList<String> mEtimEletroI = FXCollections.observableArrayList("Língua Portuguesa e Literatura", "Artes", "Educação Física", "História", "Geografia", "Filosofia", "Sociologia", "Matematica", "Física", "Quimica", "Biologia", "Inglês", "EB", "AIDT", "STM", "ED", "IE I", "Eletrônica I");
		ObservableList<Integer> cargasEtimEletroI = FXCollections.observableArrayList(160, 120, 80, 80, 80, 40, 40, 160, 80, 80, 80, 80, 120, 80, 80, 80, 80, 80);
		ObservableList<Boolean> divsEtimEletroI = FXCollections.observableArrayList(false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true , true, true);
		ObservableList<Materia> materiasEtimEletroI = FXCollections.observableArrayList();
		for (int x = 0; x < mEtimEletroI.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mEtimEletroI.get(x));
			m.setCarga_materia(cargasEtimEletroI.get(x));
			m.setDivisivel_materia(divsEtimEletroI.get(x));
			materiasEtimEletroI.add(m);
		}

		ObservableList<String> mEtimEletroII = FXCollections.observableArrayList("Língua Portuguesa e Literatura", "Educação Física","História", "Geografia", "Filosofia", "Sociologia", "Matematica", "Física", "Quimica", "Biologia", "Inglês", "IE II", "Eletrônica II", "ECO", "CE", "ME I", "CCA I");
		ObservableList<Integer> cargasEtimEletroII = FXCollections.observableArrayList(160, 80, 80, 80, 40, 40, 160, 80, 80, 80, 80, 80, 80, 40, 80, 120, 120);
		ObservableList<Boolean> divsEtimEletroII = FXCollections.observableArrayList(false, false, false, false, false, false, false, false, false, false, false, true, true, true, true , true, true);
		ObservableList<Materia> materiasEtimEletroII = FXCollections.observableArrayList();
		for (int x = 0; x < mEtimEletroII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mEtimEletroII.get(x));
			m.setCarga_materia(cargasEtimEletroII.get(x));
			m.setDivisivel_materia(divsEtimEletroII.get(x));
			materiasEtimEletroII.add(m);
		}

		ObservableList<String> mEtimEletroIII = FXCollections.observableArrayList("Língua Portuguesa e Literatura", "Educação Física","História", "Geografia", "Filosofia", "Sociologia", "Matematica", "Física", "Quimica", "Biologia", "Inglês", "ME II", "CCA II", "EEST", "TMIE", "GTDE", "PTCC");
		ObservableList<Integer> cargasEtimEletroIII = FXCollections.observableArrayList(160, 80, 80, 80, 40, 40, 160, 80, 80, 80, 80, 80, 80, 120, 120, 80, 80);
		ObservableList<Boolean> divsEtimEletroIII = FXCollections.observableArrayList(false, false, false, false, false, false, false, false, false, false, false, true, true, true, true , true, true);
		ObservableList<Materia> materiasEtimEletroIII = FXCollections.observableArrayList();
		for (int x = 0; x < mEtimEletroIII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mEtimEletroIII.get(x));
			m.setCarga_materia(cargasEtimEletroIII.get(x));
			m.setDivisivel_materia(divsEtimEletroIII.get(x));
			materiasEtimEletroIII.add(m);
		}

		ObservableList<String> mEtimLogI = FXCollections.observableArrayList("Língua Portuguesa e Literatura", "Artes", "Educação Física","História", "Geografia", "Filosofia", "Sociologia", "Matematica", "Física", "Quimica", "Biologia", "Inglês", "ECO", "IL", "PEE", "AI", "GP", "Marketing");
		ObservableList<Integer> cargasEtimLogI = FXCollections.observableArrayList(160, 120, 80, 80, 80, 40, 40, 160, 80, 80, 80, 80, 40, 80, 80, 80, 80, 80);
		ObservableList<Boolean> divsEtimLogI = FXCollections.observableArrayList(false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true , true, true);
		ObservableList<Materia> materiasEtimLogI = FXCollections.observableArrayList();
		for (int x = 0; x < mEtimLogI.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mEtimLogI.get(x));
			m.setCarga_materia(cargasEtimLogI.get(x));
			m.setDivisivel_materia(divsEtimLogI.get(x));
			materiasEtimLogI.add(m);
		}

		ObservableList<String> mEtimLogII = FXCollections.observableArrayList("Língua Portuguesa e Literatura", "Educação Física","História", "Geografia", "Filosofia", "Sociologia", "Matematica", "Física", "Quimica", "Biologia", "Inglês", "MED", "TIL", "PPCP", "CL", "GRM");
		ObservableList<Integer> cargasEtimLogII = FXCollections.observableArrayList(160, 80, 80, 80, 40, 40, 160, 80, 80, 80, 80, 120, 80, 120, 80, 120);
		ObservableList<Boolean> divsEtimLogII = FXCollections.observableArrayList(false, false, false, false, false, false, false, false, false, false, false, true, true, true, true , true);
		ObservableList<Materia> materiasEtimLogII = FXCollections.observableArrayList();
		for (int x = 0; x < mEtimLogII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mEtimLogII.get(x));
			m.setCarga_materia(cargasEtimLogII.get(x));
			m.setDivisivel_materia(divsEtimLogII.get(x));
			materiasEtimLogII.add(m);
		}

		ObservableList<String> mEtimLogIII = FXCollections.observableArrayList("Língua Portuguesa e Literatura", "Educação Física","História", "Geografia", "Filosofia", "Sociologia", "Matematica", "Física", "Quimica", "Biologia", "Inglês", "GCAT", "LIELT", "LRGQT", "SST", "PDTCC");
		ObservableList<Integer> cargasEtimLogIII = FXCollections.observableArrayList(160, 80, 80, 80, 40, 40, 160, 80, 80, 80, 80, 120, 80, 80, 40, 80);
		ObservableList<Boolean> divsEtimLogIII = FXCollections.observableArrayList(false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true);
		ObservableList<Materia> materiasEtimLogIII = FXCollections.observableArrayList();
		for (int x = 0; x < mEtimLogIII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mEtimLogIII.get(x));
			m.setCarga_materia(cargasEtimLogIII.get(x));
			m.setDivisivel_materia(divsEtimLogIII.get(x));
			materiasEtimLogIII.add(m);
		}

		ObservableList<String> mTecnicoInfoI = FXCollections.observableArrayList("LP", "TPI I", "TLBD I", "AS", "GSO I", "OSA", "IMC", "II", "LTT");
		ObservableList<Integer> cargaTecnicoInfoI = FXCollections.observableArrayList(80, 40, 40, 40, 40, 40, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoInfoI = FXCollections.observableArrayList(true, true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoInfoI = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoInfoI.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoInfoI.get(x));
			m.setCarga_materia(cargaTecnicoInfoI.get(x));
			m.setDivisivel_materia(divsTecnicoInfoI.get(x));
			materiasTecnicoInfoI.add(m);
		}

		ObservableList<String> mTecnicoInfoII = FXCollections.observableArrayList("TOO", "TPI II", "TLBD II", "DS I", "PC I", "GSO II", "EI", "PTCC");
		ObservableList<Integer> cargaTecnicoInfoII = FXCollections.observableArrayList(40, 40, 40, 80, 80, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoInfoII = FXCollections.observableArrayList(true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoInfoII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoInfoII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoInfoII.get(x));
			m.setCarga_materia(cargaTecnicoInfoII.get(x));
			m.setDivisivel_materia(divsTecnicoInfoII.get(x));
			materiasTecnicoInfoII.add(m);
		}

		ObservableList<String> mTecnicoInfoIII = FXCollections.observableArrayList("RCD", "TM", "TLBD III", "DS II", "PC II", "SD", "ECO", "DTCC");
		ObservableList<Integer> cargaTecnicoInfoIII = FXCollections.observableArrayList(40, 40, 40, 80, 80, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoInfoIII = FXCollections.observableArrayList(true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoInfoIII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoInfoIII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoInfoIII.get(x));
			m.setCarga_materia(cargaTecnicoInfoIII.get(x));
			m.setDivisivel_materia(divsTecnicoInfoIII.get(x));
			materiasTecnicoInfoIII.add(m);
		}

		ObservableList<String> mTecnicoAdmI = FXCollections.observableArrayList("GP I", "CF", "GE", "ECO", "AI", "POC", "LTT", "TO");
		ObservableList<Integer> cargaTecnicoAdmI = FXCollections.observableArrayList(40, 80, 80, 40, 40, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoAdmI = FXCollections.observableArrayList(true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoAdmI = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoAdmI.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoAdmI.get(x));
			m.setCarga_materia(cargaTecnicoAdmI.get(x));
			m.setDivisivel_materia(divsTecnicoAdmI.get(x));
			materiasTecnicoAdmI.add(m);
		}

		ObservableList<String> mTecnicoAdmII = FXCollections.observableArrayList("GP II", "AM", "LE", "COC", "CE", "GEI", "PTCC");
		ObservableList<Integer> cargaTecnicoAdmII = FXCollections.observableArrayList(80, 80, 40, 40, 40, 80, 40);
		ObservableList<Boolean> divsTecnicoAdmII = FXCollections.observableArrayList(true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoAdmII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoAdmII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoAdmII.get(x));
			m.setCarga_materia(cargaTecnicoAdmII.get(x));
			m.setDivisivel_materia(divsTecnicoAdmII.get(x));
			materiasTecnicoAdmII.add(m);
		}

		ObservableList<String> mTecnicoAdmIII = FXCollections.observableArrayList("MI", "GFE", "GP III", "GPM", "LENI", "II", "DTCC");
		ObservableList<Integer> cargaTecnicoAdmIII = FXCollections.observableArrayList(40, 80, 40, 80, 80, 40, 40);
		ObservableList<Boolean> divsTecnicoAdmIII = FXCollections.observableArrayList(true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoAdmIII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoAdmIII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoAdmIII.get(x));
			m.setCarga_materia(cargaTecnicoAdmIII.get(x));
			m.setDivisivel_materia(divsTecnicoAdmIII.get(x));
			materiasTecnicoAdmIII.add(m);
		}

		ObservableList<String> mTecnicoNoiteEletroI = FXCollections.observableArrayList("EB", "CE I", "ME I", "ED I", "IER", "DT", "AI", "STMA");
		ObservableList<Integer> cargaTecnicoNoiteEletroI = FXCollections.observableArrayList(80, 40, 40, 40, 80, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoNoiteEletroI = FXCollections.observableArrayList(true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteEletroI = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteEletroI.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteEletroI.get(x));
			m.setCarga_materia(cargaTecnicoNoiteEletroI.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteEletroI.get(x));
			materiasTecnicoNoiteEletroI.add(m);
		}

		ObservableList<String> mTecnicoNoiteEletroII = FXCollections.observableArrayList("CE II", "Eletrônica I", "ME II", "ED II", "IEP", "Comandos Elétricos", "ECO", "II");
		ObservableList<Integer> cargaTecnicoNoiteEletroII = FXCollections.observableArrayList(80, 40, 40, 40, 80, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoNoiteEletroII = FXCollections.observableArrayList(true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteEletroII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteEletroII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteEletroII.get(x));
			m.setCarga_materia(cargaTecnicoNoiteEletroII.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteEletroII.get(x));
			materiasTecnicoNoiteEletroII.add(m);
		}

		ObservableList<String> mTecnicoNoiteEletroIII = FXCollections.observableArrayList("CE III", "Eletrônica II", "ME III", "CA I", "IEI", "LTT", "PTCC");
		ObservableList<Integer> cargaTecnicoNoiteEletroIII = FXCollections.observableArrayList(80, 40, 40, 80, 80, 40, 40);
		ObservableList<Boolean> divsTecnicoNoiteEletroIII = FXCollections.observableArrayList(true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteEletroIII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteEletroIII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteEletroIII.get(x));
			m.setCarga_materia(cargaTecnicoNoiteEletroIII.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteEletroIII.get(x));
			materiasTecnicoNoiteEletroIII.add(m);
		}

		ObservableList<String> mTecnicoNoiteEletroVI = FXCollections.observableArrayList("GTDE", "Eletrônica III", "ME IV", "CA II", "TME", "EE", "DTCC");
		ObservableList<Integer> cargaTecnicoNoiteEletroVI = FXCollections.observableArrayList(80, 80, 40, 80, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoNoiteEletroVI = FXCollections.observableArrayList(true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteEletroVI = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteEletroVI.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteEletroVI.get(x));
			m.setCarga_materia(cargaTecnicoNoiteEletroVI.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteEletroVI.get(x));
			materiasTecnicoNoiteEletroVI.add(m);
		}

		ObservableList<String> mTecnicoNoiteLogI = FXCollections.observableArrayList("IL", "PEE", "AI", "LTT", "ECO", "II", "GP", "CFE");
		ObservableList<Integer> cargaTecnicoNoiteLogI = FXCollections.observableArrayList(40, 80, 40, 40, 40, 40, 40, 80);
		ObservableList<Boolean> divsTecnicoNoiteLogI = FXCollections.observableArrayList(true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteLogI = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteLogI.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteLogI.get(x));
			m.setCarga_materia(cargaTecnicoNoiteLogI.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteLogI.get(x));
			materiasTecnicoNoiteLogI.add(m);
		}

		ObservableList<String> mTecnicoNoiteLogII = FXCollections.observableArrayList("GRM", "CL", "PPCP", "MED", "GCA", "Marketing", "PTCC");
		ObservableList<Integer> cargaTecnicoNoiteLogII = FXCollections.observableArrayList(80, 40, 80, 80, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoNoiteLogII = FXCollections.observableArrayList(true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteLogII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteLogII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteLogII.get(x));
			m.setCarga_materia(cargaTecnicoNoiteLogII.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteLogII.get(x));
			materiasTecnicoNoiteLogII.add(m);
		}

		ObservableList<String> mTecnicoNoiteLogIII = FXCollections.observableArrayList("GT", "SST", "LIE", "LR", "TI", "LT", "DTCC");
		ObservableList<Integer> cargaTecnicoNoiteLogIII = FXCollections.observableArrayList(80, 40, 40, 40, 40, 80, 40, 40);
		ObservableList<Boolean> divsTecnicoNoiteLogIII = FXCollections.observableArrayList(true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteLogIII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteLogIII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteLogIII.get(x));
			m.setCarga_materia(cargaTecnicoNoiteLogIII.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteLogIII.get(x));
			materiasTecnicoNoiteLogIII.add(m);
		}

		ObservableList<String> mTecnicoNoiteInfoNetI = FXCollections.observableArrayList("GSO", "OSA I", "IMC", "DDW I", "LP", "AD", "Empreendedorismo", "II", "LTT");
		ObservableList<Integer> cargaTecnicoNoiteInfoNetI = FXCollections.observableArrayList(40, 40, 40, 40, 80, 40, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoNoiteInfoNetI = FXCollections.observableArrayList(true, true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteInfoNetI = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteInfoNetI.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteInfoNetI.get(x));
			m.setCarga_materia(cargaTecnicoNoiteInfoNetI.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteInfoNetI.get(x));
			materiasTecnicoNoiteInfoNetI.add(m);
		}

		ObservableList<String> mTecnicoNoiteInfoNetII = FXCollections.observableArrayList("RCD I", "OSA II", "DS I", "DDW II", "PAW I", "TLBD I", "CP", "CEI", "PTCC");
		ObservableList<Integer> cargaTecnicoNoiteInfoNetII = FXCollections.observableArrayList(40, 40, 80, 40, 40, 40, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoNoiteInfoNetII = FXCollections.observableArrayList(true, true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteInfoNetII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteInfoNetII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteInfoNetII.get(x));
			m.setCarga_materia(cargaTecnicoNoiteInfoNetII.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteInfoNetII.get(x));
			materiasTecnicoNoiteInfoNetII.add(m);
		}

		ObservableList<String> mTecnicoNoiteInfoNetIII = FXCollections.observableArrayList("RCD II", "PI", "DS II", "DDW III", "PAW II", "TLBD II", "MW", "ECO", "DTCC");
		ObservableList<Integer> cargaTecnicoNoiteInfoNetIII = FXCollections.observableArrayList(40, 40, 80, 40, 40, 40, 40, 40, 40);
		ObservableList<Boolean> divsTecnicoNoiteInfoNetIII = FXCollections.observableArrayList(true, true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasTecnicoNoiteInfoNetIII = FXCollections.observableArrayList();
		for (int x = 0; x < mTecnicoNoiteInfoNetIII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mTecnicoNoiteInfoNetIII.get(x));
			m.setCarga_materia(cargaTecnicoNoiteInfoNetIII.get(x));
			m.setDivisivel_materia(divsTecnicoNoiteInfoNetIII.get(x));
			materiasTecnicoNoiteInfoNetIII.add(m);
		}

		ObservableList<String> mMedioI = FXCollections.observableArrayList("Língua Portuguesa e Literatura", "Artes", "Educação Física", "História", "Geografia", "Filosofia", "Sociologia", "Matematica", "Física", "Quimica", "Biologia", "Inglês");
		ObservableList<Integer> cargasMedioI = FXCollections.observableArrayList(160, 120, 80, 80, 80, 40, 40, 160, 80, 80, 80, 80);
		ObservableList<Boolean> divsMedioI = FXCollections.observableArrayList(true, true, true, true, true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasMedioI = FXCollections.observableArrayList();
		for (int x = 0; x < mMedioI.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mMedioI.get(x));
			m.setCarga_materia(cargasMedioI.get(x));
			m.setDivisivel_materia(divsMedioI.get(x));
			materiasMedioI.add(m);
		}

		ObservableList<String> mMedioII = FXCollections.observableArrayList("Língua Portuguesa e Literatura", "Artes", "Educação Física", "História", "Geografia", "Filosofia", "Sociologia", "Matematica", "Física", "Quimica", "Biologia", "Inglês");
		ObservableList<Integer> cargasMedioII = FXCollections.observableArrayList(160, 120, 80, 80, 80, 40, 40, 160, 80, 80, 80, 80);
		ObservableList<Boolean> divsMedioII = FXCollections.observableArrayList(true, true, true, true, true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasMedioII = FXCollections.observableArrayList();
		for (int x = 0; x < mMedioII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mMedioII.get(x));
			m.setCarga_materia(cargasMedioII.get(x));
			m.setDivisivel_materia(divsMedioII.get(x));
			materiasMedioII.add(m);
		}

		ObservableList<String> mMedioIII = FXCollections.observableArrayList("Língua Portuguesa e Literatura", "Artes", "Educação Física", "História", "Geografia", "Filosofia", "Sociologia", "Matematica", "Física", "Quimica", "Biologia", "Inglês");
		ObservableList<Integer> cargasMedioIII = FXCollections.observableArrayList(160, 120, 80, 80, 80, 40, 40, 160, 80, 80, 80, 80);
		ObservableList<Boolean> divsMedioIII = FXCollections.observableArrayList(true, true, true, true, true, true, true, true, true, true, true, true);
		ObservableList<Materia> materiasMedioIII = FXCollections.observableArrayList();
		for (int x = 0; x < mMedioIII.size(); x++) {
			Materia m = new Materia();
			m.setNome_materia(mMedioIII.get(x));
			m.setCarga_materia(cargasMedioIII.get(x));
			m.setDivisivel_materia(divsMedioIII.get(x));
			materiasMedioIII.add(m);
		}


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
		this.VBCurso.getChildren().addAll(this.lblCurso, this.txtCurso);
		this.VBModulo.getChildren().addAll(this.lblModulo, this.txtModulo);
		this.VBEscolhaMaterias.getChildren().addAll(this.lblMateria,this.txtMateria, this.HBCheckbox,this.HBBotoes, this.btnEditarMateria,this.listvMaterias);

		//----------------> Adicionando tudo na HBox Principal; <----------------//
		this.HBAlinhar.getChildren().addAll(this.VBGeral,this.VBEscolhaMaterias);
		this.VBAlinhar.getChildren().addAll(this.HBAlinhar, this.btnAdcCurso, this.btnVoltar);
		//----------------> Adicionando hbox Principal no painel; <----------------//
		this.painel.getChildren().addAll(this.VBAlinhar);

		//--------------------------------------------------------------------------------------//

		//----------------> DANDO Aï¿½ï¿½O PARA OS BOTï¿½ES; <----------------//



		this.cmbTiposCurso.valueProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue ov, String t, String t1) {

	        	if(cmbTiposCurso.getSelectionModel().getSelectedIndex() == -1){
	        		VBGeral.getChildren().remove(VBModulo);
	        		VBGeral.getChildren().remove(VBCurso);
	        	}

	        	txtNomeCurso.setEditable(false);

	        	if (t1.equals("ETIM")) {
	        		txtCurso.setItems(cursosETIM);
	        	}
	        	if (t1.equals("Tecnico Vespertino")) {
	        		txtCurso.setItems(cursosTecnico);
	        	}
	        	if (t1.equals("Tecnico Noturno")) {
	        		txtCurso.setItems(cursosTecnicoN);
	        	}
	        	tipo = t1;
	        	txtNomeCurso.setText(tipo);
	        	curso = "";
	        	listvMaterias.setItems(null);
	        	txtCurso.getSelectionModel().select(-1);

	        	if(!t1.equals("Medio")){
		        	if(VBGeral.getChildren().contains(VBCurso)){
		        		txtCurso.getSelectionModel().select(-1);
		        		if(VBGeral.getChildren().contains(VBModulo)){
		        			VBGeral.getChildren().remove(VBModulo);
		        			txtModulo.getSelectionModel().select(-1);
		        		}
		        	}else{
		        		if(VBGeral.getChildren().contains(VBModulo)){
		        			VBGeral.getChildren().remove(VBModulo);
		        			txtModulo.getSelectionModel().select(-1);
		        		}
		        		VBGeral.getChildren().add(VBCurso);
		        	}
	        	}else{
	        		if(VBGeral.getChildren().contains(VBCurso)){
	        			VBGeral.getChildren().remove(VBCurso);
	        		}
	        		if(VBGeral.getChildren().contains(VBModulo)){
	        			txtModulo.getSelectionModel().select(-1);
	        		}else{
	        			VBGeral.getChildren().add(VBModulo);
	        		}
	        	}
	        }
		});

		this.txtCurso.valueProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue ov, String t, String t1) {
	        	txtNomeCurso.setEditable(false);
	        	if(txtCurso.getSelectionModel().getSelectedIndex() != -1){
		        	if(t1.equals("Eletrotécnica") && tipo.equals("Tecnico Noturno")){
		        		txtModulo.setItems(modulosE);
		        	}else{
		        		txtModulo.setItems(modulos);
		        	}
		        	curso = t1;
		        	txtNomeCurso.setText(tipo + " " + curso);
		        	if(!VBGeral.getChildren().contains(VBModulo)){
		        		VBGeral.getChildren().add(VBModulo);
		        	}else{
		        		txtModulo.getSelectionModel().select(-1);
		        		listvMaterias.setItems(null);
		        	}
		        }
	        }
		});

		this.txtModulo.valueProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue ov, String t, String t1) {
	        	if(txtModulo.getSelectionModel().getSelectedIndex() != -1){
	        		txtNomeCurso.setEditable(true);
		        	if(t1.equals("Primeiro")){
		        		modulo = "I";
		        		txtNomeCurso.setText(tipo + " " + curso + " " + modulo);
		        		if(tipo.equals("ETIM")){
		        			if(curso.equals("Eletrotécnica")){
		        				ListMaterias = materiasEtimEletroI;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Logistica")){
		        				ListMaterias = materiasEtimLogI;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        		if(tipo.equals("Tecnico Vespertino")){
		        			if(curso.equals("Administração")){
		        				ListMaterias = materiasTecnicoAdmI;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Informatica")){
		        				ListMaterias = materiasTecnicoInfoI;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        		if(tipo.equals("Tecnico Noturno")){
		        			if(curso.equals("Eletrotécnica")){
		        				ListMaterias = materiasTecnicoNoiteEletroI;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Logistica")){
		        				ListMaterias = materiasTecnicoNoiteLogI;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Informatica para internet")){
		        				ListMaterias = materiasTecnicoNoiteInfoNetI;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        		if(tipo.equals("Medio")){
		        			ListMaterias = materiasMedioI;
	        				listvMaterias.setItems(ListMaterias);
		        		}
		        	}
		        	if(t1.equals("Segundo")){
		        		modulo = "II";
		        		txtNomeCurso.setText(tipo + " " + curso + " " + modulo);
		        		if(tipo.equals("ETIM")){
		        			if(curso.equals("Eletrotécnica")){
		        				ListMaterias = materiasEtimEletroII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Logistica")){
		        				ListMaterias = materiasEtimLogII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        		if(tipo.equals("Tecnico Vespertino")){
		        			if(curso.equals("Administração")){
		        				ListMaterias = materiasTecnicoAdmII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Informatica")){
		        				ListMaterias = materiasTecnicoInfoII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        		if(tipo.equals("Tecnico Noturno")){
		        			if(curso.equals("Eletrotécnica")){
		        				ListMaterias = materiasTecnicoNoiteEletroII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Logistica")){
		        				ListMaterias = materiasTecnicoNoiteLogII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Informatica para internet")){
		        				ListMaterias = materiasTecnicoNoiteInfoNetII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        		if(tipo.equals("Medio")){
		        			ListMaterias = materiasMedioII;
	        				listvMaterias.setItems(ListMaterias);
		        		}
		        	}
		        	if(t1.equals("Terceiro")){
		        		modulo = "III";
		        		txtNomeCurso.setText(tipo + " " + curso + " " + modulo);
		        		if(tipo.equals("ETIM")){
		        			if(curso.equals("Eletrotécnica")){
		        				ListMaterias = materiasEtimEletroIII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Logistica")){
		        				ListMaterias = materiasEtimLogIII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        		if(tipo.equals("Tecnico Vespertino")){
		        			if(curso.equals("Administração")){
		        				ListMaterias = materiasTecnicoAdmIII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Informatica")){
		        				ListMaterias = materiasTecnicoInfoIII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        		if(tipo.equals("Tecnico Noturno")){
		        			if(curso.equals("Eletrotécnica")){
		        				ListMaterias = materiasTecnicoNoiteEletroIII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Logistica")){
		        				ListMaterias = materiasTecnicoNoiteLogIII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        			if(curso.equals("Informatica para internet")){
		        				ListMaterias = materiasTecnicoNoiteInfoNetIII;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        		if(tipo.equals("Medio")){
		        			ListMaterias = materiasMedioIII;
	        				listvMaterias.setItems(ListMaterias);
		        		}
		        	}
		        	if(t1.equals("Quarto")){
		        		modulo = "IV";
		        		txtNomeCurso.setText(tipo + " " + curso + " " + modulo);
		        		if(tipo.equals("Tecnico Noturno")){
		        			if(curso.equals("Eletrotécnica")){
		        				ListMaterias = materiasTecnicoNoiteEletroVI;
		        				listvMaterias.setItems(ListMaterias);
		        			}
		        		}
		        	}
	        	}
	        }
		});



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

		this.btnEditarMateria.setOnAction(value -> {

			Materia m = new Materia();
			m.setNome_materia(this.txtMateria.getText());
			m.setCarga_materia(Double.parseDouble(this.txtCargaHoraria.getText()));
			m.setDivisivel_materia(this.ckbIsDivisible.isSelected());
			if(listvMaterias.getItems().contains(materiaEditar)){
				listvMaterias.getItems().remove(materiaEditar);
			}
			listvMaterias.getItems().add(m);
			limparCamposMateria();
			btnEditarMateria.setDisable(true);

		});

		//----------------> BOTï¿½O DELETE MATERIA; <----------------//
		this.btnRmvMateria.setOnAction(evento -> {

			ListMaterias.remove(this.listvMaterias.getSelectionModel().getSelectedIndex());
			this.txtMateria.requestFocus();


		});

		this.listvMaterias.setOnMouseClicked(value -> {
			if(value.getClickCount() >= 2){
				if(listvMaterias.getSelectionModel().getSelectedItem() != null){
					Materia m = new Materia();
					m = (Materia) listvMaterias.getSelectionModel().getSelectedItem();
					txtMateria.setText(m.getNome_materia());
					txtCargaHoraria.setText(String.valueOf(m.getCarga_materia()));
					ckbIsDivisible.setSelected(m.getDivisivel_materia());
					materiaEditar = m;
					btnEditarMateria.setDisable(false);
				}
			}
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
			ListHorarios.clear();	
			
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
				if(ListHorarios.size() > 1){
					if(ListMaterias.size() > 1){
						try{
							Curso c = new Curso();
							c.setNome_curso(txtNomeCurso.getText());
							c.setMaterias(ListMaterias);
							c.setHorarios(ListHorarios);
							if(txtModulo.getSelectionModel().getSelectedItem().equals("Primeiro")){
								c.setModulo_curso(1);								
							}else if(txtModulo.getSelectionModel().getSelectedItem().equals("Segundo")){
								c.setModulo_curso(2);	
							}else if(txtModulo.getSelectionModel().getSelectedItem().equals("Terceiro")){
								c.setModulo_curso(3);	
							}else if(txtModulo.getSelectionModel().getSelectedItem().equals("Quarto")){
								c.setModulo_curso(4);	
							}
							Conexao.insert(c);
							limparCamposCurso();
							MessageBox.ShowInfo("Sucesso!", "Cadastrado com Sucesso");
						}catch (Exception e) {
							MessageBox.ShowError("Erro ao inserir", e.getMessage());
						}
					}else{
						MessageBox.ShowError("Erro ao inserir", "Verifique as materias");
					}
				}else{
					MessageBox.ShowError("Erro ao inserir", "Verifique o tipo do curso");
				}
			    //----------------> Define horas para o inicio e fim do curso; <----------------//
			//}
		});

		this.btnVoltar.setOnAction(value -> {
			this.close();
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
		this.VBCurso.setAlignment(Pos.CENTER);
		this.VBModulo.setAlignment(Pos.CENTER);
		this.VBGeral.setMinWidth(250);
		this.VBAlinhar.setAlignment(Pos.CENTER);
		this.btnAdcCurso.setMaxWidth(Double.MAX_VALUE);
		this.btnVoltar.setMaxWidth(Double.MAX_VALUE);

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
