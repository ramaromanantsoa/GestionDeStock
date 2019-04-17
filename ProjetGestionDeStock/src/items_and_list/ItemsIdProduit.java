package items_and_list;

import java.sql.ResultSet;

import commande.CommandWindow;
import main_package.Connector;

public class ItemsIdProduit extends  CommandWindow {
	private static final long serialVersionUID = 1L;
	public static String[] createItemsIdProuit() {
		ResultSet uniteRes;
		
		try {
			
			statement = Connector.connect().createStatement();
			
			uniteRes = statement.executeQuery("SELECT ID_Produit FROM Produit");
			while (uniteRes.next()) {
				listItemsIdProduit.add(uniteRes.getString("ID_Produit"));
			}
			
			cmbItemsIdProduit = new String[listItemsIdProduit.size()];
			listItemsIdProduit.toArray(cmbItemsIdProduit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			return cmbItemsIdProduit;
	}
}
