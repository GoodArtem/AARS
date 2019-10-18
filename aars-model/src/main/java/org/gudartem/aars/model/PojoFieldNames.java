package org.gudartem.aars.model;

public class PojoFieldNames {
    public interface HasId {
        String ID = "id";
    }

    public interface HasRevision {
        String REVISION = "revision";
    }

    public interface HasThemeId {
        String THEME_ID = "themeId";
    }

    public interface HasInventoryCardId {
        String INVENTORY_CARD_ID = "inventoryCardId";
    }

    public static class Applicability implements HasId, HasInventoryCardId, HasRevision {
        public static final String APPLICABILITY_DATE = "applicabilityDate";
        public static final String DESIGNATION = "designation";
        public static final String CIPHER = "cipher";
        public static final String APP_INVENTORY_CARD_ID = "appInventoryCardId";
    }

    public static class CardToFormat implements HasInventoryCardId {
        public static final String FORMAT_ID = "formatId";
    }

    public static class CopiesInfo implements HasId, HasInventoryCardId, HasRevision {
        public static final String COPY_DATE = "copyDate";
        public static final String RECEIVED_COPY = "receivedCopy";
        public static final String ANNULLED_COPY = "annulledCopy";
        public static final String DESIGNATION = "designation";
        public static final String EMPLOYEE_ID = "employeeId";
    }

    public static class Directory implements HasId, HasThemeId, HasRevision {
        public static final String DIRECTORY_NAME = "directoryName";
        public static final String DIRECTORY_TYPE = "directoryType";
        public static final String PARENT_ID = "parentId";
    }

    public static class Employee implements HasId, HasRevision {
        public static final String EMPLOYEE_NAME = "employeeName";
    }

    public static class Format implements HasId, HasRevision {
        public static final String FORMAT_NAME = "formatName";
    }

    public static class InventoryCard implements HasId, HasThemeId, HasRevision {
        public static final String INVENTORY_NUMBER = "inventoryNumber";
        public static final String INVENTORY_NUMBER_SUF = "inventoryNumberSuf";
        public static final String CARD_NAME = "cardName";
        public static final String DESIGNATION = "designation";
        public static final String CIPHER = "cipher";
        public static final String SHEET_COUNT = "sheetCount";
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
        public static final String ANNULLED_DATE = "annulledDate";
        public static final String DIRECTORY_ID = "directoryId";
    }

    public static class OnceOnlyIssue implements HasId, HasInventoryCardId, HasRevision {
        public static final String ISSUE_DATE = "issueDate";
        public static final String EX_NUMBER = "exNumber";
        public static final String DESIGNATION  = "designation";
        public static final String TO_WHOM = "toWhom";
    }

    public static class Stocktaking implements HasId, HasInventoryCardId, HasRevision {
        public static final String CHANGING = "changing";
        public static final String DOC_NUMBER = "docNumber";
        public static final String DATE_CHANGING = "dateChanging";
        public static final String CHANGED_SHEETS = "changedSheets";
    }

    public static class Subscriber implements HasId, HasThemeId, HasRevision {
        public static final String SUBSCRIBER_NAME = "subscriberName";
        public static final String SUBSCRIBE_DATE = "subscribeDate";
        public static final String EX_NUMBER = "exNumber";
        public static final String DESIGNATION = "designation";
        public static final String ANNULLED = "annulled";
    }

    public static class Theme implements HasId, HasRevision {
        public static final String THEME_NAME = "themeName";
        public static final String ARCHIVE_DATE = "archiveDate";
        public static final String CIPHER = "cipher";
        public static final String HAS_CHANGES = "hasChanges";
    }
}
