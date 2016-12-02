package doodle.tray;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import doodle.window.Doodle;

public class DoodleTray extends TrayIcon {

    public DoodleTray(final Doodle doodle, final Image startImage, final Image stopImage) {
        super(startImage);

        this.setToolTip("Start Doodle!");

        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent event) {
                String string = event.getActionCommand();

                if("Exit".equals(string)) {
                	System.exit(0);
                }
            }
        });

        PopupMenu menu = new PopupMenu();
        menu.add(exit);

        this.setPopupMenu(menu);

        Runnable hideDoodle = new Runnable() {
        	@Override
        	public void run() {
        		try {
        			Thread.sleep(50);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}

        		doodle.setVisible(false);
                DoodleTray.this.setImage(startImage);
                DoodleTray.this.setToolTip("Start Doodle!");
        	}
        };

        doodle.setStopActionMenuListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
                doodle.clearDoodles();

                SwingUtilities.invokeLater(hideDoodle);
            }

        });

        doodle.addKeyListener(new KeyListener() {

            @Override
			public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    doodle.clearDoodles();

                    SwingUtilities.invokeLater(hideDoodle);
                }
            }

            @Override
			public void keyReleased(KeyEvent event) {
            }

            @Override
			public void keyTyped(KeyEvent event) {
            }

        });

        this.addMouseListener(new MouseListener() {
            @Override
			public void mouseClicked(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    if (doodle.isVisible()) {
                        doodle.clearDoodles();

                        SwingUtilities.invokeLater(hideDoodle);
                    } else {
                        doodle.setVisible(true);

                        DoodleTray.this.setImage(stopImage);
                        DoodleTray.this.setToolTip("Stop Doodle!");
                    }
                }
            }

            @Override
			public void mouseEntered(MouseEvent event) {
            }

            @Override
			public void mouseExited(MouseEvent event) {
            }

            @Override
			public void mousePressed(MouseEvent event) {
            }

            @Override
			public void mouseReleased(MouseEvent event) {
            }

        });
    }
}

