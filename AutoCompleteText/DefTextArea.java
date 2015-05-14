import java.awt.*;
import java.util.TreeMap;
import javax.swing.JTextArea;
import java.util.TreeMap;

public class DefTextArea extends JTextArea {
	private TreeMap<String,String> tree;
	public String current;
	public DefTextArea(int h, int w, TreeMap<String,String> t){
		super(h,w);	
		tree = t;
		current="";
		this.setLineWrap(true);
	}
	public void keyPress(String s){
		current = s;
		if(tree.containsKey(current))
			this.setText(tree.get(current));
		else
			this.setText("");
	}
	public void focusLost(){
		if(this.getText()!="" && !tree.containsKey(current)){
			tree.put(current,this.getText());
		}
		else if (tree.containsKey(current))
			tree.put(current,this.getText());
	}

}