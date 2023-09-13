package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Client_GUI {

	JFrame frame;
	public JTextField textFTime; 
	public JButton btnRun, btnStop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_GUI window = new Client_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 172);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		textFTime = new JTextField();
		textFTime.setBounds(10, 10, 416, 56);
		frame.getContentPane().add(textFTime);
		textFTime.setColumns(10);
		
		btnRun = new JButton("Run");
		btnRun.setBounds(10, 77, 200, 40);
		frame.getContentPane().add(btnRun);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(220, 77, 200, 40);
		frame.getContentPane().add(btnStop);

	}
}
