package items_and_list;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import details_commande.DetailComWindow;
import main_package.Connector;

public class GetDetailsCommand extends  DetailComWindow {
	private static final long serialVersionUID = 1L;
	public static void getListOfCommand() {
		listOfCommande = new	DefaultTableModel();
		
		ResultSet result = null;
		try {

			statement = Connector.connect().createStatement();
			String requete = "SELECT c.ID_Commande, p.Nom_Produit, c.Quantite_Commande, k.Nom_Client, c.Date_Commande "
					+ "FROM Client k INNER JOIN (Commande c INNER JOIN Produit p ON p.ID_Produit = c.ID_Produit) "
					+ "ON k.ID_Client = c.ID_Client ORDER BY c.ID_Commande ASC";
			result = statement.executeQuery(requete);

			listOfCommande.addColumn("ID");
			listOfCommande.addColumn("Nom du produit");
			listOfCommande.addColumn("Quantité commandé");
			listOfCommande.addColumn("Nom du client");
			listOfCommande.addColumn("Date de commande");
			
			while(result.next()) {
				listOfCommande.addRow(new Object[] {
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(4),
					result.getString(5)
				});
			}
			tabDetailCommande.setModel(listOfCommande);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
}
