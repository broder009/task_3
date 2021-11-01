package wintable;

import com.bethecoder.ascii_table.ASCIITable;

import java.util.Arrays;

public class WinTable {

    private final String[] header;
    private final String[][] data;

    public WinTable(String[] header, String[][] data) {
        this.header = header;
        this.data = data;
    }

    public String[][] getData() {
        return data;
    }

    public String[] getHeader() {
        return header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinTable winTable = (WinTable) o;
        return Arrays.equals(header, winTable.header) && Arrays.deepEquals(data, winTable.data);
    }

    @Override
    public String toString() {
        return ASCIITable.getInstance().getTable(header, ASCIITable.ALIGN_CENTER, data, ASCIITable.ALIGN_CENTER);
    }
}