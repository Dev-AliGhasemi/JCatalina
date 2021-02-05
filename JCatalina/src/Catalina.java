
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Catalina {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("images");
        Stream<Path> images = Files.list(path);
        List<Path> imagesSpectrum = images.collect(Collectors.toList());
        List<Integer> imageTimeSpectrum = new ArrayList<>();
        imagesSpectrum.forEach(image -> imageTimeSpectrum.add(Integer.parseInt(image.getFileName().toString().substring(0, image.getFileName().toString().indexOf(".")))));
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
                } else {
                    max = 1;
                }
                try {
                    if (lastMax != max) {
                        lastMax = max;
                        Files.copy(Paths.get(path.toAbsolutePath().toString() + "/" + max + ".jpg"), Paths.get("/home/ali/Pictures/Wallpapers/Catalina.jpg"), StandardCopyOption.REPLACE_EXISTING);
                        Thread.sleep(300000);
                    }
//                    Runtime runtime = Runtime.getRuntime();
//                    runtime.exec("gsettings set org.gnome.desktop.background picture-uri file:///" + path.toAbsolutePath() + "/" + max + ".jpg");
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}