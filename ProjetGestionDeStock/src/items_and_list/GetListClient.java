package items_and_list;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import client.ClientWindow;
import main_package.Connector;;

public class GetListClient extends ClientWindow{
	private static final long serialVersionUID = 7590066300716725430L;
	private static Statement statement;
	public static void getListOfClient() {
		listOfClient = new	DefaultTableModel();
		
		ResultSet result = null;
		try {
			
			statement = Connector.connect().createStatement();
			String requete = "SELECT * FROM Client ORDER BY Nom_Client ASC";
			result = statement.executeQuery(requete);
			
			listOfClient.addColumn("ID");
			listOfClient.addColumn("Nom");
			listOfClient.addColumn("Prénom");
			listOfClient.addColumn("Adresse");
			listOfClient.addColumn("Téléphone");
			listOfClient.addColumn("Email");
			
			while(result.next()) {
				listOfClient.addRow(new Object[] {
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getString(5),
					result.getString(6)
				});
			}
			tabInfoClient.setModel(listOfClient);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
}
