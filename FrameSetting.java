package JFrame;

import javax.swing.*;

import JFrame.MyFrame.downAction;
import JFrame.MyFrame.leftAction;
import JFrame.MyFrame.rightAction;
import JFrame.MyFrame.upAction;

import java.awt.*;
import java.awt.event.ActionEvent;


/**
 * @문제
 * 일단 디자인이 후져도 작동이 되야함(기능 작동 후 꾸미기)
 * 고정관념의 리스트화로 나중에 와서 또 깨도록함 파일로 저장, 순위화
 * 게임은 기록이 안남으면 재미가 없고 도전의식이 있어야 한다.
 * 랜덤 고정관념 최소 100
 * 48개 중에 몇개를 깨야 클리어인지
 * 저장되는 기능의 여부
 * 
 * 실행 시 다른 앱 다 닫기
 * 
 * */


/*Default setting about Frame*/

public class FrameSetting extends JFrame{
	
	Declaration dec = new Declaration();
	Brick brick[][] = new Brick[7][7]; //it has bool values
	
	Action upAction;
	Action downAction;
	Action leftAction;
	Action rightAction;
	
	JLabel me;
	ImageIcon me_default, me_left, me_right, me_up;
	/* player(character that you can move) : JLabel-me |
	 * me_left,right -> moving icon(it's change by direction) */

	int X, Y; //player의 현재 좌표를 저장하는 곳

	FrameSetting(){
		
		//JFrame 설정
		this.setTitle("Break!");
		this.setSize(dec.FRAME_WIDTH,dec.FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //추후 DO_NOTHING_ON_CLOSE
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		Container contentPane = this.getContentPane();  
		contentPane.setLayout(null);
		
		JLayeredPane layeredpane = new JLayeredPane();
		layeredpane.setBounds(0,0, dec.FRAME_WIDTH, dec.FRAME_HEIGHT);
		
		ImageIcon icon = new ImageIcon("src/img/default_background.png");
		me_default = new ImageIcon("src/img/default_p.png");
		me_left = new ImageIcon("src/img/go_left_p.png");
		me_right = new ImageIcon("src/img/go_right_p.png");
		me_up = new ImageIcon("src/img/back_p.png");
		
		JLabel background = new JLabel(icon); //it is just color
		me = new JLabel(me_default); //기본으로 설정
		background.setBounds(0,0,dec.FRAME_WIDTH, dec.FRAME_HEIGHT);
		me.setBounds(372,219,54,69); //it's just result of calc... player can locate center
		
		//add background
		layeredpane.add(background, JLayeredPane.DEFAULT_LAYER);
		layeredpane.add(me,JLayeredPane.MODAL_LAYER);
		//add
		this.add(layeredpane);
		
		//이를 사용해서 brick의 위치를 정한다.
		int x = 0;
		int y = 10;
		for(int i=0; i<brick.length; i++) {
			for(int j=0; j<brick[0].length; j++) {
				
				brick[i][j] = new Brick();
		        
		        if(i==3&j==3) {
		        	brick[i][j].FlagtoFalse(); //플레이어가 최초로 위치할 공간에 대한 예외이다.
		        	continue;
				}
		        
		        brick[i][j].brickLabel = new JLabel(new ImageIcon("src/img/bricks.png"));
		        
		        // 좌표 계산: brickWidth와 gap(8)을 고려
		        x = j * (dec.brickWidth + 7); // 열마다의 위치
		        // 행마다의 위치
		        brick[i][j].brickLabel.setBounds(x, y, dec.brickWidth, dec.brickHeight);
		        
		        layeredpane.add(brick[i][j].brickLabel, JLayeredPane.MODAL_LAYER);
		        
			}
			y += dec.brickHeight+10;
			x = 0;
		} 
		
//		int totalHeight = (brick.length * (dec.brickHeight + 8)) + 10 + 10;
//		layeredpane.setPreferredSize(new Dimension(dec.FRAME_WIDTH, totalHeight));
		
	
		//창 고정, 창 최상위
		this.setUndecorated(true);
//		this.setAlwaysOnTop(true);
		
		upAction = new upAction();
		downAction = new downAction();
		leftAction = new leftAction();
		rightAction = new rightAction();
		
		layeredpane.setFocusable(true);
		layeredpane.requestFocusInWindow();

		layeredpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "upAction");
		layeredpane.getActionMap().put("upAction", upAction);
		layeredpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downAction");
		layeredpane.getActionMap().put("downAction", downAction);
		layeredpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
		layeredpane.getActionMap().put("leftAction", leftAction);
		layeredpane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
		layeredpane.getActionMap().put("rightAction", rightAction);

		
		setRowCol(); //init
		this.setVisible(true);
		
	}
	
	void setRowCol() {
		
		//debug
	    System.out.printf("cols : %d, rows : %d\n",  dec.col, dec.row);
	    System.out.println("순서쌍 : " + me.getX() + "," + me.getY());
			
	    this.X = me.getX()-10;
	    this.Y = me.getY();
	    /*문제 발견. */
	    if(Y>=0 && Y<80) dec.row = 0;
	    else if(Y>=80 && Y<150) dec.row = 1;
	    else if(Y>=150 && Y<220) dec.row = 2;
	    else if(Y>=220 && Y<288) dec.row = 3;
	    else if(Y>=288 && Y<360) dec.row = 4;
	    else if(Y>=360 && Y<410) dec.row = 5;
	    else if(Y>=410 && Y<500) dec.row = 6;
	    
	    if(Y>=0 && X<115) dec.col = 0; 
	    else if(X>=115 && X<230) dec.col = 1;
	    else if(X>=230 && X<345) dec.col = 2;
	    else if(X>=345 && X<460) dec.col = 3;
	    else if(X>=460 && X<575) dec.col = 4;
	    else if(X>=575 && X<690) dec.col = 5;
	    else if(X>=690 && X<800) dec.col = 6;
	    
	    
	}
	
//	boolean willCollide(int nextX, int nextY) {
//		 	int brickWidthWithGap = dec.brickWidth + 7;
//		    int brickHeightWithGap = dec.brickHeight + 10;
//		    
//		    int nextRow = (nextY - 10) / brickHeightWithGap;
//		    int nextCol = nextX / brickWidthWithGap;
//
//		    // 배열 범위 내인지 확인
//		    if (nextRow >= 0 && nextRow < brick.length && nextCol >= 0 && nextCol < brick[0].length) {
//		        return brick[nextRow][nextCol].getFlag(); // 충돌 여부 반환
//		    }
//		    return false; // 배열 범위를 벗어날 충돌 ㄴ
//	}
	
	
	class upAction extends AbstractAction {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        
	        me.setLocation(me.getX(), me.getY() - 2); // 충돌하지 않으면 이동
	        setRowCol();
	        me.setIcon(me_up); // 아이콘 변경 
	    }
	}

	class downAction extends AbstractAction {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        
	        me.setLocation(me.getX(), me.getY() + 2);
	        setRowCol();
	        me.setIcon(me_default);
	    }
	}

	class leftAction extends AbstractAction {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        
	        me.setLocation(me.getX() - 2, me.getY());
	        setRowCol();
	        me.setIcon(me_left);
	       
	    }
	}
	
	class rightAction extends AbstractAction {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        me.setLocation(me.getX() + 2, me.getY());
	        setRowCol();
	        me.setIcon(me_right);
	    }
	}
}