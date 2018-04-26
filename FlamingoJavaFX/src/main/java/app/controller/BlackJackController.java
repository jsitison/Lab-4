package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import app.Flamingo;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import pkgCore.Action;
import pkgCore.GamePlay;
import pkgCore.Player;
import pkgCore.Table;
import pkgEnum.eAction;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;

public class BlackJackController implements Initializable {

	private Flamingo FlamingoGame;
	@FXML
	private Label lblTopName;
	@FXML
	private Label lblBottomName;
	@FXML
	private Button btnTop;
	@FXML
	private Button btnBottom;
	@FXML
	private Button btnHitTop;
	@FXML
	private Button btnHitBottom;
	@FXML
	private Button btnStandTop;
	@FXML
	private Button btnStandBottom;

	@FXML
	private BorderPane mainScreen;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnBottom.setText("Sit");
		btnTop.setText("Sit");
		btnHitTop.setText("Hit");
		btnHitBottom.setText("Hit");
		btnStandTop.setText("Stand");
		btnStandBottom.setText("Stand");
		
		btnHitTop.setVisible(false);
		btnHitBottom.setVisible(false);
		btnStandTop.setVisible(false);
		btnStandBottom.setVisible(false);
		

	}

	public void setMainApp(Flamingo FlamingoGame) {
		this.FlamingoGame = FlamingoGame;
	}

	@FXML
	public void btnSitLeave_Click(ActionEvent event) {
		Button btn = (Button) event.getSource();

		Action act = new Action();

		act.setAction(btn.getText().equals("Sit") ? eAction.Sit : eAction.Leave);
		Player p = FlamingoGame.getAppPlayer();
		if (btn.getId().equals("btnTop")) {
			p.setiPlayerPosition(2);
		} else if (btn.getId().equals("btnBottom")) {
			p.setiPlayerPosition(0);
		}

		act.setPlayer(FlamingoGame.getAppPlayer());
		FlamingoGame.SendMessageToClient(act);
		
	}

	public void HandleTableState(Table t) {

		btnBottom.setText("Sit");
		btnTop.setText("Sit");

		btnBottom.setVisible(true);
		btnTop.setVisible(true);
		
		btnHitTop.setVisible(false);
		btnStandTop.setVisible(false);
		
		btnHitBottom.setVisible(false);
		btnStandBottom.setVisible(false);
		
		lblBottomName.setText("Label");
		lblTopName.setText("Label");

		for (Player p : t.GetTablePlayers()) {
			switch (p.getiPlayerPosition()) {
			case 0:
				lblBottomName.setText(p.getPlayerName());
				if (p.getPlayerID().equals(FlamingoGame.getAppPlayer().getPlayerID())) {
					btnBottom.setText("Leave");
					btnHitBottom.setVisible(true);
					btnStandBottom.setVisible(true);
					
					btnTop.setVisible(false);
					btnHitTop.setVisible(false);
					btnStandTop.setVisible(false);
				} else {
					btnBottom.setVisible(false);
				}
				break;
			case 2:
				lblTopName.setText(p.getPlayerName());
				if (p.getPlayerID().equals(FlamingoGame.getAppPlayer().getPlayerID())) {
					btnTop.setText("Leave");
					btnHitTop.setVisible(true);
					btnStandTop.setVisible(true);
					
					btnBottom.setVisible(false);
					btnHitBottom.setVisible(false);
					btnStandBottom.setVisible(false);
				} else {
					btnTop.setVisible(false);
				}
				break;
			}
		}

		Action act = new Action();
		act.setAction(eAction.TableState);
		FlamingoGame.SendMessageToClient(act);

	}

	public void HandleGameState(GamePlay gp) {

		// Coming Soon....!
	}

}
