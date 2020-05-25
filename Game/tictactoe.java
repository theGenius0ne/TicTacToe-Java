import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class tictactoe extends JFrame implements ActionListener{
	
	public static int board_size = 3;
	
	public static enum gameStatus{
		Incomplete, XWins, OWins, Tie
	}
	
	private JButton [][] buttons = new JButton[board_size][board_size];
	boolean crossTurn = true;	
	
	public tictactoe() {
		super.setTitle("Tic-Tac-Toe");		//creating frame and setting its parameters
		super.setSize(800, 800);
		super.setVisible(true);
		super.setResizable(false);
		
		GridLayout grid = new GridLayout(board_size,board_size);
		super.setLayout(grid);				//putting the grid inside the frame
		
		Font font = new Font("Comic-Sans",1,150); //1 represents bold
		
		for(int row = 0; row < board_size; row++) {
			for(int col= 0; col< board_size ; col++) {
				JButton button = new JButton("");
				buttons[row][col] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);				//add button to frame
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		JButton clickedButton =(JButton) e.getSource();
		makeMove(clickedButton);	
		gameStatus gs = getGameStatus();
		if(gs == gameStatus.Incomplete) {
			return;
		}
		
		declareWinner(gs);
		
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to restart?");
		if(choice == JOptionPane.YES_OPTION) {
			for(int row = 0; row < board_size; row++) {
				for(int col = 0; col < board_size ; col++) {
					buttons[row][col].setText("");
				}
			}
			crossTurn = true;
		}else {
			super.dispose();
		}
	}

	private void declareWinner(gameStatus gs) {
		if(gs == gameStatus.XWins) {
			JOptionPane.showMessageDialog(this, "X Wins");
		}else if(gs == gameStatus.OWins) {
			JOptionPane.showMessageDialog(this, "O Wins");
		}else {
			JOptionPane.showMessageDialog(this, "Game Tie");
		}
		
	}

	private gameStatus getGameStatus() {
		String text1 = "";
		String text2 = "";
		int row = 0;
		int col = 0;
		
		// for row win
		while(row < board_size) {
			col = 0;
			while(col < board_size -1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col+1].getText();
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				col++;
			}
			if(col == board_size-1) {
				if(text1.equals("X")) {
					return gameStatus.XWins;
				}else {
					return gameStatus.OWins;
				}
			}
			row++;
		}
		
		//for col win
		col = 0;
		while(col < board_size) {
			row = 0;
			while(row < board_size -1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row+1][col].getText();
				if(!text1.equals(text2) || text2.length()==0) {
					break;
				}
				row++;
			}
			if(row == board_size-1) {
				if(text1.equals("X")) {
					return gameStatus.XWins;
				}else {
					return gameStatus.OWins;
				}
			}
			col++;
		}
		
		//for diagonal1 win
		
		row = 0;
		col=0;
		while(row != board_size-1) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row+1][col+1].getText();
			if(!text1.equals(text2) || text1.length()==0) {
				break;
			}
			row++;
			col++;
		}
		if(row == board_size-1) {
			if(text1.equals("X")) {
				return gameStatus.XWins;
			}else {
				return gameStatus.OWins;
			}
		}
		
		//for diagonal2 win
		row = board_size-1;
		col= 0;
		while(row != 0) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row-1][col+1].getText();
			if(!text1.equals(text2) || text1.length()==0) {
				break;
			}
			row--;
			col++;
		}
		if(row == 0) {
			if(text1.equals("X")) {
				return gameStatus.XWins;
			}else {
				return gameStatus.OWins;
			}
		}
		
		//for Incomplete
		String txt = "";
		for(row = 0; row < board_size; row++) {
			for(col = 0; col < board_size; col++) {
				txt = buttons[row][col].getText();
				if(txt.length()==0) {
					return gameStatus.Incomplete;
				}
			}
		}
		
		return gameStatus.Tie;
		
	}

	private void makeMove(JButton clickedButton){
		String btntxt = clickedButton.getText();
		if(btntxt.length()>0) {
			JOptionPane.showMessageDialog(this,"Invalid Move");
		}else {
			if(crossTurn) {
				clickedButton.setText("X");
			}else {
				clickedButton.setText("O");
			}
			crossTurn = !crossTurn;
		}
				
	}
	
}
