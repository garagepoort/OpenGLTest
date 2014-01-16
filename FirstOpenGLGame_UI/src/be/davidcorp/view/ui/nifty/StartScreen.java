package be.davidcorp.view.ui.nifty;

import static be.davidcorp.view.game.GameLoop.nifty;
import be.davidcorp.controllers.StartScreenController;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.StyleBuilder.ChildLayoutType;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;

public class StartScreen {

	public static final String KEY = "START";

	public static void createStartScreen() {
		nifty.addScreen(KEY, new ScreenBuilder(KEY) {
			{
				controller(new StartScreenController());

				layer(new LayerBuilder("background") {
					{
						childLayoutCenter();
						backgroundColor("#056f");
					}
				});

				layer(new LayerBuilder("foreground") {
					{
						childLayoutCenter();
						backgroundColor("#4850");
						panel(new PanelBuilder(){{
							height("50%");
							width("50%");
							backgroundColor("#480f");
							childLayout(ChildLayoutType.Center);
							control(new ButtonBuilder("Button_ID", "Start game"){{
			                    alignCenter();
			                    valignCenter();
			                    height("5%");
			                    width("15%");
			                    interactOnClick("startGame()");
			                }});
						}});
					}
				});
			}
		}.build(nifty));
	}
}
