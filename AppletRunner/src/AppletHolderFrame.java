import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.AWTEvent;
import java.awt.Event;
import java.awt.Frame;
import java.net.URL;

/**
 * Frame which holds the applet to display.
 *
 * @author GHajba
 */
public class AppletHolderFrame extends Frame implements AppletStub {

    private static final long serialVersionUID = 343169165587847369L;

    private final String text = "";

    /**
     * Initializes the applet with the given parameters.
     *
     * @param appletName
     *            has to be provided, defines the package and class name from which the applet is instantiated.
     * @param width
     *            the width of the applet
     * @param height
     *            the height of the applet
     */
    public AppletHolderFrame(String appletName, int width, int height) {
        super(appletName);
        try {
            final Applet applet = (Applet) Class.forName(appletName).newInstance();
            init(applet, width, height);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void init(Applet applet, int width, int height) {
        applet.setStub(this);
        add("Center", applet);
        applet.init();
        appletResize(width, height);
        setVisible(true);
        applet.start();
    }

    @Override
    public URL getDocumentBase() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public URL getCodeBase() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getParameter(String name) {
        if ("text".equals(name)) {
            return this.text;
        }
        return null;
    }

    @Override
    public AppletContext getAppletContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void appletResize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void processEvent(AWTEvent e) {
        if (e.getID() == Event.WINDOW_DESTROY) {
            System.exit(0);
        }
        super.processEvent(e);
    }
}