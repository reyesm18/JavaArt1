import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class ImageGenerator {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 500;
    private Color[][] image = new Color[HEIGHT][WIDTH];

    // Color constants
    private static final Color RED = new Color(255, 0, 0);
    private static final Color GREEN = new Color(0, 255, 0);
    private static final Color BLUE = new Color(0, 0, 255);
    private static final Color LIGHT_RED = new Color(255, 204, 203);
    private static final Color BLACK = new Color(0, 0, 0);
    private static final Color SILVER = new Color(192, 192, 192);
    private static final Color WHITE = new Color(255, 255, 255);
    private static final Color MIDNIGHT_PURPLE = new Color(46, 26, 71);

    public static void main(String[] args) {
        ImageGenerator generator = new ImageGenerator();
        generator.initializeCanvas(MIDNIGHT_PURPLE);
        generator.createImage();
        generator.writeImage("myArt.ppm");
    }

    private void initializeCanvas(Color color) {
        for(int row = 0; row < HEIGHT; row++){
            for(int col = 0; col < WIDTH; col++){
                image[row][col] = color;
            }
        }
    }

    private void createImage() {
        Random rand = new Random();

        // Stars
        for (int i = 0; i < 150; i++) {
            int randomXPosition = rand.nextInt(WIDTH);
            int randomYPosition = rand.nextInt(HEIGHT);
            //drawCircle(randomXPosition, randomYPosition, 1, WHITE, WHITE);
        }

        drawRect(200, 100, 70, 20, GREEN);
        drawRect(100,50,100,20,RED);
        drawRect(30,50,20,20,WHITE);
        drawCircle(80, 10, 50, BLUE, BLACK);
        drawCircle(70, 90, 30, BLUE, RED);
    
    

    }

    /**
     * @param left starting x coordinate
     * @param top starting y coordinate
     * @param width how wide
     * @param height how tall
     * @param color color you want
     */
    private void drawRect(int left, int top, int width, int height, Color color) {
        for(int row = top; row < top + height; row++){
            for(int col = left; col < left + width; col++){
                //check to make sure you are in bounds
                //hint: this will help with circle stuff
                if(row >=0 && row<HEIGHT && col>=0 && col<WIDTH)
                    image[row][col] = color;
            }
        }

    }

    private void drawCircle(int centerX, int centerY, int radius, Color color, Color gradientColor) {
        //check to see if centerY +- radius or centerX +- radius is out of bounds
        for (int row = centerY - radius; row <= centerY + radius; row++) {
            for (int col = centerX - radius; col <= centerX + radius; col++) {
                if ((col - centerX) * (col - centerX) + (row - centerY) * (row - centerY) <= radius * radius) {
                    image[row][col] = color; // Simplified for uniform color
                }
            }
        }
    }

    private void drawEllipse(int centerX, int centerY, int xRadius, int yRadius, Color color, Color gradientColor) {
        
    }

    private boolean writeImage(String fileName) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("P3");
            out.println(WIDTH + " " + HEIGHT);
            out.println("255");
            for (Color[] row : image) {
                for (Color pixel : row) {
                    out.print(pixel.red + " " + pixel.green + " " + pixel.blue + "   ");
                }
                out.println();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing the image file: " + e.getMessage());
            return false;
        }
    }

    private static class Color {
        int red, green, blue;

        public Color(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
    }
}
