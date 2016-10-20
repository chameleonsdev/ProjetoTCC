package janelas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Banco.Conexao;
import controles.TimePicker;
import entidades.Curso;
import entidades.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlfaAlfaJanelaDeDisponibilidades extends Stage{
	
	private Label lblSelectProfessor;
	private Label lblPontuacao;
	private Label lblQntPont;
	
	private ComboBox<Professor> cboProfessores;
	private ObservableList<Professor> oblstProfessor;
	
	
	private TimePicker tpSeg = new TimePicker();
	private TimePicker tpTer = new TimePicker();
	private TimePicker tpQua = new TimePicker();
	private TimePicker tpQui = new TimePicker();
	private TimePicker tpSex = new TimePicker();
	
	private Button btnAdicionarDisp;
	private Button btnRemoverDisp;
	
	private AnchorPane painelOutrosControles = new AnchorPane();
	private AnchorPane painelTimePickers = new AnchorPane();
	
	
	public AlfaAlfaJanelaDeDisponibilidades() {
		
		this.oblstProfessor = FXCollections.observableArrayList();
		
		EntityManager gerenciador = Conexao.gerarGerenciador();
		
		Query sql = gerenciador.createQuery("from Professor");
		List<Professor> retornos = sql.getResultList();
		for(Professor c : retornos){
			oblstProfessor.add(c);
		}
		
		
		this.lblSelectProfessor = new Label("Selecione o Professor:");
		this.lblPontuacao =  new Label("Pontuação: ");
		this.lblQntPont = new Label("0000");
		
		this.cboProfessores = new ComboBox<Professor>();
		
		this.cboProfessores.setItems(oblstProfessor);
		
		this.btnAdicionarDisp = new Button("Adicionar Disponibilidade");
		this.btnRemoverDisp = new Button("Remover Disponibilidade");
		
		VBox vBoxOutrosControles = new VBox();
		vBoxOutrosControles.getChildren().addAll(this.lblSelectProfessor, this.cboProfessores, this.lblPontuacao, this.lblQntPont, this.btnAdicionarDisp, this.btnRemoverDisp);
		this.painelOutrosControles.getChildren().add(vBoxOutrosControles);
		
		HBox hBoxPrincipal = new HBox();
		hBoxPrincipal.getChildren().addAll(this.painelOutrosControles);
		
		Scene cena = new Scene(hBoxPrincipal);
		this.setScene(cena);
		this.show();
	}
}
