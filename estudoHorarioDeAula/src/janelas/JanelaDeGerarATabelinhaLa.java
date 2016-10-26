package janelas;

import java.util.List;

import javax.persistence.EntityManager;

import entidades.Curso;
import Banco.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

@SuppressWarnings("rawtypes") //----------------> SAI WARNING!!!; <----------------//
public class JanelaDeGerarATabelinhaLa extends Stage{

	//--------------------------------------------------------------------------------------//

	//----------------> DEFININDO PROPRIEDADES; <----------------//

	private HBox HbButtons;

	private Menu mnAjuda;
	private Menu mnOpcoes;
	private Menu mnArquivo;
	private Menu mnEditar;

	private MenuBar menuBar;

	private AnchorPane root;

	private Label lblCurso;

	private ComboBox<Curso> cmbCurso;

	private Button gerarHorario;

	private TableView<String> tabelasso;

	private TableColumn sextaCol;
	private TableColumn tercaCol;
	private TableColumn quintaCol;
	private TableColumn quartaCol;
	private TableColumn segundaCol;

	private ObservableList<Curso> listaDeCursos;

	//--------------------------------------------------------------------------------------//

	@SuppressWarnings("unchecked")//----------------> SAI WARNING!!!; <----------------//
	public JanelaDeGerarATabelinhaLa() {

		System.out.println("Fala Elite!");

		//----------------> INSTANCIANDO AS PARADAS; <----------------//

		this.HbButtons = new HBox(5);

		this.mnAjuda = new Menu("Ajuda");
		this.mnOpcoes = new Menu("Opções");
		this.mnArquivo = new Menu("Arquivo");
		this.mnEditar = new Menu("Editar");

		this.menuBar = new MenuBar();

		this.root = new AnchorPane();

		this.lblCurso = new Label("Curso: ");

		this.cmbCurso = new ComboBox<Curso>();

		this.gerarHorario = new Button("Gerar Horario");

		this.tabelasso = new TableView<String>();

		this.sextaCol = new TableColumn("Sexta-Feira");
		this.tercaCol = new TableColumn("Terça-Feira");
		this.quintaCol = new TableColumn("Quinta-Feira");
		this.quartaCol = new TableColumn("Quarta-Feira");
		this.segundaCol = new TableColumn("Segunda-Feira");

		this.listaDeCursos = FXCollections.observableArrayList();

		//-------------------------------------------------------------//

		//----------------> Criando SubMenus; <----------------//
		MenuItem sair = new MenuItem("Sair");
		MenuItem novohorario = new MenuItem("Novo Horario");
		MenuItem novocurso = new MenuItem("Cadastrar Curso");
		MenuItem novoprof = new MenuItem("Cadastrar Professor");
		MenuItem atribuirhorarios = new MenuItem("Cadastrar Disponibilidades");

		MenuItem editarCursoItem = new MenuItem("Editar Curso/Materias");
		MenuItem editarProfessor = new MenuItem("Editar Professor");

		//----------------> Adicionando menus na barra; <----------------//
		menuBar.getMenus().addAll(mnArquivo, mnOpcoes, mnEditar, mnAjuda);

		//----------------> Adicionando SubMenus; <----------------//
		mnArquivo.getItems().addAll(novohorario,novoprof,novocurso,atribuirhorarios,sair);
		mnEditar.getItems().addAll(editarCursoItem, editarProfessor);

		//----------------> Criando Gerenciador de Entidades; <----------------//
		EntityManager gerenciador = Conexao.gerarGerenciador();

		//----------------> Adicionando componentes na hbox inferior; <----------------//
		HbButtons.getChildren().addAll(gerarHorario,lblCurso,cmbCurso);

		//----------------> Adicionando as colunas na tabela; <----------------//
				tabelasso.getColumns().addAll(segundaCol,tercaCol,quartaCol,quintaCol,sextaCol);

		//-------------------------------------------------------------//


		//----------------> Dando Select e adicionando os cursos existentes na ComboBox; <----------------//
		List<Curso> Retornos = gerenciador.createQuery("Select a From Curso a", Curso.class).getResultList();
		for (Curso i : Retornos) {
			listaDeCursos.add(i);
		}

		cmbCurso.getItems().addAll(listaDeCursos);
		//-------------------------------------------------------------------------------------------------//

		//-------------------------------------------------------------------------------------------------//

		//----------------> Dando Funções para os Botões e Menus; <----------------//

		//----------------> Menu Sair; <----------------//
		sair.setOnAction(evento -> {
			this.close();
		});

		//----------------> Menu novo curso !Abre a janela de cadastro de cursos! <----------------//
		novocurso.setOnAction(evento -> {
			new JanelaCadastroCursos();
		});

		//----------------> Menu novo professor !Abre a janela de cadastro de professores!; <----------------//
		novoprof.setOnAction(evento -> {
			new JanelaCadastroProfessores();
		});

		atribuirhorarios.setOnAction(evento -> {
			new JanelaDeColocarOsHorarios();
		});

		editarCursoItem.setOnAction(value -> {
			new JanelaEditarCurso();
		});

		editarProfessor.setOnAction(value -> {
			new JanelaEditarProfessores();
		});


		//-------------------------------------------------------------------------------------------------//

		//----------------> UM POUCO DE DESIGN; <----------------//

		//----------------> Define espaçamento dos elementos no AnchorPane; <----------------//
		AnchorPane.setTopAnchor(tabelasso, 26d);
		AnchorPane.setBottomAnchor(tabelasso, 26d);
		AnchorPane.setTopAnchor(HbButtons, 600d);
		AnchorPane.setBottomAnchor(HbButtons, 0d);
		//----------------> Deixa a barra de menu da largura da janela; <----------------//
		menuBar.prefWidthProperty().bind(this.widthProperty());

		//-------------------------------------------------------------------------------------------------//

		//-------------------------------------------------------------------------------------------------//

		//----------------> DEFINIÇÕES DA JANELA; <----------------//

		//----------------> Adicionando tudo no AnchorPane; <----------------//
		root.getChildren().addAll(menuBar,tabelasso,HbButtons);
		//----------------> Criando cena contendo o AnchorPane; <----------------//
		Scene scene = new Scene(root);
		//----------------> Adicionando cena na janela; <----------------//
		this.setScene(scene);
		//----------------> Mostrando a Janela; <----------------//
		this.show();

		//-------------------------------------------------------//
		//---------------->        FIM;        <----------------//
		//----------------> TA TUDO BEM AGORA; <----------------//
		//-------------------------------------------------------//
	}

}
