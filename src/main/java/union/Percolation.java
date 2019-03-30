package union;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private int[][] siteState;
    private int numberOfOpenSites;
    private final int size;

    public Percolation(int n) {
        this.size = n;
        if (this.size <= 0) {
            throw new IllegalArgumentException("Index is below zero");
        }
        if (n == 1) {
            uf = new WeightedQuickUnionUF(size);
        } else {
            uf = new WeightedQuickUnionUF((size + 2) * size);

            for (int i = 0; i < size; ++i) {
                uf.union(i + size, 0);
                uf.union(size * size + i, size * (size + 2) - 1);
            }
        }

        siteState = new int[size][size];
        numberOfOpenSites = 0;

    }         // create size-by-size grid, with all sites blocked


    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        numberOfOpenSites++;
        siteState[row - 1][col - 1] = 1;

        openCell(row - 1, col, row, col);
        openCell(row, col - 1, row, col);
        openCell(row + 1, col, row, col);
        openCell(row, col + 1, row, col);

    }  // open site (row, col) if it is not open already


    public boolean isOpen(int row, int col) {
        validate(row, col);
        return siteState[row - 1][col - 1] == 1;
    } // is site (row, col) open?

    public boolean isFull(int row, int col) {
        validate(row, col);
        boolean open = isOpen(row, col);
        if (!open) {
            return false;
        }
        return uf.connected(0, (row) * size + (col - 1));
    } // is site (row, col) full?

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }       // number of open sites

    public boolean percolates() {
        return uf.connected(0, (2 + size) * size - 1);
    }              // does the system percolate?

    private void validate(int row, int col) {
        if (!isSiteCell(row - 1, col - 1)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isSiteCell(int row, int col) {
        int n = size;
        return !(row < 0 || row >= n || col < 0 || col >= n);

    }

    private void openCell(int row, int col, int rowToUnite, int colToUnite) {
        if (isSiteCell(row - 1, col - 1) && isOpen(row, col)) {
            uf.union(row * size + (col - 1), rowToUnite * size + colToUnite - 1);
        }
    }

}
