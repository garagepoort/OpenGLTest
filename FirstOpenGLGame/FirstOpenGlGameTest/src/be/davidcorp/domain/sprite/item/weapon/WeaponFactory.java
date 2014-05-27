package be.davidcorp.domain.sprite.item.weapon;

import be.davidcorp.domain.sprite.SpriteType;

public class WeaponFactory {

	public static RangedWeapon createPistol(float x, float y){
		RangedWeaponBuilder rangedWeaponBuilder = new RangedWeaponBuilder();
		RangedWeapon rangedWeapon = rangedWeaponBuilder
				.withMaxAmountOfAmmo(100)
				.withAttackCooldownTime(40)
				.withInfoTekst("This is a pistol")
				.withSpriteType(SpriteType.PISTOL)
				.withX(x)
				.withY(y)
				.withWidth(32)
				.withHeight(32)
				.build();
		return rangedWeapon;
	}
}
