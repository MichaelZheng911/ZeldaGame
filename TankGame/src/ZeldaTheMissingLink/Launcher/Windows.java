package ZeldaTheMissingLink.Launcher;
import javax.swing.JFrame;

public class Windows extends JFrame{

    public Windows(){
        setTitle("Zelda the Missing Link");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280,720));
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
