package arkanoid.openingMenu;

import arkanoid.animation.AnimationRunner;
import arkanoid.background.ImageS;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> .
 * @author Eliad Sellem.
 * @version "1.8.0_201"
 * @since 2019-06-07
 */
public class MenuAnimation<T> implements Menu {
    private String menuName;
    private List<String> keyList;
    private List<String> keyListSubMenu;
    private List<String> messageList;
    private List<String> messageListSubMenu;
    private List<T> returnValList;
    private T status;
    private KeyboardSensor keyboardSensor;
    private Boolean stop;
    private List<Menu<T>> subMenus;
    private AnimationRunner runner;
    private ImageS img;

    /**
     * constructor.
     * <p>
     * initialize our filed.
     *
     * @param name            the title of the menu.
     * @param keyboardSensor  KeyboardSensor.
     * @param animationRunner AnimationRunner.
     */
    public MenuAnimation(KeyboardSensor keyboardSensor, AnimationRunner animationRunner, String name) {
        this.keyList = new ArrayList<>();
        this.messageList = new ArrayList<>();
        this.messageListSubMenu = new ArrayList<>();
        this.returnValList = new ArrayList<T>();
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
        this.subMenus = new ArrayList<>();
        this.runner = animationRunner;
        this.menuName = name;
        this.keyListSubMenu = new ArrayList<>();
        this.img = new ImageS("image(background_images/arknoid.jpg)", 0, 0);
    }

    @Override
    public void addSelection(String key, String message, Object returnVal) {
        this.keyList.add(key);
        this.messageList.add(message);
        this.returnValList.add((T) returnVal);
        this.status = null;
    }

    @Override
    public Object getStatus() {
        return this.status;
    }

    @Override
    public void setStop(boolean b) {
        this.stop = b;
    }

    @Override
    public void addSubMenu(String key, String message, Menu subMenu) {
        this.keyListSubMenu.add(key);
        this.messageListSubMenu.add(message);
        this.subMenus.add(subMenu);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        for (String s : keyListSubMenu) {
            if (keyboardSensor.isPressed(s)) {
                runner.run(subMenus.get(keyListSubMenu.indexOf(s)));
                T task = subMenus.get(keyListSubMenu.indexOf(s)).getStatus();
                this.status = task;
                this.stop = true;
                subMenus.get(keyListSubMenu.indexOf(s)).doOneFrame(d);
                return;
            }
        }

//        d.setColor(Color.LIGHT_GRAY);
//        //d.setColor(new Color(0, 0, 128));
//        d.fillRectangle(0, 0, 800, 600);
        this.img.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(50, 98, this.menuName, 60);

        d.setColor(Color.yellow);
        d.drawText(50, 100, this.menuName, 60);

        int y = 200;
        for (String s : keyListSubMenu) {
            d.setColor(Color.BLACK);
            d.drawText(115, y - 2, "(" + s + ")", 45);
            d.drawText(190, y - 2, this.messageListSubMenu.get(keyListSubMenu.indexOf(s)), 50);
            d.setColor(Color.GREEN);
            d.drawText(115, y, "(" + s + ")", 45);
            d.drawText(190, y, this.messageListSubMenu.get(keyListSubMenu.indexOf(s)), 50);
            y += 70;
        }

        for (String s : keyList) {
            d.setColor(Color.BLACK);
            d.drawText(115, y - 2, "(" + s + ")", 45);
            d.drawText(190, y - 2, this.messageList.get(keyList.indexOf(s)), 50);
            d.setColor(Color.GREEN);
            d.drawText(115, y, "(" + s + ")", 45);
            d.drawText(190, y, this.messageList.get(keyList.indexOf(s)), 50);
            y += 70;
        }
        for (String s : keyList) {
            if (keyboardSensor.isPressed(s)) {
                this.status = returnValList.get(keyList.indexOf(s));
                this.stop = true;
                break;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
