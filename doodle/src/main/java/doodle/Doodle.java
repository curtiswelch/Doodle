package doodle;

import java.awt.SystemTray;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import doodle.tray.DoodleTray;
import doodle.window.DoodleView;

public class Doodle {
	private DoodleView view;
	private DoodleTray tray;

	private BufferedImage startImage;
	private BufferedImage stopImage;

	public Doodle() throws Exception {
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

	public void showView() {
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

		public DoodleViewHider(DoodleView view, DoodleTray tray) {
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
