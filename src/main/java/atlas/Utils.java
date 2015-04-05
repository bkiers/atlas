package atlas;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class Utils {

  public static Integer toInt(String value, Integer fallback) {

    if (value == null || value.isEmpty()) {
      return fallback;
    }

    try {
      return Integer.valueOf(value);
    }
    catch (Exception e) {
      return fallback;
    }
  }

  public static Double toDouble(String value, Double fallback) {

    if (value == null || value.isEmpty()) {
      return fallback;
    }

    try {
      return Double.valueOf(value);
    }
    catch (Exception e) {
      return fallback;
    }
  }

  public static <T> T deserialize(String fileName, Class<T> type) {
    try {
      Kryo kryo = new Kryo();
      Input input = new Input(Atlas.class.getResourceAsStream("/" + fileName));
      T value = kryo.readObject(input, type);
      input.close();
      return value;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void serialize(Object value, File file) {
    try {
      Kryo kryo = new Kryo();
      Output output = new Output(new FileOutputStream(file));
      kryo.writeObject(output, value);
      output.close();
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Map<String, String> read(File file, String delimiter) {
    Map<String, String> map = new LinkedHashMap<>();
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String[] tokens = scanner.nextLine().split(Pattern.quote(delimiter));
        if (tokens.length >= 2) {
          map.put(tokens[0], tokens[1]);
        }
      }
    }
    catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return map;
  }
}
