package be.davidcorp.view.ui.nifty;

import static be.davidcorp.view.game.GameLoop.nifty;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.controllers.StartScreenController;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.window.builder.WindowBuilder;

public class GamePanelScreen {

	private static final String STARTGAME_BUTTON = "STARTGAME_BUTTON";
	public static final String KEY = "GAMEPANEL";
	private static PlayerFacade playerFacade = new PlayerFacade();

	public static void createStartScreen() {
		nifty.addScreen(KEY, new ScreenBuilder(KEY) {
			{
				controller(new StartScreenController());

				layer(new LayerBuilder("background") {
					{
						childLayoutCenter();
//						backgroundColor("#056f");
					}
				});

				layer(new LayerBuilder("foreground") {
					{
						childLayoutCenter();
						control(new WindowBuilder("myWindow", "Title of Window") {{
							  closeable(false); // you can't close this window
							  width("320px"); // windows will need a size
							  height("200px");
							  text(new TextBuilder() {{
							    text("Hello Window");
							    style("base-font");
							    color("#eeef");
							    valignCenter();
							    width("100%");
							  }});
							}});
//						backgroundColor("#4850");
						panel(new PanelBuilder(){{
							height("75%");
							width("30%");
							backgroundColor("#480a");
							childLayout(ChildLayoutType.Horizontal);
							align(Align.Right);
							for(final ItemDTO itemDTO : playerFacade.getInventoryItems()){
								image(new ImageBuilder(){{
									filename(itemDTO.getTexture());
//									alignCenter();
//									valignCenter();
								}});
							}
						}});
					}
				});
			}
		}.build(nifty));
	}
}
