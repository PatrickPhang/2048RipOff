import java.util.Random;
import java.util.Scanner;

public class ripOff2048 {
    static int N = 2;
    static int T = 2048;
    // Nice to have #5
    static int skor = 0;
    static int[][] board;
    static boolean victory = false;
    static boolean end = false;
    static Random r;

    // Constructor
    public ripOff2048(int grid, int target) throws Exception {
        // Nice to have # 2
        if (grid <= 0) {
            throw new Exception("Error : Dimensi <= 0");
        }
        // Nice to have # 3
        if (target <= 1 || !isPowerOfTwo(target)) {
            throw new Exception("Error : Target bukan 2^n atau bukan positif");
        }
        N = grid;
        T = target;
        board = new int[N][N];
        r = new Random();
        initPut(2);
    }

    // Checks if an integer is n^2
    static boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }

        while (n != 1) {
            if (n % 2 != 0) {
                return false;
            }
            n = n / 2;
        }
        return true;
    }

    // Nice to have #1
    // Initializes board
    private static void initPut(int num) {
        int x;
        int y;
        int insert;
        for (int i = 0; i < num; ++i) {
            x = r.nextInt(N);
            y = r.nextInt(N);
            // Nice to have #1
            if (r.nextInt(9) == 1) {
                insert = 4;
            } else {
                insert = 2;
            }
            if (board[y][x] == 0) {
                board[y][x] = insert;
            } else {
                --i;
            }
        }
    }

    // Puts 1 random 2 in board
    private static void put(int num) {
        int x;
        int y;
        int insert;
        for (int i = 0; i < num; ++i) {
            x = r.nextInt(N);
            y = r.nextInt(N);
            if (board[y][x] == 0) {
                board[y][x] = 2;
            } else {
                --i;
            }
        }
    }

    // Simulates swiping up in 2048
    private static boolean moveUp() {
        boolean valid = false;
        for (int col = 0; col < N; ++col) {
            int currRow = 0;
            for (int row = 0; row < N; ++row) {
                if (board[row][col] != 0) {
                    board[currRow][col] = board[row][col];
                    if (currRow != row) {
                        board[row][col] = 0;
                        valid = true;
                    }
                    ++currRow;
                }
            }

        }
        for (int col = 0; col < N; ++col) {
            for (int row = 0; row < N - 1; ++row) {
                int curr = board[row][col];
                int next = board[row + 1][col];
                if (curr == next) {
                    board[row][col] += board[row + 1][col];
                    board[row + 1][col] = 0;
                    skor += board[row][col];
                    ++row;
                    if (curr != 0) {
                        valid = true;
                    }
                }
            }
        }
        for (int col = 0; col < N; ++col) {
            int currRow = 0;
            for (int row = 0; row < N; ++row) {
                if (board[row][col] != 0) {
                    board[currRow][col] = board[row][col];
                    if (currRow != row) {
                        board[row][col] = 0;
                        valid = true;
                    }
                    ++currRow;
                }
            }

        }
        return valid;
    }

    // Simulates swiping down in 2048
    private static boolean moveDown() {
        boolean valid = false;
        for (int col = 0; col < N; ++col) {
            int currRow = 0;
            for (int row = N - 1; row >= 0; --row) {
                if (board[row][col] != 0) {
                    board[N - 1 - currRow][col] = board[row][col];
                    if (N - 1 - currRow != row) {
                        board[row][col] = 0;
                        valid = true;
                    }
                    ++currRow;
                }
            }

        }
        for (int col = 0; col < N; ++col) {
            for (int row = N - 1; row >= 1; --row) {
                int curr = board[row][col];
                int next = board[row - 1][col];
                if (curr == next) {
                    board[row][col] += board[row - 1][col];
                    board[row - 1][col] = 0;
                    skor += board[row][col];
                    --row;
                    if (curr != 0) {
                        valid = true;
                    }
                }
            }

        }
        for (int col = 0; col < N; ++col) {
            int currRow = 0;
            for (int row = N - 1; row >= 0; --row) {
                if (board[row][col] != 0) {
                    board[N - 1 - currRow][col] = board[row][col];
                    if (N - 1 - currRow != row) {
                        board[row][col] = 0;
                        valid = true;
                    }
                    ++currRow;
                }
            }

        }
        return valid;
    }

    // Simulates swiping left in 2048
    private static boolean moveLeft() {
        boolean valid = false;
        for (int row = 0; row < N; ++row) {
            int currCol = 0;
            for (int col = 0; col < N; ++col) {
                if (board[row][col] != 0) {
                    board[row][currCol] = board[row][col];
                    if (currCol != col) {
                        board[row][col] = 0;
                        valid = true;
                    }
                    ++currCol;
                }
            }

        }
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < N - 1; ++col) {
                int curr = board[row][col];
                int next = board[row][col + 1];
                if (curr == next) {
                    board[row][col] += board[row][col + 1];
                    board[row][col + 1] = 0;
                    skor += board[row][col];
                    ++col;
                    if (curr != 0) {
                        valid = true;
                    }
                }
            }
        }
        for (int row = 0; row < N; ++row) {
            int currCol = 0;
            for (int col = 0; col < N; ++col) {
                if (board[row][col] != 0) {
                    board[row][currCol] = board[row][col];
                    if (currCol != col) {
                        board[row][col] = 0;
                        valid = true;
                    }
                    ++currCol;
                }
            }

        }
        return valid;
    }

    // Simulates swiping right in 2048
    private static boolean moveRight() {
        boolean valid = false;
        for (int row = 0; row < N; ++row) {
            int currCol = 0;
            for (int col = N - 1; col >= 0; --col) {
                if (board[row][col] != 0) {
                    board[row][N - 1 - currCol] = board[row][col];
                    if (N - 1 - currCol != col) {
                        board[row][col] = 0;
                        valid = true;
                    }
                    ++currCol;
                }
            }

        }
        for (int row = 0; row < N; ++row) {
            for (int col = N - 1; col >= 1; --col) {
                int curr = board[row][col];
                int next = board[row][col - 1];
                if (curr == next) {
                    board[row][col] += board[row][col - 1];
                    board[row][col - 1] = 0;
                    skor += board[row][col];
                    --col;
                    if (curr != 0) {
                        valid = true;
                    }
                }
            }

        }

        for (int row = 0; row < N; ++row) {
            int currCol = 0;
            for (int col = N - 1; col >= 0; --col) {
                if (board[row][col] != 0) {
                    board[row][N - 1 - currCol] = board[row][col];
                    if (N - 1 - currCol != col) {
                        board[row][col] = 0;
                        valid = true;
                    }
                    ++currCol;
                }
            }

        }
        return valid;
    }


    // Displays the current state of the board
    private static boolean show() {
        boolean ended = true;
        String curr = "";
        for (int x = 0; x < N; ++x) {
            curr += "----";
        }
        curr += "-";
        System.out.println(curr);
        for (int row = 0; row < N; ++row) {
            curr = "";
            for (int col = 0; col < N; ++col) {
                if (board[row][col] == 0) {
                    ended = false;
                    curr += "| " + " " + " ";
                } else {
                    curr += "| " + Integer.toString(board[row][col]) + " ";
                    if (board[row][col] == T) {
                        victory = true;
                        end = true;
                    }
                }
            }
            curr += "|";
            System.out.println(curr);
        }
        curr = "";
        for (int x = 0; x < N; ++x) {
            curr += "----";
        }
        curr += "-";
        System.out.println(curr);
        if (end) {
            return true;
        }
        return ended;
    }


    // Checks if it is not possible to move anymore
    private static void checkLoss() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N - 1; ++j) {
                int curr = board[i][j];
                int next = board[i][j + 1];
                if (curr == 0 || next == 0 || curr == next) {
                    end = false;
                    return;
                }
            }
        }
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N - 1; ++j) {
                int curr = board[j][i];
                int next = board[j + 1][i];
                if (curr == 0 || next == 0 || curr == next) {
                    end = false;
                    return;
                }
            }
        }
        end = true;
    }

    // Main function
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to 2048(parody, please don't sue)!");
        System.out.println("Please input the size of your grid : ");
        int n = Integer.valueOf(in.nextLine());
        ;
        System.out.println("Now enter your target integer (doesn't have to be 2048 : ");
        int t = Integer.valueOf(in.nextLine());
        ripOff2048 game = new ripOff2048(n, t);
        end = show();
        boolean valid;
        while (!end && !victory) {
            System.out.println("Whats your next move? : ");
            String move = in.nextLine().toLowerCase();
            valid = false;
            switch (move) {
                case "w":
                    valid = moveUp();
                    break;
                case "s":
                    valid = moveDown();
                    break;
                case "a":
                    valid = moveLeft();
                    break;
                case "d":
                    valid = moveRight();
                    break;
                default:
                    System.out.println("Invalid move");
                    break;
            }
            if (valid && !end && !victory) {
                put(1);
            }
            end = show();
            checkLoss();
        }
        if (victory) {
            System.out.println("Congrats you won");
            System.out.print("Your Score is : ");
            System.out.print(skor);

        } else {
            System.out.println("Dang, try again next time!");
            System.out.print("Your Score is : ");
            System.out.print(skor);
        }
    }
}
