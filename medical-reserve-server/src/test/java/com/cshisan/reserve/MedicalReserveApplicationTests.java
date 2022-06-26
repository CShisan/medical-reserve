package com.cshisan.reserve;

import com.cshisan.reserve.common.utils.WxUtil;
import com.cshisan.reserve.dto.DoctorDTO;
import com.cshisan.reserve.entity.User;
import com.cshisan.reserve.service.*;
import com.cshisan.reserve.vo.DepartmentVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MedicalReserveApplicationTests {
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final SystemService systemService;
    private final WxUtil wxUtil;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;

    @Autowired
    MedicalReserveApplicationTests(ScheduleService scheduleService, UserService userService, SystemService systemService, WxUtil wxUtil, DepartmentService departmentService, DoctorService doctorService) {
        this.scheduleService = scheduleService;
        this.userService = userService;
        this.systemService = systemService;
        this.wxUtil = wxUtil;
        this.departmentService = departmentService;
        this.doctorService = doctorService;
    }

    @Test
    void contextLoads() {
        generatorDoctor();
    }


    /**
     * 批量创建患者(微信小程序用户)
     */
    @Deprecated
    public void generatorPatient(){
        for (int i = 0; i < 200; i++) {
            User user = new User();
            user.setRealName(GeneratorUtil.name());
            user.setPhone(GeneratorUtil.phone());
            user.setIdCard(GeneratorUtil.idCard());
            user.setEditor(9527L);
            systemService.wxRegister(user);
        }
    }

    /**
     * 按照每个科室及每个职称生成医生
     */
    @Deprecated
    public void generatorDoctor() {
        List<DepartmentVO> departments = departmentService.listAll();
        departments.forEach(department -> {
            for (int i = 1; i <= 4; i++) {
                DoctorDTO doctor = new DoctorDTO();
                doctor.setRealName(GeneratorUtil.name());
                doctor.setPassword("123");
                doctor.setDeptId(department.getDeptId());
                doctor.setJobTitleId((long) i);
                doctor.setPhone(GeneratorUtil.phone());
                doctor.setIdCard(GeneratorUtil.idCard());
                doctorService.save(doctor);
            }
        });
    }
}
