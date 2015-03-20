# Applet Runner

This is a simple application to enable running applets from the local PC as Java Applications if launching fails in the browser because of the new security restrictions.

## Usage

```
java -cp .:<the JAR containing the applet> AppletRunner <full qualified name of the Applet's Main class>
```

As you can see, you should add the JAR containing the applet to the classpath along with the current folder where you run the app.

## License

MIT as for the whole repository.

The application is provided as-is, feel free to extend it to match your needs -- or you can provide feedback and I'll try to extend this app to be more usable.
