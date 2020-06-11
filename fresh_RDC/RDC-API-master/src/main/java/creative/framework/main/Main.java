package creative.framework.main;

import creative.framework.model.Apparel;
import creative.framework.model.Artifact;
import creative.framework.model.ClothingItem;
import creative.framework.model.Color;
import creative.framework.model.Scene;
import creative.framework.model.SceneItem;
import creative.framework.model.SceneValue;
import creative.framework.model.Type;
import creative.framework.util.Utils;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// calculateLab7();
		calculateLab8();
	}

	/**
	 * run the apparel example with argument colors as input
	 * 
	 * @param args
	 */
	public static void calculateApparel(String[] args) {
		if (args.length < 3) {
			System.out.println(
					"Insufficient number of arguments. \nEnter a colors to shirt, pants and shoes (in that order).");
		} else {
			List<String> shirtColors = Arrays.asList("BLUE", "GRAY", "LILAC", "NAVY", "WHITE", "blue", "gray", "lilac",
					"navy", "white");
			List<String> pantsColors = Arrays.asList("BLACK", "BROWN", "GRAY", "NAVY", "WHITE", "black", "brown",
					"gray", "navy", "white");
			List<String> shoesColors = Arrays.asList("BLACK", "BROWN", "GRAY", "NAVY", "WHITE", "black", "brown",
					"gray", "navy", "white");

			String color1 = args[0].toUpperCase();
			String color2 = args[1].toUpperCase();
			String color3 = args[2].toUpperCase();
			if (shirtColors.contains(color1)) {
				if (pantsColors.contains(color2)) {
					if (shoesColors.contains(color3)) {
						apparelExample(color1, color2, color3);
					} else {
						System.out.println(color3 + " is not a color to shoes");
					}
				} else {
					System.out.println(color2 + " is not a color to pants");
				}
			} else {
				System.out.println(color1 + " is not a color to shirt");
			}
		}
	}

	/**
	 * run lab 8
	 */
	public static void calculateLab8() {
		// initialize the values
		SceneValue waterShape = null;
		SceneValue waterAppearance = null;
		SceneValue trees = null;
		SceneValue clouds = null;
		SceneValue elevation = null;
		SceneValue park = null;
		SceneValue otherObjects = null;
		SceneValue particles = null;
		SceneValue skybox = null;
		SceneValue buildingShape = null;
		SceneValue streets = null;
		SceneValue buildingsAppearance = null;
		SceneValue inspirationContext = null;
		SceneValue inspirationSize = null;
		SceneValue inspirationWater = null;
		SceneValue inspirationPark = null;
		SceneValue citySize = null;
		SceneValue colorSynergy = null;
		SceneValue heightVariation = null;
		SceneValue inspirationHeightVariation = null;
		SceneValue id = null;

		// gather the dataset so we can loop through and try each scene
		java.lang.reflect.Type artifactType = new TypeToken<List<Scene>>() {
		}.getType();
		Gson gson = new Gson();
		Utils utils = new Utils();
		List<Scene> scenesToTest = gson.fromJson(utils.getReader("/datafiles/lab8Dataset.json"), artifactType);

		List<SceneItem> sceneItems;
		Type type;
		SceneValue value;
		StringBuilder csv = new StringBuilder();
		csv.append("id,novelty,value,rdc");

		for (Scene scene : scenesToTest) {
			sceneItems = scene.getSceneItems();
			for (SceneItem sceneItem : sceneItems) {
				switch (sceneItem.getType()) {
				case WATER_SHAPE:
					waterShape = sceneItem.getValue();
				case WATER_APPEARANCE:
					waterAppearance = sceneItem.getValue();
				case TREES:
					trees = sceneItem.getValue();
				case CLOUDS:
					clouds = sceneItem.getValue();
				case ELEVATION:
					elevation = sceneItem.getValue();
				case PARK:
					park = sceneItem.getValue();
				case OTHER_OBJECTS:
					otherObjects = sceneItem.getValue();
				case PARTICLES:
					particles = sceneItem.getValue();
				case SKYBOX:
					skybox = sceneItem.getValue();
				case BUILDING_SHAPE:
					buildingShape = sceneItem.getValue();
				case STREETS:
					streets = sceneItem.getValue();
				case BUILDINGS_APPEARANCE:
					buildingsAppearance = sceneItem.getValue();
				case INSPIRATION_CONTEXT:
					inspirationContext = sceneItem.getValue();
				case INSPIRATION_SIZE:
					inspirationSize = sceneItem.getValue();
				case INSPIRATION_WATER:
					inspirationWater = sceneItem.getValue();
				case INSPIRATION_PARK:
					inspirationPark = sceneItem.getValue();
				case CITY_SIZE:
					citySize = sceneItem.getValue();
				case COLOR_SYNERGY:
					colorSynergy = sceneItem.getValue();
				case HEIGHT_VARIATION:
					heightVariation = sceneItem.getValue();
				case INSPIRATION_HEIGHT_VARIATION:
					inspirationHeightVariation = sceneItem.getValue();
				case ID:
				default:
					id = sceneItem.getValue();
				}
			}
			if ((waterShape != null) && (waterAppearance != null) && (trees != null) && (clouds != null)
					&& (elevation != null) && (park != null) && (otherObjects != null) && (particles != null)
					&& (skybox != null) && (buildingShape != null) && (streets != null) && (buildingsAppearance != null)
					&& (inspirationContext != null) && (inspirationSize != null) && (inspirationWater != null)
					&& (inspirationPark != null) && (citySize != null) && (colorSynergy != null)
					&& (heightVariation != null) && (inspirationHeightVariation != null) && (id != null)) {
				// calculate the data for this scene
				csv.append(lab8Example(waterShape, waterAppearance, trees, clouds, elevation, park, otherObjects,
						particles, skybox, buildingShape, streets, buildingsAppearance, inspirationContext,
						inspirationSize, inspirationWater, inspirationPark, citySize, colorSynergy, heightVariation,
						inspirationHeightVariation, id));
			}

		}
		System.out.println(csv);
	}

	public static String lab8Example(SceneValue waterShape, SceneValue waterAppearance, SceneValue trees,
			SceneValue clouds, SceneValue elevation, SceneValue park, SceneValue otherObjects, SceneValue particles,
			SceneValue skybox, SceneValue buildingShape, SceneValue streets, SceneValue buildingsAppearance,
			SceneValue inspirationContext, SceneValue inspirationSize, SceneValue inspirationWater,
			SceneValue inspirationPark, SceneValue citySize, SceneValue colorSynergy, SceneValue heightVariation,
			SceneValue inspirationHeightVariation, SceneValue id) {

		// a context which the artifact is evalutated
		ArtifactContext<Scene> context = new SceneContext("/datafiles/lab8Dataset.json", "/datafiles/lab8Synergy.json",
				false);

		// a julde to evaluate an artifact in a context
		ArtifactJudge judge = new SceneJudge();

		// make the scene with a scene item that has the scene value
		List<SceneItem> sceneItem = Arrays.asList(new SceneItem(Type.WATER_SHAPE, waterShape),
				new SceneItem(Type.WATER_APPEARANCE, waterAppearance), new SceneItem(Type.TREES, trees),
				new SceneItem(Type.CLOUDS, clouds), new SceneItem(Type.ELEVATION, elevation),
				new SceneItem(Type.PARK, park), new SceneItem(Type.OTHER_OBJECTS, otherObjects),
				new SceneItem(Type.PARTICLES, particles), new SceneItem(Type.SKYBOX, skybox),
				new SceneItem(Type.BUILDING_SHAPE, buildingShape), new SceneItem(Type.STREETS, streets),
				new SceneItem(Type.BUILDINGS_APPEARANCE, buildingsAppearance),
				new SceneItem(Type.INSPIRATION_CONTEXT, inspirationContext),
				new SceneItem(Type.INSPIRATION_SIZE, inspirationSize),
				new SceneItem(Type.INSPIRATION_WATER, inspirationWater),
				new SceneItem(Type.INSPIRATION_PARK, inspirationPark), new SceneItem(Type.CITY_SIZE, citySize),
				new SceneItem(Type.COLOR_SYNERGY, colorSynergy), new SceneItem(Type.HEIGHT_VARIATION, heightVariation),
				new SceneItem(Type.INSPIRATION_HEIGHT_VARIATION, inspirationHeightVariation));

		// an artifact to be evaluated
		Artifact artifact = new Scene(sceneItem);
		return "\n" + id.toString().substring(1) + judge.evaluateArtifact(context, artifact);
	}

	/**
	 * run lab 7
	 */
	public static void calculateLab7() {
		// initialize the values
		SceneValue waterShape = null;
		SceneValue waterAppearance = null;
		SceneValue trees = null;
		SceneValue clouds = null;
		SceneValue boats = null;
		SceneValue animals = null;
		SceneValue otherObjects = null;
		SceneValue particles = null;
		SceneValue skybox = null;
		SceneValue mountainAppearance = null;
		SceneValue id = null;

		// gather the dataset so we can loop through and try each scene
		java.lang.reflect.Type artifactType = new TypeToken<List<Scene>>() {
		}.getType();
		Gson gson = new Gson();
		Utils utils = new Utils();
		List<Scene> scenesToTest = gson.fromJson(utils.getReader("/datafiles/lab7Dataset.json"), artifactType);

		List<SceneItem> sceneItems;
		Type type;
		SceneValue value;
		StringBuilder csv = new StringBuilder();
		csv.append("id,novelty,value,rdc");

		for (Scene scene : scenesToTest) {
			sceneItems = scene.getSceneItems();
			for (SceneItem sceneItem : sceneItems) {
				switch (sceneItem.getType()) {
				case WATER_SHAPE:
					waterShape = sceneItem.getValue();
				case WATER_APPEARANCE:
					waterAppearance = sceneItem.getValue();
				case TREES:
					trees = sceneItem.getValue();
				case CLOUDS:
					clouds = sceneItem.getValue();
				case BOATS:
					boats = sceneItem.getValue();
				case ANIMALS:
					animals = sceneItem.getValue();
				case OTHER_OBJECTS:
					otherObjects = sceneItem.getValue();
				case PARTICLES:
					particles = sceneItem.getValue();
				case SKYBOX:
					skybox = sceneItem.getValue();
				case MOUNTAIN_APPEARANCE:
					mountainAppearance = sceneItem.getValue();
				case ID:
				default:
					id = sceneItem.getValue();
				}
			}
			if ((waterShape != null) && (waterAppearance != null) && (trees != null) && (clouds != null)
					&& (boats != null) && (animals != null) && (otherObjects != null) && (particles != null)
					&& (skybox != null) && (mountainAppearance != null) && (id != null)) {
				// calculate the data for this scene
				csv.append(lab7Example(waterShape, waterAppearance, trees, clouds, boats, animals, otherObjects,
						particles, skybox, mountainAppearance, id));
			}

		}
		System.out.println(csv);
	}

	public static String lab7Example(SceneValue waterShape, SceneValue waterAppearance, SceneValue trees,
			SceneValue clouds, SceneValue boats, SceneValue animals, SceneValue otherObjects, SceneValue particles,
			SceneValue skybox, SceneValue mountainAppearance, SceneValue id) {

		// a context which the artifact is evalutated
		ArtifactContext<Scene> context = new SceneContext("/datafiles/lab7Dataset.json", "/datafiles/testSynergy.json",
				true);

		// a julde to evaluate an artifact in a context
		ArtifactJudge judge = new SceneJudge();

		// make the scene with a scene item that has the scene value
		List<SceneItem> sceneItem = Arrays.asList(new SceneItem(Type.WATER_SHAPE, waterShape),
				new SceneItem(Type.WATER_APPEARANCE, waterAppearance), new SceneItem(Type.TREES, trees),
				new SceneItem(Type.CLOUDS, clouds), new SceneItem(Type.BOATS, boats),
				new SceneItem(Type.ANIMALS, animals), new SceneItem(Type.OTHER_OBJECTS, otherObjects),
				new SceneItem(Type.PARTICLES, particles), new SceneItem(Type.SKYBOX, skybox),
				new SceneItem(Type.MOUNTAIN_APPEARANCE, mountainAppearance));

		// an artifact to be evaluated
		Artifact artifact = new Scene(sceneItem);
		return "\n" + id.toString().substring(1) + judge.evaluateArtifact(context, artifact);
	}

	/**
	 * Run Apparel Example
	 *
	 * @param color1
	 *            - shirt color
	 * @param color2
	 *            - pants color
	 * @param color3
	 *            - shoes color
	 */
	public static void apparelExample(String color1, String color2, String color3) {

		// a context which the artifact is evalutated
		ArtifactContext<Apparel> context = new ApparelContext("/datafiles/apparelDataset.json",
				"/datafiles/apparelSynergy.json");

		// a julde to evaluate an artifact in a context
		ArtifactJudge judge = new ApparelJudge();

		// some clothing items
		List<ClothingItem> clothingItem = Arrays.asList(new ClothingItem(Type.SHIRT, Color.valueOf(color1)), // a navy
																												// shirt
				new ClothingItem(Type.PANTS, Color.valueOf(color2)), // pants white
				new ClothingItem(Type.SHOES, Color.valueOf(color3))); // shoes brown

		// an artifact to be evaluated
		Artifact artifact = new Apparel(clothingItem);
		System.out.println(artifact.toString());

		// show the evaluation of an artifact
		System.out.println("Apparel RDC Metric:" + judge.evaluateArtifact(context, artifact));

	}
}
