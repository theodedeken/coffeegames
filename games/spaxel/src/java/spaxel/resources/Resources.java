package spaxel.resources;

import java.util.Map;
import java.util.Map.Entry;

import spaxel.Constants;
import spaxel.entity.item.ItemCatalogue;
import spaxel.entity.item.ItemProperties;
import voide.graphics.load.Image;
import voide.graphics.load.ImagePart;
import voide.graphics.renderable.Texture;
import voide.graphics.util.ImagePacker;
import voide.input.Key;
import voide.input.KeyState;

/**
 * Singleton class to hold all the game resources
 */
public final class Resources {
	private static final Resources resources = new Resources();

	private Map<Key, KeyState> keyConfiguration;

	private Resources() {

	}

	/**
	 * Initializes the resources needed to show the loadingscreen
	 */
	public void initLoadingResources() {
		voide.resources.Resources.get().load(Constants.LOAD_RESOURCE_MAP);

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

		voide.resources.Resources.get().load(Constants.RESOURCE_MAP);

		// music = loadMusic(resourcePaths.get("music"));

		// sounds = loadSounds(resourcePaths.get("sounds"));

		Map<String, ItemProperties> loadedItems = voide.resources.Resources.get().getNamespaceResources("item",
				ItemProperties.class);
		ItemCatalogue items = new ItemCatalogue(loadedItems);
		voide.resources.Resources.get().addResource("spaxel.item_catalogue", items);
	}

	public void exit() {
		// TODO this should still happen somehow
		/*
		 * for (Music m : music.values()) { m.close(); } music.clear();
		 */
		// TODO sounds
	}

	public Map<Key, KeyState> getKeyConfiguration() {
		return keyConfiguration;
	}

}
