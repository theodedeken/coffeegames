package spaxel.engine;

import static spaxel.loaders.Loader.*;
import java.util.Map;
import java.util.List;
import voide.collision.HitShape;
import voide.graphics.animation.Animation;
import voide.graphics.renderable.Rectangle;
import voide.graphics.renderable.Renderable;
import spaxel.factories.entities.EntityIndustry;
import spaxel.ui.elements.UI;
import spaxel.ui.elements.UIType;
import spaxel.util.TextureUtil;
import spaxel.Constants;
import voide.input.Key;
import voide.input.KeyState;
import voide.sound.Music;
import voide.sound.Sound;
import spaxel.graphics.texture.Texture;
import spaxel.graphics.texture.TexturePart;
import spaxel.graphics.texture.PackedTexture;

import java.util.HashMap;

/**
 * Singleton class to hold all the game resources
 */
public final class Resources {
	private static final Resources resources = new Resources();

	private Map<String, EntityIndustry> industryMap;
	private Map<String, HitShape> hitShapeAtlas;
	private Map<UIType, UI> uis;
	private Map<String, Map<String, Map<String, String>>> stylesheets;
	private Map<String, Animation> animationAtlas;
	private ItemCatalogue items;
	private Map<Key, KeyState> keyConfiguration;
	private Map<String, Renderable> renderables;
	private Map<String, Sound> sounds;
	private Map<String, Music> music;

	private Resources() {

	}

	/**
	 * Initializes the resources needed to show the loadingscreen
	 */
	public void initLoadingResources() {
		Map<String, List<String>> resourcePaths = loadResourcePaths(Constants.LOAD_RESOURCE_PATH);

		voide.resources.Resources.get().load(Constants.LOAD_RESOURCE_MAP);

		// animationAtlas = loadAnimations(resourcePaths.get("animation"));
		stylesheets = loadStylesheets(resourcePaths.get("stylesheet"));
		uis = loadUI(resourcePaths.get("ui"), stylesheets);
		// Renderables loading
		renderables = new HashMap<>();
		Map<String, Texture> textures = loadTextures(resourcePaths.get("texture"));
		PackedTexture packedTexture = new PackedTexture(textures.values());
		packedTexture.load();
		packedTexture.initializeCoordinates();
		Map<String, TexturePart> textureParts = loadTextureParts(resourcePaths.get("texture_part"));
		for (TexturePart tPart : textureParts.values()) {
			tPart.initializeCoordinates(textures.get(tPart.getSheetName()));
		}

		Map<String, Rectangle> rectangles = voide.resources.Resources.get().getNamespaceResources("rectangle",
				Rectangle.class);
		renderables.putAll(textures);
		renderables.putAll(textureParts);
		renderables.putAll(rectangles);
		renderables.put("packed", packedTexture);

		Engine.get().setCurrentUI(uis.get(UIType.LOAD));
	}

	/**
	 * Get the singleton instance of this class
	 * 
	 * @return the singleton
	 */
	public static Resources get() {
		return resources;
	}

	/**
	 * Starts the loading of all the game resources
	 */
	public void startLoading() {
		Map<String, List<String>> resourcePaths = loadResourcePaths(Constants.RESOURCE_PATH);

		voide.resources.Resources.get().load(Constants.RESOURCE_MAP);

		// music = loadMusic(resourcePaths.get("music"));

		// sounds = loadSounds(resourcePaths.get("sounds"));

		hitShapeAtlas = loadHitShapes(resourcePaths.get("hitshape"));

		items = loadItems(resourcePaths.get("item"));

		stylesheets = loadStylesheets(resourcePaths.get("stylesheet"));

		uis = loadUI(resourcePaths.get("ui"), stylesheets);

		industryMap = loadEntityIndustries(resourcePaths.get("industry"));

		keyConfiguration = loadKeyConfiguration(resourcePaths.get("keys"));

		Engine.get().finishLoading();
	}

	public void exit() {
		// TODO this should still happen somehow
		/*
		 * for (Music m : music.values()) { m.close(); } music.clear();
		 */
		// TODO sounds
	}

	public Map<String, Map<String, Map<String, String>>> getStylesheets() {
		return stylesheets;
	}

	public Map<String, Animation> getAnimationAtlas() {
		return animationAtlas;
	}

	public Map<String, EntityIndustry> getIndustryMap() {
		return industryMap;
	}

	public Map<String, HitShape> getHitShapeAtlas() {
		return hitShapeAtlas;
	}

	public Map<UIType, UI> getUIS() {
		return uis;
	}

	public Map<String, Music> getMusic() {
		return music;
	}

	public ItemCatalogue getItems() {
		return items;
	}

	public Map<Key, KeyState> getKeyConfiguration() {
		return keyConfiguration;
	}

	public Map<String, Renderable> getRenderables() {
		return renderables;
	}

}
