package janelas;

import javafx.application.Application;
import javafx.stage.Stage;

public class Principal extends Application{

	//--------------------------------------------------------------------------------------//

	//----------------> Padr�o de comentarios do projeto; <----------------//

	//----------------> Separador V ; <----------------//
	//--------------------------------------------------------------------------------------//

	//----------------> TITULOS DE BLOCOS; <----------------//

	//----------------> A��es ou Avisos; <----------------//

	//----------------> FIM V ; <----------------//
	//-------------------------------------------------------//
	//---------------->        FIM;        <----------------//
	//----------------> TA TUDO BEM AGORA; <----------------//
	//-------------------------------------------------------//

	//--------------------------------------------------------------------------------------//

	public static void main(String[] args) {

		//----------------> Roda a Aplica��o; <----------------//
		launch(args);

	}

	@SuppressWarnings("unused")//----------------> SAI WARNING!!!; <----------------//
	@Override
	public void start(Stage arg0) throws Exception {
		//----------------> Coisa de Testes :v pita EU TOU CERTO; <----------------//
		System.out.println("Eu sou eu - Pau no seu cu!");
		//----------------> MOSTRA A JANELA PRINCIPAL (DEFAULT = JanelaLogin); <----------------//
		JanelaEditarCurso janela = new JanelaEditarCurso();
	}

}
