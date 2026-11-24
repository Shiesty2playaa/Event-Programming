# Limitations of java.util.Observer and java.util.Observable

## Limitation 1: Single Inheritance
* **Why it's a problem: that observable is a class and not an interface** `Observable` is a **class**, not an interface. Because Java does not support multiple class inheritance, if your custom class needs to extend another class (e.g., a GUI component like `JFrame`), it cannot extend `Observable`.

## Limitation 2: Cannot Change the Notification Order
* **Why it's a problem:In what order are observers notified? Can you control this?** Observers are notified in an unspecified, internal order managed by the JDK. You cannot customize or prioritize which observer gets notified first.

## Limitation 3: setChanged() is Confusing and Error-Prone
* **Why it's a problem:Why must you call setChanged() before notifyObservers()?** You must manually call `setChanged()` before calling `notifyObservers()`. If you forget, notifications are silently dropped and observers won't update, with no error thrown.

## Limitation 4: Pull Model by Default
* **Why it's a problem:How does the observer get the data? Is this efficient?** Observers must call getter methods on the subject to pull data. This creates tighter coupling and requires extra method calls compared to the push model.

## Limitation 5: Deprecated Since Java 9
* **Why it's a problem:Why did Java deprecate these classes?** The API is not thread-safe, lacks flexibility, and has been officially deprecated since Java 9[cite: 15] in favor of cleaner modern patterns and reactive frameworks.