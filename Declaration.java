package JFrame;

import javax.swing.*;
import java.awt.*;


/**
 *#프로젝트 중 문제 
 *
 * - frame은 기본적으로 borderlayout에 설정되는데 이는 내부에서 좌표가 자동 배정이 되므로 원하는 좌표에 직접 set할 수 없음 & 테두리 로인해 프레임 좌측 상단이 0,0으로 간주되지 않음
 * 
 * */



/* variable declaration set*/

public class Declaration{
	
	public final int FRAME_WIDTH = 800;
	public final int FRAME_HEIGHT = 500;
	public final int brickHeight = 60;
	public final int brickWidth = 108;
	
	public int row, col; // me의 좌표 상의 brick 위치를 파악하기 위해 정하는 것. 즉 me(player)의 좌표를 brick의 행렬 형식으로 변환한 것
	
	public static void main(String args[]) {
		
	}
}

class Brick{
	public static final int width = 7, height = 7;
	private boolean flag; //flag는 brick의 존재여부를 나타낸다. flag가 false라면 brick의 이미지 역시 삭제된다.
	
	JLabel brickLabel = new JLabel();
	
	Brick(){
		flag = true;
	}

	
	/*NOT completion, THIS method IN TEST.. 
	 * 
	 * prevent player and brick is overlapping
	 * 
	 * 메서드 취지 : 움직임을 관장하는 각 keybinding action class 속에 삽입하여 예측되는 움직임이 올바르다면 이동, 그렇지 못하다면 움직임을 저지한다.
	 * 			 그에 대해 움직임이 brick에 닿으면 움직여 지지 않도록 설정한다.
	 * */
	
	// if method result is true -> it means : player's can't go way.
	//이는 좌표적으로 양수로 향하는 움직임에 사용하는 메서드
//	boolean Up_mov_check(int dimen, int flag) { //flag 0:x, 1:y
//		int target = flag==0?brickLabel.getX():brickLabel.getY();
//		
//		if(dimen > target) return true;
//		
//		return false;
//	}
//	//좌표적으로 음수를 향하는 움직임에 사용하는 메서드
//	boolean Down_mov_check(int dimen, int flag){ //flag 1:x, 2:y
//		int target = flag==0?brickLabel.getX():brickLabel.getY();
//		
//		if(dimen < target) return true; //
//		
//		return false;
//	}
		
	void FlagtoFalse() {
		flag = false;
	}
	
	//벽돌의 존재여부를 반환한다.
	boolean getFlag() {
		return flag;
	}
}
