package JFrame;

import javax.swing.SwingUtilities;

public class Main {
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new FrameSetting(); // 안전하게 돌릴 수 있다. 
            }
        });
//		new MyFrame(); 
	}
	
}
