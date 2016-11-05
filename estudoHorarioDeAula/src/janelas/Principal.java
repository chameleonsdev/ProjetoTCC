package janelas;

import java.util.ArrayList;
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

		JanelaDeGerarATabelinhaLa janela = new JanelaDeGerarATabelinhaLa();
	}

}
