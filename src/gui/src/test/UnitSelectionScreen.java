package test;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class UnitSelectionScreen extends JPanel {
    private GameGUI mainFrame;
    private List<String> selectedUnits = new ArrayList<>();
    private JButton confirmButton;
    private JLabel instructionLabel;

    public UnitSelectionScreen(GameGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        // 상단 중앙 안내 문구
        instructionLabel = new JLabel("2개를 선택하시오");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setBounds(0, 10, 800, 30);
        add(instructionLabel);

        String[] unitNames = {
                "Assault Cruiser", "Guardian Battleship", "Medical Frigate", "Tactical Corvette"};
        String[] imageFiles = {
                "AssaultCruiser.png", "Guardian Battleship.png", "MedicalFrigate.png", "TacticalCorvette.png"};

        for (int i = 0; i < 4; i++) {
            String unitName = unitNames[i];
            String imageFile = imageFiles[i];

            JPanel unitPanel = createUnitPanel(unitName, imageFile);
            unitPanel.setBounds(50 + (i % 2) * 350, 50 + (i / 2) * 250, 300, 200);
            add(unitPanel);
        }

        confirmButton = new JButton("전투화면으로 가기");
        confirmButton.setBounds(300, 510, 200, 40);
        confirmButton.setEnabled(false);
        add(confirmButton);

        confirmButton.addActionListener(e -> {
            if (selectedUnits.size() == 2) {
                mainFrame.showBattleScreen(selectedUnits);
            }
        });
    }

    private JPanel createUnitPanel(String name, String imageFile) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 이미지 로드 및 크기 조정
        ImageIcon icon = new ImageIcon(getClass().getResource(imageFile));
        Image scaled = icon.getImage().getScaledInstance(280, 120, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaled));
        panel.add(imgLabel, BorderLayout.CENTER);

        // 유닛 이름 라벨 - 이미지 아래에 위치
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(nameLabel, BorderLayout.NORTH);

        JButton selectButton = new JButton("선택");
        selectButton.addActionListener(e -> {
            if (selectedUnits.contains(name)) {
                selectedUnits.remove(name);
                selectButton.setText("선택");
            } else {
                if (selectedUnits.size() < 2) {
                    selectedUnits.add(name);
                    selectButton.setText("선택된 유닛");
                }
            }
            // 선택된 유닛 개수에 따라 버튼 활성화 및 안내 문구 토글
            confirmButton.setEnabled(selectedUnits.size() == 2);
            instructionLabel.setVisible(selectedUnits.size() < 2);
        });

        panel.add(selectButton, BorderLayout.SOUTH);

        return panel;
    }
}

