package com.malynovsky.restapp.reporting;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;

public enum ItemType {
    TEXT(CellType.STRING) {
        @Override
        void setValue(CreationHelper helper, Cell cellConsumer, Object value) {
            cellConsumer.setCellValue((String) value);
        }
    },
    HYPERLINK(CellType.STRING) {
        @Override
        void setValue(CreationHelper helper, Cell cellConsumer, Object value) {
            Hyperlink hyperlink = helper.createHyperlink(HyperlinkType.URL);
            hyperlink.setAddress((String) value);
            hyperlink.setLabel(((String) value).split("=")[1].split("-")[1]);
            cellConsumer.setHyperlink(hyperlink);
        }
    },
    NUMERIC(CellType.NUMERIC) {
        @Override
        void setValue(CreationHelper helper, Cell cellConsumer, Object value) {
            cellConsumer.setCellValue(1.0 * ((Integer) value));
        }
    };

    private final CellType type;

    ItemType(CellType string) {
        this.type = string;
    }

    public CellType getType() {
        return type;
    }

    abstract void setValue(CreationHelper helper, Cell cellConsumer, Object value);
}
