import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.TreeMap;

public class AutoCompleteTextField extends JTextField implements KeyListener{
	public TreeMap<String,String> tree;
	private char c;
	public String s;
	private int counter;
	public DefTextArea textarea;
	public AutoCompleteTextField(DefTextArea ta, TreeMap<String,String> t){
		super();
		this.setPreferredSize(new Dimension(100,30));
		textarea = ta;
		tree = t;
		counter=0;
		addKeyListener(this);
	}
	public AutoCompleteTextField(int col){
		super(col);
		addKeyListener(this);	
	}
	public void focusLost(){
		this.replaceSelection("");
	}
	
	public void keyTyped(KeyEvent e){
		c = e.getKeyChar();
		System.out.println("s before..."+s);
		e.consume();
		if(e.getKeyChar()==KeyEvent.VK_BACK_SPACE){
			if(s.length()>0)
				s=s.substring(0,s.length()-1);
		}
		else{
			if(counter==0)
				s = Character.toString(c);
			else
				s+=Character.toString(c);
		}	
		counter++;
		setText();
		selectText();
		textarea.keyPress(s);
		System.out.println("s after..."+s);
		
	}
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void selectText(){
		if(tree.ceilingKey(s).contains(s))
			this.select(s.length(),tree.ceilingKey(s).length());
	}
	public void setText(){
		if(s.length()==0)
			this.setText("");
		else
		{
			if(tree.ceilingKey(s).startsWith(s))
			{
				this.setText(tree.ceilingKey(s));
			}
				
			else
				this.setText(s);
		}
	}
}