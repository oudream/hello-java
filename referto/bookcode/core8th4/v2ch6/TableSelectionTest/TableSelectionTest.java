/**
 * @version 1.00 1999-07-17
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableSelectionTest
{  public static void main(String[] args)
   {  JFrame frame = new TableSelectionFrame();
      frame.show();
   }
}

class TableSelectionFrame extends JFrame
   implements ActionListener
{  public TableSelectionFrame()
   {  setTitle("TableSelectionTest");
      setSize(300, 200);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent e)
            {  System.exit(0);
            }
         } );

      // set up multiplication table

      model = new DefaultTableModel(10, 10);

      for (int i = 0; i < model.getRowCount(); i++)
         for (int j = 0; j < model.getColumnCount(); j++)
            model.setValueAt(
               new Integer((i + 1) * (j + 1)), i, j);

      table = new JTable(model);

      Container contentPane = getContentPane();
      contentPane.add(new JScrollPane(table), "Center");

      // create menu

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);

      JMenu selectionMenu = new JMenu("Selection");
      menuBar.add(selectionMenu);

      rowsItem = new JCheckBoxMenuItem("Rows");
      rowsItem.setSelected(table.getRowSelectionAllowed());
      rowsItem.addActionListener(this);
      selectionMenu.add(rowsItem);

      columnsItem = new JCheckBoxMenuItem("Columns");
      columnsItem.setSelected(table.getColumnSelectionAllowed());
      columnsItem.addActionListener(this);
      selectionMenu.add(columnsItem);

      cellsItem = new JCheckBoxMenuItem("Cells");
      cellsItem.setSelected(table.getCellSelectionEnabled());
      cellsItem.addActionListener(this);
      selectionMenu.add(cellsItem);

      JMenu tableMenu = new JMenu("Edit");
      menuBar.add(tableMenu);

      showColumnsItem = new JMenuItem("Show Columns");
      showColumnsItem.addActionListener(this);
      tableMenu.add(showColumnsItem);

      hideColumnsItem = new  JMenuItem("Hide Columns");
      hideColumnsItem.addActionListener(this);
      tableMenu.add(hideColumnsItem);

      addRowItem = new JMenuItem("Add Row");
      addRowItem.addActionListener(this);
      tableMenu.add(addRowItem);

      removeRowsItem = new  JMenuItem("Remove Rows");
      removeRowsItem.addActionListener(this);
      tableMenu.add(removeRowsItem);

      clearCellsItem = new  JMenuItem("Clear Cells");
      clearCellsItem.addActionListener(this);
      tableMenu.add(clearCellsItem);
   }

   public void actionPerformed(ActionEvent event)
   {  Object source = event.getSource();
      if (source == rowsItem)
      {  table.setRowSelectionAllowed(rowsItem.isSelected());
         table.clearSelection();
      }
      else if (source == columnsItem)
      {  table.setColumnSelectionAllowed(columnsItem.isSelected());
         table.clearSelection();
      }
      else if (source == cellsItem)
      {  table.setCellSelectionEnabled(cellsItem.isSelected());
         table.clearSelection();
      }
      else if (source == hideColumnsItem)
      {  int[] selected = table.getSelectedColumns();
         TableColumnModel columnModel = table.getColumnModel();

         /* remove columns from view, starting at the last
            index so that the column numbers aren't affected
         */

         for (int i = selected.length - 1; i >= 0; i--)
         {  TableColumn column
               = columnModel.getColumn(selected[i]);
            table.removeColumn(column);

            // store removed columns for "show columns" command

            removedColumns.add(column);
         }
      }
      else if (source == showColumnsItem)
      {  // restore all removed columns
         for (int i = 0; i < removedColumns.size(); i++)
            table.addColumn((TableColumn)removedColumns.get(i));
         removedColumns.clear();
      }
      else if (source == removeRowsItem)
      {  int[] selected = table.getSelectedRows();

         /* remove rows from model, starting at the last
            index so that the row numbers aren't affected
         */

         for (int i = selected.length - 1; i >= 0; i--)
            model.removeRow(selected[i]);
      }
      else if (source == addRowItem)
      {  // add a new row to the multiplication table in the model

         Integer[] newCells = new Integer[model.getColumnCount()];
         for (int i = 0; i < newCells.length; i++)
            newCells[i]
               = new Integer((i + 1) * (model.getRowCount() + 1));
         model.addRow(newCells);
      }
      else if (source == clearCellsItem)
      {  // set all selected cells to 0

         for (int i = 0; i < table.getRowCount(); i++)
            for (int j = 0; j < table.getColumnCount(); j++)
               if (table.isCellSelected(i, j))
                  table.setValueAt(new Integer(0), i, j);
      }
   }

   private DefaultTableModel model;
   private JTable table;

   private JMenuItem showColumnsItem;
   private JMenuItem hideColumnsItem;

   private JMenuItem addRowItem;
   private JMenuItem removeRowsItem;

   private JMenuItem clearCellsItem;

   private JCheckBoxMenuItem rowsItem;
   private JCheckBoxMenuItem columnsItem;
   private JCheckBoxMenuItem cellsItem;

   private ArrayList removedColumns = new ArrayList();
}
