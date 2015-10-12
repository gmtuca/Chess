import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class ChessGUI extends JFrame
{
  public JPanel[][] grid;
  public JLabel[][] imageLabels;
  
  public ChessGUI() throws Exception
  {
    grid = new JPanel[8][8];
    setTitle("Chess");
    Container contents = getContentPane();
    contents.setLayout(new GridLayout(8 , 8));
    contents.setPreferredSize(new Dimension(650, 650));
    
    imageLabels = new JLabel[8][8];
        
    Color currentGridBg = Color.WHITE;
    
    for(int y = 0; y < 8; y++)
    {
      for(int x = 0; x < 8; x++)
      {
        grid[y][x] = new JPanel(new BorderLayout());
        grid[y][x].setSize(30,30);
        
        imageLabels[y][x] = new JLabel();
        
        grid[y][x].setBackground(currentGridBg);
        grid[y][x].add(imageLabels[y][x]);
                
        //alternate
        currentGridBg = (currentGridBg == Color.WHITE) ? Color.BLACK : Color.WHITE;
      }//for
      
      currentGridBg = (currentGridBg == Color.WHITE) ? Color.BLACK : Color.WHITE;
     }//for

   for(int y = 0; y < 8; y++)
     for(int x = 0; x < 8; x++)
       contents.add(grid[y][x]);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }//constructor
  
  public void showMovable(Position pos)
  {
    if(isInside(pos))
      grid[pos.y][pos.x].setBackground(Color.YELLOW);
  }//showMovable
  
  public void showKillable(Position pos)
  {
    if(isInside(pos))
      grid[pos.y][pos.x].setBackground(Color.BLUE);
  }//showKillable  
  
  public void showClicked(Position pos)
  {
    if(isInside(pos))
      grid[pos.y][pos.x].setBackground(Color.RED);
  }//showClicked
  
  public void showTowerKingMovement(Position pos)
  {
    if(isInside(pos))
      grid[pos.y][pos.x].setBackground(Color.GREEN);
  }//showTKM
  
  public void resetColors()
  {
    Color currentGridBg = Color.WHITE;
  
    for(int y = 0; y < 8; y++)
    {
      for(int x = 0; x < 8; x++)
      {
        grid[y][x].setBackground(currentGridBg);
        currentGridBg = (currentGridBg == Color.WHITE) ? Color.BLACK : Color.WHITE;   
      }//for
      
      currentGridBg = (currentGridBg == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }//for
  }//resetColors
  
  public boolean posIsIlluminated(Position pos)
  {
    return grid[pos.y][pos.x].getBackground().equals(Color.YELLOW) ||
             grid[pos.y][pos.x].getBackground().equals(Color.BLUE);
  }//posIsIlluminated
  
  public boolean isInside(Position pos)
  {
    return pos.x < 8 && pos.y < 8 && pos.x >= 0 && pos.y >= 0;
  }//isInside

  public void setPiece(Piece piece, Position pos)
  {
    imageLabels[pos.y][pos.x].setText(piece.image.getText());
  }//setPiece

  public void setBlank(Position pos)
  {
    imageLabels[pos.y][pos.x].setText("");
  }//setBlank

}//class
