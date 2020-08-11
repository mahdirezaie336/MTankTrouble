package client.menu.mcomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * This is a panel which has many buttons on it. When the user clicks on any buttons
 * another panel will be shown in center of this panel. It's like a TabbedPane so i
 * named it ButtonTabbedPane.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class ButtonTabbedPane extends JPanel
{
	private static final long serialVersionUID = 1680257111147111796L;
	private ArrayList<JPanel> panels;
	private ArrayList<GlassButton> buttons;
	private JPanel buttonsPanel;
	private JScrollPane viewPort;															// The scroll pane
	
	/**
	 * Gets the number of tabs and the alignment of the panel and button bar width.
	 * @param size Number of buttons
	 * @param type Alignment of the panel
	 * @param preferredWidth Button bar width
	 */
	public ButtonTabbedPane(int size, String type, int preferredWidth)
	{
		super();
		setLayout(new BorderLayout(10,10));
		setOpaque(false);
		panels = new ArrayList<>();
		buttons = new ArrayList<GlassButton>();
		buttonsPanel = new JPanel(new GridLayout(size + 2,1,5,5));
		buttonsPanel.setBackground(new Color(0,0,0,150));
		buttonsPanel.setPreferredSize(new Dimension(preferredWidth,1));
		viewPort = new JScrollPane();
		viewPort.setOpaque(false);
		viewPort.setBorder(BorderFactory.createEmptyBorder());
		viewPort.getViewport().setOpaque(false);
		
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(buttonsPanel,type);
		add(viewPort,BorderLayout.CENTER);
	}
	
	/**
	 * Adds a new tab.
	 * @param name The tab name which will be diplayed on its related button
	 * @param panel The panel of this tab
	 * @return this object
	 */
	public ButtonTabbedPane addTab(String name, JPanel panel)
	{
		GlassButton btn = new GlassButton(name);
		btn.addActionListener(new ActionListener()
		{
			int i = panels.size();
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JPanel current = null;
				try
				{
					current = (JPanel) viewPort.getViewport().getView();
				} catch(Exception w) {
				}
				
				if(current == panels.get(i))
				{
					viewPort.setViewportView(null);
					return;
				}
				viewPort.setViewportView(panels.get(i));
				getThis().updateUI();
			}
		});
		buttons.add(btn);
		panels.add(panel);
		update();
		return this;
	}
	
	/**
	 * Adds a new single button without any related panel
	 * @param btn The button to add.
	 * @return this object
	 */
	public ButtonTabbedPane addSingleButton(GlassButton btn)
	{
		buttons.add(btn);
		update();
		return this;
	}
	
	/**
	 * Updates the view of this object.
	 */
	public void update()
	{
		buttonsPanel.removeAll();
		JPanel temp = new JPanel();
		temp.setOpaque(false);
		buttonsPanel.add(temp);
		for(GlassButton btn : buttons)
			buttonsPanel.add(btn);
		updateUI();
	}

	/**
	 * Gets all button saved in this object.
	 * @return The button in array of GlassButtons
	 */
	public GlassButton[] getButtons()
	{
		return buttons.toArray(new GlassButton[buttons.size()]);
	}
	
	/**
	 * returns this object. This method is used in event handlers.
	 * @return this object
	 */
	private ButtonTabbedPane getThis()
	{
		return this;
	}
}
