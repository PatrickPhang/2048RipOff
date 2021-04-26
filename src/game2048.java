import java.util.*;

public class game2048 {
    static int N = 2;
    static int T = 2048;
    static int[][] board;
    static boolean victory = false;
    static boolean end = false;
    static Random r;
    public game2048(int grid, int target) {
        N = grid;
        T = target;
        board = new int[N][N];
        r = new Random();
        put(2);
    }

    private static void putTest() {
        for (int i = 0 ; i < N; ++i) {
            for (int j = 0 ; j < N; ++j) {
                board[j][i] = 2;
            }
        }
    }

    private static void put(int num) {
        int x;
        int y;
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

    private static boolean moveUp() {
        boolean valid = false;
        for (int col = 0; col < N; ++col) {
            int currRow = 0;
            int currTot = board[currRow][col];
            for (int nextRow = 1; nextRow < N; ++nextRow) {
                if (board[currRow][col] == 0) {
                    if (board[nextRow][col] != 0) {
                        currTot += board[nextRow][col];
                        board[nextRow][col] = 0;
                        valid = true;
                    }
                } else if (board[currRow][col] == board[nextRow][col]) {
                    currTot += board[nextRow][col];
                    board[nextRow][col] = 0;
                    valid = true;
                    board[currRow][col] = currTot;
                    if (currTot == T) {
                        victory = true;
                        end = true;
                    }
                    currTot = 0;
                    ++currRow;
                } else {
                    board[currRow][col] = currTot;
                    if (currTot == T) {
                        victory = true;
                        end = true;
                    }
                    currTot = 0;
                    ++currRow;
                }
            }
            board[currRow][col] = currTot;
            if (currTot == T) {
                victory = true;
                end = true;
            }
            currTot = 0;
            ++currRow;
        }
        return valid;
    }

    private static boolean moveDown() {
        boolean valid = false;
        for (int col = 0; col < N; ++col) {
            int currRow = N - 1;
            int currTot = board[currRow][col];
            for (int nextRow = currRow - 1; nextRow >= 0; --nextRow) {
                if (board[currRow][col] == 0) {
                    if (board[nextRow][col] != 0) {
                        currTot += board[nextRow][col];
                        board[nextRow][col] = 0;
                        valid = true;
                    }
                } else if (board[currRow][col] == board[nextRow][col]) {
                    currTot += board[nextRow][col];
                    board[nextRow][col] = 0;
                    valid = true;
                    board[currRow][col] = currTot;
                    if (currTot == T) {
                        victory = true;
                        end = true;
                    }
                    currTot = 0;
                    --currRow;
                } else {
                    board[currRow][col] = currTot;
                    if (currTot == T) {
                        victory = true;
                        end = true;
                    }
                    currTot = 0;
                    --currRow;
                }
            }
            board[currRow][col] = currTot;
            if (currTot == T) {
                victory = true;
                end = true;
            }
            currTot = 0;
            --currRow;
        }
        return true;
    }

    private static boolean moveLeft() {
        boolean valid = false;
        for (int row = 0; row < N; ++row) {
            int currCol = 0;
            int currTot = board[row][currCol];
            for (int nextCol = 1; nextCol < N; ++nextCol) {
                if (board[row][currCol] == 0) {
                    if (board[row][nextCol] != 0) {
                        currTot += board[row][nextCol];
                        board[row][nextCol] = 0;
                        valid = true;
                    }
                } else if (board[row][currCol] == board[row][nextCol]) {
                    currTot += board[row][nextCol];
                    board[row][nextCol] = 0;
                    valid = true;
                    board[row][currCol] = currTot;
                    if (currTot == T) {
                        victory = true;
                        end = true;
                    }
                    currTot = 0;
                    ++currCol;
                } else {
                    board[row][currCol] = currTot;
                    if (currTot == T) {
                        victory = true;
                        end = true;
                    }
                    currTot = 0;
                    ++currCol;
                }
            }
            board[row][currCol] = currTot;
            if (currTot == T) {
                victory = true;
                end = true;
            }
            currTot = 0;
            ++currCol;
        }
        return valid;
    }

    private static boolean moveRight() {
        boolean valid = false;
        for (int row = 0; row < N; ++row) {
            int currCol = N - 1;
            int currTot = board[row][currCol];
            for (int nextCol = currCol - 1; nextCol >= 0; --nextCol) {
                if (board[row][currCol] == 0) {
                    if (board[row][nextCol] != 0) {
                        currTot += board[row][nextCol];
                        board[row][nextCol] = 0;
                        valid = true;
                    }
                } else if (board[row][currCol] == board[row][nextCol]) {
                    currTot += board[row][nextCol];
                    board[row][nextCol] = 0;
                    valid = true;
                    board[row][currCol] = currTot;
                    if (currTot == T) {
                        victory = true;
                        end = true;
                    }
                    currTot = 0;
                    --currCol;
                } else {
                    board[row][currCol] = currTot;
                    if (currTot == T) {
                        victory = true;
                        end = true;
                    }
                    currTot = 0;
                    --currCol;
                }
            }
            board[row][currCol] = currTot;
            if (currTot == T) {
                victory = true;
                end = true;
            }
            currTot = 0;
            --currCol;
        }
        return valid;
    }

    private static boolean display() {
        boolean ended = true;
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < N; ++col) {
                if (board[row][col] == 0) {
                    ended = false;
                } else {
                    if (board[row][col] == T) {
                        victory = true;
                        end = true;
                    }
                }
            }
        }
        if (end) {
            return true;
        }
        return ended;
    }

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



    private static void checkLoss() {
        for (int i = 0 ; i < N; ++i) {
            for (int j = 0 ; j < N - 1; ++j) {
                int curr = board[i][j];
                int next = board[i][j + 1];
                if (curr == 0 || next == 0 || curr == next) {
                    return;
                }
            }
        }
        for (int i = 0 ; i < N; ++i) {
            for (int j = 0 ; j < N - 1; ++j) {
                int curr = board[j][i];
                int next = board[j + 1][i];
                if (curr == 0 || next == 0 || curr == next) {
                    return;
                }
            }
        }
        end = true;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to 2048(parody, please don't sue)!");
        System.out.println("Please input the size of your grid : ");
        int n = Integer.valueOf(in.nextLine());;
        System.out.println("Now enter your target integer (doesn't have to be 2048 : ");
        int t = Integer.valueOf(in.nextLine());
        game2048 game = new game2048(n, t);
        end = display();
        boolean valid;
        while (!end) {
            System.out.println("Whats your next move? : ");
            String move = in.nextLine().toLowerCase();
            valid = false;
            switch (move) {
                case "w" :
                    valid = moveUp();
                    break;
                case "s" :
                    valid = moveDown();
                    break;
                case "a" :
                    valid = moveLeft();
                    break;
                case "d" :
                    valid = moveRight();
                    break;
                default :
                    System.out.println("Invalid move");
                    break;
            }
            if (valid) {
                put(1);
            }
            end = display();
            if (!end) {
                show();
            }
            checkLoss();
        }
        if (victory) {
            System.out.println("Congrats you won");
        } else {
            System.out.println("Dang, try again next time!");
        }
    }



}
