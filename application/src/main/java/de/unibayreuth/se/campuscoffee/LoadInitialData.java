package de.unibayreuth.se.campuscoffee;

import de.unibayreuth.se.campuscoffee.domain.Pos;
import de.unibayreuth.se.campuscoffee.domain.TestFixtures;
import de.unibayreuth.se.campuscoffee.domain.ports.PosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Load initial data into the list via the list service from the business layer.
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
class LoadInitialData implements InitializingBean {
    private final PosService posService;

    @Override
    public void afterPropertiesSet() {
        log.info("Deleting existing data...");
        posService.clear();
        log.info("Loading initial data...");
        List<Pos> posList = TestFixtures.createPos(posService);
        log.info("Loaded {} POS.", posList.size());
        log.info("Initial data loaded successfully.");
    }
}