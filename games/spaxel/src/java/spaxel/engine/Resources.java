package spaxel.engine;

import static spaxel.loaders.Loader.loadEntityIndustries;
import static spaxel.loaders.Loader.loadHitShapes;
import static spaxel.loaders.Loader.loadItems;
import static spaxel.loaders.Loader.loadKeyConfiguration;
import static spaxel.loaders.Loader.loadResourcePaths;
import static spaxel.loaders.Loader.loadStylesheets;
import static spaxel.loaders.Loader.loadUI;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import spaxel.Constants;
import spaxel.factories.entities.EntityIndustry;
import spaxel.ui.elements.UI;
import spaxel.ui.elements.UIType;
import voide.collision.HitShape;
import voide.graphics.animation.Animation;
import voide.graphics.load.Image;
import voide.graphics.load.ImagePart;
import voide.graphics.renderable.Renderable;
import voide.graphics.renderable.Texture;
import voide.graphics.util.ImagePacker;
import voide.input.Key;
import voide.input.KeyState;
import voide.sound.Music;
import voide.sound.Sound;

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
		// Optimize loaded images and send to GPU
		// TODO this optimization should happen elsewhere
		Map<String, Image> images = voide.resources.Resources.get().getNamespaceResources("image", Image.class);
		Map<String, ImagePart> image_parts = voide.resources.Resources.get().getNamespaceResources("image_part",
				ImagePart.class);
		ImagePacker packer = new ImagePacker();
		ImagePacker.PackingResult result = packer.pack(images);
		Texture root = result.rootImage.toTexture();
		int rootId = root.getTextureId();
		voide.resources.Resources.get().addResource("voide.packed_texture", root);
		for (Entry<String, ImagePart> entry : result.parts.entrySet()) {
			voide.resources.Resources.get().addResource(entry.getKey(), entry.getValue());
			voide.resources.Resources.get().addResource(entry.getKey().replace("image.", "texture."),
					entry.getValue().toTexture(rootId));
		}

		for (Entry<String, ImagePart> entry : image_parts.entrySet()) {
			voide.resources.Resources.get().addResource(entry.getKey().replace("image_part.", "texture."),
					entry.getValue().toTexture(rootId));
		}

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
