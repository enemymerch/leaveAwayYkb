package com.mcan.ykb.unitcase.utils;

public class Constants {
    public static final String[] holidays = {"01-01", "04-01", "04-23", "01-05", "05-19", "07-15", "08-30", "09-29"};

    public static class LeaveRequestStatus {
        public static final String PENDING          = "pending";
        public static final String APPROVED         = "approved";
        public static final String REJECTED         = "rejected";
    }

    public static class HTTP_HEADER {
        public static final String AUTHORIZATION            = "Authorization";
    }

    public static class Authorization {
        public static final String userName                 = "admin";
        public static final String password                 = "admin";
        public static final String basicAuthorizationToken  = "Basic YWRtaW46YWRtaW4=";
    }

    public static class Date{
        public static final long DaysInYear                      = 365;
    }


}
