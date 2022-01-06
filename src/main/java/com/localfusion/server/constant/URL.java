package com.localfusion.server.constant;

public class URL {

    private URL() {
    }

    public static class UserController {

        private UserController() {
        }

        private static final String ROOT = "/u";

        public static final String EXIST = ROOT + "/exist";
        public static final String GET = ROOT + "/get";
        public static final String GET_ROLE = ROOT + "/getRole";
        public static final String REGISTER = ROOT + "/register";
        public static final String REGISTER_ROLE = ROOT + "/registerRole";
        public static final String REMOVE = ROOT + "/remove";
        public static final String REMOVE_ROLE = ROOT + "/removeRole";
        public static final String UPDATE = ROOT + "/update";
        public static final String WX_LOGIN = ROOT + "/wxLogin";

        public static String wxLoginApi(final String code) {
            final String APPID = "wx1caea6c0156787be";
            final String SECRET = "d8f489346974e2d12db66e78a77f3004";
            return "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        }

    }

    public static class AdminController {

        private AdminController() {
        }

        private static final String ROOT = "/a";

        public static final String GET = ROOT + "/get";
        public static final String GET_NUMBER = ROOT + "/getNumber";
        public static final String GET_RULE = ROOT + "/getRule";
        public static final String SET_REQUIRED_CREDITS_PER_LEVEL = ROOT + "/setRequiredCreditsPerLevel";
        public static final String SET_DISCOUNT = ROOT + "/setDiscount";
        public static final String SET_CREDITS_PER100_YUAN = ROOT + "/setCreditsPer100Yuan";

    }

    public static class BusinessController {

        private BusinessController() {
        }

        private static final String ROOT = "/b";

        public static final String GET = ROOT + "/get";
        public static final String GET_NUMBER = ROOT + "/getNumber";
        public static final String DISTRIBUTE_CREDIT = ROOT + "/distributeCredit";
        public static final String CONSUME_CREDIT = ROOT + "/consumeCredit";
        public static final String INCREASE_TURNOVER = ROOT + "/increaseTurnover";
        public static final String SETTLE_CREDIT = ROOT + "/settleCredit";

    }

    public static class CustomerController {

        private CustomerController() {
        }

        private static final String ROOT = "/c";

        public static final String GET = ROOT + "/get";
        public static final String GET_NUMBER = ROOT + "/getNumber";
        public static final String UPDATE = ROOT + "/update";

    }

    public static class TransactionController {

        private TransactionController() {
        }

        private static final String ROOT = "/t";

        public static final String GET = ROOT + "/get";
        public static final String SUBMIT = ROOT + "/submit";

    }

    public static class ItemController {

        private ItemController() {
        }

        private static final String ROOT = "/i";

        public static final String GET = ROOT + "/get";
        public static final String ADD = ROOT + "/add";
        public static final String REMOVE = ROOT + "/remove";
        public static final String UPDATE = ROOT + "/update";
        public static final String GET_SALES_VOLUME = ROOT + "/getSalesVolume";
    }

    public static class UtilController {

        private UtilController() {
        }

        private static final String QR_API = "http://qr.topscan.com/api.php?text=";

        private static final String ROOT = "/z";

        private static final String QR = ROOT + "/qr";

        public static final String ITEM = QR + "/item";

        public static String itemQR(final int id) {
            return QR_API + id;
        }

    }

}
