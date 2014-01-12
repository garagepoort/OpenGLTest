package be.davidcorp.view.ui.nifty;

import static be.davidcorp.view.game.GameLoop.nifty;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.builder.StyleBuilder.ChildLayoutType;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.scrollpanel.builder.ScrollPanelBuilder;

public class PickUpItemPopUp {

	public static void createPopUp(final ItemDTO itemDTO) {
		new PopupBuilder("popupItem") {
			{
				childLayout(ChildLayoutType.Center);
				control(new ScrollPanelBuilder("scrollPanelId") {
					{
						
						width("400");
						height("300");
						
						panel(new PanelBuilder() {
							{
								width("400px");
								height("100px");
								childLayoutHorizontal();
								style("nifty-panel");
								image(itemImage(itemDTO));
								text(itemText(itemDTO));
								control(button());

							}

						});
					}
				});
				// add the actual popup content here (panels, images, controls)
			}

		}.registerPopup(nifty);

	}

	private static ControlBuilder button() {
		return new ControlBuilder("takeItem") {
			{
				name("button");
				id("takeButton");
				parameter("label", "TAKE");
				visibleToMouse(true);
				alignCenter();
				valignCenter();
			}
		};
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
}
