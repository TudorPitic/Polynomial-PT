import gui.*;

import java.awt.*;

public class App
{
    public static void main( String[] args )
    {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Model theModel = new Model();
                    View theView = new View();
                    new Controller(theView, theModel);
                    theView.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}