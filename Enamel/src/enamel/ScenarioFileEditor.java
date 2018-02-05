package enamel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ScenarioFileEditor extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; 
	public String output; // global variable for output from GUI's
	public String filename;
	public JButton b1;
	public JButton b2;
	public JButton b3; 
	public File x; // global variable for absolute path of file
	public boolean fileState; // true means new file, false means existing file(no use right now)

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public ScenarioFileEditor() throws IOException {
		int y = JOptionPane.showConfirmDialog(null, "New File?");
		if (y == JOptionPane.YES_OPTION) {
			filename = JOptionPane.showInputDialog(this, "Type in file name:");
			x = new File(filename + ".txt");
			fileState = true;
		} else if (y == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Select your existing file");
			this.launcher();
			fileState = false;
		} else {
			System.exit(0); //terminate
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		b1 = new JButton("Edit Braille Cells (READ DOCUMENTATION!)");
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.WHITE);
		b1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.b1.addActionListener(this);
		b1.setBounds(64, 62, 292, 40);
		contentPane.add(b1);

		b2 = new JButton("Set Number of Buttons\r\n");
		b2.setBackground(Color.WHITE);
		b2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		b2.addActionListener(this);
		b2.setBounds(102, 113, 231, 40);
		contentPane.add(b2);

		JLabel lblNewLabel = new JLabel("How Would You Like To Edit This File?\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(54, 11, 317, 40);
		contentPane.add(lblNewLabel);

		b3 = new JButton("Enter Command");
		b3.setBackground(Color.WHITE);
		b3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		b3.addActionListener(this);
		b3.setBounds(150, 174, 131, 40);
		contentPane.add(b3);
	}

	public void launcher() throws IOException {
		JFileChooser chooser1 = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose file to edit", "txt");
		chooser1.setFileFilter(filter);
		int returnVal = chooser1.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser1.getSelectedFile().getName());
		}
		filename = chooser1.getSelectedFile().getAbsolutePath();
		x = new File(filename);
	}

	/**
	 * The method WriteCell currently deletes all content of the current chosen
	 * file and outputs the parameter string to the beginning of the parameter
	 * file WARNING DO NOT USE FOR FILES WITH IMPORTANT CONTENT!!
	 * 
	 * @param x
	 * @param f
	 * @throws IOException
	 */

	public void WriteCell(String x, File f) throws IOException {
		FileWriter fw = new FileWriter(f);
		fw.write(x + "\n");
		fw.close();
	}

	/**
	 * The method WriteButton adds to the end of the current chosen file feel
	 * free to test this
	 * 
	 * @param x
	 * @param f
	 * @throws IOException
	 */
	public void WriteButton(String x, File f) throws IOException {
		FileWriter fw = new FileWriter(f, true);
		fw.write(System.lineSeparator());
		fw.write(x + "\n");
		fw.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			output = "Cell " + JOptionPane.showInputDialog("Enter Number of Braille Cells (enter an integer): ");
			try {
				this.WriteCell(output, x);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		if (e.getSource() == b2) {
			output = "Button " + JOptionPane.showInputDialog("Enter Number of Buttons (enter an integer): ");
			try {
				this.WriteButton(output, x);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

		if (e.getSource() == b3) {
			output = JOptionPane.showInputDialog("Enter Command");
			try {
				this.WriteButton(output, x);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
}
