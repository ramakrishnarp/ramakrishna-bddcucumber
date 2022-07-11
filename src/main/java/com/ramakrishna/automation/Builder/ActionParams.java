package com.ramakrishna.automation.Builder;

public class ActionParams {

    private String byType;
    private String fileName;
    private String locator;
    private int position;
    private int dropdownOptionPosition;
    private int waitTimeInSec;
    private Boolean byJS;
    private String text;
    private String attributeName;
    private Boolean returnKey;
    private Boolean scrollTo;
;

    public ActionParams(String byType,
                        String fileName, String locator,
                        int position,
                        int dropdownOptionPosition,
                        int waitTimeInSec,
                        Boolean byJS,
                        String text,
                        String attributeName,
                        Boolean returnKey,
                        Boolean scrollTo
    ) {
        this.byType = byType;
        this.fileName = fileName;
        this.locator = locator;
        this.position = position;
        this.dropdownOptionPosition = dropdownOptionPosition;
        this.waitTimeInSec = waitTimeInSec;
        this.byJS = byJS;
        this.text = text;
        this.attributeName = attributeName;
        this.returnKey = returnKey;
        this.scrollTo = scrollTo;
    }

    public ActionParams() {

    }

    public static class Params { // builder
        private String getByType;
        private String getFileName = "";
        private String getLocator;
        private int getPosition = 0;
        private int getDropdownOptionPosition = 0;
        private int getWaitTimeInSec = 20;
        private Boolean getByJS = false;
        private String getText = "";
        private String getAttributeName = "";
        private Boolean getReturnKey = false;
        private Boolean getScrollTo = true;

        public Params() {
        }

        public Params setByType(String byType) {
            this.getByType = byType;
            return this;
        }

        public String getByType() {
            return getByType;
        }

        public Params setFileName(String fileName) {
            this.getFileName = fileName;
            return this;
        }

        public String getFileName() {
            return getFileName;
        }

        public Params setLocator(String locator) {
            this.getLocator = locator;
            return this;
        }

        public String getLocator() {
            return getLocator;
        }

        public Params setPosition(int position) {
            this.getPosition = position;
            return this;
        }

        public int getPosition() {
            return getPosition;
        }

        /**
         * Setter used in method actionSelectOptionByPosition().
         * <p>The setter subtracts 1 because the position is being passed and the method uses the index.</p>
         * @param dropdownOptionPosition uses the position of option not the index.
         * @return the getter value  of dropdownOptionPosition.
         */
        public Params setDropdownOptionPosition(int dropdownOptionPosition) {
            // subtract one passing in the position but the method uses the index
            this.getDropdownOptionPosition = dropdownOptionPosition - 1;
            return this;
        }

        public int getDropdownOptionPosition() {
            return getDropdownOptionPosition;
        }

        public Params setWaitTimeInSec(int waitTimeInSec) {
            this.getWaitTimeInSec = waitTimeInSec;
            return this;
        }

        public int getWaitTimeInSec() {
            return getWaitTimeInSec;
        }

        public Params setByJS(Boolean byJS) {
            this.getByJS = byJS;
            return this;
        }

        public Boolean getByJS() {
            return getByJS;
        }

        public Params setText(String text) {
            this.getText = text;
            return this;
        }

        public String getText() {
            return getText;
        }

        public Params setAttributeName(String attributeName) {
            this.getAttributeName = attributeName;
            return this;
        }

        public String getAttributeName() {
            return getAttributeName;
        }

        public Params setReturnKey(Boolean returnKey) {
            this.getReturnKey = returnKey;
            return this;
        }

        public Boolean getReturnKey() {
            return getReturnKey;
        }

        public Params setScrollTo(Boolean scrollTo) {
            this.getScrollTo = scrollTo;
            return this;
        }

        public Boolean getScrollTo() {
            return getScrollTo;
        }

        public ActionParams build() {
            return new ActionParams(getByType, getFileName, getLocator, getPosition, getDropdownOptionPosition, getWaitTimeInSec, getByJS, getText,
                    getAttributeName, getReturnKey, getScrollTo);
        }
    }
}
