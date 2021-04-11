package spaxel.ui.elements;

import spaxel.ui.styles.Style;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import spaxel.ui.elements.logic.Logic;
import spaxel.ui.elements.logic.OnClickLogic;
import spaxel.ui.elements.logic.OnUpdateLogic;
import spaxel.ui.elements.logic.OnInitLogic;
import spaxel.ui.elements.logic.ReleaseLogic;
import spaxel.ui.render.StyleRenderer;
import spaxel.ui.state.State;
import spaxel.graphics.buffer.MasterBuffer;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import spaxel.ui.elements.logic.HoverLogic;
import spaxel.ui.elements.logic.ClickLogic;
import spaxel.input.MouseWrapper;
import spaxel.collision.HitShape;
import spaxel.engine.Resources;

/**
 * Represent an element of a UI
 */
public class Element {
    private int index;
    private String id;
    private List<String> classes;

    private List<Element> children;

    private List<Logic> logic;

    private Style style;

    private State state;
    private StyleRenderer renderer;
    private MouseWrapper mouse;
    private String controller;
    private Map<String, HitShape> hitShapeAtlas;
    private List<Map<String, Map<String, String>>> stylesheets;

    /**
     * Create a new Element
     */
    public Element() {
        super();
        this.children = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.logic = new ArrayList<>();
        this.state = new State();
        this.style = new Style();
        logic.add(new HoverLogic());
        logic.add(new ClickLogic());
        logic.add(new ReleaseLogic());
        logic.add(new OnClickLogic());
        logic.add(new OnUpdateLogic());
        logic.add(new OnInitLogic());
    }

    /**
     * Render this element with the given buffer
     * 
     * @param buffer the master buffer of the game
     */
    public void render(MasterBuffer buffer) {
        if (this.renderer == null) {
            this.renderer = new StyleRenderer(Resources.get().getRenderables(), Resources.get().getAnimationAtlas());
        }
        renderer.renderStyle(style, state, index, buffer);
        synchronized (children) {
            for (Element child : children) {
                child.render(buffer);
            }
        }
    }

    /**
     * Update this element
     */
    public void update() {
        for (Logic l : logic) {
            l.execute(this);
        }
        for (Element element : children) {
            element.update();
        }
    }

    /**
     * Initialize the style of this element
     */
    public void initStyle(List<Map<String, Map<String, String>>> stylesheets) {
        this.stylesheets = stylesheets;
        for (String stl : classes) {
            style.merge(getStyle("." + stl, stylesheets));
            style.merge(getStyle("." + stl + ":hover", stylesheets), "hover");
            style.merge(getStyle("." + stl + ":click", stylesheets), "click");
        }
        style.merge(getStyle("#" + id, stylesheets));
        style.merge(getStyle("#" + id + ":hover", stylesheets), "hover");
        style.merge(getStyle("#" + id + ":click", stylesheets), "click");

        for (Element child : children) {
            child.initStyle(stylesheets);
        }
    }

    /**
     * Get the style configuration corresponding to the given name
     * 
     * @param name the name of the style
     * 
     * @return the style configuration
     */
    private Map<String, String> getStyle(String name, List<Map<String, Map<String, String>>> stylesheets) {
        for (Map<String, Map<String, String>> stylesheet : stylesheets) {
            if (stylesheet.containsKey(name)) {
                return stylesheet.get(name);
            }
        }
        return null;
    }

    /**
     * Reset the state of this element
     */
    public void reset() {
        state.reset();
        for (Element child : children) {
            child.reset();
        }
    }

    /**
     * Find an element by id in the subtree where this element is the root
     * 
     * @param id the id to find
     * 
     * @return the found Element
     */
    public Element findById(String id) {
        if (id.equals(this.id)) {
            return this;
        } else {
            for (Element child : children) {
                Element result = child.findById(id);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public Style getStyle() {
        return style;
    }

    public State getState() {
        return state;
    }

    public void setController(String controller) {
        this.controller = controller;
        for (Element child : children) {
            child.setController(controller);
        }
    }

    public String getController() {
        return controller;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /**
     * Add a new element to the children of this element
     * 
     * @param element the element to add
     */
    @JsonSetter("element")
    public void addElement(Element element) {
        synchronized (children) {
            this.children.add(element);
            element.getStyle().setParent(style);
            element.setIndex(children.size() - 1);
            element.setController(controller);
        }
    }

    @JsonSetter("class")
    public void setClasses(String classes) {
        for (String className : classes.split(" ")) {
            this.classes.add(className);
        }
    }

    /**
     * Dump all unknown properties in here when this Element gets deserialized
     * 
     * @param key   the key
     * @param value the value
     */
    @JsonAnySetter()
    public void dump(String key, String value) {
        // NOTE dump all unknown xml properties in this hole
    }

    /**
     * Clear the children of this element
     */
    public void clearChildren() {
        synchronized (children) {
            children.clear();
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setMouse(MouseWrapper mouse) {
        this.mouse = mouse;
    }

    public MouseWrapper getMouse() {
        return mouse;
    }

    public void setHitShapeAtlas(Map<String, HitShape> hitShapeAtlas) {
        this.hitShapeAtlas = hitShapeAtlas;
    }

    public Map<String, HitShape> getHitShapeAtlas() {
        return hitShapeAtlas;
    }

    public void setHitStylesheets(List<Map<String, Map<String, String>>> stylesheets) {
        this.stylesheets = stylesheets;
    }

    public List<Map<String, Map<String, String>>> getStylesheets() {
        return stylesheets;
    }
}
