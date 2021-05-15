package spaxel.factories.elements;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.cooldown.CooldownStorage;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.stack.StackStorage;
import voide.ui.elements.Element;
import spaxel.entity.Entity;

/**
 * Creates new UI elements
 * 
 * Created by theod on 29-6-2017.
 */
public final class ElementCreator {
    private ElementCreator() {

    }

    /**
     * Create an Item View Element
     * 
     * @param item the item to create a view for
     * 
     * @return the created Element
     */
    public static Element createItemView(Entity item) {
        RenderableStorage sc = (RenderableStorage) item.getComponent(ComponentType.RENDERABLE);
        CooldownStorage cc = (CooldownStorage) item.getComponent(ComponentType.COOLDOWN);
        StackStorage stc = (StackStorage) item.getComponent(ComponentType.STACK);

        Element base = new Element();
        base.setClasses("inventory_item");
        base.getStyle().setProperty("sprite", sc.getRenderable().getName());

        Element cooldown = new Element();
        cooldown.setClasses("item_cooldown");
        cooldown.getStyle().setProperty("completion",
                String.valueOf((double) cc.getCurrentCooldown() / cc.getMaxCooldown()));

        Element stacks = new Element();
        stacks.setClasses("item_stacks");
        stacks.getStyle().setProperty("text", String.valueOf(stc.getStacks()));

        base.addElement(cooldown);
        base.addElement(stacks);

        return base;
    }
}
