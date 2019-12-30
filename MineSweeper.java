import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MineSweeper{
	public static void main(String[] args){
		// int size=20;
		// int density;//{0-4 increasing order of density}
		// Board b=new Board(size);
		// b.fillBomb();
		// b.calculateValue();
		// b.display();
		TT1 t1=new TT1();
	}
}
class TT1 extends JFrame implements ActionListener{
	Icon []num=new Icon[9];
	JLabel l1,l2;
	JButton buttons[];
	JButton reset;
	JRadioButton rad0,rad1,rad2,rad3,rad4;
	int size=20;
	int [][]mat;
	int x_length,y_length;
	public TT1(){
		super("MineSweeper Game");
		setLayout(new GridLayout(2,1));
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel headerLabel = new JLabel("",JLabel.CENTER);
		headerLabel.setText("Difficulty Scale");
		add(headerLabel);
		rad0 = new JRadioButton("0");
		rad1 = new JRadioButton("1");
		rad2 = new JRadioButton("2");
		rad3 = new JRadioButton("3");
		rad4 = new JRadioButton("4");
		rad0.addActionListener(this);
		rad1.addActionListener(this);
		rad2.addActionListener(this);
		rad3.addActionListener(this);
		rad4.addActionListener(this);
		ButtonGroup group=new ButtonGroup();
		group.add(rad0);
		group.add(rad1);
		group.add(rad2);
		group.add(rad3);
		group.add(rad4);
		JPanel panel=new JPanel();
		panel.add(rad0);
		panel.add(rad1);
		panel.add(rad2);
		panel.add(rad3);
		panel.add(rad4);
		add(panel);
		setSize(500,500);
		// x_length=(50)*size+100;
		// y_length=(50)*size+200;
		// showButton();		
	}
	public TT1(Board b){
		super("MineSweeper Game");
		buttons=new JButton[size*size];
		num[0]=new ImageIcon("bomb.png");
		num[1]=new ImageIcon("1.png");
		num[2]=new ImageIcon("2.png");
		num[3]=new ImageIcon("3.png");
		num[4]=new ImageIcon("4.png");
		num[5]=new ImageIcon("5.png");
		num[6]=new ImageIcon("6.png");
		num[7]=new ImageIcon("7.png");
		num[8]=new ImageIcon("8.png");

	}
	void setup(int d){
		int density=d;
		Board b=new Board(size);
		b.fillBomb(density);
		b.calculateValue();
		b.display();
		TT1 t1=new TT1(b);
		t1.mat=b.matrix;
		t1.setLayout(null);
		t1.setVisible(true);
		t1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		x_length=(50)*size+100;
		y_length=(50)*size+100;
		t1.showButton();
		t1.setSize(x_length,y_length);
	}
	void showButton(){
		int x=0,y=0;
		for(int i=0,j=0;i<size*size;i++,x+=50,j++){
			buttons[i]=new JButton();
			buttons[i].setBackground(Color.LIGHT_GRAY);
			if(j==size){j=0;y+=50;x=0;}
			buttons[i].setBounds(x,y,50,50);
			add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		reset = new JButton("RESET");
		reset.setBounds(0,y+50,50,100);
		add(reset);
		reset.addActionListener(this);
	}
	void show_all_mines(){
		//reset();
		//gameOver() ho chuka hai
		for(int i=0;i<size*size;i++){
			if(mat[i/size][i%size]==-1){
				buttons[i].setBackground(Color.DARK_GRAY);
				buttons[i].setIcon(num[0]);
			}
		}
	}
	void gameOver(){
		show_all_mines();
		// final JOptionPane optionPane = new JOptionPane(
		// 	"You hit a mine\n"+"Do you want to play again",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
		// final JDialog dialog=new JDialog(this,"click a button",true);
		// dialog.setContentPane(optionPane);
		// // optionPane.addPropertyChangeListener(
		// // 	new PropertyChangeListener(){
		// // 		String prop = e.getPropertyName();
		// // 		if (dialog.isVisible()      && (e.getSource() == optionPane)
  // //            && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
  // //               //If you were going to check something
  // //               //before closing the window, you'd do
  // //               //it here.
  // //               dialog.setVisible(false);
  // //           }
  // //       }
		// 	// });
		// dialog.setVisible(true);
		// dialog.pack();
		// System.out.println(optionPane.getValue());
		// System.out.println("hh");
		// System.exit(0);
		// int choice=((Integer)optionPane.getValue()).intValue();
		// JOptionPane jop=new JOptionPane();
		int choice=JOptionPane.showConfirmDialog(null,"You Hit a Mine. Want to play again","Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		// // jop.setDefaultCloseOperation(JOptionPane.DISPOSE_ON_CLOSE);
		// //0 for Yes 1 for No

		if(choice==0){
			dispose();
			new TT1();
		}
		else if(choice==1){
			setVisible(false);
			// dispose();
			System.exit(0);
		}
		// JOptionPane.showConfirmDialog(TT1.this,"YOU HIT A MINE.\n BETTER LUCK NEXT TIME");
	}
	void reset(){
		for(int i=0;i<size*size;i++){
			buttons[i].setIcon(null);
		}		
	}
	void show(int i){
		buttons[i].setBackground(Color.DARK_GRAY);
		if(buttons[i].getIcon()==null){
			if(mat[i/size][i%size]!=0){
				if(mat[i/size][i%size]==-1){
					buttons[i].setIcon(num[0]);
					gameOver();
				}
				else if(mat[i/size][i%size]==100);
				else{
					buttons[i].setIcon(num[mat[i/size][i%size]]);
				}
			}else{
				mat[i/size][i%size]=100;
				showAdjoint(i);
			}
		}		
	}
	void showAdjoint(int i){
		if(i%size==0){
			if(i-size>=0 && i-size<size*size) show(i-size);
			if(i-size+1>=0 && i-size+1<size*size) show(i-size+1);
			if(i+1>=0 && i<size*size) show(i+1);
			if(i+size>=0 && i+size<size*size) show(i+size);
			if(i+size+1>=0 && i+size+1<size*size) show(i+size+1);
			return;
		}
		if((i+1)%size==0){
			if(i-size>=0 && i-size<size*size) show(i-size);
			if(i-size-1>=0 && i-size-1<size*size) show(i-size-1);
			if(i-1>=0 && i-1<size*size) show(i-1);
			if(i+size-1>=0 && i+size-1<size*size) show(i+size-1);
			if(i+size>=0 && i+size<size*size) show(i+size);
			return;
		}
		if(i-1>=0 && i-1<size*size) show(i-1);
		if(i+1>=0 && i+1<size*size) show(i+1);
		if(i-size>=0 && i-size<size*size) show(i-size);
		if(i-1-size>=0 && i-1-size<size*size) show(i-1-size);
		if(i+1-size>=0 && i+1-size<size*size) show(i+1-size);
		if(i+size-1>=0 && i+size-1<size*size) show(i+size-1);
		if(i+size>=0 && i+size<size*size) show(i+size);
		if(i+size+1>=0 && i+size+1<size*size) show(i+size+1);
	}
	void reset_all_100(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(mat[i][j]==100){
					mat[i][j]=0;
				}
			}
		}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==reset){
			reset();
		}else if(e.getSource()==rad0){
			dispose();
			//System.out.println("hey");
			setup(0);
		}else if(e.getSource()==rad1){
			dispose();
			setup(1);
		}else if(e.getSource()==rad2){
			dispose();
			setup(2);
		}else if(e.getSource()==rad3){
			dispose();
			setup(3);
		}else if(e.getSource()==rad4){
			dispose();
			setup(4);
		}
		else{
			for(int i=0;i<size*size;i++){
				if(e.getSource()==buttons[i]){
					show(i);
					reset_all_100();
					break;
				}
			}

		}
	}
}
class Board{
	int[][] matrix;
	int boardSize;
	public Board(int size){
		boardSize=size;
		matrix = new int[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				matrix[i][j]=0;
			}
		}
	}
	public void fillBomb(int density){
		Random rn=new Random();
		int num_bombs=(int)((0.1+(density*0.1))*(boardSize*boardSize));
	//	System.out.println(num_bombs);

		for(int i=0;i<num_bombs;i++){
			int x=Math.abs(rn.nextInt(boardSize));
			int y=Math.abs(rn.nextInt(boardSize));
			// System.out.print(x+" ");
			// System.out.println(y);
			matrix[x][y]=-1;
		}
	}
	public boolean isBomb(int x,int y){
		if(matrix[x][y]==-1) return true;
		return false;
	}
	public boolean isValid(int x,int y){
		if(x<boardSize && x>=0){
			if(y<boardSize && y>=0){
				return true;
			}
		}
		return false;
	}
	public void calculateValue(){
		for(int i=0;i<boardSize;i++){
			for(int j=0;j<boardSize;j++){
				if(isBomb(i,j)){
					if(isValid(i,j+1) && !isBomb(i,j+1)) matrix[i][j+1]++;
				        if(isValid(i,j-1) && !isBomb(i,j-1)) matrix[i][j-1]++;
					if(isValid(i+1,j) && !isBomb(i+1,j)) matrix[i+1][j]++;
					if(isValid(i-1,j) && !isBomb(i-1,j)) matrix[i-1][j]++;
					if(isValid(i+1,j+1) && !isBomb(i+1,j+1)) matrix[i+1][j+1]++;
					if(isValid(i+1,j-1) && !isBomb(i+1,j-1)) matrix[i+1][j-1]++;
					if(isValid(i-1,j+1) && !isBomb(i-1,j+1)) matrix[i-1][j+1]++;
					if(isValid(i-1,j-1) && !isBomb(i-1,j-1)) matrix[i-1][j-1]++;
				}
			}
		}
	}
	public void display(){
		for(int i=0;i<boardSize;i++){
			for(int j=0;j<boardSize;j++){
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
}