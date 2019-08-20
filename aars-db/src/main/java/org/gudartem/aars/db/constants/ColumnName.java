package org.gudartem.aars.db.constants;

public class ColumnName {

    public interface HasId {
        String ID = "id";
    }

    public interface HasRevision {
        String REVISION = "revision";
    }

    public interface HasThemeId {
        String THEME_ID = "theme_id";
    }

    public interface HasInventoryCardId {
        String INVENTORY_CARD_ID = "inventory_card_id";
    }

    public static class Applicability implements HasId, HasInventoryCardId, HasRevision {
        public static final String APPLICABILITY_DATE = "applicability_date";
        public static final String DESIGNATION = "designation";
        public static final String CIPHER = "cipher";
        public static final String APP_INVENTORY_CARD_ID = "app_inventory_card_id";
    }

    public static class CardToFormat implements HasInventoryCardId {
        public static final String FORMAT_ID = "format_id";
    }

    public static class CopiesInfo implements HasId, HasInventoryCardId, HasRevision {
        public static final String COPY_DATE = "copy_date";
        public static final String RECEIVED_COPY = "received_copy";
        public static final String ANNULLED_COPY = "annulled_copy";
        public static final String DESIGNATION = "designation";
        public static final String EMPLOYEE_ID = "employee_id";
    }

    public static class Directory implements HasId, HasThemeId, HasRevision {
        public static final String DIRECTORY_NAME = "directory_name";
        public static final String DIRECTORY_TYPE = "directory_type";
        public static final String PARENT_ID = "parent_id";
    }

    public static class Employee implements HasId, HasRevision {
        public static final String EMPLOYEE_NAME = "employee_name";
    }

    public static class Format implements HasId, HasRevision {
        public static final String FORMAT_NAME = "format_name";
    }

    public static class InventoryCard implements HasId, HasThemeId, HasRevision {
        public static final String INVENTORY_NUMBER = "inventory_number";
        public static final String INVENTORY_NUMBER_SUF = "inventory_number_suf";
        public static final String CARD_NAME = "card_name";
        public static final String DESIGNATION = "designation";
        public static final String CIPHER = "cipher";
        public static final String SHEET_COUNT = "sheet_count";
        public static final String TL = "tl";
        public static final String VTD = "vtd";
        public static final String MK = "mk";
        public static final String MKT = "mkt";
        public static final String VOPTK = "voptk";
        public static final String OKUFSB = "okufsb";
        public static final String OKUFREG = "okufreg";
        public static final String CARD_TYPE = "card_type";
        public static final String CARD_DATE = "card_date";
        public static final String STATE = "state";
        public static final String ANNULLED_DATE = "annulled_date";
        public static final String DIRECTORY_ID = "directory_id";
    }

    public static class OnceOnlyIssue implements HasId, HasInventoryCardId, HasRevision {
        public static final String ISSUE_DATE = "issue_date";
        public static final String EX_NUMBER = "ex_number";
        public static final String DESIGNATION  = "designation";
        public static final String TO_WHOM = "to_whom";
    }

    public static class Stocktaking implements HasId, HasInventoryCardId, HasRevision {
        public static final String CHANGING = "changing";
        public static final String DOC_NUMBER = "doc_number";
        public static final String DATE_CHANGING = "date_changing";
        public static final String CHANGED_SHEETS = "changed_sheets";
    }

    public static class Subscriber implements HasId, HasThemeId, HasRevision {
        public static final String SUBSCRIBER_NAME = "subscriber_name";
        public static final String SUBSCRIBE_DATE = "subscribe_date";
        public static final String EX_NUMBER = "ex_number";
        public static final String DESIGNATION = "designation";
        public static final String ANNULLED = "annulled";
    }

    public static class Theme implements HasId, HasRevision {
        public static final String THEME_NAME = "theme_name";
        public static final String ARCHIVE_DATE = "archive_date";
        public static final String CIPHER = "cipher";
        public static final String HAS_CHANGES = "has_changes";
    }
}