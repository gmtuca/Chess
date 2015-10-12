public class Chess
{
  public static void main(String[] args)
  {
    try
    {
      GameManager gameManager = new GameManager();
    }//try
    catch(Exception ex)
    {
      System.err.println("ERROR:" + ex);
    }//catch
  }//main
}//class
