package items_and_list;

import java.sql.ResultSet;

import main_package.Connector;
import main_package.MainWindow;
public class ItemsUnite extends MainWindow{
	private static final long serialVersionUID = 1L;
	public static String[] createItemsUnites() {
		ResultSet uniteRes;
		
		try {

			statement = Connector.connect().createStatement();
			
			uniteRes = statement.executeQuery("SELECT Type_Unite FROM Unite ORDER BY Type_Unite ASC");
			while (uniteRes.next()) {
				listItemsUnite.add(uniteRes.getString("Type_Unite"));
			}
			
			cmbItemsUnite = new String[listItemsUnite.size()];
			listItemsUnite.toArray(cmbItemsUnite);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cmbItemsUnite;
	}
}
