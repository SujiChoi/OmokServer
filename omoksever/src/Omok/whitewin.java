package Omok;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class whitewin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					whitewin frame = new whitewin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public whitewin() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 254, 165);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		JLabel turn = new JLabel();
		turn.setIcon(new ImageIcon("C:\\Users\\it\\git\\Omok\\img\\white-go-stone24.png"));
		turn.setFont(new Font("µ¸¿òÃ¼", Font.BOLD, 14));
		turn.setHorizontalAlignment(SwingConstants.CENTER);
		turn.setBounds(0, 34, 150, 57);
		contentPane.add(turn);
		
		JLabel lblNewLabel = new JLabel("¹éµ¹ ½Â¸®");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 34, 270, 57);
		contentPane.add(lblNewLabel);
		
		contentPane.setBackground(Color.orange);
	}
}
