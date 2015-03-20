/**
 * Main class to start the applet runner application.
 *
 * @author GHajba
 *
 */
public class AppletRunner {

    private static final int DEFAULT_HEIGHT = 600;
    private static final int DEFAULT_WIDTH = 800;

    /**
     * Argument parsing is kept simple.
     *
     * The class_name parameter has to include the package too because the application will instantiate from this the applet.
     *
     * The default width is 800 if not provided.
     *
     * The default height is 600 if not provided.
     *
     * @param args
     *            class_name [width height]
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("At least the package and class name has to be provided as parameter.");
            System.exit(-2);
        }
        int width = 0;
        int height = 0;
        if (args.length >= 3) {
            width = Integer.valueOf(args[1]);
            height = Integer.valueOf(args[2]);
        } else {
            width = DEFAULT_WIDTH;
            height = DEFAULT_HEIGHT;
        }

        final AppletHolder frame = new AppletHolder(args[0], width, height);
        frame.setBounds(10, 10, width, height);
        frame.setVisible(true);
    }
}
