package client.menu.mcomponents;

import java.util.ArrayList;

import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * This class is used in JSpinners model to set maximum and minimum value for them.
 * 
 * @author Mahdi Rezaie 9728040
 *
 */
public class PeriodicSpinnerModel implements SpinnerModel
{
	private int max;
	private int min;
	private int current;
	private ArrayList<ChangeListener> changeListeners;
	
	public PeriodicSpinnerModel(int min, int max, int current)
	{
		this.max = max;
		this.min = min;
		this.current = current;
		changeListeners = new ArrayList<>();
	}

	@Override
	public void addChangeListener(ChangeListener l)
	{
		changeListeners.add(l);
	}

	@Override
	public Object getNextValue()
	{
		return (Integer) (current+1>max?min:current+1);
	}

	@Override
	public Object getPreviousValue()
	{
		return (Integer) (current-1<min?max:current-1);
	}

	@Override
	public Object getValue()
	{
		return (Integer) current;
	}

	@Override
	public void removeChangeListener(ChangeListener l)
	{
		changeListeners.remove(l);
	}

	@Override
	public void setValue(Object value)
	{
		current = (Integer) value;
		if(changeListeners.size() != 0)
		{
			ChangeEvent event = new ChangeEvent(this);
			for(ChangeListener l : changeListeners)
				l.stateChanged(event);
		}
	}

}
