package janelas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Banco.Conexao;
import controles.CelulaCBox;
import entidades.Curso;
import entidades.Horario;
import entidades.Materia;
import entidades.Professor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JanelaDeColocarOsHorarios extends Stage {
	
	private Label lblPontuacaoDoCaralinhoLa;
	private Label lblNomeMaterias;
	private Label lblNomeCursos;
	private Label lblNomeProfs;
	
	private ComboBox<Professor> cboProfessor;
	private ComboBox<Materia> cboMaterias;
	private ComboBox<Curso> cboCursos;

	private ObservableList<Professor> listProfessores;
	private ObservableList<Materia> listMaterias;
	private ObservableList<Curso> listCusros;
	
	

	private TableView <Horario> tabelassaTeste = new TableView<Horario>();

	private AnchorPane painelHoOh = new AnchorPane();
	private AnchorPane painelEntei = new AnchorPane();
	private AnchorPane painelSuicune = new AnchorPane();
	private StackPane painelRaikou = new StackPane();
	
	private TableColumn horariosAulas;
	private TableColumn segundaFeira;
	private TableColumn tercaFeira;
	private TableColumn quartaFeira;
	private TableColumn quintaFeira;
	private TableColumn sextaFeira;

	private ObservableList<Horario> listHorarios;

	public JanelaDeColocarOsHorarios() {

		this.lblNomeProfs = new Label("Nome do Professor: ");
		this.lblPontuacaoDoCaralinhoLa = new Label("8001");
		this.lblNomeCursos = new Label("Nome do Curso: ");
		this.lblNomeMaterias = new Label("Nome da Materia: ");

		this.cboProfessor = new ComboBox<>();
		this.cboCursos = new ComboBox<>();
		this.cboMaterias = new ComboBox<>();

		this.listCusros = FXCollections.observableArrayList();
		this.listHorarios = FXCollections.observableArrayList();
		
		EntityManager gerenciador = Conexao.gerarGerenciador();
		
		Query sql = gerenciador.createQuery("from Curso");
		List<Curso> retornos = sql.getResultList();
		for(Curso c : retornos){
			listCusros.add(c);
		}
		
		cboProfessor.setItems(listProfessores);
		cboCursos.setItems(listCusros);
		cboMaterias.setItems(listMaterias);

		//Bloqueando Movimentacao de colunas START;
		tabelassaTeste.setEditable(true);
		TableColumn colunas[] = { horariosAulas = new TableColumn("---"),segundaFeira = new TableColumn<>("Segunda-Feira"),
				tercaFeira = new TableColumn<>("Terça-Feira"), quartaFeira = new TableColumn<>("Quarta-Feira"),
				quintaFeira = new TableColumn<>("Quinta-Feira"), sextaFeira = new TableColumn<>("Sexta-Feira") };

		tabelassaTeste.getColumns().setAll(colunas);
		tabelassaTeste.getColumns().addListener(new ListChangeListener() {
			public boolean suspended;

			@Override
			public void onChanged(Change change) {
				change.next();
				if (change.wasReplaced() && !suspended) {
					this.suspended = true;
					tabelassaTeste.getColumns().setAll(colunas);
					this.suspended = false;
				}
			}
		});
		//END.
		
		//Fazendo Merda Nas Rows.
		segundaFeira.setCellFactory(new PropertyValueFactory("Teste"));
		//END.
		
		
		
		//Setando Props das colunas.
		horariosAulas.setMinWidth(35);
		horariosAulas.setMaxWidth(35);
		//horariosAulas.setEditable(false);
		horariosAulas.setResizable(false);
		horariosAulas.setSortable(false);
		horariosAulas.setCellValueFactory(new PropertyValueFactory<>("hora_disp"));
		
		
		segundaFeira.setMinWidth(100d);
		//segundaFeira.setEditable(false);
		segundaFeira.setResizable(false);
		segundaFeira.setSortable(false);
		segundaFeira.setCellFactory(new CelulaCBox());
		
		tercaFeira.setMinWidth(100d);
		tercaFeira.setEditable(false);
		tercaFeira.setResizable(false);
		tercaFeira.setSortable(false);
		tercaFeira.setCellFactory(new CelulaCBox());

		quartaFeira.setMinWidth(100d);
		quartaFeira.setEditable(false);
		quartaFeira.setResizable(false);
		quartaFeira.setSortable(false);
		quartaFeira.setCellFactory(new CelulaCBox());
		
		quintaFeira.setMinWidth(100d);
		quintaFeira.setEditable(false);
		quintaFeira.setResizable(false);
		quintaFeira.setSortable(false);
		quintaFeira.setCellFactory(new CelulaCBox());
		
		
		sextaFeira.setMinWidth(100d);
		sextaFeira.setEditable(false);
		sextaFeira.setResizable(false);
		sextaFeira.setSortable(false);
		sextaFeira.setCellFactory(new CelulaCBox());
		
		//End Setando props das colunas.
		
		
		tabelassaTeste.setMaxWidth(1000d);
		
		this.cboCursos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> ov, Object t, Object t1) {
            	for(Horario h : cboCursos.getSelectionModel().getSelectedItem().getHorarios()){
            		
            		listHorarios.add(h);
            		
            	}
            }
            
            
        });
		
		tabelassaTeste.setItems(listHorarios);
		final VBox vBoxTabela = new VBox();
		vBoxTabela.getChildren().add(this.tabelassaTeste);
		
		HBox hBoxProfessoresPontuacao = new HBox(10);

		HBox hBoxCboProfsTabela = new HBox(200);

		HBox hBoxCaralho = new HBox();
			
		hBoxProfessoresPontuacao.getChildren().addAll(this.lblNomeProfs, this.lblPontuacaoDoCaralinhoLa);
		
		hBoxCboProfsTabela.getChildren().addAll(this.cboProfessor);

		
		
		VBox vBox = new VBox(5);
		vBox.getChildren().addAll(hBoxProfessoresPontuacao, hBoxCboProfsTabela, this.lblNomeCursos, this.cboCursos,
				this.lblNomeMaterias, this.cboMaterias);
		vBox.setAlignment(Pos.TOP_LEFT);

		painelEntei.getChildren().add(vBox);

		painelSuicune.getChildren().add(vBoxTabela);

		painelRaikou.getChildren().addAll(painelSuicune);

		hBoxCaralho.getChildren().addAll(painelEntei, painelRaikou);

		painelHoOh.getChildren().addAll(hBoxCaralho);
		

		Scene cena = new Scene(painelHoOh);

		this.setScene(cena);
		this.show();

	}

}
