package util;

public class CriaTabela {

	public static void main(String[] args) {
		
		JPAUtil.createEntityManager();
		JPAUtil.closeEntityManager();
		
		System.exit(0);

	}

}
