package org.gudartem.aars.db.model.entity;

import org.gudartem.aars.db.model.abstraction.AbstractHasId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

import static org.gudartem.aars.db.constants.ColumnName.Format.FORMAT_NAME;
import static org.gudartem.aars.db.constants.TableName.FORMAT;

@Entity
@Table(name = FORMAT)
public class Format extends AbstractHasId<UUID> {

    @Column(name = FORMAT_NAME)
    private String formatName;

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }
}
