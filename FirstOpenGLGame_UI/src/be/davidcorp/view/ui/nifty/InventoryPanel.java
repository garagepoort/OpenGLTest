package be.davidcorp.view.ui.nifty;

import static be.davidcorp.view.game.GameLoop.nifty;

import java.util.List;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;

public class InventoryPanel {

	private static final String PANEL_STYLE = "nifty-panel-no-shadow";
	
	public static void createInventoryPanel(final List<ItemDTO> itemDTOs) {
//		nifty.getCurrentScreen();
//		new PanelBuilder() {
//			{
//				valign(VAlign.Center);
//				align(Align.Right);
//				width("200px");
//				height("500px");
//				childLayoutOverlay();
//				for (final ItemDTO itemDTO : itemDTOs) {
//					panel(new PanelBuilder() {
//						{
//							padding("0px");
//							margin("0px");
//							style(PANEL_STYLE);
//							width("32px");
//							height("32px");
//							visibleToMouse(true);
//							align(Align.Center);
//							name(itemDTO.getId() + "-imageInventoryPanel");
//							id(itemDTO.getId() + "-imageInventoryPanel");
//							childLayoutCenter();
//							image(itemImage(itemDTO));
//						}
//
//					});
//				}
//			}
//		}.build(nifty, nifty.getCurrentScreen(), nift);
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
