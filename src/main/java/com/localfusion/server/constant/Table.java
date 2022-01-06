package com.localfusion.server.constant;

public class Table {

    private Table() {
    }

    public static class User {

        private User() {
        }

        public static final String ID = "id";
        public static final String VISIBLE = "visible";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String NICKNAME = "nickname";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";

    }

    public static class Admin {

        private Admin() {
        }

        public static final String ID = "id";
        public static final String LINK = "link";

    }

    public static class Business {

        private Business() {
        }

        public static final String ID = "id";
        public static final String LINK = "link";
        public static final String NAME = "name";
        public static final String DISTRIBUTED_CREDIT = "distributed_credit";
        public static final String CONSUMED_CREDIT = "consumed_credit";
        public static final String TURNOVER = "turnover";
        public static final String COMMENT = "comment";

    }

    public static class Customer {

        private Customer() {
        }

        public static final String ID = "id";
        public static final String LINK = "link";
        public static final String LEVEL = "level";
        public static final String CREDIT = "credit";
        public static final String BALANCE = "balance";
        public static final String COMMENT = "comment";
        public static final String EXP = "exp";

    }

    public static class Item {

        private Item() {
        }

        public static final String ID = "id";
        public static final String BUSINESS_ID = "business_id";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String PICTURE = "picture";
        public static final String SALES_VOLUME = "sales_volume";

        public static final String IS_DISCOUNTED = "is_discounted";
        public static final String DISCOUNT = "discount";
        public static final String IS_CREDITED = "is_credited";
        public static final String HOW_MANY_CREDITS = "how_many_credits";
        public static final String HOW_MUCH_MONEY = "how_much_money";
        public static final String SPECIAL = "special";
        public static final String SPECIAL_PRICE = "special_price";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
    }

    public static class Rule {

        private Rule() {
        }

        public static final String ID = "id";

        public static final String FIRST_TO_SECOND = "first_to_second";
        public static final String SECOND_TO_THIRD = "second_to_third";
        public static final String THIRD_TO_FOURTH = "third_to_fourth";
        public static final String FOURTH_TO_FIFTH = "fourth_to_fifth";
        public static final String FIFTH_TO_SIXTH = "fifth_to_sixth";

        public static final String DISCOUNT_LEVEL1 = "discount_level1";
        public static final String DISCOUNT_LEVEL2 = "discount_level2";
        public static final String DISCOUNT_LEVEL3 = "discount_level3";
        public static final String DISCOUNT_LEVEL4 = "discount_level4";
        public static final String DISCOUNT_LEVEL5 = "discount_level5";
        public static final String DISCOUNT_LEVEL6 = "discount_level6";

        public static final String CREDITS_PER100_YUAN = "credits_per100_yuan";

    }

    public static class Transaction {

        private Transaction() {
        }

        public static final String ID = "id";
        public static final String TRANSFEROR = "transferor";
        public static final String TRANSFEROR_ROLE = "transferor_role";
        public static final String TRANSFEREE = "transferee";
        public static final String TRANSFEREE_ROLE = "transferee_role";
        public static final String TIME = "time";
        public static final String CREDIT = "credit";
        public static final String MONEY = "money";
        public static final String COMMENT = "comment";
        public static final String EXP = "exp";
    }

}
