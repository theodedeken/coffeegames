package voide.ui;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

import voide.input.MouseWrapper;
import voide.render.buffer.MasterBuffer;
import voide.resources.Resource;
import voide.resources.ResourceProxy;
import voide.ui.elements.Element;
import voide.ui.elements.StyleSheet;

/**
 * Root class of an ui structure
 */
public class UI implements Resource {
    private String controller;
    private List<ResourceProxy<StyleSheet>> styles;
    private List<StyleSheet> stylesheets;
    private Element body;

    /**
     * Create a new UI
     */
    public UI() {
        super();
        this.styles = new ArrayList<>();
    }

    public void initialize() {
        this.stylesheets = new ArrayList<>();

        for (ResourceProxy<StyleSheet> style : styles) {
            this.stylesheets.add(style.get());
        }
        body.initStyle(this.stylesheets);
    }

    /**
     * Update this UI.
     */
    public void update(MouseWrapper mouse) {
        body.update(mouse);
    }

    /**
     * Render this UI.
     * 
     * @param buffer the masterbuffer of the rendersystem
     */
    public void render(MasterBuffer buffer) {
        body.render(buffer);
    }

    /**
     * Reset this UI.
     */
    public void reset() {
        body.reset();
    }

    /**
     * Find an element in this UI by its id
     * 
     * @param id the id of the element
     * 
     * @return the element if found
     */
    public Element findById(String id) {
        return body.findById(id);
    }

    /**
     * @return the controller
     */
    public String getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(String controller) {
        this.controller = controller;
    }

    /**
     * @return the body
     */
    public Element getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Element body) {
        this.body = body;
    }

    /**
     * Add a new stylesheet path to this UI
     * 
     * @param style the path of the stylesheet
     */
    @JsonSetter("style")
    public void addStylesheetPath(String style) {
        this.styles.add(new ResourceProxy<>(style, StyleSheet.class));
    }

}
