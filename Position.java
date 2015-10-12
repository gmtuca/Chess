public class Position
{
  public int x, y;
  
  public Position(int givenX, int givenY)
  {
    x = givenX;
    y = givenY;
  }//constructor
  
  public Position plus(int xx, int yy)
  {
    return new Position(this.x + xx, this.y + yy);
  }//plus
  
  public String toString()
  {
    return "[" + x + ", " + y + "]";
  }//toString

}//class
