package be.davidcorp.domain;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.item.weapon.RocketLauncher;
import be.davidcorp.domain.sprite.organic.player.Player;
import be.davidcorp.texture.TextureBunch;


public class TextureBunchFactory {

	public static TextureBunch createTextureBunch(Sprite s){
		TextureBunch bunch = new TextureBunch();

		if(s instanceof Player){
			Player p = (Player) s;
			bunch = getDefaultTextureBunch(p);
//			if(p.getEquippedWeapon() instanceof Ax){
//				bunch
//					.withDefaultTexture("resources/images/player/playerWeapon/playerstandingWithAx.png")
//					.withTextureAtSecond("resources/images/player/playerWeapon/rightFootWithAx.png", 1)
//					.withTextureAtSecond("resources/images/player/playerWeapon/leftFootWithAx.png", 20)
//					.withLastTextureTime(40);
//			}
			if(p.getEquippedWeapon()==null || p.getEquippedWeapon() instanceof RocketLauncher){
				bunch
					.withDefaultTexture("resources/images/player/playerstanding.png")
					.withTextureAtSecond("resources/images/player/rightFoot.png", 1)
					.withTextureAtSecond("resources/images/player/leftFoot.png", 20)
					.withLastTextureTime(40);
			}
		}
		return bunch;
	}

	public static TextureBunch getDefaultTextureBunch(Sprite s){
		TextureBunch bunch = new TextureBunch();
		if(s instanceof Player){
			bunch
			.withDefaultTexture("resources/images/player/playerstanding.png")
			.withTextureAtSecond("resources/images/player/rightFoot.png", 1)
			.withTextureAtSecond("resources/images/player/leftFoot.png", 20)
			.withLastTextureTime(40);
		}
		return bunch;
	}
}
