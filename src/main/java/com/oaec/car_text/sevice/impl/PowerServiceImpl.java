package com.oaec.car_text.sevice.impl;

import com.oaec.car_text.dao.PowerMapper;
import com.oaec.car_text.entity.Power;
import com.oaec.car_text.entity.Role;
import com.oaec.car_text.entity.powerrole;
import com.oaec.car_text.sevice.PowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("/powerService")
public class PowerServiceImpl implements PowerService {
    @Resource
    private PowerMapper powerDao;
    @Override
    public List<Role> queryRole() {
        return powerDao.queryRole();
    }

    @Override
    public int deleteRole(String roleid) {
        return powerDao.deleteRole(roleid);
    }

    @Override
    public List<Role> roleByName(String rolename) {
        return powerDao.roleByName(rolename);
    }

    @Override
    public int addRole(String rolename, int rolecode) {
        UUID uuid=UUID.randomUUID();
        String roleid=uuid.toString();
        return powerDao.addRole(roleid, rolename, rolecode);
    }

    @Override
    public List<Power> queryPower(String roleid) {
        return powerDao.queryPower(roleid);
    }

    @Override
    public List<Power> queryAllPower() {
        return powerDao.queryAllPower();
    }

    @Override
    public int addpower(powerrole powerrole) {
        UUID uuid=UUID.randomUUID();
        String prid=uuid.toString();
        powerrole.setPrid(prid);
        return powerDao.addpower(powerrole);
    }

    @Override
    public int deletepower(String powerid, String roleid) {
        return powerDao.deletepower(powerid, roleid);
    }
}
