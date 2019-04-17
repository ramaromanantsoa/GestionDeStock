package items_and_list;

import java.sql.ResultSet;

import commande.CommandWindow;
import main_package.Connector;

public class ItemsIdClient extends  CommandWindow {
	private static final long serialVersionUID = 1L;
	public static String[] createItemsIdClient() {
		ResultSet uniteRes;
		
		try {
			
			statement = Connector.connect().createStatement();
			
			uniteRes = statement.executeQuery("SELECT ID_Client FROM Client");
			while (uniteRes.next()) {
				listItemsIdClient.add(uniteRes.getString("ID_Client"));
			}
			
			cmbItemsIdClient = new String[listItemsIdClient.size()];
			listItemsIdClient.toArray(cmbItemsIdClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			return cmbItemsIdClient;
	}
}
