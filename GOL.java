import javax.swing.*;
import java.awt.*;
public class GOL {
    private static JButton[][] buttons;

    //sets keys for the different types of cells
    private static final Color cell = Color.GREEN;
    private static final Color space = Color.GRAY;
    private static final Color alive = Color.YELLOW;
    private static final Color dead = Color.RED;

    //default width and height for the board
    private static int width = 30;
    private static int height = 30;

    public static void main(String[] args) {
        //Creates variable screensize
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //Creating the Frame
        JFrame frame = new JFrame("Game Of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width, screenSize.height);

        //J Panel
        JPanel panel = new JPanel(); // the panel is not visible in output

        //grid
        Container grid = new Container();

        frame.setSize(900,900);
        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.SOUTH);

        grid.setLayout(new GridLayout(height,width));

        //Loading golBoard
        loadBoard(grid);

        frame.add(grid,BorderLayout.CENTER);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);

        //This part of the code updates the screen every 4 seconds, until I can find a way to add a button onto the
        //screen that will manually update it
        boolean running = true;
        while(running) {
            try {
                Thread.sleep(2000);
                nextDay();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //loads a grid of buttons according to the width and height
    public static void loadBoard(Container container) {
        int newHeight = height + 2;
        int newWidth = width + 2;
        /*
        With this 2d array, 2 extra spaces for the width and height are added in order prevent IndexOutOfounds when
        checking for the amtNeighbors for each cell
         */
        buttons = new JButton[newHeight][newWidth];

        /* This first set of for loops fills the buttons arraylist with button objects, while the second
        set of for loops adds the buttons within a certain range into the conainer grid.
        The reason I wrote it like this was to add a buffer around the edges of the 2d array
        so that when amtNeighbors is called on a button on the edge, it wont throw an index out of bounds
        exception.
         */
        for(int r = 0; r < newHeight; r++) {
            for (int c = 0; c < newWidth; c++) {
                buttons[r][c] = new JButton();
                buttons[r][c].setBackground(space);
            }
        }
        for(int r = 1; r < newHeight - 1; r++) {
            for(int c = 1; c < newWidth - 1; c++) {
                buttons[r][c].setPreferredSize(new Dimension(10,10));
                container.add(buttons[r][c]);

                int finalC = c;
                int finalR = r;
                buttons[r][c].addActionListener(e -> setCell(finalR, finalC));
            }
        }
    }

    //sets an empty space in each cell, clearing the board
    public static void clearBoard() {
        for(int r = 1; r < height + 1; r++) {
            for(int c = 1; c < width + 1; c++) {
                buttons[r][c] = new JButton();
                buttons[r][c].setBackground(space);
            }
        }
    }

    //creates cell when empty button is clicked and removes it when a cell is clicked
    public static void setCell(int row, int col) {
        if (buttons[row][col].getBackground().equals(cell)) {
            buttons[row][col].setBackground(space);
        } else if(buttons[row][col].getBackground().equals(space)) {
            buttons[row][col].setBackground(cell);
        }
    }

    //progresses the simulation one day
    public static void nextDay() {
        for (int row = 1; row < height + 1; row++) {
            for (int col = 1; col < width + 1; col++) {
                if (buttons[row][col].getBackground().equals(cell)) {
                    if (amtNeighbors(row, col) == 2 || amtNeighbors(row, col) == 3) {
                        buttons[row][col].setBackground(cell);
                    } else {
                        buttons[row][col].setBackground(dead);
                    }
                }
                if (buttons[row][col].getBackground().equals(space)) {
                    if (amtNeighbors(row, col) == 3) {
                        buttons[row][col].setBackground(alive);
                    } else {
                        buttons[row][col].setBackground(space);
                    }
                }
            }
        }
        for (int row = 1; row < height + 1; row++) {
            for (int col = 1; col < width + 1; col++) {
                if (buttons[row][col].getBackground().equals(alive)) {
                    buttons[row][col].setBackground(cell);
                }
                if (buttons[row][col].getBackground().equals(dead)) {
                    buttons[row][col].setBackground(space);
                }
            }
        }
    }

    //calculates the amt of neighbors near a given cell
    public static int amtNeighbors(int row, int col) {
        //Not the best approach to finding the neighbors for a cell, but it works
        int neighbors = 0;
        if (buttons[row - 1][col].getBackground().equals(cell) || buttons[row - 1][col].getBackground().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row - 1][col- 1].getBackground().equals(cell) || buttons[row - 1][col - 1].getBackground().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row][col - 1].getBackground().equals(cell) || buttons[row][col - 1].getBackground().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row + 1][col - 1].getBackground().equals(cell) || buttons[row + 1][col - 1].getBackground().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row + 1][col].getBackground().equals(cell) || buttons[row + 1][col].getBackground().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row + 1][col + 1].getBackground().equals(cell) || buttons[row + 1][col + 1].getBackground().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row][col + 1].getBackground().equals(cell) || buttons[row][col + 1].getBackground().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row - 1][col + 1].getBackground().equals(cell) || buttons[row - 1][col + 1].getBackground().equals(dead)) {
            neighbors += 1;
        }
        return neighbors;
    }
}
