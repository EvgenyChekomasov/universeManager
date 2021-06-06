package com.example.universeManager;

import com.example.universeManager.controller.MasterController;
import com.example.universeManager.controller.RockController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ControllerTest {

    @Autowired
    private MasterController masterController;
    @Autowired
    private RockController rockController;

    @Test
    public void contextLoad() throws Exception {
        assertThat(masterController).isNotNull();
        assertThat(rockController).isNotNull();
    }
}
