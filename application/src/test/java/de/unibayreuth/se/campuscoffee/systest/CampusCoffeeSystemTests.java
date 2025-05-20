package de.unibayreuth.se.campuscoffee.systest;

import de.unibayreuth.se.campuscoffee.domain.Pos;
import de.unibayreuth.se.campuscoffee.domain.TestFixtures;
import org.junit.jupiter.api.Test;
import java.util.List;

import static de.unibayreuth.se.campuscoffee.TestUtil.retrievePos;
import static de.unibayreuth.se.campuscoffee.TestUtil.retrievePosById;
import static org.assertj.core.api.Assertions.assertThat;

public class CampusCoffeeSystemTests extends AbstractSysTest {

    @Test
    void getAllCreatedPos() {
        List<Pos> createdPosList = TestFixtures.createPos(posService);

        List<Pos> retrievedPos = retrievePos()
                .stream()
                .map(posDtoMapper::toDomain)
                .toList();

        assertThat(retrievedPos)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("createdAt", "updatedAt") // prevent issues due to differing timestamps after conversions
                .containsExactlyInAnyOrderElementsOf(createdPosList);
    }

    @Test
    void getPosById() {
        List<Pos> createdPosList = TestFixtures.createPos(posService);
        Pos createdPos = createdPosList.getFirst();

        Pos retrievedPos = posDtoMapper.toDomain(
                retrievePosById(createdPos.getId())
        );

        assertThat(retrievedPos)
                .usingRecursiveComparison()
                .ignoringFields("createdAt", "updatedAt") // prevent issues due to differing timestamps after conversions
                .isEqualTo(createdPos);
    }

}