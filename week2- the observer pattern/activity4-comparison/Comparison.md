# Comparison of Event Notification Mechanisms in Java

| Feature | Custom Observer | java.util.Observer | PropertyChangeListener | JavaFX Bindings |
| :--- | :--- | :--- | :--- | :--- |
| **Push or Pull?** | Both possible | Pull | Push (carries old + new) | Push |
| **Is it deprecated?** | No | Yes (Java 9+) | No | No |
| **Requires inheritance?** | No (interface) | Yes (class) | No | No |
| **Thread-safe?** | Depends on you | No | Yes (with right setup) | Yes |
| **Auto-generates boilerplate?** | No | No | Yes (via PropertyChangeSupport) | Yes |
| **Best for...** | Custom systems | Legacy code | JavaBeans / Swing | JavaFX apps |
| **Can have multiple listeners?** | Yes | Yes | Yes | Yes |
| **Recommended today?** | Yes | Only for legacy | Yes | Yes |

## Which One Should You Use?
* For a **plain Java project with custom needs** $\rightarrow$ Custom Observer
* For a **JavaFX application** $\rightarrow$ JavaFX Bindings
* For **legacy code using JavaBeans** $\rightarrow$ PropertyChangeListener
* **Never** $\rightarrow$ java.util.Observer