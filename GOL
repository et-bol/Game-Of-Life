import javax.swing.*;
import java.awt.*;
public class GOL {
    private static JButton[][] buttons;

    //sets keys for the different types of cells
    private static final String cell = "X";
    private static final String space = "O";
    private static final String alive = "%";
    private static final String dead = "^";

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

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Next Day");
        m1.addActionListener(e -> nextDay());

        mb.add(m1);

        frame.setSize(900,900);
        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.SOUTH);

        grid.setLayout(new GridLayout(height,width));

        //Loading golBoard
        loadBoard(grid);

        frame.add(grid,BorderLayout.CENTER);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.setVisible(true);
    }

    //loads a grid of buttons according to the width and height
    public static void loadBoard(Container container) {
        buttons = new JButton[height][width];

        for(int r = 0; r < height; r++) {
            for(int c = 0; c < width; c++) {
                JPanel panel = new JPanel();
                JButton button = new JButton(space);
                button.setPreferredSize(new Dimension(20,20));
                buttons[r][c] = button;
                container.add(button);
                int finalC = c;
                int finalR = r;
                buttons[r][c].addActionListener(e -> setCell(finalR, finalC));
            }
        }
    }

    //sets an empty space in each cell, clearing the board
    public static void clearBoard() {
        for(int r = 0; r < height; r++) {
            for(int c = 0; c < width; c++) {
                buttons[r][c] = new JButton(space);
            }
        }
    }

    //creates cell when empty button is clicked and removes it when a cell is clicked
    public static void setCell(int row, int col) {
        if (buttons[row][col].getText().equals(cell)) {
            buttons[row][col].setText(space);
        } else if(buttons[row][col].getText().equals(space)) {
            buttons[row][col].setText(cell);
        }
    }

    //progresses the simulation one day
    public static void nextDay() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (buttons[row][col].getText().equals(cell)) {
                    if (amtNeighbors(row, col) == 2 || amtNeighbors(row, col) == 3) {
                        buttons[row][col].setText(cell);
                    } else {
                        buttons[row][col].setText(dead);
                    }
                }
                if (buttons[row][col].getText().equals(space)) {
                    if (amtNeighbors(row, col) == 3) {
                        buttons[row][col].setText(alive);
                    } else {
                        buttons[row][col].setText(space);
                    }
                }
            }
        }
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (buttons[row][col].getText().equals(alive)) {
                    buttons[row][col].setText(cell);
                }
                if (buttons[row][col].getText().equals(dead)) {
                    buttons[row][col].setText(space);
                }
            }
        }
    }

    //calculates the amt of neighbors near a given cell
    public static int amtNeighbors(int row, int col) {
        int neighbors = 0;
        if (buttons[row - 1][col].getText().equals(cell) || buttons[row - 1][col].getText().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row - 1][col- 1].getText().equals(cell) || buttons[row - 1][col - 1].getText().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row][col - 1].getText().equals(cell) || buttons[row][col - 1].getText().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row + 1][col - 1].getText().equals(cell) || buttons[row + 1][col - 1].getText().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row + 1][col].getText().equals(cell) || buttons[row + 1][col].getText().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row + 1][col + 1].getText().equals(cell) || buttons[row + 1][col + 1].getText().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row][col + 1].getText().equals(cell) || buttons[row][col + 1].getText().equals(dead)) {
            neighbors += 1;
        }
        if (buttons[row - 1][col + 1].getText().equals(cell) || buttons[row - 1][col + 1].getText().equals(dead)) {
            neighbors += 1;
        }
        return neighbors;
    }
}
