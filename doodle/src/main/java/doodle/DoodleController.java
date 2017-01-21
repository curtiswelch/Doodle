package doodle;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.SystemTray;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import doodle.ui.tray.DoodleTray;
import doodle.ui.window.DoodleView;

public class DoodleController {
	private DoodleView view;
	private DoodleTray tray;

	private BufferedImage startImage;
	private BufferedImage stopImage;

	public DoodleController() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            throw new IllegalStateException("Transparent Windows aren't supported :(");
        }

    	this.startImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle.png"));
		this.stopImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("doodle/images/doodle_stop.png"));

		this.view = new DoodleView(this);
		this.tray = new DoodleTray(this, this.startImage);

		SystemTray tray = SystemTray.getSystemTray();
		tray.add(this.tray);
	}

	public void clearDoodles() {
		this.view.clearDoodles();
	}

	public void undo() {
		this.view.undo();
	}

	public void setDoodleColor(DoodleColor color) {
		this.view.setDoodleColor(color);
	}

	public void toggleView() {
		if(this.view.isVisible()) {
			this.hideView();
		} else {
			this.showView();
		}
	}

	private void showView() {
		this.view.setVisible(true);
		this.tray.setImage(stopImage);
        this.tray.setToolTip(Strings.STOP_DOODLE);
	}

	public void hideView() {
		this.view.clearDoodles();

		SwingUtilities.invokeLater(new DoodleViewHider(this.view, this.tray));
	}

	class DoodleViewHider implements Runnable {
		private DoodleView view;
		private DoodleTray tray;

		DoodleViewHider(DoodleView view, DoodleTray tray) {
			this.view = view;
			this.tray = tray;
		}

		@Override
		public void run() {
			this.view.setVisible(false);
			this.tray.setImage(startImage);
			this.tray.setToolTip(Strings.START_DOODLE);
		}
	}
}
