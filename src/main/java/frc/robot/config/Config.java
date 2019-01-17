package frc.robot.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * The Configuration utility allows you to read and write JSON configuration files.
 * It can read and write to a .json file and produce a mapped object as the data.<p>
 * If the file does not exist, a warning will be printed and the file will be created and saved using defaults.<p>
 * The mapped object MUST have a zero-argument constructor!<p>
 *
 * This class is <i>fail-fast</i>. If an error occurs, a wrapped RuntimeException will be thrown.
 * If the configuration file does not exist, it will be created using defaults from an instance of the class provided.
 *
 * <p>
 *   Example:<br>
 * <pre>
 *   public class ConfigTest {
 *     String str = "potato";
 *     int number = 12;
 *     double decimal = 1500.0;
 *   }
 *   // Somewhere...
 *   File configFile = new File("/path/to/config.json");
 *   ConfigTest config = Config.readConfig(configFile, ConfigTest.class);
 *   System.out.println(config.number); // 12
 *   config.number = 3;
 *   config.str = "different string";
 *   // To save it:
 *   Config.saveConfig(configFile, config);
 *   // The config.json will be updated according to the new data.
 * </pre>
 */
public final class Config {

  private static final Gson gson = new GsonBuilder()
          .setPrettyPrinting()
          .serializeNulls()
          .create();

  private Config() {
  }

  /**
   * Call this to read the configuration file and return an object with the configuration data.
   * The class must have a zero-argument constructor.
   * @param clazz The class you want to deserialize. <pre>X.class</pre>
   */
  public static <T> T readConfig(File file, Class<T> clazz) {
    if (!file.exists()) {
      System.err.println("Warning: The configuration file for " + file.getAbsolutePath() + " does not exist. Resorting to defaults and saving that.");
      try {
        T instance = clazz.getDeclaredConstructor().newInstance();
        saveConfig(file, instance);
        return instance;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    try (final FileInputStream fis = new FileInputStream(file)) {
      return gson.fromJson(new InputStreamReader(fis), clazz);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  /**
   * Saves the configuration object to the file this Configuration is using.
   */
  public static <T> void saveConfig(File file, T configObj) {
    try {
      new File(file, "/..").mkdirs();
      file.createNewFile();

      try (final FileOutputStream fos = new FileOutputStream(file); final OutputStreamWriter writer = new OutputStreamWriter(fos)) {
        writer.write(gson.toJson(configObj));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
