package GameCreator.panels;

import static be.davidcorp.applicationLayer.dto.mapper.ConstructionType.DESK;
import static be.davidcorp.applicationLayer.dto.mapper.ConstructionType.WALL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import GameCreator.createframes.CreateEnemyDialog;
import GameCreator.createframes.CreateItemDialog;
import GameCreator.createframes.CreatePropDialog;
import GameCreator.createframes.CreateWallDialog;
import be.davidcorp.applicationLayer.dto.mapper.ConstructionType;
import be.davidcorp.applicationLayer.dto.mapper.EnemyType;
import be.davidcorp.applicationLayer.dto.mapper.ItemType;

@SuppressWarnings("serial")
public class SpriteTreePanel extends JPanel implements TreeSelectionListener {

	private JTree spriteTree;

	public SpriteTreePanel() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Sprites");
		createNodes(top);
		spriteTree = new JTree(top);
		spriteTree.setName("spriteTree");
		spriteTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		spriteTree.addTreeSelectionListener(this);
		spriteTree.addMouseListener(new SpriteTreeMouseAdapter());
		JScrollPane treeView = new JScrollPane(spriteTree);
		add(treeView);
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
	}

	private void createNodes(DefaultMutableTreeNode top) {
		addCategory("Enemies", EnemyType.values(), top);
		addCategory("ConstructionSprites", ConstructionType.values(), top);
		addCategory("Items", ItemType.values(), top);
	}

	@SuppressWarnings("rawtypes")
	private DefaultMutableTreeNode addCategory(String categoryName, Enum[] values, DefaultMutableTreeNode top) {
		DefaultMutableTreeNode category = new DefaultMutableTreeNode(categoryName);
		top.add(category);

		DefaultMutableTreeNode sprite;

		for (Enum type : values) {
			sprite = new DefaultMutableTreeNode(type);
			category.add(sprite);
		}

		return category;
	}

	private class SpriteTreeMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int selRow = spriteTree.getRowForLocation(e.getX(), e.getY());
			TreePath selPath = spriteTree.getPathForLocation(e.getX(), e.getY());
			if (selRow != -1) {
				if (e.getClickCount() == 1) {
					// mySingleClick(selRow, selPath);
				} else if (e.getClickCount() == 2) {
					// myDoubleClick(selRow, selPath);
					DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) selPath.getLastPathComponent();
					if (lastPathComponent.isLeaf()) {
						openCorrespondendingDialog(lastPathComponent);
					}
				}
			}
		}

		private void openCorrespondendingDialog(DefaultMutableTreeNode lastPathComponent) {
			Object value = lastPathComponent.getUserObject();

			if (value instanceof ConstructionType) {
				ConstructionType constructionType = (ConstructionType) value;
				if (constructionType == WALL) {
//					openCreateWallDialog();
					new CreateWallDialog();
				}
				if (constructionType == DESK) {
//					openCreateWallDialog();
					new CreatePropDialog();
				}
			}
			if (value instanceof EnemyType) {
				EnemyType enemyType = (EnemyType) value;
//				openEnemyDialog(enemyType);
				new CreateEnemyDialog(enemyType);
			}
			if (value instanceof ItemType) {
				ItemType itemType = (ItemType) value;
//				openItemDialog(itemType);
				new CreateItemDialog(itemType);
			}
		}
	}
}
