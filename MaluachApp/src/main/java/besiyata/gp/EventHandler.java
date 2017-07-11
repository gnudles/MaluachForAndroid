package besiyata.gp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by orr on 05/02/17.
 */


public class EventHandler {
    public interface Listener
    {
        void process( Object sender );
    }
    private List <Listener> listeners;
    public EventHandler(){
        listeners = new ArrayList<>();
    }
    public void addListener(Listener l)
    {
        listeners.add(l);
    }
    public void trigger( Object sender )
    {
        for (Listener l : listeners)
        {
            l.process( sender );
        }
    }

}