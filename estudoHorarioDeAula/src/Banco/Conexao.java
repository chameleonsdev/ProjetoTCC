package Banco;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {


		private static EntityManagerFactory gerenciadoresBD;
		
		public static EntityManager gerarGerenciador()
		{
		
			if (Conexao.gerenciadoresBD == null)
			{
				Conexao.gerenciadoresBD = Persistence.createEntityManagerFactory("PUtarde");
			}
			return Conexao.gerenciadoresBD.createEntityManager();
			
			
			
		}
		
		
		
		
		
		
	}

	