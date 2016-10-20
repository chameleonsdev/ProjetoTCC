package janelas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JanelaLogin extends Stage{
	
	//--------------------------------------------------------------------------------------//
	
	//----------------> DEFININDO PROPRIEDADES; <----------------//
	
	private Pane painel;
	
	private VBox vbGeral;
	
	private HBox hbLogin;
	private HBox hbSenha;
	
	private Label lblLogin;
	private Label lblSenha;
	
	private Button btnEntrar;
	
	private TextField txtLogin;
	private PasswordField txtSenha;
	
	private String stringLogin = "admin";
	private String stringSenha = "admin";
	
	private JanelaDeGerarATabelinhaLa janela;
	
	//--------------------------------------------------------------------------------------//
	
	public JanelaLogin() {
		
		//--------------------------------------------------------------------------------------//
		
		//----------------> INSTANCIANDO AS PARADAS; <----------------//
		
		this.painel = new Pane();
		
		this.vbGeral = new VBox(5);
		this.hbLogin = new HBox(2);
		this.hbSenha = new HBox(2);
		
		this.txtLogin = new TextField();
		this.txtSenha = new PasswordField();
		
		this.lblLogin = new Label("Login: ");
		this.lblSenha = new Label("Senha: ");
		
		this.btnEntrar = new Button("Logar");
		
		//--------------------------------------------------------------------------------------//
		
		//----------------> UM POUCO DE DESIGN; <----------------//
		
		//----------------> Centraliza as H/VBoxes; <----------------//
		this.hbLogin.setAlignment(Pos.CENTER);
		this.hbSenha.setAlignment(Pos.CENTER);
		this.vbGeral.setAlignment(Pos.CENTER);
		
		//--------------------------------------------------------------------------------------//
		
		//----------------> Adicionando Label e TextField nas hboxes; <----------------//
		this.hbSenha.getChildren().addAll(this.lblSenha, this.txtSenha);
		this.hbLogin.getChildren().addAll(this.lblLogin, this.txtLogin);
		//----------------> Adicionando tudo na VBox Principal; <----------------//
		this.vbGeral.getChildren().addAll(this.hbLogin, this.hbSenha, this.btnEntrar);
		//----------------> Adicionando a Vbox Principal no painel; <----------------//
		this.painel.getChildren().add(vbGeral);
		
		//--------------------------------------------------------------------------------------//
		
		//----------------> DANDO AÇÃO PARA O BOTÃO; <----------------//
		
		this.btnEntrar.setOnAction(eveto -> {
			//----------------> Verifica login e senha; <----------------//
			if(this.txtLogin.getText().equals(stringLogin) && this.txtSenha.getText().equals(stringSenha)){
				//----------------> Verifica se janela existe; <----------------//
				if(janela == null){
					System.out.println("Ta tudo bem agora..");
					//----------------> Instancia janela; <----------------//
					janela = new JanelaDeGerarATabelinhaLa();
					//----------------> Fecha janela login; <----------------//
					this.close();
				}
				//----------------> Mostra Janela; <----------------//
				janela.show();
			}else{
				//----------------> Errou a senha?; <----------------//
				System.out.println("Errooooooooooooooou!");
			}
		});
		
		//--------------------------------------------------------------------------------------//
		
		//----------------> DEFINIÇÕES DA JANELA; <----------------//
		
		//----------------> Criando cena contendo o painel; <----------------//
		Scene cena = new Scene(painel);
		//----------------> Definindo titulo da janela; <----------------//
		this.setTitle("Login");
		//----------------> Definindo cena para a janela; <----------------//
		this.setScene(cena);
		//----------------> Monstra a Janela; <----------------//
		this.show();
		
		//-------------------------------------------------------//
		//---------------->        FIM;        <----------------//
		//----------------> TA TUDO BEM AGORA; <----------------//
		//-------------------------------------------------------//
		
	}
}
