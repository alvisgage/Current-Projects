import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

class View extends JFrame implements FocusListener,ActionListener{
	public static TreeMap<String,String> tree;
	public DefTextArea ta;
	public AutoCompleteTextField tf;

	public View() throws Exception, FileNotFoundException{
		setLayout(new FlowLayout());
		enterKeys();
		JButton save = new JButton("Save");
		save.addActionListener(this);
		ta = new DefTextArea(5,30,tree);
		ta.addFocusListener(this);
		tf = new AutoCompleteTextField(ta,tree);
		tf.addFocusListener(this);
		this.setTitle("Assignment 6");
		this.setSize(500, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().add(new JPanel());
		this.getContentPane().add(ta);
		this.getContentPane().add(tf);
		this.getContentPane().add(save);
		this.setVisible(true);
	}
	public void enterKeys() throws FileNotFoundException{
		this.tree = new TreeMap<String,String>();
		Scanner s = new Scanner(new File("lexicon.txt"));
		while(s.hasNextLine())
			tree.put(s.nextLine(),"");
	}

	public void actionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(null,"Sorry, saving the dictionary is not yet implemented.");
	}
	public void focusGained(FocusEvent fe){
	}
	public void focusLost(FocusEvent fe){
		ta.focusLost();
		tf.focusLost();
	}
	public static void main(String[] args)throws Exception{
		new View();
	}
}
