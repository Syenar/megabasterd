package com.tonikelope.megabasterd;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import javax.swing.*;

/** Run with -Djava.awt.headless=true; no application database or network. */
public final class SettingsShortcutSmoke {
    private static JTabbedPane tabs;
    private static AtomicInteger opened = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtilities.invokeAndWait(() -> {
            tabs = new JTabbedPane();
            tabs.setFont(new Font("Dialog", Font.BOLD, 24));
            tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            for (String label : new String[]{"Downloads", "Uploads", "START", "COPY LINKS", "DEBUG"}) {
                tabs.addTab(label, new JPanel());
            }
            tabs.setSelectedIndex(1);
            SettingsShortcutButton.install(tabs, "Settings", 1f, opened::incrementAndGet);
            check(tabs.getTabCount() == 6, "one shortcut after DEBUG");
            JButton button = (JButton) tabs.getTabComponentAt(5);
            check("Settings".equals(button.getToolTipText()), "tooltip");
            check("Settings".equals(button.getAccessibleContext().getAccessibleName()), "accessible name");
            check(button.isFocusable(), "keyboard focus");
            button.doClick();
            check(opened.get() == 1 && tabs.getSelectedIndex() == 1, "click keeps Uploads selected");
            tabs.setSelectedIndex(4);
            tabs.setSelectedIndex(5);
            check(tabs.getSelectedIndex() == 4, "keyboard navigation restores DEBUG");
        });
        SwingUtilities.invokeAndWait(() -> {
            check(opened.get() == 2, "keyboard navigation opens Settings once");
            for (float zoom : new float[]{0.8f, 1f, 1.5f, 2f}) {
                SettingsShortcutButton button = new SettingsShortcutButton("Settings", zoom, () -> {});
                check(button.getPreferredSize().width >= 40, "minimum target size");
                button.setSize(button.getPreferredSize());
                BufferedImage image = new BufferedImage(button.getWidth(), button.getHeight(), BufferedImage.TYPE_INT_ARGB);
                button.paint(image.getGraphics());
            }
            tabs.setSize(950, 260);
            layoutTree(tabs);
            BufferedImage image = new BufferedImage(950, 260, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.setColor(Color.WHITE); g.fillRect(0, 0, 950, 260);
            tabs.printAll(g); g.dispose();
            try { ImageIO.write(image, "png", new File(args.length > 0 ? args[0] : "settings-cog-preview.png")); }
            catch (Exception e) { throw new RuntimeException(e); }
            tabs.setSize(420, 260); tabs.doLayout();
            check(tabs.getTabLayoutPolicy() == JTabbedPane.SCROLL_TAB_LAYOUT, "narrow window remains scrollable");
        });
        System.out.println("PASS: click, keyboard navigation, selection preservation, accessibility, zoom, narrow layout");
    }

    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }

    private static void layoutTree(Container parent) {
        parent.doLayout();
        for (Component child : parent.getComponents()) {
            if (child instanceof Container) layoutTree((Container) child);
        }
    }
}
