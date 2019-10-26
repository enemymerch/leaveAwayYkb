package com.mcan.ykb.unitcase.utils;

import org.apache.tomcat.util.http.parser.Authorization;

public class Constants {
    public static class LeaveRequestStatus {
        public static final String PENDING         = "PENDING";
        public static final String APPROVED        = "APPROVED";
        public static final String REJECTE         = "REJECTE";
    }

    public static class HTTP_HEADER {
        public static final String AUTHORIZATION = "Authorization";
    }

    public static class Authorization {
        public static final String userName                 = "admin";
        public static final String password                 = "admin";
        public static final String basicAuthorizationToken = "Basic YWRtaW46YWRtaW4=";
    }
}
