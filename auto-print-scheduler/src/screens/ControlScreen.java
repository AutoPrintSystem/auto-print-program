package screens;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Vector;

public class ControlScreen extends JPanel {
    FileTable fileTable;
    PrintTable printTable;
    public ControlScreen(){
        JLabel label = new JLabel("컨트롤화면입니다.");
        this.add(label);

        fileTable = new FileTable();
        JScrollPane scrollPane = new JScrollPane(fileTable);

        this.add(scrollPane);

        printTable = new PrintTable();
        JScrollPane scrollPane2 = new JScrollPane(printTable);

        this.add(scrollPane2);

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
    public class PrintTable extends JTable{
        private DefaultTableModel tableModel;
        private String[] headers={"사용 가능한 프린터기"};
        private Vector<String> printers;
        public PrintTable(){
            Vector<String> header = createHeader(headers);
            tableModel = new DefaultTableModel(headers,0){
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            this.setModel(tableModel);
            PrintManager printManager = new PrintManager();
            printers = new Vector<>();
            PrintService[] availablePrinter=printManager.refreshPrinter();

            // Replace this loop in the PrintTable constructor
            for (int i = 0; i < availablePrinter.length; i++) {
                Vector<String> rowData = new Vector<>();
                rowData.add(availablePrinter[i].toString());
                tableModel.addRow(rowData);
            }

        }
        public Vector<String> createHeader(String[] inputList) { // create Table Header
            Vector<String> outputList = new Vector<String>();
            Collections.addAll(outputList, inputList);
            return outputList;
        }

    }
    public class PrintManager{
        public PrintManager(){
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            for(int i=0;i<printServices.length;i++){
                System.out.println(printServices[i]);
            }

        }
        public PrintService[] refreshPrinter(){
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            return printServices;
        }
    }
}
