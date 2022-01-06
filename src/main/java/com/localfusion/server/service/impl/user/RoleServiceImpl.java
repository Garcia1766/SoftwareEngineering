package com.localfusion.server.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.UserRole;
import com.localfusion.server.entity.user.Admin;
import com.localfusion.server.entity.user.Business;
import com.localfusion.server.entity.user.Customer;
import com.localfusion.server.entity.user.Role;
import com.localfusion.server.mapper.user.RoleMapper;
import com.localfusion.server.service.user.IAdminService;
import com.localfusion.server.service.user.IBusinessService;
import com.localfusion.server.service.user.ICustomerService;
import com.localfusion.server.service.user.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * user role service implementation
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private IAdminService adminService;
    private IBusinessService businessService;
    private ICustomerService customerService;

    public RoleServiceImpl(final AdminServiceImpl adminService,
                           final BusinessServiceImpl businessService,
                           final CustomerServiceImpl customerService) {
        this.adminService = adminService;
        this.businessService = businessService;
        this.customerService = customerService;
    }

    private void registerAsAdministrator(final Role savedRole, final Integer roleId) {
        int newRoleId = roleId == null
                ? adminService.list(new QueryWrapper<Admin>().orderByDesc(Table.Admin.ID)).get(0).getId() + 1
                : roleId;
        if (!Arrays.asList(savedRole.getAsAdministrator().split(",")).contains(String.valueOf(newRoleId))) {
            if (adminService.getById(newRoleId) == null) {
                adminService.save(new Admin().setId(newRoleId).setLink(1));
            } else {
                Admin admin = adminService.getById(newRoleId);
                adminService.updateById(admin.setLink(admin.getLink() + 1));
            }
            savedRole.setAsAdministrator(savedRole.getAsAdministrator() + newRoleId + ",");
        }
    }

    private void registerAsBusiness(final Role savedRole, final Integer roleId) {
        int newRoleId = roleId == null
                ? businessService.list(new QueryWrapper<Business>().orderByDesc(Table.Business.ID)).get(0).getId() + 1
                : roleId;
        if (!Arrays.asList(savedRole.getAsBusiness().split(",")).contains(String.valueOf(newRoleId))) {
            if (businessService.getById(newRoleId) == null) {
                businessService.save(new Business().setId(newRoleId).setLink(1));
            } else {
                Business business = businessService.getById(newRoleId);
                businessService.updateById(business.setLink(business.getLink() + 1));
            }
            savedRole.setAsBusiness(savedRole.getAsBusiness() + newRoleId + ",");
        }
    }

    private void registerAsCustomer(final Role savedRole, final Integer roleId) {
        int newRoleId = roleId == null
                ? customerService.list(new QueryWrapper<Customer>().orderByDesc(Table.Customer.ID)).get(0).getId() + 1
                : roleId;
        if (!Arrays.asList(savedRole.getAsCustomer().split(",")).contains(String.valueOf(newRoleId))) {
            if (customerService.getById(newRoleId) == null) {
                customerService.save(new Customer().setId(newRoleId).setLink(1));
            } else {
                Customer customer = customerService.getById(newRoleId);
                customerService.updateById(customer.setLink(customer.getLink() + 1));
            }
            savedRole.setAsCustomer(savedRole.getAsCustomer() + newRoleId + ",");
        }
    }

    @Override
    public boolean register(final int userId, final String role, final Integer roleId) {
        Role savedRole = getById(userId);
        switch (role) {
            case UserRole.ADMIN: {
                registerAsAdministrator(savedRole, roleId);
                break;
            }
            case UserRole.BUSINESS: {
                registerAsBusiness(savedRole, roleId);
                break;
            }
            case UserRole.CUSTOMER: {
                registerAsCustomer(savedRole, roleId);
                break;
            }
            default:
                return false;
        }
        return updateById(savedRole);
    }

    private void removeAsAdministrator(final Role savedRole, final Integer roleId) {
        if (roleId == null) {
            String[] toRemove = savedRole.getAsAdministrator().split(",");
            for (String it : toRemove) {
                if (!it.equals("")) {
                    removeAsAdministrator(savedRole, Integer.parseInt(it));
                }
            }
        } else {
            savedRole.setAsAdministrator(savedRole.getAsAdministrator().replaceAll(roleId + ",", ""));
            Admin admin = adminService.get(roleId, null).get(0);
            adminService.updateById(admin.setLink(admin.getLink() - 1));
        }
    }

    private void removeAsBusiness(final Role savedRole, final Integer roleId) {
        if (roleId == null) {
            String[] toRemove = savedRole.getAsBusiness().split(",");
            for (String it : toRemove) {
                if (!it.equals("")) {
                    removeAsBusiness(savedRole, Integer.parseInt(it));
                }
            }
        } else {
            savedRole.setAsBusiness(savedRole.getAsBusiness().replaceAll(roleId + ",", ""));
            Business business = businessService.get(roleId, null, null, null).get(0);
            businessService.updateById(business.setLink(business.getLink() - 1));
        }
    }

    private void removeAsCustomer(final Role savedRole, final Integer roleId) {
        if (roleId == null) {
            String[] toRemove = savedRole.getAsCustomer().split(",");
            for (String it : toRemove) {
                if (!it.equals("")) {
                    removeAsCustomer(savedRole, Integer.parseInt(it));
                }
            }
        } else {
            savedRole.setAsCustomer(savedRole.getAsCustomer().replaceAll(roleId + ",", ""));
            Customer customer = customerService.get(roleId, null, null).get(0);
            customerService.updateById(customer.setLink(customer.getLink() - 1));
        }
    }

    @Override
    public boolean remove(final int userId, final String role, final Integer roleId) {
        if (role == null) {
            remove(userId, UserRole.ADMIN, null);
            remove(userId, UserRole.BUSINESS, null);
            remove(userId, UserRole.CUSTOMER, null);
            return true;
        } else {
            Role savedRole = getById(userId);
            switch (role) {
                case UserRole.ADMIN: {
                    removeAsAdministrator(savedRole, roleId);
                    break;
                }
                case UserRole.BUSINESS: {
                    removeAsBusiness(savedRole, roleId);
                    break;
                }
                case UserRole.CUSTOMER: {
                    removeAsCustomer(savedRole, roleId);
                    break;
                }
                default:
                    return false;
            }
            return updateById(savedRole);
        }
    }

}
