package items_and_list;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import commande.CommandWindow;
import main_package.Connector;

public class GetListProduitForCom extends  CommandWindow {
	private static final long serialVersionUID = 1L;
	public static void getListOfProduit() {
		listOfProduit = new	DefaultTableModel();
		
		ResultSet result = null;
		try {

			statement = Connector.connect().createStatement();
			String requete = "SELECT p.ID_Produit, p.Nom_Produit, u.Type_Unite, p.Quantite_Produit, p.Prix_Produit, c.Nom_Categorie "
					+ "FROM Unite u INNER JOIN (Produit p INNER JOIN Categorie c ON p.ID_Categorie = c.ID_Categorie) "
					+ "ON p.ID_Unite = u.ID_Unite "
					+ "ORDER BY c.Nom_Categorie ASC";
			result = statement.executeQuery(requete);

			listOfProduit.addColumn("ID");
			listOfProduit.addColumn("Nom du produit");
			listOfProduit.addColumn("Unité");
			listOfProduit.addColumn("Prix unitaire");
			listOfProduit.addColumn("Quantité");
			listOfProduit.addColumn("Catégorie");
			
			while(result.next()) {
				listOfProduit.addRow(new Object[] {
					result.getInt(1),
					result.getString(2),
					result.getString(3),
					result.getString(5),
					result.getString(4),
					result.getString(6)
				});
			}
			tabProduit.setModel(listOfProduit);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
}
