package janelas;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Banco.Conexao;
import controles.CelulaBotao;
import controles.CelulaComCampoDeTexto;
import controles.CelulaComCampoDeTextoDouble;
import controles.CelulaComCampoDeTextoDoubleMax5;
import controles.MessageBox;
import entidades.Professor;

public class JanelaEditarProfessores extends Stage {

	Pane painel = new Pane();

	VBox hbGeral;

	Button btnSalvar = new Button("Salvar alterações.");

	Professor professorSelecionado;

	TableView<Professor> tableProfessor;

	TableColumn colunas;
	TableColumn<Professor, String> clNome;
	TableColumn<Professor, String> clNumProfessor;
	TableColumn<Professor, String> clPontuacao;
	TableColumn<Professor, String> clEditCursosMaterias;
	TableColumn<Professor, String> clEditDisponibilidade;

	ObservableList<Professor> professoresEditados;

	EntityManager gerenciador = new Conexao().gerarGerenciador();

	public JanelaEditarProfessores() {

		this.painel = new Pane();

		this.hbGeral = new VBox();

		this.tableProfessor = new TableView<Professor>();
		this.tableProfessor.setEditable(true);

		this.clNome = new TableColumn<Professor, String>();
		this.clNumProfessor = new TableColumn<Professor, String>();
		this.clPontuacao = new TableColumn<Professor, String>();
		TableColumn colunasdatabela[] = { colunas = clNome = new TableColumn<Professor, String>("Nome do Professor"),
				clNumProfessor = new TableColumn<Professor, String>("Numero do Professor"),
						clPontuacao = new TableColumn<Professor, String>("Pontuação"),
							clEditCursosMaterias = new TableColumn<Professor, String>("Cursos e Materias"),
								clEditDisponibilidade = new TableColumn<Professor, String>("Disponibilidades")
		};

		this.professoresEditados = FXCollections.observableArrayList();


		this.clNome.setMinWidth(200);
		this.clNome.setResizable(false);
		this.clNumProfessor.setMinWidth(200);
		this.clNumProfessor.setResizable(false);
		this.clPontuacao.setMinWidth(100);
		this.clPontuacao.setResizable(false);
		this.clEditCursosMaterias.setMinWidth(250);
		this.clEditCursosMaterias.setResizable(false);
		this.clEditDisponibilidade.setMinWidth(200);
		this.clEditDisponibilidade.setResizable(false);
		this.tableProfessor.getColumns().setAll(colunasdatabela);

		this.hbGeral.getChildren().addAll(this.tableProfessor, this.btnSalvar);

		this.painel.getChildren().add(this.hbGeral);

		ObservableList<Professor> listDeProfessores = FXCollections.observableArrayList();

		Query selectProfessores = gerenciador.createQuery("from Professor");
		List<Professor> professoresCadastrados = selectProfessores.getResultList();
		for (Professor p : professoresCadastrados){
			listDeProfessores.add(p);
		}

		this.clNome.setCellValueFactory(new PropertyValueFactory<Professor, String>("nome_professor"));
		this.clNome.setCellFactory(new CelulaComCampoDeTexto());


		this.clNumProfessor.setCellValueFactory(new PropertyValueFactory<Professor, String>("num_professor"));
		this.clNumProfessor.setCellFactory(new CelulaComCampoDeTextoDoubleMax5());

		this.clPontuacao.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Professor,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(
					CellDataFeatures<Professor, String> arg0) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(String.valueOf(arg0.getValue().getPontuacao()));
			}
		});
		this.clPontuacao.setCellFactory(new CelulaComCampoDeTextoDouble());

		this.tableProfessor.getItems().setAll(listDeProfessores);
		this.clEditCursosMaterias.setCellFactory(new CelulaBotao());
		this.clEditCursosMaterias.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Professor,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Professor, String> arg0) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty("Editar");
			}
		});

		this.clEditDisponibilidade.setCellFactory(new CelulaBotao());
		this.clEditDisponibilidade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Professor,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Professor, String> arg0) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty("Editar");
			}
		});


		this.tableProfessor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Professor>() {
			public void changed(ObservableValue<? extends Professor> observable, Professor oldValue, Professor newValue) {
				atualizarCampos(observable);
			}
		});



		this.clNome.setOnEditCommit(value -> {
			professorSelecionado.setNome_professor(value.getNewValue());
			if(!professoresEditados.contains(professorSelecionado)){
				professoresEditados.add(professorSelecionado);
			}else{
				professoresEditados.remove(professorSelecionado);
				professoresEditados.add(professorSelecionado);
			}
		});
		this.clNumProfessor.setOnEditCommit(value -> {
			professorSelecionado.setNum_professor(value.getNewValue());
			if(!professoresEditados.contains(professorSelecionado)){
				professoresEditados.add(professorSelecionado);
			}else{
				professoresEditados.remove(professorSelecionado);
				professoresEditados.add(professorSelecionado);
			}
		});
		this.clPontuacao.setOnEditCommit(value -> {
			professorSelecionado.setPontuacao(Double.parseDouble(value.getNewValue()));
			if(!professoresEditados.contains(professorSelecionado)){
				professoresEditados.add(professorSelecionado);
			}else{
				professoresEditados.remove(professorSelecionado);
				professoresEditados.add(professorSelecionado);
			}
		});
		//this.clEditCursosMaterias.



		btnSalvar.setOnAction(value -> {

			if(this.professoresEditados.size() < 1){

			}else{
				try{
					for(Professor p : professoresEditados){
						Conexao.update(p);
					}
					MessageBox.ShowInfo("Sucesso", "Professores editados com sucesso!");
				} catch (Exception e) {
					MessageBox.ShowInfo("Erro", "Erro ao inserir : " + e.getMessage());
				}
			}
		});

		Scene cena = new Scene(painel);
		this.setTitle("Editar Professores");
		this.setScene(cena);
		this.show();

	}

	private void atualizarCampos(ObservableValue<? extends Professor> p) {

		try {
			this.professorSelecionado = p.getValue();
		} catch (NullPointerException e) {
			this.professorSelecionado = null;

		}
	}

}
