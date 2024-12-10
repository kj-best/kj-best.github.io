package com.cow.horse.service;

import com.cow.horse.model.AdminModel;
import com.cow.horse.vo.PageVo;

public interface AdminService {

    AdminModel login(String accountNumber, String adminPassword);

    PageVo<AdminModel> getAdminList(int page, int nums);

    boolean addAdmin(AdminModel adminModel);


}
