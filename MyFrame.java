package JFrame;

import java.awt.*;
import java.math.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame{
	
	Action upAction;
	Action downAction;
	Action leftAction;
	Action rightAction;
	
	/**
	 * 이 클래스는 기존 단일 클래스. 
	 * @class 에서 전부 작성 후 파일별 기능을 부여하여 분기하고 있다. 
	 * 
	 * */
	
	
	/* 플레이어 : JLabel-me , me_left,right 등은 움직일때마다의 동작 아이콘 */
	JLabel me;
	ImageIcon me_default, me_left, me_right, me_up; 
	
	boolean[][] Brick;
	
	
	public MyFrame(){ 
		
		
		
		int brick_row = 7, brick_col = 7;
		
		int brickX = 0, brickY = 10; 
		Brick = new boolean[brick_row][brick_col];
		
		JLabel brick; 
		
//		for(int i=0; i<brick_row; i++) {
//			for(int j=0; j<brick_col; j++) {
//				
//				if(i==3 && j==3) {
//					Brick[3][3] = false; //player가 없는 곳
//					continue;
//				}
////				
////				brick = new JLabel(new ImageIcon("src/img/bricks.png"));
////				brick.setBounds(brickX+(j*(brickWidth+7)),brickY,brickWidth,brickHeight);
////				layeredpane.add(brick,JLayeredPane.MODAL_LAYER);
////				Brick[i][j] = true; 
////				
////			}
////			brickY += brickHeight+10; //10 == vertical gap
////			brickX = 0;
////			
////		}
//		
//		//add
//		this.add(layeredpane);
//		
//		//JFrame 설정
//		this.setTitle("Break!");
//		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //추후 DO_NOTHING_ON_CLOSE
//		this.setLayout(null);
//		this.setLocationRelativeTo(null);
//		this.setResizable(false);
//		//창 고정, 창 최상위
//		this.setUndecorated(true);
////		this.setAlwaysOnTop(true);
//		
//		upAction = new upAction();
//		downAction = new downAction();
//		leftAction = new leftAction();
//		rightAction = new rightAction();
//		
//		me.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction"); //키 바인딩 -> 액션(움직)
//		me.getActionMap().put("upAction", upAction);
//		me.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
//		me.getActionMap().put("downAction", downAction);
//		me.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
//		me.getActionMap().put("leftAction", leftAction);
//		me.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
//		me.getActionMap().put("rightAction", rightAction);
		
//		this.setVisible(true); //마지막으로 오도록
	}
	
	//player와 brick이 겹치지 않기 위한 판별 메서드
	//해당 공간의 벽돌이 있는지 검사한다. true : 벽돌이 있다. / false : 벽돌 없다ㅋ
	//조건을 통해 벽돌 감지 후 있다면 true 반환, if 통과한 후 false
	//왼쪽오른쪽으로 갈때, 위아래로갈때 따로따로 처리
	
	public boolean isCrashed(int X, int Y) {
		
		
		
		return false;
	}
	
	public class upAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			int newY = me.getX()-3;
			if (!isCrashed(me.getX(), newY)) { // 충돌이 없으면 이동
				me.setLocation(me.getX(),me.getY()-3);
				me.setIcon(me_up);
			}
		}
		
	}
	public class downAction extends AbstractAction{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int newY = me.getX()+3;
			if (!isCrashed(me.getX(), newY)) {
				me.setLocation(me.getX(),me.getY()+3);
				me.setIcon(me_default);
			}
		}
		
	}
	public class leftAction extends AbstractAction{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int newX = me.getX()-3;
			if (!isCrashed(newX, me.getY())) {
				me.setLocation(me.getX()-3,me.getY());
				me.setIcon(me_left);
			}
		}
		
	}
	public class rightAction extends AbstractAction{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int newX = me.getX()+3;
			if (!isCrashed(newX, me.getY())){
				me.setLocation(me.getX()+3,me.getY());
				me.setIcon(me_right);
			}
		}
		
	}
	
}

