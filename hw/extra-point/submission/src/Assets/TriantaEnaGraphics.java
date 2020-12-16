package Assets;

public class TriantaEnaGraphics {

    private final StringBuilder banner;
    private final StringBuilder welcomeMsg;
    private final StringBuilder rotate;

    public TriantaEnaGraphics(){
        GraphicReader gr = new GraphicReader();
        banner = gr.readGraphic("./graphic/banner.txt");
        welcomeMsg = gr.readGraphic("./graphic/welcomeMsg.txt");
        rotate = gr.readGraphic("./graphic/rotate.txt");
    }

    public String getBanner() {
        return String.valueOf(banner);
    }

    public String getWelcomeMsg() {
        return String.valueOf(welcomeMsg);
    }

    public String getRotate() {
        return String.valueOf(rotate);
    }
}
