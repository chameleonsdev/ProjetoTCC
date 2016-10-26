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

@SuppressWarnings("all") //----------------> SAI WARNINGs!!!; <----------------//
public class JanelaCadastroProfessores extends Stage{

	//--------------------------------------------------------------------------------------//

	//----------------> DEFININDO PROPRIEDADES; <----------------//

	private Label lblNomeProfDoubleDot;
	private Label lblPontuacao;
	private Label lblNumProf;

	private TextField txtNomeProfDoubleDot;
	private TextField txtPontuacao;
	private TextField txtNumProf;

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

	//--------------------------------------------------------------------------------------//

	public JanelaCadastroProfessores() {

		//--------------------------------------------------------------------------------------//

		//----------------> INSTANCIANDO AS PARADAS; <----------------//

		this.lblNomeProfDoubleDot = new Label("Professor: ");
		this.lblPontuacao = new Label("Pontuação: ");
		this.lblNumProf = new Label("Num. Professor: ");

		this.txtNomeProfDoubleDot = new TextField();
		this.txtPontuacao = new TextField();
		this.txtPontuacao.setOnKeyTyped(arg0 -> {
			char teste = arg0.getCharacter().charAt(0);
			if(Character.isLetter(teste) && !Character.isDigit(teste))
			{
				arg0.consume();
			}
		});

		this.txtNumProf = new TextField();
		this.txtNumProf.setOnKeyTyped(arg0 -> {
			char teste = arg0.getCharacter().charAt(0);
			if(Character.isLetter(teste) && !Character.isDigit(teste))
			{
				arg0.consume();
			}else {
				if(this.txtNumProf.getLength() > 5) {
					arg0.consume();
				}
			}
		});

		this.cboCursoProfessor = new ComboBox<Curso>();
		this.cboMateriaCurso = new ComboBox<Materia>();

		this.listaDeCursos = FXCollections.observableArrayList();
		this.listaDeMaterias = FXCollections.observableArrayList();

		this.listaDeCursosProfessor = FXCollections.observableArrayList();
		this.listaDeMateriasProfessor = FXCollections.observableArrayList();

		this.btnAddMateriaCurso = new Button("Adicionar Materia/Curso");
		this.btnRmMateriaCurso = new Button("Remover Materia/Curso");
		this.btnCadastrarProfessor = new Button("Cadastrar Professor");

		this.tableviewDeMaterias = new TableView<Materia>();

		this.vBoxProfessor = new VBox(5);

		this.hBoxProfessorTabela = new HBox(5);

		this.painelBonitasso = new AnchorPane();

		this.gerenciador = Conexao.gerarGerenciador();

		this.tree = new TreeView<String> ();

		this.rootItem = new TreeItem<String> ("Cursos");

		//--------------------------------------------------------------------------------------//

		//----------------> PREENCHENDO BOXES E OUTROS; <----------------//

		//----------------> Adiciona o root da treeview; <----------------//
		this.tree.setRoot(this.rootItem);

		//----------------> Preenche o combobox; <----------------//
		this.cboCursoProfessor.setItems(this.listaDeCursos);

		//----------------> Adicionando componentes nas V/Hboxes; <----------------//
		this.vBoxProfessor.getChildren().addAll(this.lblNomeProfDoubleDot, this.txtNomeProfDoubleDot,this.lblPontuacao, this.txtPontuacao, this.lblNumProf, this.txtNumProf, this.cboCursoProfessor, this.cboMateriaCurso,this.btnAddMateriaCurso,this.btnRmMateriaCurso,this.btnCadastrarProfessor);
		this.hBoxProfessorTabela.getChildren().addAll(this.vBoxProfessor,this.tree);

		//----------------> Adicionando a HBox Principal ao painel; <----------------//
		this.painelBonitasso.getChildren().add(this.hBoxProfessorTabela);

		//--------------------------------------------------------------------------------------//

		//----------------> UM POUCO DE DESIGN; <----------------//

		//----------------> Define alinhamento das V/Hboxes; <----------------//
		this.vBoxProfessor.setAlignment(Pos.BASELINE_LEFT);
		this.hBoxProfessorTabela.setAlignment(Pos.BASELINE_CENTER);

		//--------------------------------------------------------------------------------------//

		//----------------> Dando SELECT nos cursos existentes; <----------------//
		List<Curso> c = Conexao.select("Curso");
		for (Curso i : c) {
			//----------------> Carregando lista com os cursos retornados; <----------------//
			this.listaDeCursos.add(i);
		}

		//----------------> Verifica se o combobox do curso foi modificado; <----------------//
		this.cboCursoProfessor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
            	//----------------> Limpa a lista de materias; <----------------//
            	listaDeMaterias.clear();
            	//----------------> Limpa a combobox de materias; <----------------//
            	cboMateriaCurso.getItems().clear();
            	//----------------> Guarda o id do curso selecionado; <----------------//
            	idcursoselecionado = cboCursoProfessor.getSelectionModel().getSelectedItem().getId_curso();

            	//----------------> Da "SELECT" nas materias do curso selecionado; <----------------//
    			List<Materia> Retornos2 = cboCursoProfessor.getSelectionModel().getSelectedItem().getMaterias();
    			for (Materia i : Retornos2) {
    				listaDeMaterias.add(i);
    			}
    			//----------------> Preenche a combobox de materias com as materias retornadas; <----------------//
    			cboMateriaCurso.getItems().addAll(listaDeMaterias);
            }


        });

		//--------------------------------------------------------------------------------------//

		//----------------> DANDO AÇÃO AOS BOTÕES; <----------------//

		//----------------> BOTÃO ADD MATERIA/CURSO; <----------------//
		this.btnAddMateriaCurso.setOnAction(evento -> {

			//----------------> Variaveis auxiliares para ver se existe curso/materia; <----------------//
			boolean existeCurso = false;
			boolean existeMateria = false;

			//----------------> Varre a treeview; <----------------//
			for(int x = 0;x < rootItem.getChildren().size();x++)
			{
				//----------------> Ve se o curso a ser adicionado ja existe; <----------------//
				if(rootItem.getChildren().get(x).getValue() == cboCursoProfessor.getSelectionModel().getSelectedItem().getNome_curso())
				{
					existeCurso = true;
				}
			}
			//----------------> Se não existir adiciona o curso na treeview; <----------------//
			if(existeCurso == false)
			{
				//----------------> Adiciona o curso na lista de cursos do professor; <----------------//
				listaDeCursosProfessor.add(cboCursoProfessor.getSelectionModel().getSelectedItem());
				listaDeMateriasProfessor.add(cboMateriaCurso.getSelectionModel().getSelectedItem());
				//----------------> Cria novo item da treeview com o nome do curso; <----------------//
				itemcurso = new TreeItem<String> (cboCursoProfessor.getSelectionModel().getSelectedItem().getNome_curso());
				//----------------> Adiciona item criado na treeview; <----------------//
				rootItem.getChildren().add(itemcurso);
				//----------------> Cria novo item da treeview com o nome da materia; <----------------//
				item = new TreeItem<String> (cboMateriaCurso.getSelectionModel().getSelectedItem().getNome_materia());
				//----------------> Adiciona item criado na treeview; <----------------//
				itemcurso.getChildren().add(item);
				//----------------> Deixa a treeview expandida; <----------------//
				rootItem.setExpanded(true);
				itemcurso.setExpanded(true);
			}
			//----------------> Se existir o curso; <----------------//
			else
			{
				//----------------> Varre o curso; <----------------//
				for(int x = 0;x < itemcurso.getChildren().size();x++)
				{
					//----------------> Verifica se a materia ja existe; <----------------//
					if(itemcurso.getChildren().get(x).getValue() == cboMateriaCurso.getSelectionModel().getSelectedItem().getNome_materia())
					{
						existeMateria = true;
					}
				}
				//----------------> Se não existir adiciona a materia na treeview; <----------------//
				if(existeMateria == false)
				{
					//----------------> Adiciona materia na lista de materias do professor; <----------------//
					listaDeMateriasProfessor.add(cboMateriaCurso.getSelectionModel().getSelectedItem());
					//----------------> Cria novo item da treeview com nome da materia; <----------------//
					item = new TreeItem<String> (cboMateriaCurso.getSelectionModel().getSelectedItem().getNome_materia());
					//----------------> Adiciona item criado; <----------------//
					itemcurso.getChildren().add(item);
				}
			}
		});

		this.btnRmMateriaCurso.setOnAction(evento -> {

			this.rootItem.getChildren().remove(tree.getSelectionModel().getSelectedItem());
			this.itemcurso.getChildren().remove(tree.getSelectionModel().getSelectedItem());

		});

		//----------------> BOTÃO CADASTRAR PROFESSOR; <----------------//
		this.btnCadastrarProfessor.setOnAction(evento -> {

			if(txtNomeProfDoubleDot.getText().isEmpty()){

			}else if(txtPontuacao.getText().isEmpty()){

			}else if(txtNumProf.getText().isEmpty()){

			}else{
				//----------------> Cria o Gerenciador de entidades; <----------------//

				//----------------> Cria novo objeto professor; <----------------//
				try{
					Professor p = new Professor();
					p.setNome_professor(txtNomeProfDoubleDot.getText());
					p.setPontuacao(Double.parseDouble(txtPontuacao.getText()));
					p.setNum_professor(txtNumProf.getText());
					p.setCursos(listaDeCursosProfessor);
					p.setMaterias(listaDeMateriasProfessor);

					//----------------> Da INSERT na tabela professor; <----------------//
					Conexao.insert(p);
					MessageBox.ShowInfo("Sucesso", "Professor cadastrado com sucesso!");
				} catch (Exception e) {
					MessageBox.ShowError("Erro!", "Erro ao inserir professor : " + e.getMessage());
				}
				limparCampos();
			}
		});

		//--------------------------------------------------------------------------------------//

		//----------------> DEFINIÇÕES DA JANELA; <----------------//

		//----------------> Criando cena contendo o painel; <----------------//
		Scene cena = new Scene(painelBonitasso);
		//----------------> Definindo cena para a janela; <----------------//
		this.setTitle("Cadastro de Professores");
		this.setScene(cena);
		//----------------> Monstra a Janela; <----------------//
		this.show();

		//-------------------------------------------------------//
		//---------------->        FIM;        <----------------//
		//----------------> TA TUDO BEM AGORA; <----------------//
		//-------------------------------------------------------//

	}

	private void limparCampos() {
		txtNomeProfDoubleDot.clear();
		txtNumProf.clear();
		txtPontuacao.clear();
		rootItem.getChildren().clear();
		cboCursoProfessor.getSelectionModel().select(-1);
		cboMateriaCurso.getSelectionModel().select(-1);
	}
}
