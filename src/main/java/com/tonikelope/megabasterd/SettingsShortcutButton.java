package com.tonikelope.megabasterd;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/** Main-page Settings shortcut. Modified 2026-11-18; GPL-3.0. */
final class SettingsShortcutButton extends JButton {

    private final int iconSize;

    SettingsShortcutButton(String label, float zoom, Runnable openSettings) {
        iconSize = Math.max(20, Math.round(24 * zoom));
        int targetSize = Math.max(40, Math.round(40 * zoom));
        setPreferredSize(new Dimension(targetSize, targetSize));
        setToolTipText(label);
        getAccessibleContext().setAccessibleName(label);
        getAccessibleContext().setAccessibleDescription(label);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setRolloverEnabled(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addActionListener(e -> openSettings.run());
    }

    static void install(JTabbedPane tabs, String label, float zoom, Runnable openSettings) {
        SettingsShortcutButton button = new SettingsShortcutButton(label, zoom, openSettings);
        JPanel placeholder = new JPanel();
        tabs.addTab("", placeholder);
        int index = tabs.indexOfComponent(placeholder);
        tabs.setToolTipTextAt(index, label);
        tabs.setTabComponentAt(index, button);
        // Clicking the real button leaves the current page selected. Arrow-key
        // navigation to its tab performs the same action and restores that page.
        final int[] previous = {tabs.getSelectedIndex()};
        tabs.addChangeListener(e -> {
            if (tabs.getSelectedComponent() == placeholder) {
                tabs.setSelectedIndex(previous[0]);
                SwingUtilities.invokeLater(openSettings);
            } else if (tabs.getSelectedIndex() >= 0) {
                previous[0] = tabs.getSelectedIndex();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color foreground = getForeground();
            if (foreground == null) foreground = Color.DARK_GRAY;
            int inset = 2;
            if (getModel().isRollover() || getModel().isPressed()) {
                g.setColor(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(),
                        getModel().isPressed() ? 40 : 22));
                g.fillRoundRect(inset, inset, getWidth() - 2 * inset, getHeight() - 2 * inset, 12, 12);
            }
            if (isFocusOwner()) {
                Color focus = UIManager.getColor("Component.focusColor");
                g.setColor(focus != null ? focus : new Color(26, 115, 232));
                g.setStroke(new BasicStroke(2));
                g.drawRoundRect(inset, inset, getWidth() - 2 * inset - 1, getHeight() - 2 * inset - 1, 12, 12);
            }
            g.translate((getWidth() - iconSize) / 2.0, (getHeight() - iconSize) / 2.0);
            g.scale(iconSize / 24.0, iconSize / 24.0);
            Area gear = new Area(new Ellipse2D.Double(4, 4, 16, 16));
            for (int tooth = 0; tooth < 8; tooth++) {
                Shape shape = AffineTransform.getRotateInstance(tooth * Math.PI / 4, 12, 12)
                        .createTransformedShape(new RoundRectangle2D.Double(10, 1, 4, 6, 1, 1));
                gear.add(new Area(shape));
            }
            gear.subtract(new Area(new Ellipse2D.Double(8, 8, 8, 8)));
            g.setColor(foreground);
            g.fill(gear);
        } finally {
            g.dispose();
        }
    }
}
