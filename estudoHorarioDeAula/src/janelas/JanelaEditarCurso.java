package janelas;

import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Banco.Conexao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import controles.MessageBox;
import controles.TimePicker;
import entidades.Curso;
import entidades.Horario;
import entidades.Materia;

public class JanelaEditarCurso extends Stage {

	// --------------------------------------------------------------------------------------//

	// ----------------> SAI WARNINGS; <----------------//

	private Label lblInicio;
	private Label lblTermino;
	private Label lblMateria;
	private Label lblHorario;
	private Label lblQtdAulas;
	private Label lblIntervalo;
	private Label lblNomeCurso;
	private Label lblTipoCurso;
	private Label lblNewNomeCurso;
	private Label lblFiltrosPesq;
	private Label lblNewTipoCurso;
	private Label lblCargaHoraria;

	private TextField txtMateria;
	private TextField txtHorario;
	private TextField txtQtdAulas;
	private TextField txtNomeCurso;
	private TextField txtNewNomeCurso;
	private TextField txtCargaHoraria;

	private CheckBox ckbIsDivisible;

	private TimePicker horaInicio;
	private TimePicker horaTermino;
	private TimePicker horaDuracaoIntervalo;

	private Button btnVoltar;
	private Button btnEdtCurso;
	private Button btnRmvMateria;
	private Button btnAdcMateria;
	private Button btnAdcHorario;

	private VBox VBGeral;
	private VBox VBCargaHoraria;
	private VBox VBNomeCurso;
	private VBox VBTipoCurso;
	private VBox VBEscolhaMaterias;
	private VBox VBEscolhaHorarios;

	private Curso cursoSelecionado;

	private HBox HBBotoes;
	private HBox HBCheckbox;
	private HBox HBAlinhar;

	private ObservableList<String> TiposCurso;
	private ObservableList<Horario> ListHorarios;
	private ObservableList<Materia> ListMaterias;
	private ObservableList<Curso> ListCursos;

	private ListView listvMaterias;
	private ListView<Curso> listvCursos;

	private AnchorPane painel;

	private ComboBox<String> cmbTiposCurso;
	private ComboBox<String> cmbNewTipoCurso;

	private EntityManager gerenciador;

