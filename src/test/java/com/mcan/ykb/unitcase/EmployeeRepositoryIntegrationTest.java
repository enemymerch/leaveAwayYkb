package com.mcan.ykb.unitcase;

import com.mcan.ykb.unitcase.controller.EmployeeController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeRepositoryIntegrationTest {

}
