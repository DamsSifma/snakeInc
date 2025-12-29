package org.snakeinc.snake;

import org.snakeinc.snake.client.ApiClient;
import org.snakeinc.snake.client.PlayerDto;
import org.snakeinc.snake.client.ScoreDto;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.List;
import java.util.Optional;

public class GameIntegrationHelper {
    private final ApiClient api;
    private Integer playerId;
    private final String snakeName;

    public GameIntegrationHelper(String apiBaseUrl, String snakeName) {
        this.api = new ApiClient(apiBaseUrl);
        this.snakeName = snakeName;
    }

    public void askPlayer() {
        List<PlayerDto> players = api.fetchAllPlayers();

        if (players.isEmpty()) {
            // Fallback: demander l'ID manuellement si aucun joueur n'est disponible
            String input = JOptionPane.showInputDialog(null,
                "Aucun joueur trouvé. Entrez l'ID du joueur:",
                "Select Player",
                JOptionPane.QUESTION_MESSAGE);
            try {
                playerId = Integer.valueOf(input);
            } catch (Exception e) {
                playerId = null;
            }
            return;
        }

        // Créer un JComboBox avec la liste des joueurs
        JComboBox<PlayerDto> comboBox = new JComboBox<>(players.toArray(new PlayerDto[0]));
        comboBox.setSelectedIndex(0);

        int result = JOptionPane.showConfirmDialog(
            null,
            comboBox,
            "Sélectionnez un joueur",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            PlayerDto selected = (PlayerDto) comboBox.getSelectedItem();
            playerId = (selected != null) ? selected.getId() : null;
        } else {
            playerId = null;
        }
    }

    public void sendScoreAndShowResultAsync(int score) {
        if (playerId == null) return;
        ScoreDto dto = new ScoreDto(playerId, snakeName, score);
        new Thread(() -> {
            boolean ok = api.sendScore(dto);
            Optional<Integer> best = api.fetchBestScore(playerId, snakeName);
            String message = "Score: " + score + "\n";
            message += best.map(b -> "Best score: " + b).orElse("Best score: non disponible");

            String finalMessage = message;
            SwingUtilities.invokeLater(() -> {
                if (!ok) {
                    JOptionPane.showMessageDialog(null, "Impossible d'envoyer le score au serveur.");
                }
                JOptionPane.showMessageDialog(null, finalMessage, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            });
        }).start();
    }

}
