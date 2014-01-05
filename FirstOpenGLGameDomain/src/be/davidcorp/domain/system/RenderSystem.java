package be.davidcorp.domain.system;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CURRENT_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;

import be.davidcorp.domain.components.PositionComponent;
import be.davidcorp.domain.components.RenderComponent;
import be.davidcorp.domain.components.RotationComponent;
import be.davidcorp.domain.entity.Sprite;

public class RenderSystem extends System {

	private static RenderSystem instance = new RenderSystem();

	private RenderSystem() {
	}

	public static RenderSystem getInstance() {
		return instance;
	}

	@Override
	public void executeSystem(Sprite sprite, float secondsMovedInGame) {
		PositionComponent position = sprite.getComponent(PositionComponent.class);
		RenderComponent renderComponent = sprite.getComponent(RenderComponent.class);
		RotationComponent rotationComponent = sprite.getComponent(RotationComponent.class);

		if (containsNecessaryComponents(position, renderComponent)) {
			glPushMatrix();
			{
				glSetup();

				Texture texture = renderComponent.texture;
				texture.bind();

				if (containsNecessaryComponents(rotationComponent)) {
					glTranslatef(position.x + texture.getTextureWidth() / 2, position.y + texture.getTextureHeight() / 2, 0);
					glRotatef(rotationComponent.rotationAngle, 0f, 0f, 1f);
					glTranslatef(-position.x - texture.getTextureWidth() / 2, -position.y - texture.getTextureHeight()/ 2, 0);
				}
				
				glBegin(GL_QUADS);
				{
					glTexCoord2f(0, 0);// top left
					glVertex2f(position.x, position.y + texture.getTextureHeight());
					glTexCoord2f(1, 0);// top right
					glVertex2f(position.x + texture.getTextureWidth(), position.y + texture.getTextureHeight());
					glTexCoord2f(1, 1);// bottom right
					glVertex2f(position.x + texture.getTextureWidth(), position.y);
					glTexCoord2f(0, 1);// bottom left
					glVertex2f(position.x, position.y);
				}
				glEnd();
				glTearDown();
			}
			glPopMatrix();
		}
	}

	private static void glTearDown() {
		glPopAttrib();
		glEnable(GL_TEXTURE_2D);
	}

	private static void glSetup() {
		glLoadIdentity();
		glDisable(GL_TEXTURE_2D);
		glPushAttrib(GL_CURRENT_BIT);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
}