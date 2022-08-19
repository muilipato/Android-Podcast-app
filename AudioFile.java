package meemseen.podcasto;

public class AudioFile {

    /** AudioFile file title */
    private String mAudioProducer;
    /** AudioFile file title */
    private String mAudioTitle;
    /** AudioFile host */
    private String mAudioHost;
    /** Link of the audio file */
    private String mUrl;

    private final static int NO_IMAGE_PROVIDED = -1;
    /** photo for each word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;


    /**
     * Create a new AudioFile object.
     *
     * @param audioTitle is the title of the audio file
     * @param audioHost is the name of the host person
     * @param url is the link of the audio file
     * @param imageResourceId is the id of the local image for each category
     */
    public AudioFile(String audioProducer, String audioTitle, String audioHost, String url, int imageResourceId) {
        this.mAudioProducer = audioProducer;
        this.mAudioTitle = audioTitle;
        this.mAudioHost = audioHost;
        this.mUrl = url;
        this.mImageResourceId = imageResourceId;
    }

    //Getters
    /**
     * Get the name of the producer
     */
    public String getAudioProducer() {
        return mAudioProducer;
    }
    /**
     * Get the title of the podcast/audio file
     */
    public String getAudioTitle() {
        return mAudioTitle;
    }
    /**
     * Get the name of the podcast/audio host
     */
    public String getAudioHost() {
        return mAudioHost;
    }
    /**
     * Get the link of the podcast/audio file
     */
    public String getUrl() {
        return mUrl;
    }
    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }


    @Override
    public String toString() {
        return "meemseen.podcasto.AudioFile{" +
                "mAudioTitle='" + mAudioTitle + '\'' +
                ", mAudioHost='" + mAudioHost + '\'' +
                ", mUrl='" + mUrl + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                '}';
    }
}
