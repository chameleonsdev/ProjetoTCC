package janelas;

import java.util.List;

import entidades.Curso;
import entidades.Materia;
import entidades.Professor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Principal extends Application{

	//--------------------------------------------------------------------------------------//

	//----------------> Padrão de comentarios do projeto; <----------------//

	//----------------> Separador V ; <----------------//
	//--------------------------------------------------------------------------------------//

	//----------------> TITULOS DE BLOCOS; <----------------//

	//----------------> Ações ou Avisos; <----------------//

	//----------------> FIM V ; <----------------//
	//-------------------------------------------------------//
	//---------------->        FIM;        <----------------//
	//----------------> TA TUDO BEM AGORA; <----------------//
	//-------------------------------------------------------//

	//--------------------------------------------------------------------------------------//

	public static void main(String[] args) {

		//----------------> Roda a Aplicação; <----------------//
		launch(args);

	}

	@SuppressWarnings("unused")//----------------> SAI WARNING!!!; <----------------//
	@Override
	public void start(Stage arg0) throws Exception {
		//----------------> Coisa de Testes :v pita EU TOU CERTO; <----------------//
		System.out.println("Eu sou eu - Pau no seu cu!");
		//----------------> MOSTRA A JANELA PRINCIPAL (DEFAULT = JanelaLogin); <----------------//
		Professor p = new Professor();
		p.setNome_professor("Sergio");
		Curso c = new Curso();
		c.setNome_curso("curso1");
		Curso c2 = new Curso();
		c2.setNome_curso("curso2");
		ObservableList<Curso> listc = FXCollections.observableArrayList(c);
		ObservableList<Curso> listc2 = FXCollections.observableArrayList(c, c2);
		Materia m = new Materia();
		m.setNome_materia("npc");
		m.setCursos(listc);
		Materia m2 = new Materia();
		m2.setNome_materia("adc");
		m2.setCursos(listc);
		Materia m3 = new Materia();
		m3.setNome_materia("sup");
		m3.setCursos(listc2);
		ObservableList<Materia> listm = FXCollections.observableArrayList(m, m2, m3);
		p.setCursos(listc2);
		p.setMaterias(listm);
		JanelaEditarMateriasCursos janela = new JanelaEditarMateriasCursos(p);
	}

}
