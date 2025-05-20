package de.unibayreuth.se.campuscoffee.systest;

import de.unibayreuth.se.campuscoffee.api.mapper.PosDtoMapper;
import de.unibayreuth.se.campuscoffee.domain.ports.PosService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static de.unibayreuth.se.campuscoffee.TestUtil.configurePostgresContainers;
import static de.unibayreuth.se.campuscoffee.TestUtil.getPostgresContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractSysTest {
    static final PostgreSQLContainer<?> postgresContainer = getPostgresContainer();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        configurePostgresContainers(registry, postgresContainer);
    }

    @Autowired
    protected PosService posService;

    @Autowired
    protected PosDtoMapper posDtoMapper;

    @LocalServerPort
    private Integer port;

    @BeforeAll
    public static void beforeAll() {
        postgresContainer.start();
    }

    @AfterAll
    public static void afterAll() {
        postgresContainer.stop();
    }

    @BeforeEach
    public void beforeEach() {
        posService.clear();
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @AfterEach
    public void afterEach() {
        posService.clear();
    }
}