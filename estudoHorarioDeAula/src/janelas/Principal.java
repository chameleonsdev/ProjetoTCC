package janelas;

import java.util.List;

import Banco.Conexao;
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
		System.out.println("Nosa Jureg!");
		//----------------> MOSTRA A JANELA PRINCIPAL (DEFAULT = JanelaLogin); <----------------//

		//String[] cursos = ["",""];

		if(Conexao.select("Curso").size() > 0){
			//--------------------------------------------//
			Curso medio1 = new Curso();
			ObservableList<Materia> materiasmedio1 = FXCollections.observableArrayList();
			Materia portugues = new Materia();
			portugues.setNome_materia("Português");
			portugues.setCarga_materia(160);
			portugues.setDivisivel_materia(false);
			Materia matematica = new Materia();
			matematica.setNome_materia("Matematica");
			matematica.setCarga_materia(160);
			matematica.setDivisivel_materia(false);
			Materia artes = new Materia();
			artes.setNome_materia("Artes");
			artes.setCarga_materia(120);
			artes.setDivisivel_materia(false);
			Materia edfisica = new Materia();
			edfisica.setNome_materia("Educação Fisica");
			edfisica.setCarga_materia(80);
			edfisica.setDivisivel_materia(false);
			Materia historia = new Materia();
			historia.setNome_materia("História");
			historia.setCarga_materia(80);
			historia.setDivisivel_materia(false);
			Materia geografia = new Materia();
			geografia.setNome_materia("Geografia");
			geografia.setCarga_materia(80);
			geografia.setDivisivel_materia(false);
			Materia filosofia = new Materia();
			filosofia.setNome_materia("Filosofia");
			filosofia.setCarga_materia(40);
			filosofia.setDivisivel_materia(false);
			Materia sociologia = new Materia();
			sociologia.setNome_materia("Sociologia");
			sociologia.setCarga_materia(40);
			sociologia.setDivisivel_materia(false);
			Materia fisica = new Materia();
			fisica.setNome_materia("Física");
			fisica.setCarga_materia(80);
			fisica.setDivisivel_materia(false);
			Materia quimica = new Materia();
			quimica.setNome_materia("Química");
			quimica.setCarga_materia(80);
			quimica.setDivisivel_materia(false);
			Materia biologia = new Materia();
			biologia.setNome_materia("Biologia");
			biologia.setCarga_materia(80);
			biologia.setDivisivel_materia(false);

			medio1.setNome_curso("Medio I");
			medio1.setTipo_curso("Medio");
			materiasmedio1.addAll(portugues, matematica);
			medio1.setMaterias(materiasmedio1);
			//-------------------------------------------//
		}

		JanelaDeGerarATabelinhaLa janela = new JanelaDeGerarATabelinhaLa();
	}

}
