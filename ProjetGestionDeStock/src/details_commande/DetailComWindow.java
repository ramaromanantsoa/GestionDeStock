package details_commande;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import items_and_list.GetDetailsCommand;
import method_create_toolbar.ToolBar;

public class DetailComWindow extends JFrame{
	private static final long serialVersionUID = 4066281281449502891L;
	public static JTable tabDetailCommande;
	protected static DefaultTableModel listOfCommande;
	protected static Statement statement;
	private static JPanel panel_2;

	public DetailComWindow() {
		super("Gestion de stock");
		JPanel commandPanel = (JPanel) this.getContentPane();
		
		/********* TOOLBAR ***********/
		commandPanel.add(ToolBar.createToolBar(), BorderLayout.NORTH);
		
		/************ TABLE COMMANDE ******************/
		tabDetailCommande = new JTable();
		tabDetailCommande.setBackground(new Color(230, 230, 250));
		tabDetailCommande.setColumnSelectionAllowed(true);
		tabDetailCommande.setShowVerticalLines(true);
		tabDetailCommande.setShowHorizontalLines(true);
		tabDetailCommande.setEnabled(false);
		tabDetailCommande.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		tabDetailCommande.setRowHeight(25);
		GetDetailsCommand.getListOfCommand();

		panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(new JScrollPane(tabDetailCommande));

		/********* SPLIT PANE *************/
		/*
		 * JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		 * panelSaisie, panel_2); splitPanel.setResizeWeight(0.0);
		 */
		commandPanel.add(panel_2);		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1000, 400));
		this.setLocationRelativeTo(null);
	}
	
	public static void actualiserTable() {
		DefaultTableModel model = (DefaultTableModel)tabDetailCommande.getModel();
		model.setRowCount(0);
		GetDetailsCommand.getListOfCommand();
	}
}
