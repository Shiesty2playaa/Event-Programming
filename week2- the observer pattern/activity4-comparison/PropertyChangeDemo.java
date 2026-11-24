import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// === DEMO 3: PropertyChangeListener (from java.beans) ===
// Common in JavaBeans and Swing. Uses a helper called 
// PropertyChangeSupport to manage listeners automatically.
public class PropertyChangeDemo {
    static class Person {
        private final PropertyChangeSupport support = 
            new PropertyChangeSupport(this);
        private String name;

        public void addPropertyChangeListener(PropertyChangeListener l) {
            support.addPropertyChangeListener(l); // Adds a listener
        }

        public void setName(String newName) {
            String oldName = this.name;
            this.name = newName;
            // Fires a PropertyChangeEvent to all listeners with old and new values
            support.firePropertyChange("name", oldName, newName);
        }
    }

    public static void main(String[] args) {
        Person person = new Person();

        // Add Listener A (tracks old and new values)
        person.addPropertyChangeListener(evt ->
            System.out.println("Listener A: " + evt.getPropertyName() 
                + " changed from " + evt.getOldValue() 
                + " to " + evt.getNewValue()));

        // Add Listener B (notices current value)
        person.addPropertyChangeListener(evt ->
            System.out.println("Listener B: noticed name is now " 
                + evt.getNewValue()));

        // Trigger the changes
        person.setName("Alice");
        person.setName("Bob");
    }
}