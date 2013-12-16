package be.davidcorp.applicationLayer.dto.mapper.gamefield;

import be.davidcorp.applicationLayer.dto.GamefieldDTO;
import be.davidcorp.domain.game.Gamefield;

public class GamefieldToGamefieldDTOMapper {

	public GamefieldDTO mapToDTO(Gamefield gamefield){
		GamefieldDTO gamefieldDTO = new GamefieldDTO();
		gamefieldDTO.setId(gamefield.getID());
		gamefieldDTO.setName(gamefield.getName());
		gamefieldDTO.setX(gamefield.getX());
		gamefieldDTO.setY(gamefield.getY());
		gamefieldDTO.setWidth(gamefield.getWidth());
		gamefieldDTO.setHeight(gamefield.getHeight());
		return gamefieldDTO;
	}
}
