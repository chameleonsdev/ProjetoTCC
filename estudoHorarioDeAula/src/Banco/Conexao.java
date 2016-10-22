package Banco;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

		public static List select(String table) {
			EntityManager gerenciador = Conexao.gerarGerenciador();
			Query query = gerenciador.createQuery("from " + table);
			return query.getResultList();
		}

		public static List selectWhere(String table, String condition) {
			EntityManager gerenciador = Conexao.gerarGerenciador();
			Query query = gerenciador.createQuery("from " + table + " where " + condition);
			return query.getResultList();
		}

		public static List selectWhereAnd(String table, String condition, String condition2) {
			EntityManager gerenciador = Conexao.gerarGerenciador();
			Query query = gerenciador.createQuery("from " + table + " where " + condition + " and " + condition2);
			return query.getResultList();
		}

		public static List selectLike(String table, String condition) {
			EntityManager gerenciador = Conexao.gerarGerenciador();
			Query query = gerenciador.createQuery("from " + table + " like " + condition);
			return query.getResultList();
		}

		public static List selectQuery(String query) {
			EntityManager gerenciador = Conexao.gerarGerenciador();
			Query sql = gerenciador.createQuery(query);
			return sql.getResultList();
		}

		public void update(Object objeto) {

			EntityManager gerenciador = Conexao.gerarGerenciador();
			gerenciador.getTransaction().begin();
			gerenciador.merge(objeto);
			gerenciador.getTransaction().commit();
			gerenciador.close();

		}

		public void delete(Object objeto) {

			EntityManager gerenciador = Conexao.gerarGerenciador();
			gerenciador.getTransaction().begin();
			gerenciador.remove(objeto);
			gerenciador.getTransaction().commit();
			gerenciador.close();

		}

		public static void insert(Object objeto){

			EntityManager gerenciador = Conexao.gerarGerenciador();
			gerenciador.getTransaction().begin();
			gerenciador.persist(objeto);
			gerenciador.getTransaction().commit();
			gerenciador.close();

		}



	}

