package frc.robot.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * The Configuration class represents the persistence of a certain configuration.
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
 *   Configuration cfg = new Configuration("/path/to/config.json");
 *   ConfigTest config = cfg.readConfig(ConfigTest.class);
 *   System.out.println(config.number); // 12
 *   config.number = 3;
 *   config.str = "different string";
 *   // To save it:
 *   cfg.saveConfig(config);
 *   // The config.json will be updated according to the new data.
 * </pre>
 */
public class Configuration {

  private final Gson gson = new GsonBuilder()
          .setPrettyPrinting()
          .serializeNulls()
          .create();

  /**
   * The file this Configuration object refers to.
   */
  public final File file;

  /**
   * Constructor.
   * @param file The File object that represents the configuration file
   */
  public Configuration(File file) {
    this.file = file;
    if (!file.exists()) {
      System.err.println("The configuration file for " + file.getAbsolutePath() + " doesn't exist. Resorting to using defaults.");
    }
  }

  /**
   * Constructor.
   * @param filePath The string path to the configuration file
   */
  public Configuration(String filePath) {
    this(new File(filePath));
  }

  /**
   * Call this to read the configuration file and return an object with the configuration data.
   * The class must have a zero-argument constructor.
   * @param clazz The class you want to deserialize. <pre>X.class</pre>
   */
  public <T> T readConfig(Class<T> clazz) {
    if (!file.exists()) {
      try {
        T instance = clazz.newInstance();
        saveConfig(instance);
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
  public <T> void saveConfig(T configObj) {
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
