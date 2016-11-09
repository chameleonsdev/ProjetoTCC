package janelas;

import java.util.List;

import Banco.Conexao;
import controles.MessageBox;
import controles.TimePicker;
import entidades.Curso;
import entidades.Professor;
import entidades.Questionmarks;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JanelaDeEditarQuestionmark extends Stage {

//	private Label lblSelecioneCurso;
	//private Label lblSelecioneProfessor;
	private Label lblPontuacaoProfessor;
	private Label lblValorPontuacao;
	private Label lblDispDe;
	private Label lblDispAte;
	private Label lblSelecioneDiaSemana;

	private Button btnAdicionarDisp;
	private Button btnRemoverDisp;
	private Button btnSalvar;
	private Button btnEditar;

	private TextField nomeDoProfessor;

	private Questionmarks qParaEditar = new Questionmarks();

	private ComboBox<Curso> cbSelecionarCurso;
	private ComboBox<Professor> cbSelecionarProfessor;
	private ComboBox<String> cbDiaSemana;

	private ObservableList<String> listDiaSemana = FXCollections
			.observableArrayList("Segunda-Feira", "Terça-Feira",
					"Quarta-Feira", "Quinta-Feira", "Sexta-Feira");
	private ObservableList<Curso> listCurso = FXCollections
			.observableArrayList();
	private ObservableList<Professor> listProfessor = FXCollections
			.observableArrayList();
	private ObservableList<Questionmarks> listInsertQuestionmarks = FXCollections
			.observableArrayList();
	private ObservableList<Questionmarks> listQuestionmarksDoProfessor = FXCollections
			.observableArrayList();

	private TimePicker tpDispDe;
	private TimePicker tpDispAte;

	private AnchorPane apPainelControles1 = new AnchorPane();
	private AnchorPane apPainelControles2 = new AnchorPane();

	private ListView<Questionmarks> lvDispsDiaSemana;

	public JanelaDeEditarQuestionmark(Professor pEdit) {

		List<Curso> selectcurso = Conexao.select("Curso");
		for (Curso i : selectcurso) {
			this.listCurso.add(i);
		}

		//this.lblSelecioneCurso = new Label("Curso: ");
		//this.lblSelecioneProfessor = new Label("Professor: ");
		this.lblValorPontuacao = new Label("0000.00");
		this.lblPontuacaoProfessor = new Label("Pontuação: ");
		this.lblSelecioneDiaSemana = new Label("Dia da Semana: ");
		this.lblDispDe = new Label("Disponível Das: ");
		this.lblDispAte = new Label("Disponível Até As: ");

		this.nomeDoProfessor = new TextField();
		this.nomeDoProfessor.setEditable(false);

		this.btnAdicionarDisp = new Button("Adicionar");
		this.btnAdicionarDisp.setOnAction(event -> {

			try {
				Questionmarks q = new Questionmarks();
				q.setProfessor(cbSelecionarProfessor.getSelectionModel()
						.getSelectedItem());
				q.setHora_entrada(tpDispDe.getTime());
				q.setHora_saida(tpDispAte.getTime());
				q.setDiasemana(cbDiaSemana.getSelectionModel()
						.getSelectedItem().toString());

				Conexao.insert(q);

				MessageBox.ShowInfo("Sucesso!",
						"Disponibilidade inserida com Sucesso!");
				atualizarList(pEdit);
			} catch (Exception e) {
				MessageBox.ShowError("Erro!", "Erro ao inserir Disponibilidade"
						+ e.getMessage());
			}

		});


		this.lvDispsDiaSemana = new ListView<Questionmarks>();
		this.lvDispsDiaSemana.setPrefHeight(80d);
		this.lvDispsDiaSemana.setPrefWidth(120d);

		this.btnRemoverDisp = new Button("Remover");
		this.btnSalvar = new Button("Salvar Disponibilidade");
		this.btnEditar = new Button("Editar Disponibilidade");
		this.btnEditar.setDisable(true);

		this.nomeDoProfessor.setText(pEdit.getNome_professor());

		this.cbSelecionarCurso = new ComboBox<>();
		this.cbSelecionarCurso.setItems(listCurso);
		this.cbSelecionarCurso.setPrefWidth(120d);

		this.cbSelecionarProfessor = new ComboBox<>();
		this.cbSelecionarProfessor.setPrefWidth(120d);

		cbSelecionarCurso.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Object>() {

					@Override
					public void changed(
							ObservableValue<? extends Object> observable,
							Object oldValue, Object newValue) {
						listProfessor.addAll(cbSelecionarCurso
								.getSelectionModel().getSelectedItem()
								.getProfessores());
						cbSelecionarProfessor.setItems(listProfessor);

					}
				});

		cbSelecionarProfessor.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Object>() {

					@Override
					public void changed(
							ObservableValue<? extends Object> observable,
							Object oldValue, Object newValue) {

						if (Conexao.select("Questionmarks").isEmpty()) {
						} else {
							Conexao.selectWhere("Questionmarks",
									"id_professor = "
											+ cbSelecionarProfessor
													.getSelectionModel()
													.getSelectedItem()
													.getId_professor());
						}

					}
				});

		this.cbDiaSemana = new ComboBox<>();
		this.cbDiaSemana.setItems(listDiaSemana);
		this.cbDiaSemana.setPrefWidth(120d);

		cbDiaSemana.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Object>() {

					@Override
					public void changed(ObservableValue<? extends Object> arg0,
							Object arg1, Object arg2) {

						atualizarList(pEdit);


					}

				});



		lvDispsDiaSemana.setOnMouseClicked(value -> {
			if(value.getClickCount() > 1){
				tpDispDe.setTime(lvDispsDiaSemana.getSelectionModel().getSelectedItem().getHora_entrada());
				tpDispAte.setTime(lvDispsDiaSemana.getSelectionModel().getSelectedItem().getHora_saida());
				qParaEditar = lvDispsDiaSemana.getSelectionModel().getSelectedItem();
				btnEditar.setDisable(false);
			}
		});

		this.btnEditar.setOnAction(arg0 -> {
			try{
				qParaEditar.setDiasemana(cbDiaSemana.getSelectionModel().getSelectedItem());
				qParaEditar.setHora_entrada(tpDispDe.getTime());
				qParaEditar.setHora_saida(tpDispAte.getTime());
				Conexao.update(qParaEditar);
				MessageBox.ShowInfo("Sucesso", "Disponibilidade Editada com sucesso");
				btnEditar.setDisable(true);
				atualizarList(pEdit);
			}catch (Exception e) {
				// TODO: handle exception
			}
		});
		this.btnRemoverDisp.setOnAction(value -> {
			if(lvDispsDiaSemana.getSelectionModel().getSelectedIndex() > -1){
				Conexao.delete(lvDispsDiaSemana.getSelectionModel().getSelectedItem());
				MessageBox.ShowInfo("Removido", "Removido com Sucesso");
				lvDispsDiaSemana.getItems().remove(lvDispsDiaSemana.getSelectionModel().getSelectedIndex());
			}
		});

		this.tpDispDe = new TimePicker();
		this.tpDispAte = new TimePicker();

		VBox vbControlesLabels1 = new VBox(5);

		vbControlesLabels1.getChildren().addAll(this.nomeDoProfessor,
			    this.lblSelecioneDiaSemana,
				this.cbDiaSemana, this.lvDispsDiaSemana);

		VBox vbControlesLabels2 = new VBox(5);

		HBox hbBotoes = new HBox(8);

		hbBotoes.getChildren().addAll(this.btnAdicionarDisp,
				this.btnRemoverDisp);

		vbControlesLabels2.getChildren().addAll(this.lblDispDe, this.tpDispDe,
				this.lblDispAte, this.tpDispAte, hbBotoes, this.btnEditar);

		this.apPainelControles1.getChildren().add(vbControlesLabels1);
		this.apPainelControles2.getChildren().add(vbControlesLabels2);

		HBox hbPrincipal = new HBox(50);

		hbPrincipal.getChildren().addAll(this.apPainelControles1,
				this.apPainelControles2);

		Scene cena = new Scene(hbPrincipal);
		this.setScene(cena);
		this.show();

	}

	public void atualizarList(Professor pEdit){
		if (Conexao.select("Questionmarks").isEmpty()) {
		} else {
			listQuestionmarksDoProfessor.clear();
			List<Questionmarks> lsq = Conexao.selectWhere("Questionmarks",
					"id_professor = "
							+ pEdit.getId_professor());

			for (Questionmarks qsmrk : lsq){
				listQuestionmarksDoProfessor.add(qsmrk);
			}
			lvDispsDiaSemana.setItems(listQuestionmarksDoProfessor);
		}
	}
}
