package com.example.carapp.Controller.CallBacks;

import com.example.carapp.Entites.Report;

public interface SelectReportCallBack {
    void onSuccess(Report report);
    void onfailure(String error);
}
