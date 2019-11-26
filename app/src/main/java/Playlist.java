import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String mName;
    private String mPath;
    private String mJSON;
    private ArrayList<String> mSongs;

    public Playlist(String name) {
        mName = name;
        mJSON = "{\"playlist\":[]}";
        mSongs.clear();
    }
    public void addSong(String path) {

    }
}
