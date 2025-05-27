package test;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

public class GameGUI extends JFrame {
    private StartScreen startScreen;
    private BattleScreen battleScreen;
    private UnitSelectionScreen unitSelectionScreen;

    public GameGUI() {
        setTitle("게임 시작");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        startScreen = new StartScreen(this);
        setContentPane(startScreen);
        setVisible(true);
    }

    public void showUnitSelectionScreen() {
    	unitSelectionScreen = new UnitSelectionScreen(this);
    	setContentPane(unitSelectionScreen);
    	revalidate();
    	repaint();
    }
    public void showBattleScreen(List<String> selectedUnits) {
        BattleScreen battleScreen = new BattleScreen(selectedUnits);
        setContentPane(battleScreen);
        revalidate();  // 새 화면 갱신
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameGUI());
    }
}
