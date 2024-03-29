package com.mcan.ykb.unitcase.utils;

import com.mcan.ykb.unitcase.exception.LeaveRequestException;

public class RequestCheckResult {
    private boolean isAcceptable;
    private LeaveRequestException leaveRequestException;

    public RequestCheckResult() {
        this.isAcceptable = true;
        this.leaveRequestException = null;
    }

    public boolean isAcceptable() {
        return isAcceptable;
    }

    public void setAcceptable(boolean acceptable) {
        isAcceptable = acceptable;
    }

    public LeaveRequestException getLeaveRequestException() {
        return leaveRequestException;
    }

    public void setLeaveRequestException(LeaveRequestException leaveRequestException) {
        this.leaveRequestException = leaveRequestException;
    }
}
