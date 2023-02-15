import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(100, 5, 12, 500);
        GameProgress gameProgress2 = new GameProgress(95, 4, 13, 400);
        GameProgress gameProgress3 = new GameProgress(50, 2, 14, 100);
        saveGame("C://Games/savegames/save1.dat", gameProgress1);
        saveGame("C://Games/savegames/save2.dat", gameProgress2);
        saveGame("C://Games/savegames/save3.dat", gameProgress3);
        List<String> list = new ArrayList<>();
        list.add("save1.dat");
        list.add("save2.dat");
        list.add("save3.dat");
        zipFiles("C://Games/savegames/zip.zip", list);

    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path));
             FileInputStream fis = new FileInputStream("zip.zip")) {
            for(String listS: list){
                ZipEntry entry = new ZipEntry(listS);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                zout.write(buffer);
                zout.closeEntry();

            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
