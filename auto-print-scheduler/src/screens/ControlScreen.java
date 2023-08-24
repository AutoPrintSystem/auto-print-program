package screens;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Vector;

public class ControlScreen extends JPanel {
    FileTable fileTable;
    public ControlScreen(){
        JLabel label = new JLabel("컨트롤화면입니다.");
        this.add(label);

        fileTable = new FileTable();
        JScrollPane scrollPane = new JScrollPane(fileTable);

        this.add(scrollPane);
    }

    public class FileTable extends JTable{
        private DefaultTableModel tableModel;
        private String[] headers={"번호","파일명","컬러/흑백","출력시간", "상태"};
        public FileTable(){
            Vector<String> header = createHeader(headers);


            tableModel = new DefaultTableModel(headers, 5) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells not editable
                }
            };
            this.setModel(tableModel);


        }
        public Vector<String> createHeader(String[] inputList) { // create Table Header
            Vector<String> outputList = new Vector<String>();
            Collections.addAll(outputList, inputList);
            return outputList;
        }
    }

}
