package be.davidcorp.view.ui.nifty;

import static be.davidcorp.view.game.GameLoop.nifty;

import java.util.List;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.scrollpanel.builder.ScrollPanelBuilder;

public class PickUpItemPopUp {

	private static final String SCROLLPANEL_STYLE = "nifty-scrollpanel";
	private static final String PANEL_STYLE = "nifty-panel-no-shadow";
	private static final int SCROLLPANEL_WIDTH = 450;
	private static final int SCROLLPANEL_HEIGHT = 300;

	public static void createPopUp(final List<ItemDTO> itemDTOs) {
		new PopupBuilder("popupItem") {
			{
				childLayout(ChildLayoutType.Center);
				control(new ScrollPanelBuilder("scrollPanelId") {
					{

						width(SCROLLPANEL_WIDTH + "px");
						height(SCROLLPANEL_HEIGHT + "px");
						style(PANEL_STYLE);
						set("horizontal","false");
						padding("0px");
						margin("0px");
						panel(new PanelBuilder() {
							{
								width(SCROLLPANEL_WIDTH + "px");
								int height = calculateHeight(itemDTOs.size());
								height(height +"px");
								childLayoutVertical();
								style(PANEL_STYLE);
								padding("0px");
								margin("0px");
								for (final ItemDTO itemDTO : itemDTOs) {
									panel(new PanelBuilder() {
										{
											padding("0px");
											margin("0px");
											style(PANEL_STYLE);
											width("400px");
											height("100px");
											visibleToMouse(true);
											align(Align.Center);
											name(itemDTO.getId() + "-itemPanel");
											id(itemDTO.getId() + "-itemPanel");
											interactOnClickRepeat("takeItem(" + itemDTO.getId() + ")");
											childLayoutHorizontal();
											image(itemImage(itemDTO));
											text(itemText(itemDTO));
										}

									});
								}
							}
						});
					}
				});
				// add the actual popup content here (panels, images, controls)
			}

		}.registerPopup(nifty);

	}

	private static TextBuilder itemText(final ItemDTO itemDTO) {
		return new TextBuilder() {
			{
				font("aurulent-sans-16.fnt");
				color("#f00f");
				text(itemDTO.getInfoTekst());
				alignCenter();
				valignCenter();
			}
		};
	}

	private static ImageBuilder itemImage(final ItemDTO itemDTO) {
		return new ImageBuilder() {
			{
				filename(itemDTO.getTexture());
				alignCenter();
				valignCenter();
			}
		};
	}
	
	private static int calculateHeight(int size) {
		int height = 150 * size;
		if(height <= SCROLLPANEL_HEIGHT){
			height = SCROLLPANEL_HEIGHT;
		}
		return height;
	}
}
