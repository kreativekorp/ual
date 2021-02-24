# Universal Application Library

Universal Application Library provides parts of the Java 9+ `java.awt.desktop` API for versions of Java as far back as 1.5.
It consists of four parts:

* A stub library for the `java.awt.desktop` package
* A stub library for the `com.apple.eawt` package
* A translation layer between the `java.awt.desktop` and `com.apple.eawt` APIs
* A universal application adapter that provides the lowest common denominator using whichever API is available

## Usage

Implement your application event handlers using the `java.awt.desktop` API (stubs are included) then use the `UniversalApplication` class to register them:

```java
import java.awt.desktop.AboutHandler;
import java.awt.desktop.OpenFilesHandler;
import java.awt.desktop.PreferencesHandler;
import java.awt.desktop.PrintFilesHandler;
import java.awt.desktop.QuitHandler;
import com.kreative.ual.UniversalApplication;

public class Foo {
  public static void main(String[] args) {
    AboutHandler aboutHandler = new AboutHandler() { ... };
    OpenFilesHandler openHandler = new OpenFilesHandler() { ... };
    PreferencesHandler prefsHandler = new PreferencesHandler() { ... };
    PrintFilesHandler printHandler = new PrintFilesHandler() { ... };
    QuitHandler quitHandler = new QuitHandler() { ... };
    new UniversalApplication()
      .setAboutHandler(aboutHandler)
      .setOpenFileHandler(openHandler)
      .setPreferencesHandler(prefsHandler)
      .setPrintFileHandler(printHandler)
      .setQuitHandler(quitHandler)
      .register();
  }
}
```

The `UniversalApplication` class accesses the underlying `java.awt.desktop` and `com.apple.eawt` APIs using reflection, to avoid compile and link errors if those APIs are not available. The downside is that it is limited to the five events (About, OpenFiles, Preferences, PrintFiles, and Quit) supported by all APIs. For more functionality, you can use the `java.awt.desktop` API with the translation classes in the `com.kreative.ual.eawt` package as a fallback, but you'll have to use reflection yourself to ensure compatibility with earlier versions of Java.

```java
try {
  Class<?> cls = Class.forName("java.awt.Desktop");
  Method get = cls.getMethod("getDesktop");
  Object obj = get.invoke(null);
  cls.getMethod("setAboutHandler", AboutHandler.class).invoke(obj, aboutHandler);
  cls.getMethod("setOpenURIHandler", OpenURIHandler.class).invoke(obj, openURIHandler);
  ...
} catch (Exception e) {}
try {
  Class<?> cls = Class.forName("com.kreative.ual.eawt.NewApplicationAdapter");
  Method get = cls.getMethod("getInstance");
  Object obj = get.invoke(null);
  cls.getMethod("setAboutHandler", AboutHandler.class).invoke(obj, aboutHandler);
  cls.getMethod("setOpenURIHandler", OpenURIHandler.class).invoke(obj, openURIHandler);
  ...
} catch (Exception e) {}
try {
  Class<?> cls = Class.forName("com.kreative.ual.eawt.OldApplicationAdapter");
  Method get = cls.getMethod("getInstance");
  Object obj = get.invoke(null);
  cls.getMethod("setAboutHandler", AboutHandler.class).invoke(obj, aboutHandler);
  // OldApplicationAdapter does not support setOpenURIHandler!
  ...
} catch (Exception e) {}
```
