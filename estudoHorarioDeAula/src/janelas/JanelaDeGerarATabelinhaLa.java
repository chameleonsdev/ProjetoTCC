package janelas;

import java.util.List;

import javax.persistence.EntityManager;

import controles.TabelaHorario;
import entidades.Curso;
import entidades.Horario;
import Banco.Conexao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("rawtypes") //----------------> SAI WARNING!!!; <----------------//
public class JanelaDeGerarATabelinhaLa extends Stage{

	//--------------------------------------------------------------------------------------//

	//----------------> DEFININDO PROPRIEDADES; <----------------//

	private Menu mnAjuda;
	private Menu mnOpcoes;
	private Menu mnArquivo;
	private Menu mnEditar;

	private MenuBar menuBar;

	private VBox root;
	
	private TabPane teste;

	//private ComboBox<Curso> cmbCurso;
	
	private ComboBox<String> cmbModulo;

	private ObservableList<Curso> listaDeCursos;
	
	private ObservableList<String> listaDeModulos;

	//--------------------------------------------------------------------------------------//

	@SuppressWarnings("unchecked")//----------------> SAI WARNING!!!; <----------------//
	public JanelaDeGerarATabelinhaLa() {

		System.out.println("Fala Elite!");

		//----------------> INSTANCIANDO AS PARADAS; <----------------//

		this.mnAjuda = new Menu("Ajuda");
		this.mnOpcoes = new Menu("Opções");
		this.mnArquivo = new Menu("Arquivo");
		this.mnEditar = new Menu("Editar");

		this.menuBar = new MenuBar();

		this.root = new VBox();
		
		this.teste = new TabPane();

		this.cmbModulo = new ComboBox<String>();

		this.listaDeCursos = FXCollections.observableArrayList();
		
		this.listaDeModulos = FXCollections.observableArrayList("Primeiro", "Segundo", "Terceiro");

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

		//-------------------------------------------------------------//


		//----------------> Dando Select e adicionando os cursos existentes na ComboBox; <----------------//
		List<Curso> Retornos = gerenciador.createQuery("Select a From Curso a", Curso.class).getResultList();
		for (Curso i : Retornos) {
			listaDeCursos.add(i);
		}

		cmbModulo.getItems().addAll(listaDeModulos);
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
		
		atribuirhorarios.setOnAction(value -> {
			new JanelaDeQuestionMark();
		});

		this.root.setAlignment(Pos.TOP_CENTER);
		this.cmbModulo.setMaxWidth(Double.MAX_VALUE);

		//-------------------------------------------------------------------------------------------------//

		//----------------> UM POUCO DE DESIGN; <----------------//

		//----------------> Define espaçamento dos elementos no AnchorPane; <----------------//
		
		//----------------> Deixa a barra de menu da largura da janela; <----------------//
		menuBar.prefWidthProperty().bind(this.widthProperty());

		//-------------------------------------------------------------------------------------------------//
		
		cmbModulo.valueProperty().addListener(new ChangeListener<String>() {
			@Override
	        public void changed(ObservableValue ov, String t, String t1) {
				if(root.getChildren().size() == 4){
					root.getChildren().remove(2);
				}
				if(t1.equals("Primeiro")){
					teste.getTabs().clear();
					for (Curso curso : listaDeCursos) {
						if(curso.getModulo_curso() == 1){
							Tab tab = new Tab();
							tab.setText(curso.getNome_curso());
							tab.setClosable(false);
							TabelaHorario tableasas = new TabelaHorario();
							ObservableList<Horario> hList = FXCollections.observableArrayList();
							for (Horario h : curso.getHorarios()) {
								hList.add(h);
							}
							tableasas.setItems(hList);
							//tableasas.
							tab.setContent(tableasas);
							teste.getTabs().add(tab);
						}		
					}
				}else if(t1.equals("Segundo")){
					teste.getTabs().clear();
					for (Curso curso : listaDeCursos) {
						if(curso.getModulo_curso() == 2){
							Tab tab = new Tab();
							tab.setText(curso.getNome_curso());
							tab.setClosable(false);
							TabelaHorario tableasas = new TabelaHorario();
							ObservableList<Horario> hList = FXCollections.observableArrayList();
							for (Horario h : curso.getHorarios()) {
								hList.add(h);
							}
							tableasas.setItems(hList);
							tab.setContent(tableasas);
							teste.getTabs().add(tab);
						}		
					}
				}else if(t1.equals("Terceiro")){
					teste.getTabs().clear();
					for (Curso curso : listaDeCursos) {
						if(curso.getModulo_curso() == 3){
							Tab tab = new Tab();
							tab.setText(curso.getNome_curso());
							tab.setClosable(false);
							TabelaHorario tableasas = new TabelaHorario();
							ObservableList<Horario> hList = FXCollections.observableArrayList();
							for (Horario h : curso.getHorarios()) {
								hList.add(h);
							}
							tableasas.setItems(hList);
							tab.setContent(tableasas);
							teste.getTabs().add(tab);
							//teste.getTabs().get(teste.getTabs().size() - 1).getContent().;
						}		
					}
				}
			}
		});
		
		//-------------------------------------------------------------------------------------------------//
		
		System.out.println(this.getHeight());
		
		//----------------> DEFINIÇÕES DA JANELA; <----------------//
		this.setMinWidth(600);
		this.setMinHeight(650);
		this.setMaxWidth(600);
		this.setMaxHeight(650);
		//----------------> Adicionando tudo no AnchorPane; <----------------//
		root.getChildren().addAll(menuBar, cmbModulo, new Label("Selecione um Modulo!"),teste);
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
