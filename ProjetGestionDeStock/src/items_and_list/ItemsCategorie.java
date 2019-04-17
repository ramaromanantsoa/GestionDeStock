package items_and_list;

import java.sql.ResultSet;
import java.sql.Statement;

import main_package.Connector;
import main_package.MainWindow;

public class ItemsCategorie extends  MainWindow {
	private static final long serialVersionUID = 1L;
	public static String[] createItemsCategorie() {
		ResultSet uniteRes;
		
		try {
			
			Statement statement = Connector.connect().createStatement();
			
			uniteRes = statement.executeQuery("SELECT Nom_Categorie FROM Categorie ORDER BY Nom_Categorie ASC");
			while (uniteRes.next()) {
				listItemsCategorie.add(uniteRes.getString("Nom_Categorie"));
			}
			
			cmbItemsCategorie = new String[listItemsCategorie.size()];
			listItemsCategorie.toArray(cmbItemsCategorie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			return cmbItemsCategorie;
	}
}
