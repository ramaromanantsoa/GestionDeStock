package method_create_toolbar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import main_package.MainClass;

public class ToolBar extends JFrame {
	private static final long serialVersionUID = 1L;
	public static JToolBar createToolBar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setForeground(Color.BLUE);
		toolbar.setBackground(Color.WHITE);
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
				
		JButton btnCollectProduit = new JButton("Gestion de produit");
		btnCollectProduit.setForeground(Color.BLUE);
		btnCollectProduit.setBackground(Color.LIGHT_GRAY);
		btnCollectProduit.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnCollectProduit.setIcon(new ImageIcon("icons/addprod.png"));
		btnCollectProduit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainClass.rendreVisibleProduit();
			}
		});
		toolbar.add(btnCollectProduit);
				
		toolbar.addSeparator();
				
		JButton btnComande = new JButton("Gestion de comande");
		btnComande.setForeground(Color.BLUE);
		btnComande.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnComande.setIcon(new ImageIcon("icons/command.png"));
		btnComande.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainClass.rendreVisibleCommande();
			}
		});
		toolbar.add(btnComande);
		
		toolbar.addSeparator();
		
		JButton btnClient = new JButton("Info client");
		btnClient.setForeground(Color.BLUE);
		btnClient.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btnClient.setIcon(new ImageIcon("icons/client.png"));
		btnClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainClass.rendreVisibleClient();
			}
		});
		toolbar.add(btnClient);
		
		toolbar.addSeparator();
		
		JButton btndetailcom = new JButton("Info Vente");
		btndetailcom.setForeground(Color.BLUE);
		btndetailcom.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		btndetailcom.setIcon(new ImageIcon("icons/detailcom.png"));
		btndetailcom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainClass.rendreVisibleDetailCommande();
			}
		});
		toolbar.add(btndetailcom);
				
		return toolbar;
	}
	
}
