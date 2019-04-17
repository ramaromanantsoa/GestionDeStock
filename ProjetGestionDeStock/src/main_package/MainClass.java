package main_package;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import commande.CommandWindow;
import client.ClientWindow;
import details_commande.DetailComWindow;

public class MainClass {
	protected static MainWindow mainwindow;
	protected static CommandWindow commandwindow;
	protected static ClientWindow clientwindow;
	protected static DetailComWindow detailcommandewindow;
	protected static ConnectionWindow connectionwindow;
	
	public static void rendreVisibleCommande() {
		commandwindow.setVisible(true);
		mainwindow.setVisible(false);
		clientwindow.setVisible(false);
		detailcommandewindow.setVisible(false);
	}
	public static void rendreVisibleProduit() {
		commandwindow.setVisible(false);
		mainwindow.setVisible(true);
		clientwindow.setVisible(false);
		detailcommandewindow.setVisible(false);
	}
	public static void rendreVisibleClient() {
		commandwindow.setVisible(false);
		mainwindow.setVisible(false);
		clientwindow.setVisible(true);
		detailcommandewindow.setVisible(false);
	}
	public static void rendreVisibleDetailCommande() {
		commandwindow.setVisible(false);
		mainwindow.setVisible(false);
		clientwindow.setVisible(false);
		detailcommandewindow.setVisible(true);
	}
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		mainwindow = new MainWindow();
		mainwindow.setVisible(false);
		
		commandwindow = new CommandWindow();
		commandwindow.setVisible(false);
		
		clientwindow = new ClientWindow();
		clientwindow.setVisible(false);
		
		detailcommandewindow = new DetailComWindow();
		detailcommandewindow.setVisible(false);
		
		connectionwindow = new ConnectionWindow();
		connectionwindow.setVisible(true);
	}
}