	@SuppressWarnings("unchecked")
	public JanelaEditarCurso() {

		// --------------------------------------------------------------------------------------//

		// ----------------> INSTANCIANDO AS PARADAS; <----------------//

		this.lblInicio = new Label("Inicio:");
		this.lblTermino = new Label("T�rmino:");
		this.lblMateria = new Label("Materia:");
		this.lblIntervalo = new Label("Dura��o do Intervalo:");
		this.lblNomeCurso = new Label("Nome do Curso:");
		this.lblTipoCurso = new Label("Tipo do Curso:");
		this.lblHorario = new Label("Horario do Curso");
		this.lblQtdAulas = new Label("Quantidade de Aulas:");
		this.lblNewNomeCurso = new Label("Novo Nome:");
		this.lblFiltrosPesq = new Label("Filtros de Pesquisa");
		this.lblNewTipoCurso = new Label("Novo Tipo:");
		this.lblCargaHoraria = new Label("Carga Horaria da Materia:");

		this.txtMateria = new TextField();
		this.txtHorario = new TextField();
		this.txtQtdAulas = new TextField();
		this.txtNomeCurso = new TextField();
		this.txtNewNomeCurso = new TextField();
		this.txtCargaHoraria = new TextField();

		this.ckbIsDivisible = new CheckBox("A materia � divisivel?");

		this.horaInicio = new TimePicker();
		this.horaTermino = new TimePicker();
		this.horaDuracaoIntervalo = new TimePicker();

		this.btnVoltar = new Button("Voltar");
		this.btnEdtCurso = new Button("Editar Curso");
		this.btnRmvMateria = new Button("Remover Materia");
		this.btnAdcMateria = new Button("Adicionar Materia");
		this.btnAdcHorario = new Button("Adicionar Horario");

		this.VBGeral = new VBox(5);
		this.VBCargaHoraria = new VBox(5);
		this.VBNomeCurso = new VBox(5);
		this.VBTipoCurso = new VBox(5);
		this.VBEscolhaMaterias = new VBox(5);
		this.VBEscolhaHorarios = new VBox(5);

		this.HBBotoes = new HBox(5);
		this.HBCheckbox = new HBox(5);
		this.HBAlinhar = new HBox(5);

		this.ListHorarios = FXCollections.observableArrayList();
		this.ListMaterias = FXCollections.observableArrayList();
		this.ListCursos = FXCollections.observableArrayList();
		this.TiposCurso = FXCollections.observableArrayList("ETIM", "Tecnico", "Medio");

		this.listvMaterias = new ListView();
		this.listvCursos = new ListView<Curso>();

		this.painel = new AnchorPane();

		this.cmbTiposCurso = new ComboBox<String>();
		this.cmbNewTipoCurso = new ComboBox<String>();

		this.gerenciador = Conexao.gerarGerenciador();

		// --------------------------------------------------------------------------------------//

		// ----------------> PREENCHENDO LISTAS E BOXES; <----------------//

		// ----------------> Preenchendo as comboxes e listas;
		// <----------------//
		this.cmbTiposCurso.setItems(TiposCurso);
		this.cmbNewTipoCurso.setItems(TiposCurso);
		this.listvMaterias.setItems(ListMaterias);
		// this.listvHorarios.setItems(ListHorarios);

		// ----------------> Adicionando componentes nas H/Vboxes;
		// <----------------//
		this.VBGeral.getChildren().addAll(this.VBNomeCurso, this.VBTipoCurso, this.listvCursos);
		this.HBBotoes.getChildren().addAll(this.btnAdcMateria, this.btnRmvMateria);
		this.VBCargaHoraria.getChildren().addAll(this.lblCargaHoraria, this.txtCargaHoraria);
		this.HBCheckbox.getChildren().addAll(this.VBCargaHoraria, this.ckbIsDivisible);
		this.VBNomeCurso.getChildren().addAll(this.lblFiltrosPesq, this.lblNomeCurso, this.txtNomeCurso);
		this.VBTipoCurso.getChildren().addAll(this.lblTipoCurso, this.cmbTiposCurso);
		this.VBEscolhaMaterias.getChildren().addAll(this.lblNewNomeCurso, this.txtNewNomeCurso, this.lblNewTipoCurso,
				this.cmbNewTipoCurso, this.lblMateria, this.txtMateria, this.HBCheckbox, this.HBBotoes,
				this.listvMaterias);
		this.VBEscolhaHorarios.getChildren().addAll(this.lblHorario, this.lblInicio, this.horaInicio, this.lblTermino,
				this.horaTermino, this.lblIntervalo, this.horaDuracaoIntervalo, this.lblQtdAulas, this.txtQtdAulas,
				this.btnEdtCurso, this.btnVoltar);

		this.VBGeral.setPadding(new Insets(5, 5, 5, 5));
		this.VBGeral.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		// ----------------> Adicionando tudo na HBox Principal;
		// <----------------//
		this.HBAlinhar.getChildren().addAll(this.VBGeral, this.VBEscolhaMaterias, this.VBEscolhaHorarios);

		// ----------------> Adicionando hbox Principal no painel;
		// <----------------//
		this.painel.getChildren().addAll(this.HBAlinhar);

		// --------------------------------------------------------------------------------------//

		// ----------------> DANDO A��O PARA OS BOT�ES;
		// <----------------//

		this.txtNomeCurso.setOnKeyPressed(evento -> {
			Query sql;
			if (cmbTiposCurso.getSelectionModel().getSelectedIndex() > -1) {
				ListCursos.clear();
				sql = gerenciador.createQuery(
						"from Curso Where tipo_curso = '" + cmbTiposCurso.getSelectionModel().getSelectedItem()
								+ "' and nome_curso like '%" + txtNomeCurso.getText() + "%'");
			} else {
				ListCursos.clear();
				sql = gerenciador.createQuery("from Curso Where nome_curso like '%" + txtNomeCurso.getText() + "%'");
			}
			List<Curso> retornos = sql.getResultList();
			if (retornos.size() > 0) {
				for (Curso c : retornos) {
					ListCursos.add(c);
				}
				listvCursos.setItems(ListCursos);
			} else {
				ListCursos.clear();
			}
		});

		this.listvCursos.setOnMouseClicked(value -> {
			if (value.getClickCount() == 2) {
				cursoSelecionado = listvCursos.getSelectionModel().getSelectedItem();
				listvMaterias.getItems().clear();
				txtNewNomeCurso.setText(listvCursos.getSelectionModel().getSelectedItem().getNome_curso());
				listvMaterias.getItems().addAll(listvCursos.getSelectionModel().getSelectedItem().getMaterias());
				horaInicio
						.setTime(listvCursos.getSelectionModel().getSelectedItem().getHorarios().get(0).getHora_disp());
				LocalTime hora1 = listvCursos.getSelectionModel().getSelectedItem().getHorarios().get(0).getHora_disp();
				LocalTime hora2 = listvCursos.getSelectionModel().getSelectedItem().getHorarios().get(1).getHora_disp();
				LocalTime duracaoAula;
				LocalTime hora3 = listvCursos.getSelectionModel().getSelectedItem().getHorarios()
						.get((listvCursos.getSelectionModel().getSelectedItem().getHorarios().size() / 2) - 1)
						.getHora_disp();
				LocalTime hora4 = listvCursos.getSelectionModel().getSelectedItem().getHorarios()
						.get((listvCursos.getSelectionModel().getSelectedItem().getHorarios().size() / 2))
						.getHora_disp();
				LocalTime duracaoIntervalo;
				duracaoAula = LocalTime.of(hora2.minusHours(hora1.getHour()).getHour(),
						hora2.minusMinutes(hora2.getMinute()).getMinute());
				duracaoIntervalo = LocalTime.of(0, hora4.minusMinutes(hora3.getMinute()).getMinute());
				System.out.println(duracaoAula);
				System.out.println(duracaoAula.getHour() + " - " + duracaoAula.getMinute());
				horaTermino.setTime(listvCursos.getSelectionModel().getSelectedItem().getHorarios()
						.get(listvCursos.getSelectionModel().getSelectedItem().getHorarios().size() - 1).getHora_disp()
						.plusHours(duracaoAula.getHour()).plusMinutes(duracaoAula.getMinute()));
				horaDuracaoIntervalo.setTime(duracaoIntervalo);
				txtQtdAulas.setText(
						String.valueOf(listvCursos.getSelectionModel().getSelectedItem().getHorarios().size()));
				this.cmbNewTipoCurso.getSelectionModel()
						.select(listvCursos.getSelectionModel().getSelectedItem().getTipo_curso());
			}
		});

		this.cmbTiposCurso.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue ov, Object t, Object t1) {
				Query sql;
				if (txtNomeCurso.getText().length() > 0) {
					sql = gerenciador.createQuery(
							"from Curso Where tipo_curso = '" + cmbTiposCurso.getSelectionModel().getSelectedItem()
									+ "' and nome_curso = '" + txtNomeCurso.getText() + "'");
				} else {
					sql = gerenciador.createQuery("from Curso Where tipo_curso = '"
							+ cmbTiposCurso.getSelectionModel().getSelectedItem() + "'");
				}
				List<Curso> retornos = sql.getResultList();
				if (retornos.size() > 0) {
					for (Curso c : retornos) {
						ListCursos.add(c);
					}
					listvCursos.setItems(ListCursos);
				} else {
					ListCursos.clear();
				}
			}

		});

		// ----------------> BOT�O ADD MATERIA; <----------------//
		this.btnAdcMateria.setOnAction(evento -> {
			// ----------------> Verifica se o texto da materia n�o est�
			// vazio; <----------------//
			if (!this.txtMateria.getText().equals("")) {
				// ----------------> Cria um novo objeto materia para ser
				// adicionado � lista de materias; <----------------//
				Materia m = new Materia();
				m.setNome_materia(this.txtMateria.getText());
				this.ListMaterias.add(m);
				limparCamposMateria();
			}
		});

		// ----------------> BOT�O DELETE MATERIA; <----------------//
		this.btnRmvMateria.setOnAction(evento -> {

			ListMaterias.remove(this.listvMaterias.getSelectionModel().getSelectedIndex());
			this.txtMateria.requestFocus();

		});

		// ----------------> BOT�O ADD CURSO; <----------------//
		this.btnEdtCurso.setOnAction(evento -> {

			if (this.txtNewNomeCurso.getText().isEmpty()) {

			} else if (this.cmbNewTipoCurso.getSelectionModel().getSelectedIndex() < 0) {

			} else if (this.listvMaterias.getItems().size() < 5) {

			} else if (this.horaInicio.getTime().getHour() < 4 || this.horaInicio.getTime().getHour() > 21 || this.horaTermino.getTime().getHour() < 6 || this.horaTermino.getTime().getHour() > 0) {

			} else if (this.txtQtdAulas.getText().isEmpty() || Integer.parseInt(this.txtQtdAulas.getText()) % 2 != 0) {

			} else if (this.horaDuracaoIntervalo.getTime().getHour() > 1) {

			} else {
				// ----------------> CRIA HORAS PARA INICIO E FIM DO CURSO;
				// <----------------//
				LocalTime horainicio;
				LocalTime horafim;

				// ----------------> Define horas para o inicio e fim do curso;
				// <----------------//
				horainicio = this.horaInicio.getTime();
				horafim = this.horaTermino.getTime();
				LocalTime horaduracao = horafim.minusHours(horainicio.getHour())
						.minusHours(this.horaDuracaoIntervalo.getHoras()).minusMinutes(horainicio.getHour())
						.minusMinutes(this.horaDuracaoIntervalo.getMinutos());
				float dividehora = (((horaduracao.getHour() * 60) + horaduracao.getMinute())
						/ Integer.parseInt(this.txtQtdAulas.getText()));
				String horadividida = String.valueOf(dividehora / 60);
				// ----------------> Contador para definir o horario de cada
				// aula do curso; <----------------//
				for (int x = 0; x < Integer.parseInt(this.txtQtdAulas.getText()); x++) {
					// ----------------> Verifica se � hora do intervalo;
					// <----------------//
					if (x == Integer.parseInt(this.txtQtdAulas.getText()) / 2) {
						// ----------------> Adiciona tempo do intervalo na hora
						// da proxima aula; <----------------//
						horainicio = horainicio.plusHours(this.horaDuracaoIntervalo.getHoras());
						horainicio = horainicio.plusMinutes(this.horaDuracaoIntervalo.getMinutos());
					}
					// ----------------> Cria nova hora do curso;
					// <----------------//
					Horario h = new Horario();
					h.setHora_disp(horainicio);

					// ----------------> Adiciona nova aula na lista de
					// horarios; <----------------//
					this.ListHorarios.add(h);
					// ----------------> Soma a dura��o de uma aula nas
					// horas; <----------------//

					System.out.println(horadividida.substring(0, horadividida.indexOf(".")));
					horainicio = horainicio
							.plusHours(Integer.parseInt(horadividida.substring(0, horadividida.indexOf("."))));
					horainicio = horainicio.plusMinutes((long) Math.ceil(
							Double.parseDouble("0." + horadividida.substring(horadividida.indexOf(".") + 1)) * 60));
					System.out.println(horainicio.getHour() + ":" + horainicio.getMinute());
				}

				// ----------------> Cria um curso novo; <----------------//
				// ---------------->
				// !!!!!!!!!!!!!!!!INCOMPLETO!!!!!!!!!!!!!!!!!;
				// <----------------//

				try {
					cursoSelecionado.setNome_curso(txtNewNomeCurso.getText());
					cursoSelecionado.setMaterias(ListMaterias);
					cursoSelecionado.setTipo_curso(cmbTiposCurso.getSelectionModel().getSelectedItem().toString());
					cursoSelecionado.setHorarios(ListHorarios);

					// ----------------> Da INSERT no curso; <----------------//
					Conexao.update(cursoSelecionado);
					MessageBox.ShowInfo("Sucesso", "Curso editado com sucesso!");
				} catch (Exception e) {
					MessageBox.ShowError("Erro!", "Erro ao editar : " + e.getMessage());
				}
				limparCamposCurso();
				listvCursos.getItems().clear();
			}
		});

		// --------------------------------------------------------------------------------------//

		// ----------------> UM POUCO DE DESIGN; <----------------//

		// ----------------> Centraliza H/Vboxes; <----------------//
		this.HBBotoes.setAlignment(Pos.CENTER);
		this.HBAlinhar.setAlignment(Pos.CENTER);
		this.VBTipoCurso.setAlignment(Pos.CENTER);
		this.VBNomeCurso.setAlignment(Pos.CENTER);
		this.VBEscolhaMaterias.setAlignment(Pos.CENTER);
		this.VBEscolhaHorarios.setAlignment(Pos.CENTER);

		// --------------------------------------------------------------------------------------//

		// ----------------> Cria cena contendo o painel; <----------------//
		Scene cena = new Scene(painel);
		// ----------------> Define a cena na janela; <----------------//
		this.setTitle("Editar Curso");
		this.setScene(cena);
		// ----------------> Mostra a Janela; <----------------//
		this.show();
	}// TODO Auto-generated constructor stub

	private void limparCamposCurso() {
		this.txtNewNomeCurso.clear();
		this.cmbNewTipoCurso.getSelectionModel().select(-1);
		horaInicio.clear();
		horaTermino.clear();
		horaDuracaoIntervalo.clear();
		txtQtdAulas.clear();
		listvMaterias.getItems().clear();
	}

	private void limparCamposMateria() {
		this.txtMateria.clear();
		this.txtCargaHoraria.clear();
		ckbIsDivisible.setSelected(false);
		this.txtMateria.requestFocus();
	}

	// -------------------------------------------------------//
	// ----------------> FIM; <----------------//
	// ----------------> TA TUDO BEM AGORA; <----------------//
	// -------------------------------------------------------//

}
