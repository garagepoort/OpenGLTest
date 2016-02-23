package font;

import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class FontManager {
	
	public static HashMap<Character, Texture> fonts = new HashMap<Character, Texture>();
	
	/**
	 * Load all the textures for all the fonts and put the texture in a hasmap with as key the character and value the texture.
	 */
	public static void load(){
		
		try {
			Texture t = null;
			
			//SPACE
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/space.png"));
			fonts.put(' ', t);
			
			//POINT
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/point.png"));
			fonts.put('.', t);
			
			//0
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/zero.png"));
			fonts.put('0', t);
			
			//1
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/one.png"));
			fonts.put('1', t);
			
			//2
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/two.png"));
			fonts.put('2', t);
			
			//3
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/three.png"));
			fonts.put('3', t);
			
			//4
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/four.png"));
			fonts.put('4', t);
			
			//5
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/five.png"));
			fonts.put('5', t);
			
			//6
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/six.png"));
			fonts.put('6', t);
			
			//7
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/seven.png"));
			fonts.put('7', t);
			
			//8
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/eight.png"));
			fonts.put('8', t);
			
			//9
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/nine.png"));
			fonts.put('9', t);
			
			//a
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/a.png"));
			fonts.put('a', t);
			
			//b
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/b.png"));
			fonts.put('b', t);
			
			//c
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/c.png"));
			fonts.put('c', t);
			
			//d
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/d.png"));
			fonts.put('d', t);
			
			//e
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/e.png"));
			fonts.put('e', t);
			
			//f
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/f.png"));
			fonts.put('f', t);
			
			//g
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/g.png"));
			fonts.put('g', t);
			
			//h
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/h.png"));
			fonts.put('h', t);
			
			//i
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/i.png"));
			fonts.put('i', t);
			
			//j
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/j.png"));
			fonts.put('j', t);
			
			//k
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/k.png"));
			fonts.put('k', t);
			
			//l
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/l.png"));
			fonts.put('l', t);
			
			//m 
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/m.png"));
			fonts.put('m', t);
			
			//n
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/n.png"));
			fonts.put('n', t);
			
			//o
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/o.png"));
			fonts.put('o', t);
			
			//p
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/p.png"));
			fonts.put('p', t);
			
			//q
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/q.png"));
			fonts.put('q', t);
			
			//r
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/r.png"));
			fonts.put('r', t);
			
			//s
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/s.png"));
			fonts.put('s', t);
			
			//t
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/t.png"));
			fonts.put('t', t);
			
			//u
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/u.png"));
			fonts.put('u', t);
			
			//v
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/v.png"));
			fonts.put('v', t);
			
			//w
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/w.png"));
			fonts.put('w', t);
			
			//x
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/x.png"));
			fonts.put('x', t);
			
			//y
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/y.png"));
			fonts.put('y', t);
			
			//z
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/z.png"));
			fonts.put('z', t);
			
			
			
			
			//A
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/aUC.png"));
			fonts.put('A', t);
			
			//b
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/bUC.png"));
			fonts.put('B', t);
			
			//c
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/cUC.png"));
			fonts.put('C', t);
			
			//d
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/dUC.png"));
			fonts.put('D', t);
			
			//e
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/eUC.png"));
			fonts.put('E', t);
			
			//f
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/fUC.png"));
			fonts.put('F', t);
			
			//g
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/gUC.png"));
			fonts.put('G', t);
			
			//h
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/hUC.png"));
			fonts.put('H', t);
			
			//i
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/iUC.png"));
			fonts.put('I', t);
			
			//j
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/jUC.png"));
			fonts.put('J', t);
			
			//k
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/kUC.png"));
			fonts.put('K', t);
			
			//l
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/lUC.png"));
			fonts.put('L', t);
			
			//m hiertussen
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/mUC.png"));
			fonts.put('M', t);
			
			//n
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/nUC.png"));
			fonts.put('N', t);
			
			//o
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/oUC.png"));
			fonts.put('O', t);
			
			//p
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/pUC.png"));
			fonts.put('P', t);
			
			//q
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/qUC.png"));
			fonts.put('Q', t);
			
			//r
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/rUC.png"));
			fonts.put('R', t);
			
			//s
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/sUC.png"));
			fonts.put('S', t);
			
			//t
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/tUC.png"));
			fonts.put('T', t);
			
			//u
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/uUC.png"));
			fonts.put('U', t);
			
			//v
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/vUC.png"));
			fonts.put('V', t);
			
			//w
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/wUC.png"));
			fonts.put('W', t);
			
			//x
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/xUC.png"));
			fonts.put('X', t);
			
			//y
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/yUC.png"));
			fonts.put('Y', t);
			
			//z
			t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/fonts/zUC.png"));
			fonts.put('Z', t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
