package janelas;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;

import javax.swing.text.StyledEditorKit.BoldAction;

import Banco.Conexao;
import controles.MessageBox;
import entidades.Curso;
import entidades.Materia;
import entidades.Professor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("all") // ----------------> SAI WARNINGs!!!;
							// <----------------//
public class JanelaEditarMateriasCursos extends Stage {

	// --------------------------------------------------------------------------------------//

	// ----------------> DEFININDO PROPRIEDADES; <----------------//

	private Label lblNomeProfDoubleDot;
	private Label lblCurso;
	private Label lblMateria;

	private TextField txtNomeProfDoubleDot;

	private ComboBox<Curso> cboCursoProfessor;
	private ComboBox<Materia> cboMateriaCurso;

	public ObservableList<Curso> listaDeCursos;
	public ObservableList<Materia> listaDeMaterias;
	public ObservableList<Curso> listaDeCursosProfessor;
	public ObservableList<Materia> listaDeMateriasProfessor;

	public Button btnAddMateriaCurso;
	public Button btnRmMateriaCurso;
	public Button btnCadastrarProfessor;

	private TableView<Materia> tableviewDeMaterias;

	private VBox vBoxProfessor;

	private HBox hBoxProfessorTabela;

	private long idcursoselecionado;

	private AnchorPane painelBonitasso;

	private EntityManager gerenciador;

	private TreeView<String> tree;

	private TreeItem<String> item;
	private TreeItem<String> itemcurso;
	private TreeItem<String> rootItem;

	// --------------------------------------------------------------------------------------//

