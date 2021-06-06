
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JCatalina {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/images");
        Logger logger = Logger.getLogger("JCatalinaLogger");
        Stream<Path> images = Files.list(path);
        List<Path> imagesSpectrum = images.collect(Collectors.toList());
        List<Integer> imageTimeSpectrum = new ArrayList<>();
        imagesSpectrum.forEach(image -> imageTimeSpectrum.add(Integer.parseInt(image.getFileName().toString()
                .substring(0, image.getFileName().toString().indexOf(".")))));
        Thread thread = new Thread(() -> {
            LocalTime localTime;
            int max = 0, lastMax = max;
            while (true) {
                localTime = LocalTime.now();
                if (localTime.getHour() != 0) {
                    for (Integer integer : imageTimeSpectrum) {
                        if (integer > max && integer <= localTime.getHour())
                            max = integer;
                    }
                } else
                    max = imageTimeSpectrum.stream().min(Integer::compareTo).get();

                try {
                    if (lastMax != max) {
                        logger.info("wallpaper changed");
                        lastMax = max;
                        Files.copy(Paths.get(path.toAbsolutePath().toString() + "/" + max + ".jpg"),
                                Paths.get("/home/ali/Pictures/Wallpapers/Catalina.jpg"),
                                StandardCopyOption.REPLACE_EXISTING);
                    }
                    Thread.sleep(300000);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                    logger.log(Level.OFF, e.getMessage());
                }
            }
        });
        thread.start();
    }
}
