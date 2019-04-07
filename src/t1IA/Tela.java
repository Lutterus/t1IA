package t1IA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;

public class Tela extends JFrame {

	private JPanel contentPane;
	private Heuristic heu;
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();

	public Tela(Heuristic heu) {
		setTitle("T1 - Algoritmos Avan\u00E7ados");
		this.heu = heu;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblAlgoritmoA = new JLabel("Algoritmo A*");
		lblAlgoritmoA.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlgoritmoA.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.black);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.black);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
								.addComponent(lblAlgoritmoA, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAlgoritmoA)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
							.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel.setLayout(new BorderLayout(0, 0));

		panel.add(new DrawEstrela(), BorderLayout.CENTER);
		contentPane.setLayout(gl_contentPane);
		btnNewButton.addActionListener(new ButtonListener());
		

	}

	private class DrawEstrela extends JComponent {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D graph2 = (Graphics2D) g;

			graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			heu.getEstrela(g, panel);

		}
	}

	private class DrawHeuristica extends JComponent {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D graph2 = (Graphics2D) g;

			graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			heu.getHeuristica(g, panel_1);

		}
	}
	
	public class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	panel_1.add(new DrawHeuristica(), BorderLayout.CENTER);
    		setVisible(true);       	 
        }
    }
}
