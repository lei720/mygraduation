package edu.cuit.mainFrame;

import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.sun.jndi.toolkit.url.Uri;
/**
 * 文件树渲染器
 *
 * @author monitor
 * Created on 2011-2-28, 0:26:57
 */
public class TreeRenderer extends DefaultTreeCellRenderer {
    public TreeRenderer() {
    }
    @Override
    public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,
            boolean expanded,boolean leaf,int row,boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        if(leaf){
        	//DefaultMutableTreeNode chiNode=(DefaultMutableTreeNode) node.getChildAt(3);
        URL	 url2 = getClass().getResource("/edu/cuit/buttonIcons/provider.png");
        	if (node.getUserObject().toString()=="货品管理") {
        		 url2 = getClass().getResource("/edu/cuit/buttonIcons/ware.png");
    			ImageIcon leafIcon2 = new ImageIcon(url2);
        		setIcon(leafIcon2);
			} else {
				ImageIcon leafIcon2 = new ImageIcon(url2);
        		setIcon(leafIcon2);
			}
        }
        if (node.isRoot()) {
        	URL url2 = getClass().getResource("/edu/cuit/buttonIcons/folder.png");
 			ImageIcon leafIcon2 = new ImageIcon(url2);
     		setIcon(leafIcon2);
		}
        return this;
    }
/*    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TreeRenderer.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }*/
}