	public JanelaEditarMateriasCursos(Professor p) {

		// --------------------------------------------------------------------------------------//

		// ----------------> INSTANCIANDO AS PARADAS; <----------------//

		this.lblNomeProfDoubleDot = new Label("Professor: ");
		this.lblCurso = new Label("Curso:");
		this.lblMateria = new Label("Materia:");

		this.txtNomeProfDoubleDot = new TextField();

		this.cboCursoProfessor = new ComboBox<Curso>();
		this.cboMateriaCurso = new ComboBox<Materia>();

		this.listaDeCursos = FXCollections.observableArrayList();
		this.listaDeMaterias = FXCollections.observableArrayList();

		this.listaDeCursosProfessor = FXCollections.observableArrayList();
		this.listaDeMateriasProfessor = FXCollections.observableArrayList();

		this.btnAddMateriaCurso = new Button("Adicionar Materia/Curso");
		this.btnRmMateriaCurso = new Button("Remover Materia/Curso");
		this.btnCadastrarProfessor = new Button("Salvar Alterações");

		this.tableviewDeMaterias = new TableView<Materia>();

		this.vBoxProfessor = new VBox(5);

		this.hBoxProfessorTabela = new HBox(5);

		this.painelBonitasso = new AnchorPane();

		this.gerenciador = Conexao.gerarGerenciador();

		this.tree = new TreeView<String>();

		this.rootItem = new TreeItem<String>("Cursos");

		// --------------------------------------------------------------------------------------//

		// ----------------> PREENCHENDO BOXES E OUTROS; <----------------//

		// ----------------> Adiciona o root da treeview; <----------------//
		this.tree.setRoot(this.rootItem);

		// ----------------> Preenche o combobox; <----------------//
		this.cboCursoProfessor.setItems(this.listaDeCursos);

		// ----------------> Adicionando componentes nas V/Hboxes;
		// <----------------//
		this.vBoxProfessor.getChildren().addAll(this.lblNomeProfDoubleDot, this.txtNomeProfDoubleDot, this.lblCurso,
				this.cboCursoProfessor,this.lblMateria , this.cboMateriaCurso, this.btnAddMateriaCurso, this.btnRmMateriaCurso,
				this.btnCadastrarProfessor);
		this.hBoxProfessorTabela.getChildren().addAll(this.vBoxProfessor, this.tree);

		this.txtNomeProfDoubleDot.textProperty().bind(p.Nome_professor());
		this.txtNomeProfDoubleDot.setEditable(false);

		// ----------------> Adicionando a HBox Principal ao painel;
		// <----------------//
		this.painelBonitasso.getChildren().add(this.hBoxProfessorTabela);

		// --------------------------------------------------------------------------------------//

		// ----------------> UM POUCO DE DESIGN; <----------------//

		// ----------------> Define alinhamento das V/Hboxes;
		// <----------------//
		this.vBoxProfessor.setAlignment(Pos.CENTER);
		this.hBoxProfessorTabela.setAlignment(Pos.CENTER);

		// --------------------------------------------------------------------------------------//

		// ----------------> Dando SELECT nos cursos existentes;
		// <----------------//
		List<Curso> c = Conexao.select("Curso");
		for (Curso i : c) {
			// ----------------> Carregando lista com os cursos retornados;
			// <----------------//
			this.listaDeCursos.add(i);
		}

		// ----------------> Verifica se o combobox do curso foi modificado;
		// <----------------//
		this.cboCursoProfessor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue ov, Object t, Object t1) {
				// ----------------> Limpa a lista de materias;
				// <----------------//
				listaDeMaterias.clear();
				// ----------------> Limpa a combobox de materias;
				// <----------------//
				cboMateriaCurso.getItems().clear();
				// ----------------> Guarda o id do curso selecionado;
				// <----------------//
				idcursoselecionado = cboCursoProfessor.getSelectionModel().getSelectedItem().getId_curso();

				// ----------------> Da "SELECT" nas materias do curso
				// selecionado; <----------------//
				List<Materia> Retornos2 = cboCursoProfessor.getSelectionModel().getSelectedItem().getMaterias();
				for (Materia i : Retornos2) {
					listaDeMaterias.add(i);
				}
				// ----------------> Preenche a combobox de materias com as
				// materias retornadas; <----------------//
				cboMateriaCurso.getItems().addAll(listaDeMaterias);
			}

		});

		// --------------------------------------------------------------------------------------//

		// ----------------> DANDO AÇÃO AOS BOTÕES; <----------------//

		// ----------------> BOTÃO ADD MATERIA/CURSO; <----------------//
		this.btnAddMateriaCurso.setOnAction(evento -> {

			// ----------------> Variaveis auxiliares para ver se existe
			// curso/materia; <----------------//
			boolean existeCurso = false;
			boolean existeMateria = false;

			// ----------------> Varre a treeview; <----------------//
			for (int x = 0; x < rootItem.getChildren().size(); x++) {
				// ----------------> Ve se o curso a ser adicionado ja existe;
				// <----------------//`
				System.out.println(rootItem.getChildren().get(x).getValue() + " = " + cboCursoProfessor.getSelectionModel().getSelectedItem()
						.getNome_curso());
				if (rootItem.getChildren().get(x).getValue().equals(cboCursoProfessor.getSelectionModel().getSelectedItem().getNome_curso())) {
					existeCurso = true;
				}
			}
			// ----------------> Se não existir adiciona o curso na treeview;
			// <----------------//
			if (existeCurso == false) {
				// ----------------> Adiciona o curso na lista de cursos do
				// professor; <----------------//
				listaDeCursosProfessor.add(cboCursoProfessor.getSelectionModel().getSelectedItem());
				listaDeMateriasProfessor.add(cboMateriaCurso.getSelectionModel().getSelectedItem());
				// ----------------> Cria novo item da treeview com o nome do
				// curso; <----------------//
				itemcurso = new TreeItem<String>(
						cboCursoProfessor.getSelectionModel().getSelectedItem().getNome_curso());
				// ----------------> Adiciona item criado na treeview;
				// <----------------//
				rootItem.getChildren().add(itemcurso);
				// ----------------> Cria novo item da treeview com o nome da
				// materia; <----------------//
				item = new TreeItem<String>(cboMateriaCurso.getSelectionModel().getSelectedItem().getNome_materia());
				// ----------------> Adiciona item criado na treeview;
				// <----------------//
				itemcurso.getChildren().add(item);
				// ----------------> Deixa a treeview expandida;
				// <----------------//
				rootItem.setExpanded(true);
				itemcurso.setExpanded(true);
			}
			// ----------------> Se existir o curso; <----------------//
			else {
				// ----------------> Varre o curso; <----------------//
				for (int x = 0; x < itemcurso.getChildren().size(); x++) {
					// ----------------> Verifica se a materia ja existe;
					// <----------------//
					if (itemcurso.getChildren().get(x).getValue().equals(cboMateriaCurso.getSelectionModel()
							.getSelectedItem().getNome_materia())) {
						existeMateria = true;
					}
				}
				// ----------------> Se não existir adiciona a materia na
				// treeview; <----------------//
				if (existeMateria == false) {
					// ----------------> Adiciona materia na lista de materias
					// do professor; <----------------//
					listaDeMateriasProfessor.add(cboMateriaCurso.getSelectionModel().getSelectedItem());
					// ----------------> Cria novo item da treeview com nome da
					// materia; <----------------//
					item = new TreeItem<String>(
							cboMateriaCurso.getSelectionModel().getSelectedItem().getNome_materia());
					// ----------------> Adiciona item criado;
					// <----------------//
					itemcurso.getChildren().add(item);
				}
			}
		});

		for (Curso curs : p.getCursos()) {

			boolean existeCurso = false;
			boolean existeMateria = false;
			int curso = 0;
			int contcurso = 0;

			for (int x = 0; x < rootItem.getChildren().size(); x++) {
				if (rootItem.getChildren().get(x).getValue() == curs.getNome_curso()) {
					existeCurso = true;
				}

				// ----------------> Ve se o curso a ser adicionado ja existe;
				// <----------------//
			}
			// ----------------> Se não existir adiciona o curso na treeview;
			// <----------------//
			if (existeCurso == false) {
				// ----------------> Adiciona o curso na lista de cursos do
				// professor; <----------------//
				listaDeCursosProfessor.add(curs);
				// ----------------> Cria novo item da treeview com o nome do
				// curso; <----------------//
				itemcurso = new TreeItem<String>(curs.getNome_curso());
				// ----------------> Adiciona item criado na treeview;
				// <----------------//
				rootItem.getChildren().add(itemcurso);
				// ----------------> Cria novo item da treeview com o nome da
				// materia; <----------------//
				// ----------------> Adiciona item criado na treeview;
				// <----------------//
				// ----------------> Deixa a treeview expandida;
				// <----------------//
				rootItem.setExpanded(true);
				itemcurso.setExpanded(true);
			}

		}

		// ----------------> Variaveis auxiliares para ver se existe
		// curso/materia; <----------------//

		for (int cont = 0; cont < rootItem.getChildren().size(); cont++) {

			boolean existeMateria = false;
			int curso = 0;
			int contcurso = 0;

				System.out.println(rootItem.getChildren().size());
				System.out.println(p.getMaterias().size());
				for (Materia m : p.getMaterias()) {
					for (Curso cur : m.getCursos()) {
					if (rootItem.getChildren().get(cont).getValue().equals(cur.getNome_curso())) {

							listaDeMateriasProfessor.add(m);
							item = new TreeItem<String>(m.getNome_materia());
							rootItem.getChildren().get(cont).getChildren().add(item);
						}
					}
				}
		}

		this.btnRmMateriaCurso.setOnAction(evento -> {
			for(int i = 0; i < rootItem.getChildren().size(); i++){
				if(rootItem.getChildren().contains(tree.getSelectionModel().getSelectedItem())) {
					rootItem.getChildren().remove(tree.getSelectionModel().getSelectedItem());
				}else{
					for(int x = 0; x < rootItem.getChildren().get(i).getChildren().size();x++){
						if(rootItem.getChildren().get(i).getChildren().contains(tree.getSelectionModel().getSelectedItem()) && rootItem.getChildren().get(i).getChildren().get(x).getValue() == tree.getSelectionModel().getSelectedItem().getValue()){
							rootItem.getChildren().get(i).getChildren().remove(tree.getSelectionModel().getSelectedItem());
						}
					}
				}
			}
		});

		this.btnCadastrarProfessor.setOnAction(evento -> {

			try{
				p.setCursos(listaDeCursosProfessor);
				p.setMaterias(listaDeMateriasProfessor);

				Conexao.update(p);

				MessageBox.ShowInfo("Sucesso", "Cursos e Materias do professor editado com sucesso!");
				this.close();
			}catch (Exception e) {
				MessageBox.ShowError("Erro!", "Erro ao editar cursos e materias do professor : " + e.getMessage());
			}
		});

		// --------------------------------------------------------------------------------------//

		// ----------------> DEFINIÇÕES DA JANELA; <----------------//

		// ----------------> Criando cena contendo o painel; <----------------//
		Scene cena = new Scene(painelBonitasso);
		// ----------------> Definindo cena para a janela; <----------------//
		this.setTitle("Editar Cursos e Materias de "+p.getNome_professor());
		this.setScene(cena);
		// ----------------> Monstra a Janela; <----------------//
		this.show();

		// -------------------------------------------------------//
		// ----------------> FIM; <----------------//
		// ----------------> TA TUDO BEM AGORA; <----------------//
		// -------------------------------------------------------//

	}
}
