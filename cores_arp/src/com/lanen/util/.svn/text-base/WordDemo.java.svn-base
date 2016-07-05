package com.lanen.util;

import java.awt.*;
import java.io.IOException;

import com.lanen.model.Individual;
import com.lanen.util.WordUtils;
import com.lowagie.text.Cell;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.style.RtfParagraphStyle;

/**
 * Description:
 * User: flycloud
 * Date: 13-12-14
 * Time: 下午8:15
 */
public class WordDemo {

    /**
     * @return 带有背景颜色的table
     * @throws DocumentException
     */
    public Table insertBGColor() throws DocumentException {
        Table table = new Table(4);//生成一个四列的表格
        int width[] = {25, 25, 25, 25};
        table.setWidths(width);//设置系列所占比例
        table.setWidth(100);
        table.setAutoFillEmptyCells(true);
        table.setAlignment(Element.ALIGN_CENTER);//居中显示
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中显示
        table.setBorder(30);
        table.setBorderWidth(230);//边框宽度
        table.setSpacing(2);
        table.setPadding(3);
        table.setBorderColor(new Color(58, 255, 132));//边框颜色

        Cell cell = new Cell(new Phrase("列一"));
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(new Color(189, 22, 33));
        cell.setBackgroundColor(new Color(58, 137, 20));
        table.addCell(cell);

        Cell cell2 = new Cell(new Phrase("列二"));
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBorderColor(new Color(189, 22, 33));
        cell2.setBackgroundColor(new Color(137, 34, 44));
        table.addCell(cell2);

        Cell cell3 = new Cell(new Phrase("列三"));
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBorderColor(new Color(189, 22, 33));
        cell3.setBackgroundColor(new Color(232, 219, 48));
        table.addCell(cell3);

        Cell cell4 = new Cell(new Phrase("列四"));
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setBorderColor(new Color(189, 22, 33));
        cell4.setBackgroundColor(new Color(54, 246, 231));
        table.addCell(cell);

        for (int i = 0; i < 8; i++) {
            table.addCell(new Cell("自定义内容"));
        }
        return table;
    }

    /**
     * @return 复合表格的简单例子
     * @throws DocumentException
     */
    public Table insertComplexTable(Individual individual) throws DocumentException {
        Table table = new Table(6);
        int width[] = {20, 20, 20, 20, 20, 20};
        table.setWidths(width);//设置系列所占比例
        table.setWidth(100);
        table.setAutoFillEmptyCells(true);
        table.setAlignment(Element.ALIGN_CENTER);//居中显示
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中显示
        table.setBorder(1000);
        table.setBorderWidth(1);//边框宽度
        //table.setSpacing(2);
        //table.setPadding(3);
        table.setBorderColor(new Color(189, 22, 33));//边框颜色

        Cell cell = new Cell(new Phrase("占据三列的单元格"));
        cell.setColspan(3);//设置当前单元格占据的列数
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(new Color(189, 22, 33));
       // cell.setBackgroundColor(new Color(58, 137, 20));
        table.addCell(cell);

        Cell cell2 = new Cell(new Phrase("第四列"));
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBorderColor(new Color(189, 22, 33));
        // cell2.setBackgroundColor(new Color(137, 34, 44));
        table.addCell(cell2);

        Cell cell3 = new Cell(new Phrase("第五列"));
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBorderColor(new Color(189, 22, 33));
        // cell3.setBackgroundColor(new Color(232, 219, 48));
        table.addCell(cell3);
        
        Cell cell4 = new Cell(new Phrase("第五列"));
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setBorderColor(new Color(189, 22, 33));
        // cell3.setBackgroundColor(new Color(232, 219, 48));
        table.addCell(cell4);

        Cell cell5 = new Cell(new Phrase("占据两行的单元格"));
       // cell4.setBackgroundColor(new Color(232, 219, 48));
        cell5.setRowspan(2);
        table.addCell(cell5);

        for (int i = 0; i < 8; i++) {
            table.addCell(new Cell("自定义内容"));
        }

        Cell cell6 = new Cell(new Phrase("占据两行两列的单元格"));
       // cell5.setBackgroundColor(new Color(137, 34, 44));
        cell6.setRowspan(2);
        cell6.setColspan(2);
        table.addCell(cell6);

        for (int i = 0; i < 7; i++) {
            table.addCell(new Cell("自定义内容"));
        }
        return table;
    }
}
